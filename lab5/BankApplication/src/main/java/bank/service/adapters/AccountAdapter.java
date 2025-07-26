package bank.service.adapters;

import bank.domain.Account;
import bank.service.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter {
    public static AccountDTO getAccountDTOFromAccount(Account customer) {
        return null;
    }
    public static List<AccountDTO> getAccountDTOsFromAccounts(List<Account> accounts) {
        List<AccountDTO> accountDTOs = new ArrayList<>();

        for (Account account : accounts) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setCustomerName(account.getCustomer().getName());
            accountDTO.setEntryList(account.getEntryList());
            accountDTO.setAccountnumber(account.getAccountnumber());
            accountDTOs.add(accountDTO);
        }

        return accountDTOs;
    }
}
