package by.fin.service;

import by.fin.module.entity.Rate;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface RateService {

    Rate getByIdAndDate(Long id, Date date);

    double getGeometricMean(long id, int month);

    void addRates(List<Rate> rates);

    List<Rate> getByCurId(long curId);

    Set<String> getSavedCurrencies();



}
