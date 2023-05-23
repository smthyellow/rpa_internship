package by.fin.module;

import by.fin.module.entity.Currency;
import by.fin.module.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long>{
    List<Currency> getByCurAbbreviation(String abbreviation);

}
