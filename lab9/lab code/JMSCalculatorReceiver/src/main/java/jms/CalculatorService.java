package jms;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    private Double result = 0.0;

    public CalculatorService() {}

    public CalculatorService(Double result) {
        this.result = result;
    }

    public void calculate(String operation, Double value) {
        if (operation.equals("+")) {
            result = result + value;
        } else if (operation.equals("-")) {
            result = result - value;
        } else if (operation.equals("*")) {
            result = result * value;
        } else if (operation.equals("/")) {
            result = result / value;
        } else {
            throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }

    public Double getResult() {
        return result;
    }
}
