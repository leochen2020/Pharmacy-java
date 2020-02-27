package com.easy.auth;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.easy.filter.JwtTokenFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Resource
	private JwtTokenFilter jwtTokenFilter;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();
//		httpSecurity
//				// 因为我们的token是无状态的，不需要跨站保护
//				.csrf().disable()
//
//				// 添加异常处理，以及访问禁止（无权限）处理
////	                .exceptionHandling().authenticationEntryPoint(new RestAuthenticationSuccessHandler()).accessDeniedHandler(accessDeniedHandler).and()
//
//				// 我们不再需要session了
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//
//				// 定义拦截页面，所有api全部需要认证
//				.authorizeRequests().antMatchers("/admin/**").hasRole("admin").antMatchers("/user/**").hasRole("user")
//				.anyRequest().authenticated();
//
//		// 最后，我们定义 filter，用来替换原来的UsernamePasswordAuthenticationFilter
//		httpSecurity.addFilterAt(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
////		web.ignoring()
////				// 让我们获取 token的api不走springsecurity的过滤器，大道开放
////				.antMatchers("/all/**").antMatchers("/images/**");
//	}
}
