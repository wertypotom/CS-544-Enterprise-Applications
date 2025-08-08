package cs544.lab14;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab14Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab14Application.class, args);
	}

	@Bean
	public ChatClient chatClient(ChatModel chatModel, ChatMemory chatMemory) {
		return ChatClient.builder(chatModel)
				.defaultAdvisors(
						MessageChatMemoryAdvisor.builder(chatMemory).build(),
						new AIRequestLogInterceptor()
				)
				.build();
	}
}
