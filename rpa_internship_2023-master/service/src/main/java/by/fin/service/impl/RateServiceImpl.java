package by.fin.service.impl;

import by.fin.module.CurrencyRepository;
import by.fin.module.RateRepository;
import by.fin.module.WeekendsRepository;
import by.fin.module.entity.Rate;
import by.fin.module.entity.Weekend;
import by.fin.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;
    private final WeekendsRepository weekendsRepository;

    private final CurrencyRepository currencyRepository;

    @Override
    public Rate getByIdAndDate(Long id, Date date) {
        return null;
    }

    @Override
    public double getGeometricMean(long id, int month) {
        List<Weekend> weekends = weekendsRepository.findAll();

        int iterator = 0;
        double geometricMean = 1;

        for (Weekend weekend: weekends) {

            if ((weekend.getCalendarDate().getMonth() == month) && (!weekend.isDayOff())){

                Rate rate = rateRepository.getRateByCurIDAndDate(id, weekend.getCalendarDate());
                geometricMean*=rate.getCurOfficialRate();

                iterator++;

            }
        }

        if (iterator !=0 ) {
            geometricMean = Math.pow(geometricMean, 1.0/iterator);
        }

        return geometricMean;
    }

    @Override
    public void addRates(List<Rate> rates) {
        rateRepository.saveAll(rates);
    }

    @Override
    public List<Rate> getByCurId(long curId) {
        return rateRepository.getByCurID(curId);
    }

    @Override
    public Set<String> getSavedCurrencies() {
        Set<String> savedCurrencies = new HashSet<>();
        Set<Long> curId = new HashSet<>();
        List<Rate> rates = rateRepository.findAll();
        rates.forEach(value -> curId.add(value.getCurID()));
        curId.forEach(value -> savedCurrencies.add(currencyRepository.getById(value).getCurAbbreviation()));
        return savedCurrencies;
    }
}
