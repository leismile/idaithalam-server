package ch.inss.virtualan.idaiserver.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import ch.inss.virtualan.idaiserver.IdaiServerSpringBoot;
import ch.inss.virtualan.idaiserver.model.ApiKeyDAO;
import ch.inss.virtualan.idaiserver.repository.ApiRepository;
import ch.inss.virtualan.idaiserver.service.RepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

@SpringBootTest(classes = {ApiRepository.class, ApiKeyDAO.class, RepositoryService.class, IdaiServerSpringBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryServiceTest {

  
    @Autowired
    public RepositoryService repositoryService;
    
    @Autowired
    public ApiRepository apiRepository;
    
    @Test
    public void checkDBContent(){
        assertNotNull(this.repositoryService);
        ApiKeyDAO apiKeyDAO = this.apiRepository.findByUserid("user1");
//        List<ApiKeyDAO> list = this.apiRepository.findAll();
        assertNotNull(apiKeyDAO);
//        assertNotNull(list);
//        assertTrue(list.size() > 0);
    }
    
}
