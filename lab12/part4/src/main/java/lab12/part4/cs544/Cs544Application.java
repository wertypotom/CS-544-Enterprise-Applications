package lab12.part4.cs544;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class Cs544Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Cs544Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RestClient restClient = RestClient.builder()
				.baseUrl("http://localhost:8080")
				.defaultHeaders(headers -> headers.setBasicAuth("bob", "bob"))
				.build();

		// get shop text
		String shopText = restClient.get()
				.uri("/shop")
				.retrieve()
				.body(String.class);
		System.out.println("Bob: " + shopText);

		// get order text
		String ordersText = restClient.get()
				.uri("/orders")
				.retrieve()
				.body(String.class);
		System.out.println("Bob: " + ordersText);

		RestClient secondRestClient = RestClient.builder()
				.baseUrl("http://localhost:8080")
				.defaultHeaders(headers -> headers.setBasicAuth("mary", "mary"))
				.build();

		// get shop text
		String secondShopText = secondRestClient.get()
				.uri("/shop")
				.retrieve()
				.body(String.class);
		System.out.println("Mary: " + secondShopText);

		// get order text
		String secondOrdersText = secondRestClient.get()
				.uri("/orders")
				.retrieve()
				.body(String.class);
		System.out.println("Mary: " + secondOrdersText);

		// get order text
		String paymentText = secondRestClient.get()
				.uri("/payments")
				.retrieve()
				.body(String.class);
		System.out.println("Mary: " + paymentText);
	}
}
