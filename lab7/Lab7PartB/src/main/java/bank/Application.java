package bank;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import bank.service.BankService;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	BankService bankService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		bankService.performTransaction(12, "Jack Bauer", "jbauer@yahoo.com","1223");
		bankService.performTransaction(14, "Frank Brown", "frankbrown@gmail.com","1248");
	}
}
