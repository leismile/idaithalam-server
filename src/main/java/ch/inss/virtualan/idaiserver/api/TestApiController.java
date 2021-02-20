package ch.inss.virtualan.idaiserver.api;

import ch.inss.virtualan.idaiserver.model.Conf;
import ch.inss.virtualan.idaiserver.model.Report;
import ch.inss.virtualan.idaiserver.model.Testidlist;
import ch.inss.virtualan.idaiserver.utils.FileManagement;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import ch.inss.virtualan.idaiserver.service.Cucumblan;
import ch.inss.virtualan.idaiserver.service.TestService;
import ch.inss.virtualan.idaiserver.service.UtilService;
import io.swagger.annotations.ApiParam;


@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-21T08:48:24.320856+01:00[Europe/Zurich]")
@Controller
@RequestMapping("${openapi.idaiserver.base-path:}")
public class TestApiController implements TestApi {

	private static final String FEATURE = "feature/virtualan-contract.0.feature";
	//final String FEATURE = "feature"+FileManagement.fs+"virtualan-contract.0.feature";
    private static final String CUCUMBLAN = "cucumblan.properties";

    private static final Logger logger = LoggerFactory.getLogger(TestApiController.class);
    private final NativeWebRequest request;
    
    @Autowired private TestService testServices;

    @Autowired private UtilService utilService;

    @Autowired
    public TestApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<String> getgherkin(String testId) {
        return utilService.getSessionContent(testId, FEATURE );
    }

    @Override
    public ResponseEntity<String> getConfProperty( String testId) {
        return utilService.getContent(testId, CUCUMBLAN);
    }


    @Override
    public ResponseEntity<String> removeConf(String configkey, String testId) {
        return utilService.deleteCucumblanPropKey(testId, configkey);
    }

    @Override
    public ResponseEntity<String> updateConf(String testId, Conf conf) {
        return utilService.updateCucumblan(testId, conf);
    }

    @Override
    public ResponseEntity<Report> getReport(String testId) {
        return utilService.readLatestTestResult(testId);
    }

    @Override
    public ResponseEntity<List<Report>> report(String testId) {
        return utilService.readAllReport(testId);
    }
    
    public ResponseEntity<String> removetest(@ApiParam(value = "",required=true) @PathVariable("testId") String testId) {
    	
		testServices.remove(testId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    /** POST for the main initial test with execution and creation of the uuid. */
    @Override
    public ResponseEntity<Report> testRun(@ApiParam(value = "") @Valid @RequestPart(value = "filestream", required = true) MultipartFile filestream,@ApiParam(value = "The server url to be tested.", required=true) @Valid @RequestPart(value = "serverurl", required = true)  String serverurl,@ApiParam(value = "", allowableValues="POSTMAN, OPENAPI, EXCEL, VIRTUALAN") @Valid @RequestPart(value = "dataType", required = false)  String dataType,@ApiParam(value = "Execute test immediately. If false, only the property file will be updated (append).", defaultValue="true") @Valid @RequestPart(value = "execute", required = false)  String execute,@ApiParam(value = "Skip the respone validation in tests.", defaultValue="false") @Valid @RequestPart(value = "skipResponseValidation", required = false)  String skipResponseValidation) {
        logger.debug("Start POST /test");
        if (getRequest().isPresent() == false || filestream == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ;
        
        Report links = null;
        
        for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/octet-stream"))) {
                logger.debug("Start application/octet-stream.");
                try {
                                        
                    String dataload = filestream.getOriginalFilename();
                    
                    Boolean e = new Boolean(true);
                    if ( execute != null) {
                        e = new Boolean(execute);
                    }
                    Boolean skip = new Boolean(false);
                    if ( skipResponseValidation != null) {
                    	skip = new Boolean(skipResponseValidation);
                    }

                    Cucumblan cucumblan = new Cucumblan();
                    cucumblan.init();
                    cucumblan.addFILE(dataload);
                    cucumblan.setUploadFilename(dataload);
                    cucumblan.setSkipResponseValidation(skip);
                    cucumblan.setTYPE(dataType);
                    
                    try {
                        cucumblan.setInputStream(filestream.getInputStream());
                    } catch (IOException e1) {
                        logger.error("The uploaded file is not readable.");
                    }
                    cucumblan.addURL(null,serverurl);
                    cucumblan.setExecute(e);
                    links = testServices.doInitialTest(cucumblan);
                    
                    if (links.getError() != null && links.getError().equals(FileManagement.NOERROR) == false) {
                        return new ResponseEntity<Report>(links,HttpStatus.INTERNAL_SERVER_ERROR);    
                    }
                }catch(Exception e) {
                    Report error = new Report();
                    error.setError(e.getLocalizedMessage());
                    return new ResponseEntity<Report>(error,HttpStatus.INTERNAL_SERVER_ERROR);
                }
                
                break;
            }
        }
        return new ResponseEntity<Report>(links,HttpStatus.CREATED);
    }
    
    @Override
    public ResponseEntity<Report> runTest(UUID testid) {
    	logger.debug("Start PUT /test");
        if (getRequest().isPresent() == false || testid == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    	Report reportLinks = null;
        for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {

            	Cucumblan cucumblan = new Cucumblan();
        	  	cucumblan.init(testid);
        	  	reportLinks = testServices.runTest(cucumblan, testid);
            	break;
            }
        }
        return new ResponseEntity<Report>(reportLinks, HttpStatus.CREATED);

    }
    
    @Override
    public ResponseEntity<Testidlist> listTest() {
    	Testidlist idlist = null;
        for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                idlist = testServices.listAllIDs();
            	
            	break;
            }
        }
        return new ResponseEntity<Testidlist>(idlist,HttpStatus.NOT_IMPLEMENTED);

    }
    


}
