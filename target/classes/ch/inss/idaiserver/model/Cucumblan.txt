package ch.inss.idaiserver.model;

import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Cucumblan {
    private String URL;
    private String FILE;
    private String TYPE;
    private InputStream inputStream;
    private String folder;
    private String SUBDOMAIN;
    private Boolean execute;
    private Boolean overwrite;
    private Test test;
    private UUID uuid;

    public Cucumblan() {
        //this.folder = "results_" + Long.valueOf(Calendar.getInstance().getTimeInMillis()).toString();
        this.uuid = UUID.randomUUID();
        this.execute = new Boolean(true);
        this.overwrite = new Boolean(true);
        this.folder = "results_" + this.uuid;
    }
    
//    public Cucumblan(String url, String type, String file, InputStream inputStream) {
//        this.URL = url;
//        this.TYPE = type;
//        this.FILE = file;
//        this.inputStream = inputStream;
//        this.folder = "results_" + Long.valueOf(Calendar.getInstance().getTimeInMillis()).toString();
//    }
    
    
    
    public String getFolder() {
        return folder;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getURL() {
        return URL;
    }
    public void setURL(String uRL) {
        URL = uRL;
    }
    public String getFILE() {
        return FILE;
    }
    public void setFILE(String fILE) {
        FILE = fILE;
    }
    public String getTYPE() {
        return TYPE;
    }
    public void setTYPE(String tYPE) {
        TYPE = tYPE;
    }

    public String getSUBDOMAIN() {
        return SUBDOMAIN;
    }

    public void setSUBDOMAIN(String sUBDOMAIN) {
        SUBDOMAIN = "." + sUBDOMAIN;
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
    


    
}
