package by.fin.service;

import by.fin.module.entity.Currency;
import java.util.HashMap;
import java.util.List;

public interface CurrencyService {
    void addCurrencies(List<Currency> currencies);

    List<String> getAbbreviations();

    long getIdByAbbreviation(String abbreviation);
}
