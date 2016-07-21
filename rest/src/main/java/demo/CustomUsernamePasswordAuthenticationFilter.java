package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jweng on 7/12/2016.
 */
public class CustomUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public CustomUsernamePasswordAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
        // TODO Auto-generated constructor stub
    }

    public CustomUsernamePasswordAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login","POST"));
        // TODO Auto-generated constructor stub
    }
//    @Override
//    protected boolean requiresAuthentication(HttpServletRequest request,
//    HttpServletResponse response) {
//    	return true;
//
//    }
@Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    LoginCredentials loginCredentials = mapper.readValue(request.getInputStream(), LoginCredentials.class);


    System.out.println("attempting to authentificate");
    System.out.println(loginCredentials.toString());


              // request.getSession().setAttribute("dbValue", dbValue);
//        System.out.println("attempting to authentificate");
        String username = loginCredentials.getUserName();
        String password = loginCredentials.getUserPassword();
//        System.out.println(username+password);
//        while (request.getAttributeNames().hasMoreElements()) {
//            String e = (String) request.getAttributeNames().nextElement();
//            System.out.println("param name : " + e + " and param value : " + request.getAttribute(e));
//        }


    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
    authRequest.setDetails(loginCredentials);


//        // Allow subclasses to set the "details" property
//        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);


    }
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

}