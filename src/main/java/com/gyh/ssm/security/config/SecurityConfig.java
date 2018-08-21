package com.gyh.ssm.security.config;

import java.util.Arrays;

import com.gyh.ssm.security.MyAccessDecisionManager;
import com.gyh.ssm.security.MyFilterInvocationSecurityMetadataSource;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private AuthenticationProvider authenticationProvider;
	private AuthenticationEntryPoint authenticationEntryPoint;
	private SingleSignOutFilter singleSignOutFilter;
	private LogoutFilter logoutFilter;

	@Autowired
	public SecurityConfig(CasAuthenticationProvider casAuthenticationProvider, AuthenticationEntryPoint eP,
						  LogoutFilter lF
			, SingleSignOutFilter ssF) {
		this.authenticationProvider = casAuthenticationProvider;
		this.authenticationEntryPoint = eP;

		this.logoutFilter = lF;
		this.singleSignOutFilter = ssF;

	}

	/**================================== spring security ==========================================**/
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	
        http
			.authorizeRequests()
			.regexMatchers("/login", "/menu.*")
			// Specify that URLs are allowed by any authenticated user.
			.authenticated()
			.and()
			.authorizeRequests()
			.regexMatchers("/")
			// Specify that URLs are allowed by anyone.
			.permitAll()
			.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
				public <O extends FilterSecurityInterceptor> O postProcess(
						O fsi) {
					fsi.setSecurityMetadataSource(mySecurityMetadataSource());
					fsi.setAccessDecisionManager(myAccessDecisionManager());
					return fsi;
				}
			})
			.and().csrf().disable()
			.httpBasic()
			.authenticationEntryPoint(authenticationEntryPoint)
			.and()
			.logout().logoutSuccessUrl("/logout")
			.and()
            .addFilter(casAuthenticationFilter())
			.addFilterBefore(singleSignOutFilter, CasAuthenticationFilter.class)
			.addFilterBefore(logoutFilter, LogoutFilter.class);
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/");
		web.ignoring().antMatchers("/webjars/**");
	}

    @Bean
    public  CasAuthenticationFilter casAuthenticationFilter() {
        return new CasAuthenticationFilter();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return new ProviderManager(Arrays.asList(authenticationProvider));
	}

	@Bean
	public CasAuthenticationFilter casAuthenticationFilter(ServiceProperties sP) throws Exception {
		CasAuthenticationFilter filter = new CasAuthenticationFilter();
		filter.setServiceProperties(sP);
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}

	@Bean
	public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
		MyFilterInvocationSecurityMetadataSource securityMetadataSource = new MyFilterInvocationSecurityMetadataSource();
		return securityMetadataSource;
	}

	@Bean
	public AccessDecisionManager myAccessDecisionManager() {
		return new MyAccessDecisionManager();
	}
}
