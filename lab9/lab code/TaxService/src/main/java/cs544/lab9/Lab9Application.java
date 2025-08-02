package cs544.lab9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class Lab9Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab9Application.class, args);
	}


}
