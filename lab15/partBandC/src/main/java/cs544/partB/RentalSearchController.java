package cs544.partB;

import org.springframework.ai.document.Document;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/search")
public class RentalSearchController {

    private final RentalSearchService service;

    public RentalSearchController(RentalSearchService service) {
        this.service = service;
    }

    @GetMapping
    public List<Map<String, Object>> search(@RequestParam String query, @RequestParam(defaultValue = "3") int k) {
        List<Document> results = service.search(query, k);

        return results.stream().map(d -> Map.of(
                "text", d.getText(),
                "metadata", d.getMetadata()
        )).toList();
    }
}
