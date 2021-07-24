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
    public ResponseEntity<String> removetest(String userId, String testId) {
//        return TestApi.super.removetest(userId, testId);
        testServices.remove(testId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<String> removeConf(String userId, String configKey, String testId) {
//        return TestApi.super.removeConf(userId, configKey, testId);
        return utilService.deleteCucumblanPropKey(testId, configKey, userId);
    }

    @Override
    public ResponseEntity<String> updateConf(String userId, String testId, Conf conf) {
//        return TestApi.super.updateConf(userId, testId, conf);
        return utilService.updateCucumblan(testId, conf, userId);
    }


    
    @Override
    public ResponseEntity<Report> getReport(String userId, String testId) {
//        return TestApi.super.getReport(userId, testId);
        return utilService.readLatestTestResult( userId, testId);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<String> getConfProperty(String userId, String testId) {
//        return TestApi.super.getConfProperty(userId, testId);
        return utilService.getContent(testId, CUCUMBLAN);
    }




}
