package ch.inss.virtualan.idaiserver.api;

import ch.inss.virtualan.idaiserver.model.Report;
import ch.inss.virtualan.idaiserver.model.Testidlist;
import ch.inss.virtualan.idaiserver.service.Cucumblan;
import ch.inss.virtualan.idaiserver.service.TestService;
import ch.inss.virtualan.idaiserver.service.UtilService;
import ch.inss.virtualan.idaiserver.utils.FileManagement;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("${openapi.idaiserver.base-path:}")
public class DemotestApiController implements DemotestApi {


    private static final String FEATURE = "feature/virtualan-contract.0.feature";
    //final String FEATURE = "feature"+FileManagement.fs+"virtualan-contract.0.feature";
//    private static final String CUCUMBLAN = "cucumblan.properties";

    private static final Logger logger = LoggerFactory.getLogger(TestApiController.class);
    private final NativeWebRequest request;
    
    private final String DEMOUSER = "demouser";
    private final String DEMOWORKSPACE = "demoworkspace";
    private final String DEMOTESTRUN = "demotestrun";
    private final String DEMOVERSION = "demoversion";
    private final String DEMOSTAGING = "demo";

    @Autowired
    private TestService testService;

    @Autowired private UtilService utilService;

    @org.springframework.beans.factory.annotation.Autowired
    public DemotestApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Report> demoGetReport(String testId) {
//        return DemotestApi.super.demoGetReport(testId);
        return utilService.readLatestTestResult( testId );
    }

    @Override
    public ResponseEntity<String> demoRemovetest(String testId) {
//        return DemotestApi.super.demoRemovetest(testId);
        testService.remove(DEMOUSER, testId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<String> demoGetgherkin(String testId) {
        return utilService.getSessionContent(testId, FEATURE );
    }


    @Override
    public ResponseEntity<List<Report>> demoReport(String testId) {
//        return DemotestApi.super.demoReport(testId);
        return utilService.readAllReport(testId);
    }

      /** POST for the main initial test with execution and creation of the uuid. */
    @Override
    public ResponseEntity<Report> demoTestCreateRun(MultipartFile filestream, String serverurl, String dataType, String execute, String skipResponseValidation) {
        logger.debug("Start POST /testCreateRun");

        if (getRequest().isPresent() == false || filestream == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Report links = null;

        for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/octet-stream"))) {
                logger.debug("Start application/octet-stream.");
                /* Create and run the actual test. */
                links = testService.doInitialTest(DEMOUSER,  filestream,  serverurl,  DEMOWORKSPACE,  DEMOTESTRUN,  DEMOSTAGING, DEMOVERSION,  dataType,  execute,  skipResponseValidation);

                if (links.getError() != null ) {
                    return new ResponseEntity<Report>(links,HttpStatus.INTERNAL_SERVER_ERROR);
                }
                break;
            }
        }
        return new ResponseEntity<Report>(links,HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Report> demoRunTest(UUID testId) {
//        return DemotestApi.super.demoRunTest(testId);
        logger.debug("Start PUT /test");
        if (getRequest().isPresent() == false || testId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Report reportLinks = null;
        for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {

                Cucumblan cucumblan = new Cucumblan();
                cucumblan.init(testId);
                reportLinks = testService.runTest(DEMOUSER, cucumblan, testId);
                break;
            }
        }
        return new ResponseEntity<Report>(reportLinks, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Testidlist> demoListTest() {
//        return DemotestApi.super.demoListTest();
        Testidlist idlist = null;
        for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                idlist = testService.listAllIDs();

                break;
            }
        }
        return new ResponseEntity<Testidlist>(idlist,HttpStatus.NOT_IMPLEMENTED);

    }
    
}
