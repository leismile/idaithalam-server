package ch.inss.idaiserver.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-21T08:48:24.320856+01:00[Europe/Zurich]")
@Controller
@RequestMapping("${openapi.idaiserver.base-path:}")
public class ConfApiController implements ConfApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ConfApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
