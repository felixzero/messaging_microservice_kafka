package com.sipios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

/**
 * The Sprint Boot Application main Class
 *
 * We exclude ErrorMvcAutoConfiguration to hide the default error page that is shown to users.
 * It is not useful for an REST API
 * @see <a href="https://stackoverflow.com/questions/38747548/spring-boot-disable-error-mapping">https://stackoverflow.com/questions/38747548/spring-boot-disable-error-mapping</a>
 */
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
