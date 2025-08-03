package bank.service;

import bank.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    @Autowired
    AppProperties appProperties;

    public void printProperties() {
        System.out.println(appProperties.getName());
        System.out.println(appProperties.getServer());
        System.out.println(appProperties.getUrl());
        System.out.println(appProperties.getVersion());
        System.out.println(appProperties.getUser().getCountries());
        System.out.println(appProperties.getUser().getFirstname());
        System.out.println(appProperties.getUser().getLastname());
        System.out.println(appProperties.getUser().getPassword());
        System.out.println(appProperties.getUser().getUsername());

    }
}
