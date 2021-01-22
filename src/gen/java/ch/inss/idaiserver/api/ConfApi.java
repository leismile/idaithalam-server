/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ch.inss.idaiserver.api;

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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-22T16:54:31.589215+01:00[Europe/Zurich]")
@Validated
@Api(value = "conf", description = "the conf API")
public interface ConfApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * PUT /conf/collection/{testid} : Add a Postman collection to the configuration.
     * Add a Postman collection to the configuration. All added Postman collections will be executed as one test.
     *
     * @param testid testid for that collection (required)
     * @param fileStream  (optional)
     * @return Configuration updated. (status code 201)
     *         or Bad request. (status code 400)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Add a Postman collection to the configuration.", nickname = "addcollection", notes = "Add a Postman collection to the configuration. All added Postman collections will be executed as one test.", tags={ "configuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Configuration updated."),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @PutMapping(
        value = "/conf/collection/{testid}",
        consumes = { "multipart/form-data" }
    )
    default ResponseEntity<Void> addcollection(@ApiParam(value = "testid for that collection",required=true) @PathVariable("testid") String testid,@ApiParam(value = "") @Valid @RequestPart(value = "fileStream", required = false) MultipartFile fileStream) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /conf/server/{testid} : Add a server url to the configuration.
     * Add a server url to the configuration. That is added as additional URL.
     *
     * @param testid testid for that serverurl (required)
     * @param serverurl Serverurl to add to the test configuration. (required)
     * @return Gives back the entire cucumblan.properties file. (status code 201)
     *         or Bad request. (status code 400)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Add a server url to the configuration.", nickname = "addserver", notes = "Add a server url to the configuration. That is added as additional URL.", response = String.class, tags={ "configuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Gives back the entire cucumblan.properties file.", response = String.class),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @PutMapping(
        value = "/conf/server/{testid}",
        produces = { "text/plain" }
    )
    default ResponseEntity<String> addserver(@ApiParam(value = "testid for that serverurl",required=true) @PathVariable("testid") String testid,@NotNull @ApiParam(value = "Serverurl to add to the test configuration.", required = true) @Valid @RequestParam(value = "serverurl", required = true) String serverurl) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /conf/gherkin/{testid} : Get the content of the enerated Gherkin feature file.
     * Get the content of the generated Gherkin feature file.
     *
     * @param testid testid for that Gherkin file. (required)
     * @return Content of the generated Gherkin feature file. (status code 200)
     *         or Internal server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Get the content of the enerated Gherkin feature file.", nickname = "getgherkin", notes = "Get the content of the generated Gherkin feature file.", response = String.class, tags={ "configuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Content of the generated Gherkin feature file.", response = String.class),
        @ApiResponse(code = 500, message = "Internal server error."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @GetMapping(
        value = "/conf/gherkin/{testid}",
        produces = { "text/plain" }
    )
    default ResponseEntity<String> getgherkin(@ApiParam(value = "testid for that Gherkin file.",required=true) @PathVariable("testid") String testid) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /conf/collection/{testid} : Remove a server url from the configuration.
     * Remove a server url from the configuration.
     *
     * @param testid testid for that serverurl (required)
     * @param resource Resource of serverurl to be removed from the configuration. (required)
     * @return Serverurl removed (status code 204)
     *         or Bad request. (status code 400)
     *         or Serverurl not found. (status code 404)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Remove a server url from the configuration.", nickname = "removecollection", notes = "Remove a server url from the configuration.", tags={ "configuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Serverurl removed"),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 404, message = "Serverurl not found."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @DeleteMapping(
        value = "/conf/collection/{testid}"
    )
    default ResponseEntity<Void> removecollection(@ApiParam(value = "testid for that serverurl",required=true) @PathVariable("testid") String testid,@NotNull @ApiParam(value = "Resource of serverurl to be removed from the configuration.", required = true) @Valid @RequestParam(value = "resource", required = true) String resource) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /conf/server/{testid} : Remove a server url from the configuration.
     * Remove a server url from the configuration.
     *
     * @param testid testid for that serverurl (required)
     * @param resource Resource of serverurl to be removed from the configuration. (required)
     * @return Serverurl removed (status code 204)
     *         or Bad request. (status code 400)
     *         or Serverurl not found. (status code 404)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Remove a server url from the configuration.", nickname = "removeserver", notes = "Remove a server url from the configuration.", tags={ "configuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Serverurl removed"),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 404, message = "Serverurl not found."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @DeleteMapping(
        value = "/conf/server/{testid}"
    )
    default ResponseEntity<Void> removeserver(@ApiParam(value = "testid for that serverurl",required=true) @PathVariable("testid") String testid,@NotNull @ApiParam(value = "Resource of serverurl to be removed from the configuration.", required = true) @Valid @RequestParam(value = "resource", required = true) String resource) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
