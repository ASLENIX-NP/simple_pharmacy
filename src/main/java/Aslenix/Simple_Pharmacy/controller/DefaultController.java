package Aslenix.Simple_Pharmacy.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/api/defaultMessage")
    public String getDefaultMessage() {
        return "Welcome to the Simple Pharmacy API";
    }
}
