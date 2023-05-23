package by.fin.module;

import by.fin.module.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    Rate getRateByCurIDAndDate(Long id, Date date);

    List<Rate> getByCurID(long curId);

    Rate getByDate(Date date);

}
