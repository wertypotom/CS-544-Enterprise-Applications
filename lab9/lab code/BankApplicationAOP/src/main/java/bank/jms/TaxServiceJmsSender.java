package bank.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableJms
public class TaxServiceJmsSender implements IJMSSender{
    @Autowired
    JmsTemplate jmsTemplate;

    public void sendJMSMessage (String text){
        jmsTemplate.convertAndSend("bank", "JMSSender: sending JMS message ="+text);
    }

}
