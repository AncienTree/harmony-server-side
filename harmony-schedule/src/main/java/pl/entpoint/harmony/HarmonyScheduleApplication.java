package pl.entpoint.harmony;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJSONDoc
public class HarmonyScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarmonyScheduleApplication.class, args);
	}

}
