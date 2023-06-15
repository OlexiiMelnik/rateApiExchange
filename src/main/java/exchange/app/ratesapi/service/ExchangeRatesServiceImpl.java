package exchange.app.ratesapi.service;

import exchange.app.ratesapi.model.ExchangeRate;
import exchange.app.ratesapi.repository.ExchangeRateRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRatesServiceImpl implements ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public Optional<ExchangeRate> findById(Long id) {
        return exchangeRateRepository.findById(id);
    }

    @Override
    public List<ExchangeRate> findAll() {
        return exchangeRateRepository.findAll();
    }

    @Override
    public List<ExchangeRate> findAllByLocalDateBetween(String dateFrom, String dateTo) {
        return exchangeRateRepository.findAllByLocalDateBetween(dateFrom, dateTo);
    }

    @Override
    public void delete(Long id) {
        exchangeRateRepository.deleteById(id);
    }

    @Override
    public void deleteExchangeRateByLocalDateBetween(String dateFrom, String dateTo) {
        exchangeRateRepository.deleteExchangeRateByLocalDateBetween(dateFrom, dateTo);
    }

}
