package ch.inss.virtualan.idaiserver.security;

import ch.inss.virtualan.idaiserver.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserFilter extends OncePerRequestFilter {

    private Map<String, String> apiMap = new HashMap<>();
    private final String USERAPIKEY = "X-USER-API-KEY";
    
    @Autowired
    private RepositoryService repo;

    private void returnNoAPIKeyError(ServletResponse response) throws IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        String error = "Nonexistent or invalid User API KEY";

        resp.reset();
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentLength(error.length());
        response.getWriter().write(error);
        
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String apiKey = request.getHeader(USERAPIKEY);
        if (!this.repo.checkKeyExists(apiKey)) {
            returnNoAPIKeyError(response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return "/health".equals(path) || "/database".equalsIgnoreCase(path) || path.startsWith("/demo");
    }
}