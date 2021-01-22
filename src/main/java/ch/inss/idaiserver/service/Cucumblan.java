package ch.inss.idaiserver.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.inss.idaiserver.model.Report;
import ch.inss.idaiserver.model.TestDao;
import ch.inss.idaiserver.utils.FileManagement;

@Component
public class Cucumblan {
    
    private String TYPE;
    private InputStream inputStream;
    private String folder;
    private Boolean execute;
    private Boolean overwrite;
    private UUID uuid;
    
    /** Store keys as key=service.api. 
     * Add resourcees with key=service.api.nextresource. */
    private HashMap<String, String> URL;
    private List<String> FILE;

    public Cucumblan() {

        
    }
    
    
    private static final Logger logger = LoggerFactory.getLogger(Cucumblan.class);
    
    public void init() {
        this.uuid = UUID.randomUUID();
        this.execute = new Boolean(true);
        this.overwrite = new Boolean(true);
        this.folder = "results_" + this.uuid;
        this.FILE = new ArrayList<String>();
        this.URL = new HashMap<String, String>();
    }
    
    
    
    
    public String getFolder() {
        return folder;
    }

    public UUID getUuid() {
        return uuid;
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    public String getTYPE() {
        return TYPE;
    }
    public void setTYPE(String tYPE) {
        TYPE = tYPE;
    }

    public Boolean getExecute() {
        return execute;
    }

    public void setExecute(Boolean execute) {
        this.execute = execute;
    }

    public Boolean getOverwrite() {
        return overwrite;
    }

    public void setOverwrite(Boolean overwrite) {
        this.overwrite = overwrite;
    }
    
    /** Input is only the resource. service.api is added. */
    public void addURL(String resource, String value) {
        if ( resource == null || "".equals(resource)) {
            resource = "service.api"; 
        }else {
            resource = "service.api." + resource ;
        }
        this.URL.put(resource, value);
                
    }
    
    /** Add a postman collection file name for the property virtualan.data.load. */
    public void addFILE(String dataload) {
        if (dataload == null) return;
        this.FILE.add(dataload);
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("#Generated " + FileManagement.whatTime());
        final String dataload = "virtualan.data.load";
        final String datatype = "virtualan.data.type";
        sb.append(FileManagement.lf);
        for ( String key : this.URL.keySet()) {
            sb.append(key).append(" = ").append(this.URL.get(key)).append(FileManagement.lf);    
        }
        sb.append(dataload).append(" = ");
        for ( String filename : this.FILE) {
            sb.append(filename).append(";");
        }
        sb.append(FileManagement.lf);
        sb.append(datatype).append(" = ").append(this.TYPE).append(FileManagement.lf);
        
        return sb.toString();
    }
    
    /** Get a single file. */
    public String getOneFILE() {
        if ( this.FILE.size() > 1 ) {
            logger.warn("There are more files in this configuration. This method should be only called for a single file configuration like instan execution for a test. ");
        }
        if ( this.FILE == null || this.FILE.isEmpty()) {
            logger.error("There was no postman collection file added.");
            return null;
        }
        return this.FILE.get(0);
    }

    public Report reportFactory() {
    	Report reportLinks = new Report();
	   reportLinks.setError(FileManagement.NOERROR);
       reportLinks.setCreationtime(FileManagement.whatTime());
       reportLinks.setTestid( this.uuid );
       return reportLinks;
    }


    public HashMap<String, String> getURL() {
        return URL;
    }

    public List<String> getFILE() {
        return FILE;
    }




	public TestDao testFactory() {
		TestDao dao = new TestDao();
		dao.setUuid(this.uuid);
		dao.setCreationtime(FileManagement.whatTime());
		dao.setError(FileManagement.NOERROR);
		dao.setLoad(this.getFILE());
		dao.setServiceapi(this.getURL());
		
		return dao;
	}


    
}
