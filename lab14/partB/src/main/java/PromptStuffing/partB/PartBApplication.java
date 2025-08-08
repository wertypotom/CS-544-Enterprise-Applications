package PromptStuffing.partB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PartBApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(PartBApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ProductEntity productEntity1 = new ProductEntity("Juice", 10.20);
		ProductEntity productEntity2 = new ProductEntity("Hat", 50.00);
		ProductEntity productEntity3 = new ProductEntity("Car", 12.12);
		ProductEntity productEntity4 = new ProductEntity("Doll", 5.10);

		productRepository.save(productEntity1);
		productRepository.save(productEntity2);
		productRepository.save(productEntity3);
		productRepository.save(productEntity4);
	}
}
