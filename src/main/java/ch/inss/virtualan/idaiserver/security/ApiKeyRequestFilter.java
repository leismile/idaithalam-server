package ch.inss.virtualan.idaiserver.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiKeyRequestFilter extends GenericFilterBean {

    @Value("${ch.inss.idaiserver.http.auth-token-header-name}")
    private String principalRequestHeader;

    @Value("${ch.inss.idaiserver.http.auth-token}")
    private String principalRequestValue;

    private void returnNoAPIKeyError(ServletResponse response) throws IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        String error = "Nonexistent or invalid API KEY";

        resp.reset();
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentLength(error .length());
        response.getWriter().write(error);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String apiKeyHeaderValue = httpRequest.getHeader(principalRequestHeader);

        if(apiKeyHeaderValue != null) {
            if(apiKeyHeaderValue.equals(principalRequestValue)) {
                chain.doFilter(request, response);
                return;
            }
            else {
                returnNoAPIKeyError(response);
            }
        }
        else {
            returnNoAPIKeyError(response);
        }
    }
}
