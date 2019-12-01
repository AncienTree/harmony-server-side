package pl.entpoint.harmony.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

public class appConfig {
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource securityDataSource() {
		return DataSourceBuilder.create().build();
	}


}
