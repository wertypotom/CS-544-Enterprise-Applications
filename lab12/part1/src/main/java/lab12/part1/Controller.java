package lab12.part1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {
    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }
    @GetMapping("/orders")
    @PreAuthorize("hasRole('finance') or hasRole('sales')")
    public String orders() {
        return "orders";
    }
    @GetMapping("/payments")
    @PreAuthorize("hasRole('finance')")
    public String payments() {
        return "payments";
    }
}