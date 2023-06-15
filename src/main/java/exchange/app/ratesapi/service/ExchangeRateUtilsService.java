package exchange.app.ratesapi.service;

import exchange.app.ratesapi.model.ExchangeRate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateUtilsService {

    public Map<String, String> calculateAverageRates(List<ExchangeRate> exchangeRates) {
        DecimalFormat df = new DecimalFormat("#0.00");

        Map<String, String> averageRates = new LinkedHashMap<>();
        averageRates.put("average Buy Rate for USD",
                calculateAverageBuyRate(exchangeRates, "USD").toString());
        averageRates.put("average Sale Rate for USD",
                calculateAverageSaleRate(exchangeRates, "USD").toString());
        averageRates.put("average Buy Rate for EUR",
                calculateAverageBuyRate(exchangeRates, "EUR").toString());
        averageRates.put("average Sale Rate for EUR",
                calculateAverageSaleRate(exchangeRates, "EUR").toString());

        return averageRates;
    }

    public static BigDecimal calculateAverageBuyRate(
            List<ExchangeRate> exchangeRates, String currency) {
        BigDecimal sum = exchangeRates.stream()
                .filter(rate -> rate.getCurrencyDepended().equals(currency))
                .map(ExchangeRate::getRateBuy)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        int count = (int) exchangeRates.stream()
                .filter(rate -> rate.getCurrencyDepended().equals(currency))
                .count();
        return sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal calculateAverageSaleRate(
            List<ExchangeRate> exchangeRates, String currency) {
        BigDecimal sum = exchangeRates.stream()
                .filter(rate -> rate.getCurrencyDepended().equals(currency))
                .map(ExchangeRate::getRateSell)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        int count = (int) exchangeRates.stream()
                .filter(rate -> rate.getCurrencyDepended().equals(currency))
                .count();
        return sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_EVEN);
    }
}
