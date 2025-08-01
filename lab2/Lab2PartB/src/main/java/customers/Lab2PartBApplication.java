package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab2PartBApplication implements CommandLineRunner {
	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(Lab2PartBApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerService.addCustomer("Alex", "alex@mail.com", "alex street 12", "Taraz", "02556");
	}
}
