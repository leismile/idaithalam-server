package ch.inss.idaiserver.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import ch.inss.idaiserver.model.*;
import ch.inss.idaiserver.service.TestService;
import ch.inss.idaiserver.utils.FileManagement;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-21T08:48:24.320856+01:00[Europe/Zurich]")
@Controller
@RequestMapping("${openapi.idaiserver.base-path:}")
public class TestApiController implements TestApi {

    private static final Logger logger = LoggerFactory.getLogger(TestApiController.class);
    private final NativeWebRequest request;
    
    
    @Autowired private TestService testServices;
    
    

    @Autowired
    public TestApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
    
    @Override
    public ResponseEntity<Report> testrun(@ApiParam(value = "") @Valid @RequestPart(value = "filestream", required = true) MultipartFile filestream,@ApiParam(value = "The server url to be tested.", required=true, defaultValue="http://localhost:8080") @Valid @RequestPart(value = "serverurl", required = true)  String serverurl,@ApiParam(value = "filename", defaultValue="idaithalan.postman_collection.json") @Valid @RequestPart(value = "dataload", required = false)  String dataload,@ApiParam(value = "Execute test immediately. If false, only the property file will be updated (append).", defaultValue="true") @Valid @RequestPart(value = "execute", required = false)  String execute,@ApiParam(value = "Type of data is POSTMAN, VIRTUALAN OR EXCEL.", allowableValues="POSTMAN, VIRTUALAN, EXCEL", defaultValue="POSTMAN") @Valid @RequestPart(value = "datatype", required = false)  String datatype) {
        logger.debug("STart POST /test");
        if (getRequest().isPresent() == false || filestream == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if ( "teapot".equalsIgnoreCase(dataload) || "teapot".equalsIgnoreCase(serverurl)) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
        
        Report links = null;
        
        for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/octet-stream"))) {
                logger.debug("STart application/octet-stream.");
                try {
                    /* Define default values. */
                    if ( datatype == null || "".equals(datatype)) {
                        datatype= "POSTMAN";
                    }
                    if ( dataload == null || "".equals(dataload)) {
                        dataload = filestream.getOriginalFilename();
                    }
                    
                    Boolean e = new Boolean(true);
                    if ( execute != null) {
                        e = new Boolean(execute);
                    }

                    Cucumblan cucumblan = new Cucumblan();
                    
                    cucumblan.setFILE(dataload);
                    
                    try {
                        cucumblan.setInputStream(filestream.getInputStream());
                    } catch (IOException e1) {
                        logger.error("The uploaded file is not readable.");
                    }
                    cucumblan.setTYPE(datatype);
                    cucumblan.setURL(serverurl);
                    cucumblan.setExecute(e);
                    links = testServices.doTests(cucumblan);
                    
                    if (links.getError() != null && links.getError().equals(FileManagement.NOERROR) == false) {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);    
                    }
                }catch(Exception e) {
                    Report error = new Report();
                    error.setError(e.getLocalizedMessage());
                }
                
                break;
            }
        }
        return new ResponseEntity<Report>(links,HttpStatus.CREATED);

    }

}
