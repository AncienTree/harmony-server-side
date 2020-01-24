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
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import pl.entpoint.harmony.service.CustomDetailsService;

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

    private AuthenticationManager authenticationManager;
    private CustomDetailsService customDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenStore tokenStore;
    private JwtAccessTokenConverter defaultAccessTokenConverter;

    @Autowired
    AuthorizationServerConfig(AuthenticationManager authenticationManager, CustomDetailsService customDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, TokenStore tokenStore, JwtAccessTokenConverter defaultAccessTokenConverter) {
        this.authenticationManager = authenticationManager;
        this.customDetailsService = customDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenStore = tokenStore;
        this.defaultAccessTokenConverter = defaultAccessTokenConverter;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId).secret(bCryptPasswordEncoder.encode(clientSecret))
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .scopes("read", "write")
                .resourceIds(resourceIds)
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "USER", "ADMIN")
                .autoApprove(true)
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(7200);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager).accessTokenConverter(defaultAccessTokenConverter)
                .userDetailsService(customDetailsService);

    }
}
