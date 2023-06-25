package exchange.app.ratesapi.service;

import exchange.app.ratesapi.model.ExchangeRate;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateService {
    Optional<ExchangeRate> findById(Long id);

    List<ExchangeRate> findAll();

    List<ExchangeRate> findAllByLocalDateBetween(String dateFrom, String dateTo);

    void delete(Long id);
}
