package by.fin.web.controller;

import by.fin.module.entity.Rate;
import by.fin.service.CurrencyService;
import by.fin.service.RateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/currency-rates")
public class CurrencyRatesController {

    private final RateService rateService;
    private final CurrencyService currencyService;

    @GetMapping
    public String currencyRates(Model model){
       Set<String> savedCurrencies = rateService.getSavedCurrencies();
       model.addAttribute("savedRates", savedCurrencies);
        return "currencyRates";
    }

    @PostMapping
    public String processDailyRates(@Valid String abbreviation, Model model){
        long id = currencyService.getIdByAbbreviation(abbreviation);

        List<Rate> rates = rateService.getByCurId(id);

        model.addAttribute("rates", rates);
        return "currencyRates";
    }
}

