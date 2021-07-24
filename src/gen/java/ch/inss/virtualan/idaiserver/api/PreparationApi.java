/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ch.inss.virtualan.idaiserver.api;

import ch.inss.virtualan.idaiserver.model.Report;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-24T04:39:15.575686+02:00[Europe/Zurich]")
@Validated
@Api(value = "preparation", description = "the preparation API")
public interface PreparationApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /preparation/topostman : Create a Postman collection from an OAS3 specification.
     * Create a Postman collection from an OAS3 specification.
     *
     * @param filestream  (required)
     * @return Test created. (status code 201)
     *         or Bad request. (status code 400)
     *         or I am a teapot. (status code 418)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Create a Postman collection from an OAS3 specification.", nickname = "createPostmanCollection", notes = "Create a Postman collection from an OAS3 specification.", response = Report.class, authorizations = {
        
        @Authorization(value = "ApiKeyAuth")
         }, tags={ "Preparation", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Test created.", response = Report.class),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 418, message = "I am a teapot."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @PostMapping(
        value = "/preparation/topostman",
        produces = { "application/json" },
        consumes = { "multipart/form-data" }
    )
    default ResponseEntity<Report> createPostmanCollection(@ApiParam(value = "") @Valid @RequestPart(value = "filestream", required = true) MultipartFile filestream) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"sessionNr\" : 0, \"linkToCollection\" : \"linkToCollection\", \"linkToProperties\" : \"linkToProperties\", \"creationTime\" : \"creationTime\", \"durationSeconds\" : 6, \"linkToReport\" : \"linkToReport\", \"linkToSessions\" : \"linkToSessions\", \"message\" : \"message\", \"error\" : \"error\", \"linkToFeature\" : \"linkToFeature\", \"skipResponseValidation\" : true, \"success\" : true, \"testExecuted\" : true, \"testId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"startTime\" : \"startTime\", \"endTime\" : \"endTime\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
