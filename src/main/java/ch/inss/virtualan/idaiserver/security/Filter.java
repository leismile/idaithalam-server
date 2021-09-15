package ch.inss.virtualan.idaiserver.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Filter extends OncePerRequestFilter {
    
    private Map<String, String> apiMap = new HashMap<>();
    
       
    public Filter(){
        this.apiMap = new HashMap<>();
        apiMap.put("key1", "user1");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader("apikey");
//        String user = request.getPathInfo();
        String value = this.apiMap.get(apiKey);
        if ( apiMap.containsValue(apiKey)){
            return;
        }else{
            throw new ServletException("User not authenticated.");
        }
        
    }
}
