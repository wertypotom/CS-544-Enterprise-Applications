package bank.service.dto;

import bank.domain.AccountEntry;

import java.util.List;

public class AccountDTO {
    private String customerName;
    private List<AccountEntry> entryList;
    private Long accountnumber;

    public Long getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(Long accountnumber) {
        this.accountnumber = accountnumber;
    }

    public List<AccountEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<AccountEntry> entryList) {
        this.entryList = entryList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getBalance() {
        double balance=0;
        for (AccountEntry entry : entryList) {
            balance+=entry.getAmount();
        }
        return balance;
    }
}
