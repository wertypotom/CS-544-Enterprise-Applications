package bank.service;

import bank.dao.TraceDAO;
import bank.domain.TraceRecord;
import bank.events.AccountEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountEventListener {
    @Autowired
    private TraceDAO traceDAO;

    @EventListener
    public void listenToCustomersOperation(AccountEvent event) {
        TraceRecord tr = new TraceRecord();
        tr.setAccountNumber(event.getAccountNumber());
        tr.setOperation(event.getOperation());
        tr.setAmount(event.getAmount());
        tr.setDate(new Date());
        traceDAO.save(tr);
    }
}
