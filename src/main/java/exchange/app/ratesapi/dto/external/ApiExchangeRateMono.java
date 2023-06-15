package exchange.app.ratesapi.dto.external;

import lombok.Data;

@Data
public class ApiExchangeRateMono {
    private String currencyCodeB;
    private String currencyCodeA;
    private double rateBuy;
    private double rateSell;
    private String date;
}
