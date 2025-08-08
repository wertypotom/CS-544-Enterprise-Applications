package ToolCalling.currencyConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    CurrencyConverter converter;

    @PostMapping("/to-euros")
    public ConverterResponse dollarsToEuros (@RequestBody Double amount){
        return new ConverterResponse(converter.dollarsToEuros(amount));
    }
}
