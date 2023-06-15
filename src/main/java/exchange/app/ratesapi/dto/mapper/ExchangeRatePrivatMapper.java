package exchange.app.ratesapi.dto.mapper;

import exchange.app.ratesapi.dto.external.ApiExchangeRatePrivat;
import exchange.app.ratesapi.model.ExchangeRate;
import exchange.app.ratesapi.model.Source;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRatePrivatMapper {
    public ExchangeRate parseApiExchangeRatePrivat(ApiExchangeRatePrivat privat) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = now.format(formatter);

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setBaseCurrency(privat.getBase_ccy());
        exchangeRate.setCurrencyDepended(privat.getCcy());

        BigDecimal rateBuy =
                BigDecimal.valueOf(privat.getBuy()).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal rateSell =
                BigDecimal.valueOf(privat.getSale()).setScale(2, RoundingMode.HALF_EVEN);

        exchangeRate.setRateBuy(rateBuy);
        exchangeRate.setRateSell(rateSell);

        exchangeRate.setLocalDate(formattedDateTime);
        exchangeRate.setSource(Source.PRIVAT);
        return exchangeRate;
    }
}
