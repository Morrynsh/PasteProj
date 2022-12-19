package ua.pasta.pasteproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PasteProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasteProjApplication.class, args);
    }

}
