package by.fin.web.controller;

import by.fin.service.CurrencyService;
import by.fin.service.RateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/geometric-mean")
public class GeometricMeanController {

    private final RateService rateService;
    private final CurrencyService currencyService;

    @GetMapping
    public String currencyRates(Model model){
        Set<String> savedCurrencies = rateService.getSavedCurrencies();
        model.addAttribute("savedRates", savedCurrencies);

        return "geometricMean";
    }

    @PostMapping
    public String processDailyRates(@Valid String month, @Valid String abbreviation, Model model){

        int monthNumber = Integer.parseInt(month);

        long id = currencyService.getIdByAbbreviation(abbreviation);

        double geometricMean = rateService.getGeometricMean(id, monthNumber);

        String geometricMeanText = "Geometric mean is " + geometricMean;

        model.addAttribute("geometricMeanText", geometricMeanText);

        return "geometricMean";
    }
}
