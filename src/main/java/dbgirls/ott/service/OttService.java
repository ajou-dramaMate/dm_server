package dbgirls.ott.service;

import dbgirls.ott.domain.Ott;
import dbgirls.ott.domain.OttType;
import dbgirls.ott.repository.OttRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OttService {
    private final OttRepository ottRepository;

    public String postOttInfo() {
        Ott coupangplay = new Ott(OttType.COUPANGPLAY, 7890);
        ottRepository.save(coupangplay);

        Ott netflix = new Ott(OttType.NETFLIX, 5500);
        ottRepository.save(netflix);

        Ott disneyplus = new Ott(OttType.DISNEYPLUS, 9900);
        ottRepository.save(disneyplus);

        Ott wavve = new Ott(OttType.WAVVE, 7900);
        ottRepository.save(wavve);

        Ott watcha = new Ott(OttType.WATCHA, 7900);
        ottRepository.save(watcha);

        Ott tving = new Ott(OttType.TVING, 5500);
        ottRepository.save(tving);

        Ott uplus = new Ott(OttType.UPLUS, 5500);
        ottRepository.save(uplus);

        return("ott 정보가 등록되었습니다.");
    }
}