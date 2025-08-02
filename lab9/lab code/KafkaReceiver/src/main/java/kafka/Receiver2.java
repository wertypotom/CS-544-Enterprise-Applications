package kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver2 {

    @KafkaListener(topics = {"topicA"})
    public void receive(@Payload String message) {
        System.out.println("Receiver 2 received message= "+ message);
    }

}