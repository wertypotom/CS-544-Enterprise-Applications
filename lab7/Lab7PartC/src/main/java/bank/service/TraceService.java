package bank.service;

import bank.domain.Trace;
import bank.repositories.TraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TraceService {
    @Autowired
    private TraceRepository traceRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveTrace(String message) {
        Trace trace = new Trace(new Date(), message);
        traceRepository.save(trace);
    }
}
