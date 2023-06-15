package exchange.app.ratesapi.dto.mapper;

import exchange.app.ratesapi.dto.external.ApiExchangeRateMono;
import exchange.app.ratesapi.model.ExchangeRate;
import exchange.app.ratesapi.model.Source;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateMonoMapper {
    public ExchangeRate parseApiExchangeRateMono(ApiExchangeRateMono mono) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setBaseCurrency("UAH");
        if (mono.getCurrencyCodeA().equals("840")) {
            exchangeRate.setCurrencyDepended("USD");
        } else {
            exchangeRate.setCurrencyDepended("EUR");
        }

        BigDecimal rateBuy =
                BigDecimal.valueOf(mono.getRateBuy()).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal rateSell =
                BigDecimal.valueOf(mono.getRateSell()).setScale(2, RoundingMode.HALF_EVEN);

        exchangeRate.setRateBuy(rateBuy);
        exchangeRate.setRateSell(rateSell);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        exchangeRate.setLocalDate(formattedDateTime);
        exchangeRate.setSource(Source.MONO);
        return exchangeRate;
    }
}
