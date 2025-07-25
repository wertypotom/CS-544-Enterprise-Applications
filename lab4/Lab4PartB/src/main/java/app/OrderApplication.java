package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.AddressRepository;
import repositories.OrderRepository;
import repositories.ProductRepository;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class OrderApplication implements CommandLineRunner{
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product();
		product.setName("Hibernate 3");
		product.setDescription("Good book on Hibernate");
		product.setPrice(35.50);
		OrderLine ol1 = new OrderLine(2, product);

		Product product2 = new Product();
		product2.setName("The best of Queen");
		product2.setDescription("Album from 1995");
		product2.setPrice(12.98);
		OrderLine ol2 = new OrderLine(4, product2);

		productRepository.save(product);
		productRepository.save(product2);

		Order o1 = new Order("234743", "12/10/06", "open");
		Order o2 = new Order("098090", "12/11/12", "closed");


		Address address = new Address("Mainstreet 1", "New york", "43221", "United States");
		addressRepository.save(address);

		Customer c1 = new Customer("Frank", "Brown", address);
		Customer c2 = new Customer("Kelvin", "Klein", address);

		// one order can have many orderlines
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);

		// one customer may have many orders
		c1.addOrder(o1);
		c1.addOrder(o2);

		// one order belongs to one customer only
		o1.setCustomer(c1);
		o2.setCustomer(c2);

		orderRepository.save(o1);
		orderRepository.save(o2);

		orderRepository.findAll().forEach(System.out::println);
	}

}
