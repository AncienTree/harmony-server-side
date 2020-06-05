package pl.entpoint.harmony.config;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import pl.entpoint.harmony.service.CustomDetailsService;
import pl.entpoint.harmony.util.token.CustomAccessTokenConverter;

/**
 * @author Mateusz Dąbek
 * @created 22/01/2020
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${security.signing-key}")
    private String signingKey;
    @Value("${security.security-realm}")
    private String securityRealm;

    private final CustomDetailsService customDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomAccessTokenConverter customAccessTokenConverter;

    @Autowired
    WebSecurityConfig(CustomDetailsService customDetailsService,
                      BCryptPasswordEncoder bCryptPasswordEncoder,
                      CustomAccessTokenConverter customAccessTokenConverter) {
        this.customDetailsService = customDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customAccessTokenConverter = customAccessTokenConverter;
    }

    /*
     *
     * Beans
     *
     */

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder() );
        provider.setUserDetailsService(customDetailsService);
        return provider;
    }

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(defaultAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter defaultAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(customAccessTokenConverter);
        converter.setSigningKey(signingKey);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    TODO whitelist IP
//    @Bean
//    public FilterRegistrationBean remoteAddressFilter() {
//
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        RemoteAddrFilter filter = new RemoteAddrFilter();
//
//        //filter.setAllow("192.168.0.2");
//        filter.setDenyStatus(403);
//
//        filterRegistrationBean.setFilter(filter);
//        filterRegistrationBean.addUrlPatterns("/*");
//
//        return filterRegistrationBean;
//
//    }

    /*
     *
     * Methods
     *
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and().httpBasic().realmName(securityRealm)
                .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/oauth/token").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").authenticated()
                .anyRequest().authenticated();
    }
}
