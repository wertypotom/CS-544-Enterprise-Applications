package bank.controller;

import bank.service.AccountDTO;
import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/account")
public class BankController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody CreateAccountRequest account){
        AccountDTO acc = accountService.createAccount(account.getAccountNumber(), account.getCustomerName());

        return new ResponseEntity<>(acc, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{accountNumber}/deposit")
    public ResponseEntity deposit(@PathVariable("accountNumber") Long accountNumber, @RequestBody TransferMoneyRequest money, @RequestParam("type") String type){
        if (type.equals("euro")) {
            accountService.depositEuros(accountNumber, money.getAmount());
        } else {
            accountService.deposit(accountNumber, money.getAmount());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{accountNumber}/withdraw")
    public ResponseEntity withdraw(@PathVariable("accountNumber") Long accountNumber, @RequestBody TransferMoneyRequest money, @RequestParam("type") String type){
        if (type.equals("euro")) {
            accountService.withdrawEuros(accountNumber, money.getAmount());
        } else {
            accountService.withdraw(accountNumber, money.getAmount());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{accountFrom}/transfer/{accountTo}")
    public ResponseEntity transfer(@PathVariable("accountFrom") Long accountFrom, @PathVariable("accountTo") Long accountTo, @RequestBody TransferMoneyRequest money){
        accountService.transferFunds(accountFrom, accountTo, money.getAmount(), money.getDescription());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<AccountDTO>> getAllAccounts() {
        Collection<AccountDTO> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}