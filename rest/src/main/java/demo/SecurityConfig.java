package demo;


//@EnableWebSecurity(debug = true)
//class SecurityConfig extends WebSecurityConfigurerAdapter {
//	BasicAuthenticationFilter fd;
//	HttpSessionSecurityContextRepository f;
//	SecurityContextPersistenceFilter g;
////	OrderedRequestContextFilter
////	HttpSessionRequestCache
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.authorizeRequests()
//				.anyRequest().authenticated()
//				.and()
//				.httpBasic()
//				.authenticationEntryPoint(dRFAuthenticationEntryPoint())
//				.and()
//				.requestCache()
//				.requestCache(httpSessionRequestCache())
//				.and()
//				.anonymous().disable();}
//
//
//	@Bean
//	public HttpSessionRequestCache httpSessionRequestCache() {
//		HttpSessionRequestCache httpSessionRequestCache = new HttpSessionRequestCache();
//		httpSessionRequestCache.setCreateSessionAllowed(false);
//		return httpSessionRequestCache;
//	}
//
//	@Bean
//	public DRFAuthenticationEntryPoint dRFAuthenticationEntryPoint() {
//		return new DRFAuthenticationEntryPoint();
//
//	}
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth
//				.inMemoryAuthentication()
//				.withUser("user").password("password").roles("USER");
//	}
//}

import com.drf.betsservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jweng on 7/12/2016.
 */
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////	@Bean(name="myAuthenticationManager")
////	@Override
////	public AuthenticationManager authenticationManagerBean() throws Exception {
////		return super.authenticationManagerBean();
////	}
//
//    @Bean
//    public MyUsernamePasswordAuthenticationFilter authenticationFilteriiiii() {
//        MyUsernamePasswordAuthenticationFilter authFilter = new MyUsernamePasswordAuthenticationFilter();
//        authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login","POST"));
////		authFilter.setAuthenticationManager(myAuthenticationManager);
////		authFilter.setAuthenticationSuccessHandler(new MySuccessHandler("/app"));
////		authFilter.setAuthenticationFailureHandler(new MyFailureHandler("/login?error=1"));
////		authFilter.setUsernameParameter("username");
////		authFilter.setPasswordParameter("password");
//        return authFilter;
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(new DRFAuthenticationProvider());
///*		auth
//				.inMemoryAuthentication()
//				.withUser("user").password("password").roles("USER");*/
//    }
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(new UsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//
//}


@Configuration
@EnableWebSecurity(debug = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${betsService-server-host}")
    private String betsServiceHost;


    @Bean
    public DRFCustomSuccessHandler drfCustomSuccessHandler() {
        DRFCustomSuccessHandler drfCustomSuccessHandler = new DRFCustomSuccessHandler();
        return drfCustomSuccessHandler;
    }

    @Bean
    public HttpInvokerProxyFactoryBean httpInvokerProxy() {
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setServiceInterface(UserService.class);
        proxy.setServiceUrl(betsServiceHost);
        proxy.afterPropertiesSet();
        UserService userService = (UserService) proxy.getObject();
        try {
            String result = userService.test();

            System.out.println(result);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return proxy;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
        authenticationProviderList.add(drfAuthenticationProvider);
        AuthenticationManager authenticationManager = new ProviderManager(
                authenticationProviderList);
        return authenticationManager;
    }

    @Bean
    DRFAuthenticationFilter drfAuthenticationFilter() throws Exception {
        System.out.print("dffffffffffffff");
        DRFAuthenticationFilter drfAuthenticationFilter = new DRFAuthenticationFilter();
        drfAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        drfAuthenticationFilter.setAuthenticationSuccessHandler(drfCustomSuccessHandler());
        return drfAuthenticationFilter;
    }


    @Autowired
    private DRFAuthenticationProvider drfAuthenticationProvider;

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(drfAuthenticationFilter(), BasicAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(dRFAuthenticationEntryPoint())
                .and()
                .requestCache()
                .requestCache(httpSessionRequestCache())
                .and()
                .anonymous().disable()
        ;
        /*.addFilterBefore(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)*/



    }


    @Bean
    public HttpSessionRequestCache httpSessionRequestCache() {
        HttpSessionRequestCache httpSessionRequestCache = new HttpSessionRequestCache();
        httpSessionRequestCache.setCreateSessionAllowed(false);
        return httpSessionRequestCache;
    }

    @Bean
    public DRFAuthenticationEntryPoint dRFAuthenticationEntryPoint() {
        return new DRFAuthenticationEntryPoint();

    }


//    public void configure(AuthenticationManagerBuilder auth)  throws Exception {
//
//        auth.authenticationProvider(drfAuthenticationProvider);
//    }
}