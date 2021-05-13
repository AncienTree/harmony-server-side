package pl.entpoint.harmony.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author Mateusz Dąbek
 * @created 15/01/2020
 */

@Configuration
@ConfigurationProperties(prefix="spring.datasource")
@Getter @Setter
@Slf4j
public class DBConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private Environment env;

    @Autowired
    public DBConfiguration(Environment env) {
        this.env = env;
    }

    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        log.info("DB connection for DEV Env - H2");
        log.info(driverClassName);
        log.info(url);
        return "DB connection for DEV Env - H2";
    }

    @Profile("test")
    @Bean
    public String testDatabaseConnection() {
    	log.info("DB connection to VPS for Test Env only! - PostgreSQL");
    	log.info(driverClassName);
    	log.info(url);
        return "DB connection to VPS for Test Env only! - PostgreSQL";
    }
    
    @Primary
    @Bean
    public DataSource customDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }
}
