package example.client.hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class HelloClientApplication {

    public static void main(String... args) {
        SpringApplication.run(HelloClientApplication.class, args);
    }

    @Component
    public class Runner implements CommandLineRunner {

        @Override
        public void run(String... args) throws Exception {

        }
    }
}
