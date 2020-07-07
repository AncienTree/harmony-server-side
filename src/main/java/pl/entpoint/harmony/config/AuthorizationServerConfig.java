package pl.entpoint.harmony.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import pl.entpoint.harmony.service.CustomDetailsService;
import pl.entpoint.harmony.util.token.CustomToken;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author Mateusz Dąbek
 * @created 22/01/2020
 */

@Configuration
@ComponentScan(basePackages = "pl.entpoint.harmony")
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${security.jwt.resource-ids}")
    private String resourceIds;
    @Value("${security.jwt.client-id}")
    private String clientId;
    @Value("${security.jwt.client-secret}")
    private String clientSecret;

    private final AuthenticationManager authenticationManager;
    private final CustomDetailsService customDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenStore tokenStore;
    private final JwtAccessTokenConverter defaultAccessTokenConverter;
    private final DataSource customDataSource;

    @Autowired
    AuthorizationServerConfig(AuthenticationManager authenticationManager, CustomDetailsService customDetailsService,
                              BCryptPasswordEncoder bCryptPasswordEncoder, TokenStore tokenStore,
                              JwtAccessTokenConverter defaultAccessTokenConverter, DataSource customDataSource) {
        this.authenticationManager = authenticationManager;
        this.customDetailsService = customDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenStore = tokenStore;
        this.defaultAccessTokenConverter = defaultAccessTokenConverter;
        this.customDataSource = customDataSource;
    }

    /*
     *
     * Beans
     *
     */

    @Bean
    public TokenEnhancer tokenEnhancer(){
        return new CustomToken();
    }

    /*
     *
     * Methods
     *
     */

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(customDataSource)
                .withClient(clientId).secret(bCryptPasswordEncoder.encode(clientSecret))
                .authorizedGrantTypes("password", "authorization_code")
                .scopes("read", "write")
                .resourceIds(resourceIds)
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "USER", "ADMIN")
                .autoApprove(true)
                .accessTokenValiditySeconds(28800);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), defaultAccessTokenConverter));

        endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager).accessTokenConverter(defaultAccessTokenConverter)
                .userDetailsService(customDetailsService).tokenEnhancer(tokenEnhancerChain);
    }
}
