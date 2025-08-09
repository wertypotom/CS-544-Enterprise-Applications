package cs544.partB;

import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ai.document.Document;

import java.util.List;

@Service
public class RentalSearchService {
    private final VectorStore vectorStore;

    public RentalSearchService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public List<Document> search(String query, int k) {
        var req = SearchRequest.builder()
                .query(query)
                .topK(k)
                .build();
        return vectorStore.similaritySearch(req);
    }
}
