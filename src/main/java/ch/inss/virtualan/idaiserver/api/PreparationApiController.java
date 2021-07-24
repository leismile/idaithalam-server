package ch.inss.virtualan.idaiserver.api;

import ch.inss.virtualan.idaiserver.model.Report;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-24T02:42:46.978494+02:00[Europe/Zurich]")
@Controller
@RequestMapping("${openapi.idaiserver.base-path:}")
public class PreparationApiController implements PreparationApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PreparationApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Report> createPostmanCollection(MultipartFile filestream) {
        //TODO
        return PreparationApi.super.createPostmanCollection(filestream);
    }

}
