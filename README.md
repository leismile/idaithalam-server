# Idaiserver

[![Build Status](https://travis-ci.com/virtualansoftware/idaithalam-server.svg?branch=master)](https://travis-ci.com/virtualansoftware/idaithalam-server)

Idaiserver provides APIs to execute REST API contract tests, generate test reports and feature files.

[Powered by INSS ![INSS](https://inss.ch/wp-content/uploads/2021/01/favicon-32x32-1.png)](https://inss.ch) and
[Virtualan Software ![Virtualan](https://inss.ch/wp-content/uploads/2021/01/virtualan32x32.png)](https://virtualansoftware.com)

Idaiserver consists of two main parts:
1. idaithalam to execute the tests and generate Cucumber reports.
2. Idaiserver to provide the APIs for test execution and configuration.

## What you will get 
1. Your API implementation will be tested against the contract you defined.
2. You will get a Cucumber report with the test results.
3. You will get a Gherkin feature file with all scenarios, steps and testdata for your API.

## What it does
Idaiserver executes REST API contract tests based on a Postman collection.

You upload the Postman collection with APIs and their testdata (the contract).
You define the URL which shall be tested (the API implementation). 
You will get a full Cucumber test report along with a Gherkin feature file of that test execution.

### Contract test
That Postman collection is essential and basis for the entire test.
It is your definition of the API contract. 
If you have an OAS3 specification, it is recommended to import that into Postman and
use that collection for the test.
Idaithalam will execute API requests as defined in the Postman collection.
You define the server url against which the test will be executed.

### Collection example data
Idaithalam will use the saved request and response data that are stored in the collection.
Those data will be validated. The response data validation is optional.
The collection request and response data will be stored as example test data in the 
generated Gherkin feature file.

### Configuration
The configuration will be defined in a server side properties file.
It will be automatically created for the tests, and can be updated or downloaded.

## Idaithalam
Idaithalam is a library that can be used for REST API contract testing.
It will use Postman collections as basis for the contract.
And will generate Cucumber reports and a Gherkin feature file from that test.

## Idaiserver
Idaiserver is a reference implementation of Idaithalam.
It implements the API services to execute contract tests based on Postman collections
Idaiserver provides APIs for 
- uploading a new contract
- run the test
- update the configuration
- re-run existing test configurations
- get the report
- get the feature file
- get the configuration properties file
- get a test summary.

## Quick demo sample
User the file ./samples/idaithalan.postman_collection.json as a sample collection.
You can contract test it agains https://live.virtualandemo.com.
To do that, start Postman. Import src/main/resources/idaithalamserver.yaml as a Postman collection.
Select the API /test POST (Create and run the test with the uploaded Postman collection.).
Go to body of that request. 
Change "filestream" to "file". In Value choose the file ./samples/idaithalan.postman_collection.json.
Choose value "https://live.virtualandemo.com" for "serverurl".
Parameter "execute" shall be true. 
Parameter skipResponseValidation shall be false.
Send request.
You will get a test summary with links to a Cucumber report, a Gherkin feature file, the generated configuration
file (cucumblan.properties) and the link to the test summary itself.

## Usage steps
1. - Create a Postman collection (e.g. import from OAS3 file).
2. - Define your tests in the collection.
3. - Export Postman collection.
4. - Define parameter for /test POST.
5. - Create your test with /test POST.
6. - Check the test results.

### 1. Create Postman collection
Base of the contract test is your Postman collection.
It is recommended to import an OAS3 file into Postman.
That will ensure that the collection is exactly the contract you defined.
You can of course use also an existing or manually generated collection.

### 2. Define your tests in the collection
Simply execute the requests from your collection against a mockup server 
of your API or whatever you like.
That executions in Postman will define your test data.
You can e.g. generate a mockup server with an OAS3 code generator.
Define parameter values which you want to execute later in the test.

Store the responses as example data.
The saved responses can be used to validate the data.
The validation of the response data can be skipped in the test if not wanted.

### 3. Export Postman collection.
After the request and response data are stored in the collection, export the 
collection for the upload to the Idaiserver.

### 4. Define parameter for /test POST
The API /test is the main endpoint of the Idaiserver.
It will create your first test, and immediately executes it if you want.
You can define these parameters in the POST request body as form-data:
- filestream: the Postman collection of your API with defined test data from step 2. Mandatory.
- serverurl: The API url to be tested, like e.g. http://localhost:8080. Mandatory.
- execute: You can immediately exeucte the test. Default is true. Optional.
- skipResponseValidation: You can skip the validation of the test response data. Default is false. Optional.
- datatype: Value is "POSTMAN". Datatype defines the format of the contract. 
    Currently it is only "POSTMAN". Later further formats will follow (like Excel). Optional.

### 5. - Create your test with /test POST
Now calling the /test POST method will create your test configuration.
Execution is optional. 
You may only define your test and execute it later.
You will get a test summary together with your testId.
The summary will contain links for the Cucumber report, the Gherkin file
and a JSON test summary.
TestId is important to run a test later, or retrieve reports afterwards.

### 6. - Check the test results
You will get the links in the response body for the Cucumber report, the feature file and a test session summary.
Check also TestId. You can use it for later test executions or getting again the Cucumber report.
You will need the testId for all further API requests.
You can use the other APIs to re-run tests, get links again and the test summary.


## UI
The Idaithalam UI is hosted at:
[http://microservices.virtualandemo.com:8900/](http://microservices.virtualandemo.com:8900/ "http://microservices.virtualandemo.com:8900/")


## API 
The API is hosted at:
[http://microservices.virtualandemo.com:8900/swagger](http://microservices.virtualandemo.com:8900/swagger "http://microservices.virtualandemo.com:8900/swagger")

## API list
Here the APIs provided by Idaiserver.
### Executions
- /test POST Create and run a test
- /test/{testid} PUT Re-run an existing test.
- /test/{testid} DELETE Remove the test.
### Results
- /test GET Get a list of all test IDs.
- /test/{testid} GET Get the test summary of last test for that testId.
- /test/{testid}/report GET Get the report for this test.
- /test/{testid}/feature GET Get the Gherkin feature file for that test.
### Configuation
- /test/{testid}/conf GET Get the test configuration for that test (cucumblan.properties).
- /test/{testid}/conf Update the serverurl for that test configuration.
- /test/{testid}/conf Remove a key from the test configuration (cucumblan.properties).


## Technology stack
* [Idaithalam] - Contract test for REST API.
* [Java Spring Boot] - The server.
* [Postman collections] - The API contract.
* [OAS3] - API first with Openapi generator and Maven plugin (openapi-generator.tech/).
* [Docker] - for the containerization
* [Kubernetes] - for running
* [Google Cloud] ' as a platform.

## Project members
This project is a collaboration of Elan Thangamani and Oliver Glas. 
Elan is responsible for Idaithalam. Oliver and Elan are responsible for the reference implementation.


