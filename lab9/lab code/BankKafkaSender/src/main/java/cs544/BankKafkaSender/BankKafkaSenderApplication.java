package cs544.BankKafkaSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class BankKafkaSenderApplication implements CommandLineRunner {
	@Autowired
	private BankSender bankSender;

	public static void main(String[] args) {
		SpringApplication.run(BankKafkaSenderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		bankSender.send("bank_topic", "createAnAccount");
		bankSender.send("bank_topic", "depositMoney");
		bankSender.send("bank_topic", "withdrawMoney");
	}
}
