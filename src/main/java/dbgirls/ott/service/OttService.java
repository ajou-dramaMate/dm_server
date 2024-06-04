package dbgirls.ott.service;

import dbgirls.ott.domain.Ott;
import dbgirls.ott.domain.OttType;
import dbgirls.ott.dto.ottDto.OttReq;
import dbgirls.ott.dto.ottDto.OttRes;
import dbgirls.ott.repository.OttRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<OttRes> getOttRecommendResult(OttReq ottReq) {
        List<OttRes> ottResList = new ArrayList<>();

        //1
        OttRes ottRes = new OttRes();
        List<String> dramas = new ArrayList<>();
        dramas.add(ottReq.getDrama1());
        dramas.add(ottReq.getDrama2());
        ottRes.setDramas(dramas);
        ottRes.setPrice(5500);
        ottResList.add(ottRes);

        //2
        OttRes ottRes2 = new OttRes();
        List<String> dramas2 = new ArrayList<>();
        dramas2.add(ottReq.getDrama3());
        ottRes2.setDramas(dramas2);
        ottRes2.setPrice(7900);
        ottResList.add(ottRes2);

        //3
        OttRes ottRes3 = new OttRes();
        List<String> dramas3 = new ArrayList<>();
        ottRes3.setDramas(dramas3);
        ottRes3.setPrice(7900);
        ottResList.add(ottRes3);

        return ottResList;
    }
}