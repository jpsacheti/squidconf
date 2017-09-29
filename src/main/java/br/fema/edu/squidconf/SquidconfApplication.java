package br.fema.edu.squidconf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SquidconfApplication {
	public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(SquidconfApplication.class, args);
    }
}
