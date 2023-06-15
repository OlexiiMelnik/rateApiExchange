package exchange.app.ratesapi.controller;

import exchange.app.ratesapi.dto.ExchangeRateResponseDto;
import exchange.app.ratesapi.dto.mapper.ExchangeRateMapper;
import exchange.app.ratesapi.model.ExchangeRate;
import exchange.app.ratesapi.service.ExchangeRateService;
import exchange.app.ratesapi.service.ExchangeRateUtilsService;
import exchange.app.ratesapi.service.ExternalDataSyncMonoServiceImpl;
import exchange.app.ratesapi.service.ExternalDataSyncPrivatServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchangeRates")
@RequiredArgsConstructor
@Tag(name = "Exchange Rate Controller", description = "REST APIs related to exchange rates")
@Log4j2
public class ExchangeRateController {
    private final ExchangeRateMapper mapper;
    private final ExchangeRateService rateService;
    private final ExchangeRateUtilsService exchangeRateUtilsService;
    private final ExternalDataSyncMonoServiceImpl monoService;
    private final ExternalDataSyncPrivatServiceImpl privatService;

    @Operation(summary = "Returns a list of all exchange "
            + "rates in the database along with the average buy and sale rates for USD and EUR")
    @GetMapping("/allWithAvg")
    public ResponseEntity<Map<String, Object>> findAllWithAverage() {
        List<ExchangeRate> exchangeRates = rateService.findAll();
        List<ExchangeRateResponseDto> dtos = exchangeRates.stream()
                .map(mapper::toExchangeRateResponseD)
                .collect(Collectors.toList());

        Map<String, String> averageRates =
                exchangeRateUtilsService.calculateAverageRates(exchangeRates);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("averageRates", averageRates);
        response.put("exchangeRates", dtos);
        log.info("method findAllWithAverage was worked at: " + LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Returns a list of all exchange")
    @GetMapping("/all")
    public List<ExchangeRateResponseDto> findAll() {
        log.info("method findAll was worked at: " + LocalDateTime.now());
        return rateService.findAll()
                .stream()
                .map(mapper::toExchangeRateResponseD)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get info by Id")
    @GetMapping("/{id}")
    public ExchangeRateResponseDto findById(@PathVariable Long id) {
        log.info("method findById was worked at: " + LocalDateTime.now());
        return mapper.toExchangeRateResponseD(rateService.findById(id).orElseThrow(
                EntityNotFoundException::new));
    }

    @Operation(summary = "Get exchange rates by date range")
    @GetMapping("/byDateRange/{from}/{to}")
    public List<ExchangeRateResponseDto> findAllByLocalDateBetween(
            @PathVariable("from") String from,
            @PathVariable("to") String to) {
        log.info("method findAllByLocalDateBetween was worked at: " + LocalDateTime.now());
        return rateService.findAllByLocalDateBetween(from, to)
                .stream()
                .map(mapper::toExchangeRateResponseD)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Inject data to DB")
    @PostMapping("/addAllExchaange")
    public String inject() {
        monoService.syncExternalCharacters();
        privatService.syncExternalCharacters();
        log.info("method inject was worked at: " + LocalDateTime.now());
        return "Info added well done";
    }

    @Operation(summary = "delete exchange by Id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("method delete was worked at: " + LocalDateTime.now());
        rateService.delete(id);
    }

    @Operation(summary = "delete exchanges by period")
    @DeleteMapping("/{from}/{to}")
    public void deleteExchangeRateByLocalDateBetween(
            @PathVariable("from") String from,
            @PathVariable("to") String to) {
        log.info("method deleteExchangeRateByLocalDateBetween was worked at: "
                + LocalDateTime.now());
        rateService.deleteExchangeRateByLocalDateBetween(from, to);
    }

}
