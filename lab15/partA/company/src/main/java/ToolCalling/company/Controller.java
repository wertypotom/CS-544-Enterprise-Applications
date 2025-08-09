package ToolCalling.company;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    ConverterService converterService;

    private final ChatClient chatClient;

    public Controller(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools) {
        this.chatClient = chatClientBuilder
                .defaultSystem("Please prioritise context information for answering questions")
                .defaultToolCallbacks(tools)
                .build();
    }

    @GetMapping("/company")
    public String getProfitOfCompanyForMonth(@RequestParam("message") String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }
}
