package ch.inss.virtualan.idaiserver.api;

import ch.inss.virtualan.idaiserver.model.Conf;
import ch.inss.virtualan.idaiserver.model.Report;
import ch.inss.virtualan.idaiserver.model.Testidlist;
import ch.inss.virtualan.idaiserver.utils.FileManagement;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import ch.inss.virtualan.idaiserver.service.Cucumblan;
import ch.inss.virtualan.idaiserver.service.TestService;
import ch.inss.virtualan.idaiserver.service.UtilService;


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
    public ResponseEntity<Report> testCreateRun(String userId, MultipartFile filestream, String serverurl, String workspace, String testrun, String staging, String version, String dataType, String execute, String skipResponseValidation) {
        logger.debug("Start POST /testCreateRun");

        if (getRequest().isPresent() == false || filestream == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Report links = null;

        for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/octet-stream"))) {
                logger.debug("Start application/octet-stream.");
                    /* Create and run the actual test. */
                    links = testServices.doInitialTest(userId,  filestream,  serverurl,  workspace,  testrun,  staging,  version,  dataType,  execute,  skipResponseValidation);
                    
                    if (links.getError() != null ) {
                        return new ResponseEntity<Report>(links,HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                break;
            }
        }
        return new ResponseEntity<Report>(links,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> removeTest(String userId, String testId) {
//        return TestApi.super.removetest(userId, testId);
        testServices.remove(userId, testId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<String> removeConf(String userId, String configKey, String testId) {
//        return TestApi.super.removeConf(userId, configKey, testId);
        //TODO check userId
        return utilService.deleteCucumblanPropKey(testId, configKey);
    }

    @Override
    public ResponseEntity<String> updateConf(String userId, String testId, Conf conf) {
//        return TestApi.super.updateConf(userId, testId, conf);
        //TODO check userId
        return utilService.updateCucumblan( testId, conf);
    }


    
    @Override
    public ResponseEntity<Report> getReport(String userId, String testId) {
//        return TestApi.super.getReport(userId, testId);
        //TODO check userId
        return utilService.readLatestTestResult(  testId);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<String> getConfProperty(String userId, String testId) {
//        return TestApi.super.getConfProperty(userId, testId);
        //TODO check userId
        return utilService.getContent( testId, CUCUMBLAN);
    }


    @Override
    public ResponseEntity<String> getgherkin(String userId, String testId) {
        //TODO check userId
        return utilService.getSessionContent( testId, FEATURE );
    }

    @Override
    public ResponseEntity<Testidlist> listTest(String userId, String workspace, String testrun, String staging, String version) {
//        return TestApi.super.listTest(userId, workspace, testrun, staging, version);
        //        return DemotestApi.super.demoListTest();
        Testidlist idlist = null;
        for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                idlist = testServices.listAllIDs();

                break;
            }
        }
        return new ResponseEntity<Testidlist>(idlist,HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<List<Report>> report(String userId, String testId) {
//        return TestApi.super.report(userId, testId);
        //TODO check userId
        return utilService.readAllReport(testId);
    }

    @Override
    public ResponseEntity<Report> runTest(String userId, UUID testId) {
//        return TestApi.super.runTest(userId, testId);
        logger.debug("Start PUT /test");
        if (getRequest().isPresent() == false || testId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Report reportLinks = null;
        for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                Cucumblan cucumblan = new Cucumblan();
                cucumblan.init(testId);
                reportLinks = testServices.runTest(userId, cucumblan, testId);
                break;
            }
        }
        return new ResponseEntity<Report>(reportLinks, HttpStatus.CREATED);

    }

   
}
