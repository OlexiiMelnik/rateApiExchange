package exchange.app.ratesapi.repository;

import exchange.app.ratesapi.model.ExchangeRate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    List<ExchangeRate> findAllByLocalDateBetween(String dateFrom, String dateTo);
}
