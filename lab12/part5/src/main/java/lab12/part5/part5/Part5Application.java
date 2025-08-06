package lab12.part5.part5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;
import org.springframework.http.MediaType;

import java.util.Map;

@SpringBootApplication
public class Part5Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Part5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RestClient authClient = RestClient.builder()
				.baseUrl("http://localhost:8080/auth")
				.build();

		Map<String, String> adminLogin = Map.of(
				"email", "admin@admin.com",
				"password", "password"
		);
		JwtAuthenticationResponse adminToken = authClient.post()
				.uri("/signin")
				.contentType(MediaType.APPLICATION_JSON)
				.body(adminLogin)
				.retrieve()
				.body(JwtAuthenticationResponse.class);

		System.out.println("Admin JWT: " + adminToken.token());

		RestClient adminClient = RestClient.builder()
				.baseUrl("http://localhost:8080/api")
				.defaultHeaders(h -> h.setBearerAuth(adminToken.token()))
				.build();

		callEndpoints(adminClient, "Admin");

		Map<String, String> signupUser = Map.of(
				"firstName", "John",
				"lastName", "Doe",
				"email", "user1@test.com",
				"password", "user123"
		);

		try {
			authClient.post()
					.uri("/signup")
					.contentType(MediaType.APPLICATION_JSON)
					.body(signupUser)
					.retrieve()
					.body(JwtAuthenticationResponse.class);
			System.out.println("User signup successful.");
		} catch (Exception e) {
			System.out.println("User signup failed or already exists: " + e.getMessage());
		}

		Map<String, String> userLogin = Map.of(
				"email", "user1@test.com",
				"password", "user123"
		);
		JwtAuthenticationResponse userToken = authClient.post()
				.uri("/signin")
				.contentType(MediaType.APPLICATION_JSON)
				.body(userLogin)
				.retrieve()
				.body(JwtAuthenticationResponse.class);

		System.out.println("User JWT: " + userToken.token());

		RestClient userClient = RestClient.builder()
				.baseUrl("http://localhost:8080/api")
				.defaultHeaders(h -> h.setBearerAuth(userToken.token()))
				.build();

		callEndpoints(userClient, "User");
	}

	private void callEndpoints(RestClient client, String userType) {
		try {
			String all = client.get().uri("/all").retrieve().body(String.class);
			System.out.println(userType + " → /api/all: " + all);
		} catch (Exception e) {
			System.out.println(userType + " → /api/all failed: " + e.getMessage());
		}

		try {
			String users = client.get().uri("/users").retrieve().body(String.class);
			System.out.println(userType + " → /api/users: " + users);
		} catch (Exception e) {
			System.out.println(userType + " → /api/users failed: " + e.getMessage());
		}

		try {
			String admins = client.get().uri("/admins").retrieve().body(String.class);
			System.out.println(userType + " → /api/admins: " + admins);
		} catch (Exception e) {
			System.out.println(userType + " → /api/admins failed: " + e.getMessage());
		}
	}

	public record JwtAuthenticationResponse(String token) {
	}
}
