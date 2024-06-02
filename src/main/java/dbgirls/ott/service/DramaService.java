package dbgirls.ott.service;

import dbgirls.ott.domain.Drama;
import dbgirls.ott.dto.dramaDto.DramaRes;
import dbgirls.ott.dto.dramaDto.PostDramaReq;
import dbgirls.ott.repository.DramaRepository;
import dbgirls.ott.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DramaService {
    private final DramaRepository dramaRepository;
    private final UserRepository userRepository;

    public String postDramaInfo(PostDramaReq postDramaReq) {
        // 사용자 정보 조회
        Drama drama = postDramaReq.toEntity(postDramaReq);
        dramaRepository.save(drama);
        return("드라마가 등록되었습니다.");
    }

    public List<DramaRes> getDramaInfo() {
        List<Drama> dramas = dramaRepository.findAll();
        List<DramaRes> dramaResList = new ArrayList<>();

        for (Drama drama : dramas){
            DramaRes addDrama = DramaRes.fromEntity(drama);
            dramaResList.add(addDrama);
        }

        return dramaResList;
    }
}
