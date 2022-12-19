package ua.pasta.pasteproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.pasta.pasteproj.entity.PasteboxEntity;

import java.util.List;

@Repository
public interface PasteboxJpaRepository extends JpaRepository<PasteboxEntity, Long> {
    PasteboxEntity findByHash(String hash);
    List<PasteboxEntity> findAll();
}
