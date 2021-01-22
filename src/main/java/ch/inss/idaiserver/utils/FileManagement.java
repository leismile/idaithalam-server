package ch.inss.idaiserver.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import ch.inss.idaiserver.model.Cucumblan;

public class FileManagement {
    
    private static final Logger logger = LoggerFactory.getLogger(FileManagement.class);
    private static final String lf = System.getProperty("line.separator");
//    private static final String fs = FileSystems.getDefault().getSeparator();
    
    private static final String PROPERTIES="src/test/resources/cucumblan.properties";
    private static final String FEATURE="conf/virtualan-contract.feature";
    
    public final static String NOERROR = "No error";

    public static boolean save( String path, Cucumblan cuc) {
          boolean ok = false;
          try {
          /* Create cucumblan.properties from mustache template. */  
          MustacheFactory mf = new DefaultMustacheFactory();
          Mustache m = mf.compile("cucumblan.mustache");
          StringWriter writer = new StringWriter();
          m.execute(writer, cuc).flush();
          String props = writer.toString();
          logger.info("props: " + props);
          
          /* Read existing file if no overwrite is set. */
          if ( cuc.getOverwrite() == false ) {
              BufferedReader in = new BufferedReader(new FileReader(path));
              StringBuilder sb = new StringBuilder();
              while(in.readLine() != null) {
                  sb.append(in.readLine()).append(lf);
              }

              String content = sb.toString();
              in.close();
              props = content + lf + props;
          }
          
          /* Write new properties file. */
          writeString(props, path+"/cucumblan.properties");
          
          /* Write postman collection. */
          writeFilestream(path +"/"+cuc.getFILE(), cuc.getInputStream());
          ok = true; 
          }catch(IOException ioe) {
              logger.error("Could not save file: " + cuc.getFILE(),ioe);
              
          }finally {
              try{cuc.getInputStream().close();}catch(Exception e) {};
          }
          return ok;
          //IOUtils.closeQuietly(initialStream);
        
    }

    /* Write files like postman collection. */
    private static void writeFilestream(String filename, InputStream in) throws IOException {
          File targetFile = new File( filename);
          java.nio.file.Files.copy(
            in,
            targetFile.toPath(), 
            StandardCopyOption.REPLACE_EXISTING);
          in.close();
    }
    
    /** Add a line to the cucumblan.properties file. */
    public static String addLine(String value, String resource) {
        String content;
        String key = "service.api" + "." + resource;
        Properties prop;
        try {
            prop = readPropertiesFile(PROPERTIES);
            prop.put(key, value);
            writeProperty(prop, "adding line for " + resource);
//            content = line + lf + readToString(PROPERTIES);
//            writeString(content, PROPERTIES);
            content = prop.toString();
        } catch (IOException e) {
             logger.error("Could not add line: " + key + "=" + value,e);
             content = "Could not add line: " + key + "=" + value;
        }
        return content;
    }
    
    private static void writeProperty(Properties prop, String comment)throws IOException {
        FileOutputStream outputStrem = new FileOutputStream(PROPERTIES);
        //Storing the properties file
        prop.store(outputStrem, "Property file updated: " + comment); // at " + new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z").format(new Date(System.currentTimeMillis())),"UTF-8");
        IOUtils.closeQuietly(outputStrem);
    }

    /* Read the feature file. */
    public static String getFeaturefile() {
        try {
            return readToString(FEATURE);
        }catch(FileNotFoundException ffe) {
            logger.error("Could not find file: " + FEATURE,ffe);
            return "Error finding " + FEATURE;
        }catch(IOException ioe) {
            logger.error("Could not read file: " + FEATURE,ioe);
            return "Error reading " + FEATURE;
        }
        
    }
    
    /** Read the Property file. */
    public static String getPropertyfile() {
        try {
            return readToString(PROPERTIES);
        }catch(FileNotFoundException ffe) {
            logger.error("Could not find file: " + PROPERTIES,ffe);
            return "Error finding " + PROPERTIES;
        }catch(IOException ioe) {
            logger.error("Could not read file: " + PROPERTIES,ioe);
            return "Error reading " + PROPERTIES;
        }
        
    }
    
    private static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
           fis = new FileInputStream(fileName);
           prop = new Properties();
           prop.load(fis);
        } catch(FileNotFoundException fnfe) {
           fnfe.printStackTrace();
        } catch(IOException ioe) {
           ioe.printStackTrace();
        } finally {
           fis.close();
        }
        return prop;
     }

    private static void writeString(String content, String filename) throws IOException {
        /* Put String into writer. */
          BufferedWriter bwriter = new BufferedWriter(new FileWriter(filename));
          bwriter.write(content);
          bwriter.flush();
          bwriter.close();
    }
    

    /** Read any file and give back the String content. */
    private static String readToString(String file) throws FileNotFoundException, IOException {
        logger.debug("File path: " + file);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line = br.readLine()) != null) {
            logger.info("LINE: " + line);
            sb.append(line).append(lf);
        }
        br.close();
        return sb.toString();
    }
    
    public static String copyFiles(String source, String targetFolder) {
        Path sourcePath = Paths.get(source);
        String filename = sourcePath.getFileName().toString();
        Path newPath = Paths.get(targetFolder ).resolve(filename);
        
        try
        {
            File file = new File(targetFolder);
            if ( file.exists() == false ) {
                file.mkdir();
            }else {
                logger.error("Folder cannot be created because it exists: " + targetFolder);
                return "Error";
            }
           Files.copy(sourcePath, newPath, StandardCopyOption.REPLACE_EXISTING);
           logger.info("Copied " + source + " to " + newPath.toAbsolutePath());
        }
        catch(IOException e)
        {
            logger.error("Cannot fopy feature file.",e);
        }

        return "ok";
    }
    
    public static void copyFolder(String sourceFolder, String targetFolder) {
//        String source = "C:/your/source";
        File srcDir = new File(sourceFolder);

//        String destination = "C:/your/destination";
        File destDir = new File(targetFolder);

        try {
            FileUtils.copyDirectory(srcDir, destDir);
        } catch (IOException e) {
            logger.error("Cannot copy report folder",e);
        }
    }

    public static String getReportfile(String target) {
        Path testPath = Paths.get(target);
        String result = "notfound";
        final String report = "report-feature_";
        Stream<Path> stream = null;
        try {
        //finding files containing 'items' in name
        stream =
                Files.find(testPath, 1,
                        (path, basicFileAttributes) -> {
                            File file = path.toFile();
                            return !file.isDirectory() &&
                                    file.getName().startsWith(report);
                        });
        Object[] files = stream.toArray();
        if ( files == null || files.length == 0) {
            logger.error("Did not find a file starting with " + report);
            
        }else {
            Path p = (Path) files[0];
            result = p.getFileName().toString();
        }
        }catch(IOException ioe) {
            logger.error("Could not find report html file.",ioe);
        }finally {
            stream.close();
        }
        return result;
    }

    /** Write down the Postmancollection to the filesystem and add it to the list in cucumblan.properties. */
    public static boolean addCollection(@Valid MultipartFile fileStream) {
        try {
            writeFilestream(fileStream.getName(), fileStream.getInputStream());
            Properties prop = readPropertiesFile(PROPERTIES);
            String value = prop.getProperty("virtualan.data.load");
            String file = fileStream.getName();
            if ( file == null || file.equals("")) {
                return false;
            }
            value += ";" + file;
            prop.put("virtualan.data.load", value);
            writeProperty(prop, "adding collection: " + file);
            
        } catch (IOException e) {
            logger.error("Could not write file " + fileStream.getName());
            return false;
        }
        return true;
    }
    
    public static boolean removeServer(String resource) {
        String key = null;
        
        try {
//            String content;
            key = "service.api" + "." + resource;
            logger.debug("Removing " + key);
            Properties prop;
            prop = readPropertiesFile(PROPERTIES);
            prop.remove(key);
            writeProperty(prop, "removing " + key);
        }catch(IOException ioe) {
            logger.error("Could not remove: " + key,ioe);
            return false;
        }
        return true;
    }
    
    public static String whatTime() {
      return  new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z").format(new Date(System.currentTimeMillis()));
    }
    
//  /** Generate UUID for the testid. */  
//  public static String getUUID () {
//      byte[] result = null;
//      try {
//        //Initialize SecureRandom
//        //This is a lengthy operation, to be done only upon
//        //initialization of the application
//        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
//
//        //generate a random number
//        String randomNum = Integer.valueOf(prng.nextInt()).toString();
//
//        //get its digest
//        MessageDigest sha = MessageDigest.getInstance("SHA-1");
//        result =  sha.digest(randomNum.getBytes());
//
//        System.out.println("Random number: " + randomNum);
//        System.out.println("Message digest: " + hexEncode(result));
//      }
//      catch (NoSuchAlgorithmException ex) {
//        System.err.println(ex);
//      }
//      return hexEncode(result);
//   }
//  
//  /**
//   * The byte[] returned by MessageDigest does not have a nice
//   * textual representation, so some form of encoding is usually performed.
//   *
//   * This implementation follows the example of David Flanagan's book
//   * "Java In A Nutshell", and converts a byte array into a String
//   * of hex characters.
//   *
//   * Another popular alternative is to use a "Base64" encoding.
//   */
//  private static  String hexEncode(byte[] input){
//     StringBuilder result = new StringBuilder();
//     char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
//     for (int idx = 0; idx < input.length; ++idx) {
//       byte b = input[idx];
//       result.append(digits[ (b&0xf0) >> 4 ]);
//       result.append(digits[ b&0x0f]);
//     }
//     return result.toString();
//   }

   
    
    
}
