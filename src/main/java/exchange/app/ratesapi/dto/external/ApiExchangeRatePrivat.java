package exchange.app.ratesapi.dto.external;

import lombok.Data;

@Data
public class ApiExchangeRatePrivat {
    private String base_ccy;
    private String ccy;
    private double buy;
    private double sale;
}
