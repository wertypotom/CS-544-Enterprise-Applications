package PromptStuffing.partB;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    ProductRepository productRepository;

    ChatClient chatClient;

    public Controller(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/products")
    public String getProductsInfo(@RequestParam("message") String message) {
        List<ProductEntity> products = productRepository.findAll();
        return chatClient
                .prompt()
                .user(message)
                .system("To answer the user prompt you need to get data from here: " + products.toString())
                .call()
                .content();

    }
}
