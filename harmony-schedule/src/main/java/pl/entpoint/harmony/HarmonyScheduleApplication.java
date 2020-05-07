package pl.entpoint.harmony;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class HarmonyScheduleApplication {

    public static void main(String[] args) {
        System.out.println("--------------------------- Start Application ---------------------------");
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Warsaw");
        TimeZone.setDefault(timeZone);

        SpringApplication.run(HarmonyScheduleApplication.class, args);
    }


}
