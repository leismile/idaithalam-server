package ch.inss.idaiserver.service;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.inss.idaiserver.model.*;
import ch.inss.idaiserver.persistence.TestRepository;
import ch.inss.idaiserver.utils.FileManagement;
import ch.inss.idaiserver.utils.MavenClient;


@Service
public class TestService {
    
    private static final Logger logger = LoggerFactory.getLogger(TestService.class);
    
    @Autowired
    TestRepository repo;
        
    public Report doTests(Cucumblan cucumblan)  {
        final String FEATURE = "virtualan-contract.feature";
        final String REPORTS = "target/cucumber-html-reports/";
        
        /** Create test data. */
//        Test test = new Test();
//        test.setError(FileManagement.NOERROR);
//        test.setCreationtime(FileManagement.whatTime());
//        test.setTestid(cucumblan.getUuid());
        //test.addServiceapiItem(serviceapiItem)
        
        /* Report for the response. */
        Report reportLinks = cucumblan.reportFactory();
     
        /* Generate the cucumblan.properties file.*/
        boolean isSaved = FileManagement.save( cucumblan );
        
        /* Maybe later: create and store test in database. */
        repo.save(cucumblan.testFactory());
        

        /* Execute mvn test. */
        if ( cucumblan.getExecute()) {
            reportLinks.executiontime(FileManagement.whatTime());
            
            MavenClient.doMaven();
        }else {
            /* Message */
            reportLinks.setLinktofeature("Not generated");
            reportLinks.setLinktoreport("Not generated.");
            reportLinks.setMessage("Property file updated, no test executed (execute=false).");
            reportLinks.setError(FileManagement.NOERROR);
            return reportLinks;
        }
        
        /* Copy generated feature file to the public folder. */
        String target = "src/main/resources/public/" + cucumblan.getFolder();
        FileManagement.copyFiles("conf/" + FEATURE, target);
        reportLinks.setLinktofeature(cucumblan.getFolder() + "/" + FEATURE);
        
        /* Copy the cucumber report folder and send back the link 
         * to the main report html file: report-feature_1959214294.html.
         * */
        FileManagement.copyFolder(REPORTS, target);
        String linkReport = cucumblan.getFolder() + "/" + FileManagement.getReportfile(target) ;
        logger.info("Generated report html file: " + linkReport);
        reportLinks.setLinktoreport(linkReport);

        /* Message */
        reportLinks.setMessage("Report created.");
        reportLinks.setError(FileManagement.NOERROR);
        return reportLinks;
    }

}
