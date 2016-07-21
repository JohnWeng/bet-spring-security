package demo;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jweng on 7/12/2016.
 */

@Component
public class customAuthenticationProvider implements AuthenticationProvider{
    int bac=5;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";
        LoginCredentials    loginCredentials = (LoginCredentials) authentication.getDetails();
// here we make the call then we get back a string.
        // Code to make rest call here and check for OK or Unauthorised.
        // What do I return?
System.out.print("ggggggggggggggggg");
//        System.out.print(loginCredentials);
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));


//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password,  grantedAuths);
//        usernamePasswordAuthenticationToken.setDetails(response);
//        return usernamePasswordAuthenticationToken;
        return new UsernamePasswordAuthenticationToken(username, password,  grantedAuths);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
