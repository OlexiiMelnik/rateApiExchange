package exchange.app.ratesapi.dto.mapper;

import exchange.app.ratesapi.dto.ExchangeRateRequestDto;
import exchange.app.ratesapi.dto.ExchangeRateResponseDto;
import exchange.app.ratesapi.model.ExchangeRate;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateMapper {
    public ExchangeRateResponseDto toExchangeRateResponseD(ExchangeRate exchangeRate) {
        ExchangeRateResponseDto exchangeRateResponseDto =
                new ExchangeRateResponseDto();
        exchangeRateResponseDto.setId(exchangeRate.getId());
        exchangeRateResponseDto.setBaseCurrency(exchangeRate.getBaseCurrency());
        exchangeRateResponseDto.setCurrencyDepended(exchangeRate.getCurrencyDepended());
        exchangeRateResponseDto.setSource(exchangeRate.getSource());
        exchangeRateResponseDto.setRateBuy(exchangeRate.getRateBuy());
        exchangeRateResponseDto.setRateSell(exchangeRate.getRateSell());
        exchangeRateResponseDto.setLocalDate(exchangeRate.getLocalDate());
        return exchangeRateResponseDto;
    }

    public ExchangeRate toModel(ExchangeRateRequestDto exchangeRateRequestDto) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setRateSell(exchangeRateRequestDto.getRateSell());
        exchangeRate.setRateBuy(exchangeRateRequestDto.getRateBuy());
        exchangeRate.setSource(exchangeRateRequestDto.getSource());
        exchangeRate.setLocalDate(exchangeRateRequestDto.getLocalDate());
        exchangeRate.setBaseCurrency(exchangeRateRequestDto.getBaseCurrency());
        exchangeRate.setCurrencyDepended(exchangeRateRequestDto.getCurrencyDepended());
        return exchangeRate;
    }
}
