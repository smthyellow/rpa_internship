package by.fin.web.controller;

import by.fin.module.entity.Rate;
import by.fin.service.CurrencyService;
import by.fin.service.RateService;
import by.fin.web.Util.APIConnector;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/daily-rates")
public class DailyRatesController {

    private final APIConnector apiConnector;
    private final RateService rateService;
    private final CurrencyService currencyService;

    @GetMapping
    public String dailyCurrencies(Model model){

        List<String> currencyAbbreviations = currencyService.getAbbreviations();
        Set<String> abbreviations = new TreeSet<>();

        currencyAbbreviations.forEach(value -> abbreviations.add(value));

        model.addAttribute("currencyAbbreviations",  abbreviations);

        return "dailyRates";
    }

    @PostMapping
    public String processDailyRates(@Valid String abbreviation, @Valid String start, @Valid String end, Model model){

        long id = currencyService.getIdByAbbreviation(abbreviation);

        String jsonString;

            try {
                URL url = new URL("https://api.nbrb.by/exrates/rates/dynamics/" + id);
                URL finalUrl = UriComponentsBuilder.fromUriString(url.toString())
                        .queryParam("startdate", start + "T00:00:00")
                        .queryParam("enddate", end + "T00:00:00")
                        .build().toUri().toURL();

                jsonString = apiConnector.getJson(finalUrl);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            List<Rate> rates = objectMapper.readValue(jsonString, List.class);
            List<Rate> rates1 = objectMapper.convertValue(
                    rates,
                    new TypeReference<>() {
                    }
            );
            rateService.addRates(rates1);
            if (rates1.size() == 0){
                String errorMessage = "Sorry, the National Bank of the Republic of Belarus didn't provide rates for this currency. Try to choose another (GBP, USD, UAH or EUR, for example).";
                model.addAttribute("error", errorMessage);
            }
            model.addAttribute("rates", rates1);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "dailyRates";
    }
}
