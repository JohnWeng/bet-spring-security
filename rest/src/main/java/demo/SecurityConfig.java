package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;



@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
	BasicAuthenticationFilter fd;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.httpBasic();
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




//@Configuration
//@EnableWebSecurity(debug = true)
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean(name="loginService")
////    public LoginService loginService(){
////        return new LoginServiceImpl();
////    }
//
//    @Bean( name="myAuthenticationManager")
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFiltersssssssss() throws Exception {
//        System.out.print("dffffffffffffff");
//        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
//        customUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
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
//
//    public void configure(AuthenticationManagerBuilder auth)  throws Exception {
//
//        auth.authenticationProvider(myAuthenticationProvider);
//    }
//}