package ch.inss.virtualan.idaiserver.security;

import org.springframework.core.annotation.Order;
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
public class Filter extends OncePerRequestFilter {

    private Map<String, String> apiMap = new HashMap<>();
    private final String USERAPIKEY = "X-USER-API-KEY";

    private void returnNoAPIKeyError(ServletResponse response) throws IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        String error = "Nonexistent or invalid User API KEY";

        resp.reset();
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentLength(error.length());
        response.getWriter().write(error);
        
    }

    public Filter() {
        this.apiMap = new HashMap<>();
        apiMap.put(USERAPIKEY, "user1");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String apiKey = request.getHeader(USERAPIKEY);
//        String user = request.getPathInfo();
        String value = this.apiMap.get(apiKey);
        if (!apiMap.containsValue(apiKey)) {
            returnNoAPIKeyError(response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return "/health".equals(path) || "/database".equalsIgnoreCase(path);
    }
}
