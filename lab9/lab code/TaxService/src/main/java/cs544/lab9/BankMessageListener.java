package cs544.lab9;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class BankMessageListener {
    @JmsListener(destination = "bank")
    public void receive(final String jsmMessageFromBank) {
        System.out.println("JSM message from the bank is: " + jsmMessageFromBank);
    }
}