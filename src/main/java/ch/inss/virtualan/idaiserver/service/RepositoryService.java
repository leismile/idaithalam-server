package ch.inss.virtualan.idaiserver.service;

import ch.inss.virtualan.idaiserver.model.ApiKeyDAO;
import ch.inss.virtualan.idaiserver.repository.ApiRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryService {
    
    @Autowired
    private ApiRepository apiRepository;
    
   /* Used later in user related calls. */ 
   public boolean checkKeyValue(String userid, String value){
        ApiKeyDAO apiKeyDAO = this.apiRepository.findByUserid(userid);
        if (apiKeyDAO == null) return false;
        return (apiKeyDAO.getApikey().equals(value));
    }
    
    public boolean checkKeyExists(String apikey){
       List<ApiKeyDAO> list = this.apiRepository.findAll();
       for ( ApiKeyDAO apiKeyDAO : list){
           if ( apiKeyDAO.getUserid().equals(apikey)) return true;
       }
       return false;
    }
    
    
}
