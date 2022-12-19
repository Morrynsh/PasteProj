package ua.pasta.pasteproj.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ua.pasta.pasteproj.entity.PasteboxEntity;
import ua.pasta.pasteproj.repository.PasteboxJpaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class SchedulingConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingConfiguration.class);

    private PasteboxJpaRepository pasteboxJpaRepository;

    @Autowired
    public void setPasteboxJpaRepository(PasteboxJpaRepository pasteboxJpaRepository) {
        this.pasteboxJpaRepository = pasteboxJpaRepository;
    }

    public List<PasteboxEntity> findAllPasteboxEntities(){
        return pasteboxJpaRepository.findAll();
    }

    public void deletePasteboxEntityById(Long id){
        pasteboxJpaRepository.deleteById(id);
    }

    // Every 10 seconds we check for expired pastes and delete them from DB
    @Scheduled(fixedRate = 10000)
    void findAndDeleteAllExpiredPastes(){

        LOGGER.info("Removing expired pastes");
        findAllPasteboxEntities().stream()
                .filter(entity -> entity.getLifeTime().isBefore(LocalDateTime.now()))
                .forEach(entity -> deletePasteboxEntityById(entity.getId()));

    }

}
