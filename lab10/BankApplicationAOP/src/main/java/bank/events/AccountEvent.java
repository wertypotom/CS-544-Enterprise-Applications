package bank.events;

public class AccountEvent {
    private String operation;
    private Double amount;
    private Long accountNumber;

    public AccountEvent(String operation, Double amount, Long accountNumber) {
        this.operation = operation;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
