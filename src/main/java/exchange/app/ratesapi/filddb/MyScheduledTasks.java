package exchange.app.ratesapi.filddb;

import exchange.app.ratesapi.service.ExternalDataSyncMonoServiceImpl;
import exchange.app.ratesapi.service.ExternalDataSyncPrivatServiceImpl;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Log4j2
public class MyScheduledTasks {
    private final ExternalDataSyncMonoServiceImpl monoService;
    private final ExternalDataSyncPrivatServiceImpl privatService;

    @PostConstruct
    @Scheduled(cron = "* */30 * * * ?")
    public void inject() throws IOException {
        monoService.syncExternalCharacters();
        privatService.syncExternalCharacters();
        log.info("cron job add info to db at: " + LocalDate.now());
    }
}
