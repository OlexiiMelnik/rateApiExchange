package exchange.app.ratesapi.service;

import exchange.app.ratesapi.dto.external.ApiExchangeRateMono;
import exchange.app.ratesapi.dto.mapper.ExchangeRateMonoMapper;
import exchange.app.ratesapi.model.ExchangeRate;
import exchange.app.ratesapi.repository.ExchangeRateRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExternalDataSyncMonoServiceImpl implements ExternalDataSyncService {
    private static final String URL = "https://api.monobank.ua/bank/currency";
    private final HttpClient httpClient;
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateMonoMapper mapper;

    @Override
    public void syncExternalCharacters() {
        ApiExchangeRateMono[] apiS =
                httpClient.get(URL, ApiExchangeRateMono[].class);
        List<ApiExchangeRateMono> results = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            results.add(apiS[i]);
        }
        List<ExchangeRate> collect = results.stream()
                .map(mapper::parseApiExchangeRateMono)
                .collect(Collectors.toList());
        exchangeRateRepository.saveAll(collect);
    }
}
