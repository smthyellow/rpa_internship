package by.fin.service.impl;

import by.fin.module.CurrencyRepository;
import by.fin.module.entity.Currency;
import by.fin.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public void addCurrencies(List<Currency> currencies) {
        currencyRepository.saveAll(currencies);
    }

    @Override
    public List<String> getAbbreviations() {

        List<Currency> currencies = currencyRepository.findAll();
        List<String> currencyAbbreviations = new ArrayList<>();

        currencies.forEach(value -> currencyAbbreviations.add(value.getCurAbbreviation()));

        return currencyAbbreviations;
    }

    @Override
    public long getIdByAbbreviation(String abbreviation) {

        List<Currency> currencies = currencyRepository.getByCurAbbreviation(abbreviation);
        long id = currencies.get(currencies.size()-1).getCurID();

        return id;
    }
}
