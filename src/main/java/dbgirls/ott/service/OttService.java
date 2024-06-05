package dbgirls.ott.service;

import dbgirls.ott.domain.Drama;
import dbgirls.ott.domain.Ott;
import dbgirls.ott.domain.OttType;
import dbgirls.ott.dto.ottDto.OttReq;
import dbgirls.ott.dto.ottDto.OttRes;
import dbgirls.ott.repository.DramaRepository;
import dbgirls.ott.repository.OttRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OttService {
    private final OttRepository ottRepository;
    private final DramaRepository dramaRepository;

    public List<OttRes> getOtts(){
        List<OttRes> ottResList = new ArrayList<>();

        List<Ott> ottList = ottRepository.findAll();
        for(Ott ott : ottList) {
            ottResList.add(OttRes.fromEntityOtt(ott));
        }
        return ottResList;
    }

    public String postOttInfo() {

        Ott coupangplay = new Ott(OttType.COUPANGPLAY, 7890, changeToImage("COUPANGPLAY.png"));
        ottRepository.save(coupangplay);

        Ott netflix = new Ott(OttType.NETFLIX, 5500, changeToImage("NETFLIX.png"));
        ottRepository.save(netflix);

        Ott disneyplus = new Ott(OttType.DISNEYPLUS, 9900, changeToImage("DISNEYPLUS.png"));
        ottRepository.save(disneyplus);

        Ott wavve = new Ott(OttType.WAVVE, 7900, changeToImage("WAVVE.png"));
        ottRepository.save(wavve);

        Ott watcha = new Ott(OttType.WATCHA, 7900, changeToImage("WATCHA.png"));
        ottRepository.save(watcha);

        Ott tving = new Ott(OttType.TVING, 5500, changeToImage("TVING.png"));
        ottRepository.save(tving);

        Ott uplus = new Ott(OttType.UPLUS, 5500, changeToImage("UPLUS.png"));
        ottRepository.save(uplus);

        return("ott 정보가 등록되었습니다.");
    }

    public byte[] changeToImage(String image) {
        try {
            String filePath = "ott_images/" + image;

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);

            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + filePath);
            }

            byte[] imageBytes = inputStream.readAllBytes();
            return imageBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OttRes> getOttRecommendResult(Long drama1, Long drama2, Long drama3) {
        List<OttRes> ottResList = new ArrayList<>();
//        List<Ott> ottList = ottRepository.findAll();
//        List<Long> dramaIds = new ArrayList<>();
//
//        dramaIds.add(drama1);
//        dramaIds.add(drama2);
//        dramaIds.add(drama3);
//
//
//
//        for (Ott ott : ottList) {
//            for (Long id : dramaIds) {
//                Drama drama = dramaRepository.findById(id).orElseThrow();
//                drama.getTitle();
//            }
//        }

        Drama drama11 = dramaRepository.findById(drama1).orElseThrow();
        Drama drama12 = dramaRepository.findById(drama2).orElseThrow();
        Drama drama13 = dramaRepository.findById(drama3).orElseThrow();

        //1
        OttRes ottRes = new OttRes();
        List<String> dramas = new ArrayList<>();
        dramas.add(drama11.getTitle());
        dramas.add(drama12.getTitle());
        ottRes.setDramas(dramas);
        ottRes.setPrice(5500);
        ottRes.setOtt_image(changeToImage("NETFLIX.png"));
        ottResList.add(ottRes);

        //2
        OttRes ottRes2 = new OttRes();
        List<String> dramas2 = new ArrayList<>();
        dramas2.add(drama13.getTitle());
        ottRes2.setDramas(dramas2);
        ottRes2.setPrice(7900);
        ottRes.setOtt_image(changeToImage("WAVVE.png"));
        ottResList.add(ottRes2);

        //3
        OttRes ottRes3 = new OttRes();
        List<String> dramas3 = new ArrayList<>();
        ottRes3.setDramas(dramas3);
        ottRes3.setPrice(7900);
        ottRes.setOtt_image(changeToImage("WATCHA.png"));
        ottResList.add(ottRes3);

        return ottResList;
    }
}