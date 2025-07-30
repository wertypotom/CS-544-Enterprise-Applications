package bank.controller;

public class TransferMoneyRequest {
    private double amount;
    private String description;

    public TransferMoneyRequest() {
    }

    public TransferMoneyRequest(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
