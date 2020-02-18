package br.com.zen.catalogo.jwt.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.zen.catalogo.jwt.model.UserRoleName;
import br.com.zen.catalogo.jwt.security.auth.AuthenticationFailureHandler;
import br.com.zen.catalogo.jwt.security.auth.AuthenticationSuccessHandler;
import br.com.zen.catalogo.jwt.security.auth.LogoutSuccess;
import br.com.zen.catalogo.jwt.security.auth.RestAuthenticationEntryPoint;
import br.com.zen.catalogo.jwt.security.auth.TokenAuthenticationFilter;
import br.com.zen.catalogo.jwt.service.impl.CustomUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${jwt.cookie}")
  private String TOKEN_COOKIE;

  @Bean
  public TokenAuthenticationFilter jwtAuthenticationTokenFilter() throws Exception {
    return new TokenAuthenticationFilter();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private CustomUserDetailsService jwtUserDetailsService;

  @Autowired
  private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

  @Autowired
  private LogoutSuccess logoutSuccess;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder.userDetailsService(jwtUserDetailsService)
        .passwordEncoder(passwordEncoder());

  }

  @Autowired
  private AuthenticationSuccessHandler authenticationSuccessHandler;

  @Autowired
  private AuthenticationFailureHandler authenticationFailureHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
        
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);

    http.addFilterBefore(jwtAuthenticationTokenFilter(), BasicAuthenticationFilter.class);

    http.formLogin().loginPage("/api/login")
        .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler);
        
    http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))        
        .logoutSuccessHandler(logoutSuccess).deleteCookies(TOKEN_COOKIE);
    
    //Peças
    http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/pecas/**").hasAnyRole("ADMIN", "USER");
    http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/pecas").hasAnyRole("ADMIN");
    http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/v1/pecas/**").hasAnyRole("ADMIN");
    http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v1/pecas/**").hasAnyRole("ADMIN");
    
    http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/user/all").hasAnyRole("ADMIN");
    http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/user/signup").hasAnyRole("ADMIN");
    
    http.authorizeRequests().anyRequest().authenticated();
  }

}
