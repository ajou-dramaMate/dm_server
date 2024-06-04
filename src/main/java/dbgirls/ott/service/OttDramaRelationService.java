package dbgirls.ott.service;

import dbgirls.ott.domain.Drama;
import dbgirls.ott.domain.Ott;
import dbgirls.ott.domain.OttDramaRelation;
import dbgirls.ott.domain.OttType;
import dbgirls.ott.repository.DramaRepository;
import dbgirls.ott.repository.OttDramaRelationRepository;
import dbgirls.ott.repository.OttRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OttDramaRelationService {
    private final OttDramaRelationRepository ottDramaRelationRepository;
    private final OttRepository ottRepository;
    private final DramaRepository dramaRepository;

    public void addOttDramaRelation(Long dramaId, List<OttType> otts) {
        for (OttType ottType : otts) {
            Ott ott = ottRepository.findByName(ottType).orElseThrow();
            Drama drama = dramaRepository.findById(dramaId).orElseThrow();
            ottDramaRelationRepository.save(OttDramaRelation.builder().ott(ott).drama(drama).build());
        }
    }
}
