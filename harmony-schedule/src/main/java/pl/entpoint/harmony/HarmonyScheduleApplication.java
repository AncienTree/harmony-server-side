package pl.entpoint.harmony;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HarmonyScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarmonyScheduleApplication.class, args);
    }

//    public void AuthenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
//        if (repo.count() == 0){
//            repo.save(new User("user","user"));
//        }
//        builder.userDetailsService(username -> new CustomUserDetails(repo.findByLogin(username)));
//    }
}
