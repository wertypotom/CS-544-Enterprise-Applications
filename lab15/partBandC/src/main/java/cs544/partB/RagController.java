package cs544.partB;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ask")
public class RagController {
    @Autowired
    RentalSearchService rentalSearchService;

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RagController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                    You answer questions about rental housing using only the provided context.
                    If context is missing, say you don't have enough information.
                    """)
                .build();
        this.vectorStore = vectorStore;
    }

    @GetMapping
    public RagAnswer ask(@RequestParam("q") String q,
                         @RequestParam(value = "k", defaultValue = "4") int k) {

        // 1) Retrieve context manually so we can return it to the client
        var docs = rentalSearchService.search(q, k);
        var contextTexts = docs.stream().map(Document::getText).toList();

        // 2) Let the advisor add the context to the prompt for the LLM
        String answer =  chatClient.prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .user(q)
                .call()
                .content();


        return new RagAnswer(answer, contextTexts);
    }
}
