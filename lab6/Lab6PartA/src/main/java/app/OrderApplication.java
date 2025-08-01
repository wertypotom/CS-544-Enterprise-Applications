package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class OrderApplication implements CommandLineRunner {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CDRepository cdRepository;
	@Autowired
	AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Order o1 = createOrder1();
		Order o2 = createOrder2();
		Order o3 = createOrder3();
		Order o4 = createOrder4();


		orderRepository.save(o1);
		orderRepository.save(o2);
		orderRepository.save(o3);
		orderRepository.save(o4);

		getAllCustomers();

		// Give all customers.
		orderRepository.findAll();
		// Give all CD’s from U2 with a price smaller than 10 euro
		cdRepository.findByArtistAndPriceLessThan("U2", 10.00);
		// Give all customers with zip code 2389HJ
		customerRepository.findByAddressZip("2389HJ");
		// Give all customers who ordered a DVD with the name Rocky3
		customerRepository.findByTheordersOrderlinesProductName("Rocky3");
		// Give all customers from the USA
		customerRepository.getAllCustomersFromCountry("USA");
		// Give all CD’s from a certain artist
		cdRepository.findByArtist("Bruno Mars");
		//Give the ordernumbers of all orders with status ‘closed’
		orderRepository.getOrderNumbersFromOrdersWithStatusClosed();
		// Give the first and lastnames of all customers who live in Amsterdam.
		customerRepository.getFirstnameAndLastnameByCity("Amsterdam");
		// Give the ordernumbers of all orders from customers who live in a certain city (city is a parameter)
		orderRepository.getOrderNumbersFromOrdersFromCity("Amsterdam");


		Specification<CD> cdWithArtist = CDSpecifications.isFromArtist("John");
		Specification<CD> cdWithPrice = CDSpecifications.withPriceLargerThan(10);
		cdRepository.findAll(Specification.where(cdWithArtist).and(cdWithPrice));
	}


	private void getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		System.out.println("------All customers-------------------");
		customers.stream().forEach(c -> System.out.println(c));
	}


	public static void printOrder(Order order){
		System.out.println("Order with orderNumber: "+order.getOrdernr());
		System.out.println("Order date: "+order.getDate());
		System.out.println("Order status: "+order.getStatus());
		Customer cust=order.getCustomer();
		System.out.println("Customer: "+cust.getFirstname()+" "+cust.getLastname());
		System.out.println("Address: "+cust.getAddress().getStreet()+" "+cust.getAddress().getCity()+cust.getAddress().getZip()+" "+cust.getAddress().getCountry());

		Iterator iter = order.getOrderlines().iterator();
		while (iter.hasNext()) {
			OrderLine orderline = (OrderLine) iter.next();
			System.out.println("Order line: quantity= " + orderline.getQuantity());
			System.out.println(orderline.getProduct());
		}
	}



	private static Order createOrder1() {
		Customer c1 = new Customer("Frank", "Brown");
		Address a1= new Address("Mainstreet 1", "New york", "43221", "USA");
		c1.setAddress(a1);
		Book book = new Book();
		book.setName("Hibernate 3");
		book.setDescription("Good book on Hibernate");
		book.setPrice(35.50);
		book.setTitle("Hibernate in action");
		OrderLine ol1= new OrderLine(2);
		ol1.setProduct(book);

		CD cd = new CD();
		cd.setName("The best of Queen");
		cd.setDescription("Album from 1995");
		cd.setPrice(12.98);
		cd.setArtist("Queen");
		OrderLine ol2= new OrderLine(4);
		ol2.setProduct(cd);

		Order o1 = new Order("234743","12/10/06","open");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);
		o1.setCustomer(c1);
		return o1;
	}

	private static Order createOrder2() {
		Customer c1 = new Customer("John", "Doe");
		Address a1= new Address("Hoofdstraat 1", "Amsterdam", "2318KL", "Netherlands");
		c1.setAddress(a1);
		Book book = new Book();
		book.setName("Spring 2");
		book.setDescription("Good book on Spring");
		book.setPrice(35.50);
		book.setTitle("Spring in action");
		OrderLine ol1= new OrderLine(2);
		ol1.setProduct(book);

		CD cd = new CD();
		cd.setName("The best of Queen");
		cd.setDescription("Album from 1995");
		cd.setPrice(12.98);
		cd.setArtist("Queen");
		OrderLine ol2= new OrderLine(1);
		ol2.setProduct(cd);

		CD cd2 = new CD();
		cd2.setName("The best of U2");
		cd2.setDescription("Album from 1995");
		cd2.setPrice(16.98);
		cd2.setArtist("U2");
		OrderLine ol3= new OrderLine(1);
		ol3.setProduct(cd2);

		CD cd3 = new CD();
		cd3.setName("U2 Live");
		cd3.setDescription("Album from 1998");
		cd3.setPrice(9.96);
		cd3.setArtist("U2");
		OrderLine ol4= new OrderLine(1);
		ol4.setProduct(cd3);

		DVD dvd = new DVD();
		dvd.setName("IL DIVO Live");
		dvd.setDescription("Live concert");
		dvd.setPrice(15.98);
		dvd.setGenre("POP");
		OrderLine ol5= new OrderLine(1);
		ol5.setProduct(dvd);

		Order o1 = new Order("231678","12/12/06","closed");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);
		o1.addOrderLine(ol3);
		o1.addOrderLine(ol4);
		o1.addOrderLine(ol5);
		o1.setCustomer(c1);
		return o1;
	}

	private static Order createOrder3() {
		Customer c1 = new Customer("Mary", "Johnson");
		Address a1= new Address("Mainstreet 1", "Boston", "82554", "USA");
		c1.setAddress(a1);
		Book book = new Book();
		book.setName("J2ee design");
		book.setDescription("Good book on J2EE");
		book.setPrice(49.00);
		book.setTitle("Enterprise application development with J2EE");
		OrderLine ol1= new OrderLine(2);
		ol1.setProduct(book);



		DVD dvd = new DVD();
		dvd.setName("IL DIVO Live");
		dvd.setDescription("Live concert");
		dvd.setPrice(15.98);
		dvd.setGenre("POP");
		OrderLine ol3= new OrderLine(1);
		ol3.setProduct(dvd);

		Order o1 = new Order("555743","12/10/06","open");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol3);
		o1.setCustomer(c1);
		return o1;
	}

	private static Order createOrder4() {
		Customer c1 = new Customer("Sue", "Moore");
		Address a1= new Address("Rijnstraat 105", "Amsterdam", "2389HJ", "Netherlands");
		c1.setAddress(a1);
		Book book = new Book();
		book.setName("J2ee design");
		book.setDescription("Good book on J2EE");
		book.setPrice(49.00);
		book.setTitle("Enterprise application development with J2EE");
		OrderLine ol1= new OrderLine(2);
		ol1.setProduct(book);



		DVD dvd = new DVD();
		dvd.setName("Rocky3");
		dvd.setDescription("Boxing movie");
		dvd.setPrice(8.98);
		dvd.setGenre("drama");
		OrderLine ol3= new OrderLine(1);
		ol3.setProduct(dvd);

		Order o1 = new Order("555744","12/10/06","open");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol3);
		o1.setCustomer(c1);
		return o1;
	}

}

