package by.fin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private String result;
    private boolean isCurrencyTypesLoaded;

    @Autowired
    CurrencyTypesController currencyTypesController;
    @GetMapping("/")
    public String homePage(){
        if (isCurrencyTypesLoaded == false){
            System.out.println(currencyTypesController.getCurrencyTypes());
            isCurrencyTypesLoaded = true;
        }

        return "home";
    }

}
