package exchange.app.ratesapi.service;

import exchange.app.ratesapi.dto.external.ApiExchangeRatePrivat;
import exchange.app.ratesapi.dto.mapper.ExchangeRatePrivatMapper;
import exchange.app.ratesapi.model.ExchangeRate;
import exchange.app.ratesapi.repository.ExchangeRateRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalDataSyncPrivatServiceImpl implements ExternalDataSyncService {
    private static final String URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";
    private final HttpClient httpClient;
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRatePrivatMapper mapper;

    @Override
    public void syncExternalCharacters() {
        ApiExchangeRatePrivat[] apiExchangeRatePrivats
                = httpClient.get(URL, ApiExchangeRatePrivat[].class);
        List<ApiExchangeRatePrivat> results = new ArrayList<>();
        results.addAll(Arrays.asList(apiExchangeRatePrivats));
        List<ExchangeRate> collect = results.stream()
                .map(mapper::parseApiExchangeRatePrivat)
                .collect(Collectors.toList());
        Collections.reverse(collect);
        exchangeRateRepository.saveAll(collect);
    }
}
