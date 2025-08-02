package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CalculatorMessageListener {
    @Autowired
    CalculatorService calculatorService;

    @JmsListener(destination = "calculator")
    public void receiveMessage(final String calculatorAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Calculator calculator = objectMapper.readValue(calculatorAsString, Calculator.class);
            calculatorService.calculate(calculator.getOperator(), calculator.getValue());
            System.out.println("Result: " + calculatorService.getResult());
        } catch (IOException e) {
            System.out.println("JMS receiver 1: Cannot convert : " + calculatorAsString+" to a Calculator object");
        }
    }
}