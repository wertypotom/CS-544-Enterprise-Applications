package cs544.lab14;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;

public class AIRequestLogInterceptor implements CallAdvisor {
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        // Log request details
        System.out.println("------💥 AI REQUEST: " + chatClientRequest);

        // Make the actual call
        ChatClientResponse response = callAdvisorChain.nextCall(chatClientRequest);

        // Log response details
        System.out.println("------👨🏼‍💻 AI RESPONSE: " + response);

        return response;
    }

    @Override
    public String getName() {
        return "AIRequestLogAdvisor";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
