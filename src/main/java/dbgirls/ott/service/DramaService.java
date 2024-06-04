package dbgirls.ott.service;

import dbgirls.ott.domain.Drama;
import dbgirls.ott.domain.LikedDrama;
import dbgirls.ott.domain.User;
import dbgirls.ott.dto.dramaDto.DramaDetailRes;
import dbgirls.ott.dto.dramaDto.DramaRes;
import dbgirls.ott.dto.dramaDto.LikedDramaRes;
import dbgirls.ott.dto.dramaDto.PostDramaReq;
import dbgirls.ott.repository.DramaRepository;
import dbgirls.ott.repository.LikedDramaRepository;
import dbgirls.ott.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DramaService {
    private final DramaRepository dramaRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final OttDramaRelationService ottDramaRelationService;
    private final LikedDramaRepository likedDramaRepository;

    public String postDramaInfo(PostDramaReq postDramaReq) {
        // 사용자 정보 조회
        Drama drama = postDramaReq.toEntity(postDramaReq);
        dramaRepository.save(drama);
        ottDramaRelationService.addOttDramaRelation(drama.getId(), postDramaReq.getOtt());
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

    public DramaDetailRes getDetailDramaInfo(Long dramaId) {
        Optional<Drama> drama = dramaRepository.findById(dramaId);
        Drama dramaDetail = drama.get();

        return DramaDetailRes.fromEntity(dramaDetail);
    }

    public List<LikedDramaRes> getLikedDramaList() {
        String email = userService.getUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();

        List<LikedDrama> likedDramas = likedDramaRepository.findByUserId(user.getId());

        List<LikedDramaRes> likedDramaList = new ArrayList<>();

        for (LikedDrama likedDrama : likedDramas) {
            if (likedDrama.getDrama().isLiked()) {
                likedDramaList.add(LikedDramaRes.fromEntity(likedDrama.getDrama().getId(), likedDrama.getDrama().getTitle()));
            }
        }

        return likedDramaList;
    }

    public String postLikedDrama(Long dramaId) {
        Drama drama = dramaRepository.findById(dramaId).orElseThrow();

        String email = userService.getUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();

        likedDramaRepository.save(LikedDrama.builder().user(user).drama(drama).build());

        drama.setLiked(true);
        return "찜목록에 추가되었습니다";
    }
}
