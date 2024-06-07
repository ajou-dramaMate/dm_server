package dbgirls.ott.service;

import dbgirls.ott.domain.*;
import dbgirls.ott.dto.dramaDto.PostReviewReq;
import dbgirls.ott.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final DramaRepository dramaRepository;

    public String postReviewInfo(PostReviewReq postReviewReq) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String)authentication.getCredentials(); //email
        User user = userRepository.findByEmail(email).orElseThrow();
        Drama drama = dramaRepository.findById(postReviewReq.getDramaId()).orElseThrow();

        Review review = postReviewReq.toEntity(postReviewReq, user, drama);
        review.setDate(LocalDate.now());
        reviewRepository.save(review);
        return("드라마 리뷰가 등록되었습니다.");
    }

    public Integer getStarAverage(Long dramaId) {
        List<Review> reviewList = reviewRepository.findByDramaId(dramaId);
        Long counts = reviewList.stream().count();
        Integer star = 0;

        for(Review review : reviewList) {
            star += review.getStar();
        }

        if (star != 0) {
            star /= counts.intValue();
        }

        return star;
    }
}
