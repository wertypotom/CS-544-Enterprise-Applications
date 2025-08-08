package cs544.lab14;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private ChatClient chatClient;

    @GetMapping("/healthcare")
    public ResponseEntity healthcare(@RequestParam("message") String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .entity(ResponseEntity.class);
    }
}

