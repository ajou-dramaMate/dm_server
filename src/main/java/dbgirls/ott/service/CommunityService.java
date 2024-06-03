package dbgirls.ott.service;

import dbgirls.ott.domain.Community;
import dbgirls.ott.domain.User;
import dbgirls.ott.dto.communityDto.CommunityRes;
import dbgirls.ott.dto.communityDto.PostCommunityReq;
import dbgirls.ott.repository.CommunityRepository;
import dbgirls.ott.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    public String postCommunityInfo(PostCommunityReq postCommunityReq) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String)authentication.getCredentials(); //email
        User user = userRepository.findByEmail(email).orElseThrow();

        // 사용자 정보 조회
        Community community = postCommunityReq.toEntity(postCommunityReq, user);
        community.setDate(LocalDate.now());
        communityRepository.save(community);
        return("커뮤니티 글 등록되었습니다.");
    }

    public List<CommunityRes> getCommunityInfo() {
        List<Community> communities = communityRepository.findAll();
        List<CommunityRes> communityResList = new ArrayList<>();

        for (Community community : communities){
            if (community.getCurrentRecruit() != null) {
                if (community.getCurrentRecruit().equals(community.getTotalRecruit())) {
                    community.setRecruitStatus(false);
                }
                else {
                    community.setRecruitStatus(true);
                }
            }

            CommunityRes addCommunity = CommunityRes.fromEntity(community);
            communityResList.add(addCommunity);
        }

        return communityResList;
    }

}
