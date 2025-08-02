package jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
public class SpringJmsPersonSenderApplication implements CommandLineRunner {
	@Autowired
	JmsTemplate jmsTemplate;


	public static void main(String[] args)  {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJmsPersonSenderApplication.class, args);
		context.close();
	}

	public void sendCommand(String operation, Double value) throws JsonProcessingException {
		Calculator calculator = new Calculator();
		ObjectMapper objectMapper = new ObjectMapper();

		calculator.setOperator(operation);
		calculator.setValue(value);
		String calculatorAsString = objectMapper.writeValueAsString(calculator);

		System.out.println("Sending a JMS message:" + calculatorAsString);
		jmsTemplate.convertAndSend("calculator",calculatorAsString);
	}

	@Override
	public void run(String... args) throws Exception {
		sendCommand("+", 7.0);
		sendCommand("-", 1.0);
	}
}
