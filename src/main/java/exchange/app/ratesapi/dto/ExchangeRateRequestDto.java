package exchange.app.ratesapi.dto;

import exchange.app.ratesapi.model.Source;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateRequestDto {
    private String currencyDepended;
    private String baseCurrency;
    private BigDecimal rateSell;
    private BigDecimal rateBuy;
    private Source source;
    private String localDate;
}
