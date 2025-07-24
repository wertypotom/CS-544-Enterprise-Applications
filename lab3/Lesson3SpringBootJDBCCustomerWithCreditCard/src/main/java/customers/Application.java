package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private ProductDAO productDao;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerDao.clearDB();
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		System.out.println(customerDao.getCustomer(101));
		System.out.println(customerDao.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerDao.getAllCustomers());

		Product product1 = new Product(1001L, "499.99", "Laptop");
		Supplier supplier1 = new Supplier("Acme Supplies", "555-1234");
		product1.setSupplier(supplier1);
		productDao.save(product1);

		Product product2 = new Product(1002L, "199.50", "Smartphone");
		Supplier supplier2 = new Supplier("Mobile Depot", "555-9876");
		product2.setSupplier(supplier2);
		productDao.save(product2);

		Product product3 = new Product(1003L, "299.99", "Tablet");
		Supplier supplier3 = new Supplier("Gadget World", "555-4567");
		product3.setSupplier(supplier3);
		productDao.save(product3);

		System.out.println("-----------All Products ----------------");
		for (Product p : productDao.getAllProducts()) {
			System.out.println("Product: " + p.getName() + " | Price: " + p.getPrice()
					+ " | Supplier: " + p.getSupplier().getName() + " | Phone: " + p.getSupplier().getPhone());
		}

	}
}
