package br.com.zup.braz.rui.proposta.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration//(proxyBeanMethods = false)
public class ConfigSecurity extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers(HttpMethod.POST, "/novaProposta/**").hasAuthority("SCOPE_proposal:write")
                .antMatchers(HttpMethod.GET, "/consultaProposta").hasAuthority("SCOPE_proposal:read")
                .and().csrf().disable()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
