package ua.pasta.pasteproj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.pasta.pasteproj.entity.PasteboxEntity;
import ua.pasta.pasteproj.repository.PasteboxJpaRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ServiceTestsWithRealRepository {

    @Autowired
    PasteboxJpaRepository pasteboxJpaRepository;

    @Test
    void testAddNew(){
        PasteboxEntity pasteboxEntity = new PasteboxEntity();
        pasteboxEntity.setData("Here's my text.");
        pasteboxEntity.setHash("sdfsdfaf");
        pasteboxEntity.setPublic(true);
        pasteboxEntity.setLifeTime(LocalDateTime.now().plusSeconds(200));

        PasteboxEntity savedPaste = pasteboxJpaRepository.save(pasteboxEntity);

        assertThat(savedPaste).isNotNull();
        assertThat(savedPaste.getId()).isGreaterThan(0);

    }

    @Test
    void deleteTest(){

        LocalDateTime expTime = LocalDateTime.now().plusSeconds(35);

        PasteboxEntity entity = new PasteboxEntity();
        entity.setHash("hash");
        entity.setData("Here's my text #1.");
        entity.setLifeTime(expTime);
        entity.setPublic(true);

        PasteboxEntity savedEntity = pasteboxJpaRepository.save(entity);

        assertThat(savedEntity).isNotNull();
        assertThat(savedEntity.getId()).isGreaterThan(0);

        pasteboxJpaRepository.delete(entity);

        assertThat(pasteboxJpaRepository.findByHash("hash")).isNull();

    }

}
