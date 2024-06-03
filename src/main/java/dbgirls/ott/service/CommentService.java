package dbgirls.ott.service;

import dbgirls.ott.domain.Comment;
import dbgirls.ott.domain.Community;
import dbgirls.ott.domain.User;
import dbgirls.ott.dto.communityDto.PostCommentReq;
import dbgirls.ott.repository.CommentRepository;
import dbgirls.ott.repository.CommunityRepository;
import dbgirls.ott.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;

    public String postCommentInfo(PostCommentReq postCommentReq) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String)authentication.getCredentials(); //email
        User user = userRepository.findByEmail(email).orElseThrow();
        Community community = communityRepository.findById(postCommentReq.getCommunityId()).orElseThrow();
        System.out.println("community : " + community);

        // 사용자 정보 조회
        Comment comment = postCommentReq.toEntity(postCommentReq, user, community);
        comment.setDate(LocalDate.now());
        System.out.println("comment : " + comment);
        commentRepository.save(comment);
        return("댓글이 등록되었습니다.");
    }
}
