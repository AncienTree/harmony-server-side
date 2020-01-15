package pl.entpoint.harmony.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix="spring.datasource")
@SuppressWarnings("unused")
@Getter
@Setter
public class DBConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Bean
    public DataSource securityDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        System.out.println("DB connection for DEV Env - H2");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB connection for DEV Env - H2";
    }

    @Profile("test")
    @Bean
    public String testDatabaseConnection() {
        System.out.println("DB connection to VPS for Test Env only! - MySQL");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB connection to VPS for Test Env only! - MySQL";
    }
}
