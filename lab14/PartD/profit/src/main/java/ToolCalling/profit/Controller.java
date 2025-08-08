package ToolCalling.profit;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    ProfitRepository profitRepository;

    @GetMapping("/profits")
    public ProfitEntity getProductsInfo(@RequestParam("month") String month) {
        return profitRepository.findByMonth(month).get();
    }
}
