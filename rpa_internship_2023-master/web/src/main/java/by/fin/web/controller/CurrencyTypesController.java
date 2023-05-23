package by.fin.web.controller;

import by.fin.module.entity.Currency;
import by.fin.service.CurrencyService;
import by.fin.web.Util.APIConnector;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CurrencyTypesController {

    private final APIConnector apiConnector;

    @Autowired
    private final CurrencyService currencyService;

    private String inline = "";


    public String getCurrencyTypes(){

        String jsonStringForAll = "";

        try {
            URL url = new URL("https://api.nbrb.by/exrates/currencies/");

            jsonStringForAll = apiConnector.getJson(url);


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        List<Currency> currencies;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            currencies = objectMapper.readValue(jsonStringForAll, List.class);
            List<Currency> accountList = objectMapper.convertValue(
                    currencies,
                    new TypeReference<>() {
                    }
            );
            currencyService.addCurrencies(accountList);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "";
    }



}
