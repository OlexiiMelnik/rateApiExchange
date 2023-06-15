package exchange.app.ratesapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "exchange_rates")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "baseCurrency", nullable = false)
    private String baseCurrency;
    @Column(name = "currencyDepended", nullable = false)
    private String currencyDepended;
    @Column(name = "rateSell", nullable = false)
    private BigDecimal rateBuy;
    @Column(name = "rateBuy", nullable = false)
    private BigDecimal rateSell;
    @Column(name = "source", nullable = false)
    @Enumerated(EnumType.STRING)
    private Source source;
    @Column(name = "date", nullable = false)
    private String localDate;
}
