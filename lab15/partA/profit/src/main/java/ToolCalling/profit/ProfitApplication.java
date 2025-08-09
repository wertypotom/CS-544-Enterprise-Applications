package ToolCalling.profit;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProfitApplication implements CommandLineRunner {
	@Autowired
	private ProfitRepository profitRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProfitApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider tools(ProfitService profitService) {
		return MethodToolCallbackProvider.builder().toolObjects(profitService).build();
	}

	@Override
	public void run(String... args) throws Exception {
		ProfitEntity profitEntity = new ProfitEntity("January", 200.00);
		profitRepository.save(profitEntity);
		ProfitEntity profitEntity2 = new ProfitEntity("February", 140.00);
		profitRepository.save(profitEntity2);
		ProfitEntity profitEntity3 = new ProfitEntity("March", 150.00);
		profitRepository.save(profitEntity3);
		ProfitEntity profitEntity4 = new ProfitEntity("April", 180.00);
		profitRepository.save(profitEntity4);
	}
}
