package jms;

public class Calculator {
    private Double value;
    private String operator;
    public Calculator(Double value, String operator) {}

    public Calculator() {

    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "value=" + value +
                ", operator='" + operator + '\'' +
                '}';
    }
}
