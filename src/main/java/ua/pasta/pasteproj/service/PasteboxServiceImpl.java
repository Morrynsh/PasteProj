package ua.pasta.pasteproj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.pasta.pasteproj.api.request.PasteboxRequest;
import ua.pasta.pasteproj.api.request.PublicStatus;
import ua.pasta.pasteproj.api.response.PasteboxResponse;
import ua.pasta.pasteproj.api.response.PasteboxUrlResponse;
import ua.pasta.pasteproj.entity.PasteboxEntity;
import ua.pasta.pasteproj.repository.PasteboxJpaRepository;
import ua.pasta.pasteproj.util.TimeConverter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PasteboxServiceImpl implements PasteboxService {

    private final PasteboxJpaRepository pasteboxJpaRepository;
    private final TimeConverter timeConverter;

    @Override
    public PasteboxResponse getByHash(String hash) {

        PasteboxEntity pasteboxEntity = pasteboxJpaRepository.findByHash(hash);

        // For notfound page with such hash
        if(pasteboxEntity == null)
            return new PasteboxResponse(null, null, hash, null, false);

        // Checking is a paste expired
        if(pasteboxEntity.getLifeTime().isAfter(LocalDateTime.now())) {

            // If time's not expired, then convert remain seconds to mins, hours...
            // and prepare to send page with paste info
            long timeDifference = ChronoUnit.SECONDS.between(LocalDateTime.now(), pasteboxEntity.getLifeTime());
            return new PasteboxResponse(pasteboxEntity.getId(),
                    pasteboxEntity.getData(),
                    pasteboxEntity.getHash(),
                    timeConverter.convertSecondsToTimeformat(timeDifference),
                    pasteboxEntity.isPublic());

        } else {

            // If time expire, send info about it
            return new PasteboxResponse(pasteboxEntity.getId(),
                    "Time for this text has been expired",
                    pasteboxEntity.getHash(),
                    "-",
                    pasteboxEntity.isPublic());
        }
    }

    // Prepare to show last PUBLIC pastes in the homepage(maximum 20 pastes)
    @Override
    public List<PasteboxResponse> getLastPublic() {

        List<PasteboxEntity> pasteboxEntities;

        LocalDateTime now = LocalDateTime.now();

        pasteboxEntities = pasteboxJpaRepository.findAll().stream()
                .filter(PasteboxEntity::isPublic)
                .filter(PasteboxEntity -> PasteboxEntity.getLifeTime().isAfter(now))
                .sorted(Comparator.comparing(PasteboxEntity::getId).reversed())
                .limit(20)
                .collect(Collectors.toList());

        List<PasteboxResponse> pasteboxResponses = new ArrayList<>();
        long leftTime;

        for(PasteboxEntity e : pasteboxEntities){

            leftTime = ChronoUnit.SECONDS.between(LocalDateTime.now(), e.getLifeTime());
            pasteboxResponses.add(new PasteboxResponse(
                    e.getId(),
                    e.getData(),
                    e.getHash(),
                    timeConverter.convertSecondsToTimeformat(leftTime),
                    e.isPublic()));

        }

        return pasteboxResponses;

    }

    @Override
    public PasteboxUrlResponse create(PasteboxRequest request) {

        String symbols = "abcdefghijklmnopqrstuvwxyz";
        String randomHash = new Random().ints(10, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());

        PasteboxEntity pasteboxEntity = new PasteboxEntity();
        pasteboxEntity.setData(request.getData());
        pasteboxEntity.setLifeTime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        pasteboxEntity.setHash(randomHash);
        pasteboxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        pasteboxJpaRepository.save(pasteboxEntity);

        return new PasteboxUrlResponse(randomHash);
    }

    public boolean deleteById(String hash){

        PasteboxEntity paste = pasteboxJpaRepository.findByHash(hash);

        if(paste == null)
            return false;

        pasteboxJpaRepository.delete(paste);

        return true;

    }

}
