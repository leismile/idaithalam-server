# Idaiserver

Idaiserver provides APIs to execute REST API contract tests.

[Powered by INSS ![INSS](https://inss.ch/wp-content/uploads/2021/01/favicon-32x32-1.png)](https://inss.ch) and
[Virtualan Software ![Virtualan](https://inss.ch/wp-content/uploads/2021/01/virtualan32x32.png)](https://virtualansoftware.com)


Idaiserver has two main parts:
1. idaithalam to execute the tests and geneate Cucumber reports.
2. Idaiserver to provide the APIs for test execution and configuration.

This project is a collaboration of Elan Thangamani and Oliver Glas. 
Elan is mainly responsible for Idaithalam. Oliver is mainly responsible for the reference implementation.

## Idaithalam
Idaithalam is a library that can be used for REST API contract testing.
It will use a Postman collection as basis for the contract.
And will generate Cucumber reports and a Gherkin feature file from that test.

## Idaiserver
Idaiserver is a reference implementation of the library Idaithalam.
It provides REST API services to execute contract tests based on a Postman collection.
Idaiserver provides APIs for 
- uploading a new contract
- run the test
- update the configuration
- re-run existing test configurations
- get the report
- get the feature file
- get the configuration properties file
- get a test summary

## What you will get
1. Your API implementation will be tested against the contract you defined for it.
2. You will get a Cucumber report showing the test results.
3. You will get a Gherkin feature file with all scenarios, steps and testdata for your API.

## What it does
Idaiserver uses Idaithalam to execute a REST API contract test based on a Postman collection.

You upload the Postman collection with APIs and their testdata (the contract).
You define the URL which shall be tested (the implementation). 
You will get a full Cucumber test report along with a Gherkin feature file of that test execution.

### Contract test
That Postman collection is essential and basis for the entire test.
It is your defined API contract. 
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
It will be automatically created for the tests, and can be updated or downloaded 
with server API requests.

## Technology stack
* [Idaithalam] - Contract test for REST API.
* [Java Spring Boot] - The server.
* [Postman collections] - The API contract.
* [OAS3] - API first with Openapi generator and Maven plugin (openapi-generator.tech/).


