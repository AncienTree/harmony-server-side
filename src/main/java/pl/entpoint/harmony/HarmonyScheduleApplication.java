package pl.entpoint.harmony;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class HarmonyScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarmonyScheduleApplication.class, args);
    }
    
    @PostConstruct
    public void setTimeZone() {
       TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw"));
    }


}
