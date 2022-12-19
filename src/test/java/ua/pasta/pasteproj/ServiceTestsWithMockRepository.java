package ua.pasta.pasteproj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.pasta.pasteproj.entity.PasteboxEntity;
import ua.pasta.pasteproj.api.response.PasteboxResponse;
import ua.pasta.pasteproj.repository.PasteboxJpaRepository;
import ua.pasta.pasteproj.service.PasteboxService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceTestsWithMockRepository {

    @Autowired
    PasteboxService pasteboxService;
    @MockBean
    PasteboxJpaRepository pasteboxJpaRepository;

    @Test
    void notExistHash(){

        PasteboxResponse response = new PasteboxResponse(null, null, "fsadfsaf", null, false);
        assertEquals(response, pasteboxService.getByHash("fsadfsaf"));

    }

    @Test
    void existByHash(){

        LocalDateTime expTime = LocalDateTime.now().plusSeconds(45);

        PasteboxEntity entity = new PasteboxEntity();
        entity.setHash("fsadfsadf");
        entity.setData("Here's my text.");
        entity.setLifeTime(expTime);
        entity.setPublic(true);

        when(pasteboxJpaRepository.findByHash("fsadfsadf")).thenReturn(entity);

        PasteboxResponse expected = new PasteboxResponse
                (null, "Here's my text.", "fsadfsadf", "44 seconds ", true);
        PasteboxResponse actual = pasteboxService.getByHash("fsadfsadf");

        assertEquals(expected, actual);

    }

    @Test
    void existFirstPublicPasteBoxes(){

        LocalDateTime expTime = LocalDateTime.now().plusSeconds(45);
        LocalDateTime expTime1 = LocalDateTime.now().plusSeconds(40);
        LocalDateTime expTime2 = LocalDateTime.now().plusSeconds(35);

        PasteboxEntity entity = new PasteboxEntity();
        entity.setId(0L);
        entity.setHash("hash");
        entity.setData("Here's my text #1.");
        entity.setLifeTime(expTime);
        entity.setPublic(true);

        PasteboxEntity entity1 = new PasteboxEntity();
        entity1.setId(1L);
        entity1.setHash("hash1");
        entity1.setData("Here's my text #2.");
        entity1.setLifeTime(expTime1);
        entity1.setPublic(true);

        PasteboxEntity entity2 = new PasteboxEntity();
        entity2.setId(2L);
        entity2.setHash("hash2");
        entity2.setData("Here's my text #3.");
        entity2.setLifeTime(expTime2);
        entity2.setPublic(true);

        List<PasteboxEntity> entities = new ArrayList<>();
        entities.add(entity);
        entities.add(entity1);
        entities.add(entity2);

        when(pasteboxJpaRepository.findAll()).thenReturn(entities);

        List<PasteboxResponse> actual = pasteboxService.getLastPublic();

        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(3);
        assertEquals("hash", actual.get(2).getHash());

    }

    @Test
    void notExistFirstPublicPasteBoxes(){

        when(pasteboxJpaRepository.findAll()).thenReturn(new ArrayList<>());

        assertThat(pasteboxService.getLastPublic().size()).isEqualTo(0);

    }

}
