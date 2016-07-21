package demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
	BasicAuthenticationFilter fd;
	HttpSessionSecurityContextRepository f;
	SecurityContextPersistenceFilter g;
//	OrderedRequestContextFilter
//	HttpSessionRequestCache
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.httpBasic()
				.authenticationEntryPoint(dRFAuthenticationEntryPoint())
				.and()
				.requestCache()
				.requestCache(httpSessionRequestCache())
				.and()
				.anonymous().disable();}


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

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth
				.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER");
	}
}






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
//        auth.authenticationProvider(new customAuthenticationProvider());
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



//
//@Configuration
//@EnableWebSecurity(debug = true)
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////	FilterSecurityInterceptor
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
//
////
////    @Bean( name="myAuthenticationManagerssssssssss")
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////
////    }
//
//	 @Bean
//	    public CustomSuccessHandler customSuccessHandler() {
//	        CustomSuccessHandler customSuccessHandler = new CustomSuccessHandler();
//	        return customSuccessHandler;
//	    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
//        authenticationProviderList.add(myAuthenticationProvider);
//        AuthenticationManager authenticationManager = new ProviderManager(
//                authenticationProviderList);
//        return authenticationManager;
//    }
//
//    @Bean
//    CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFiltersssssssss() throws Exception {
//        System.out.print("dffffffffffffff");
//        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
//        customUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
//        customUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(customSuccessHandler());
//        return customUsernamePasswordAuthenticationFilter;
//    }
//
//
//    @Autowired
//    private customAuthenticationProvider myAuthenticationProvider;
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(customUsernamePasswordAuthenticationFiltersssssssss(), BasicAuthenticationFilter.class);
//        /*.addFilterBefore(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)*/;
//
//
//    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////        http.addFilterBefore(new CustomUsernamePasswordAuthenticationFilter(), DelegatingFilterProxy.class);
//////        http.addFilterAfter(new B(), new A().getClass());
////    }
////
//    public void configure(AuthenticationManagerBuilder auth)  throws Exception {
//
//        auth.authenticationProvider(myAuthenticationProvider);
//    }
//}