package com.github.springboard1st.domain.like;

import com.github.springboard1st.domain.post.Post;
import com.github.springboard1st.domain.post.PostRepository;
import com.github.springboard1st.domain.user.User;
import com.github.springboard1st.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // 좋아요
    public void like(Long postId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        if (likeRepository.existsByUserAndPost(user, post)) {
            throw new IllegalArgumentException("이미 좋아요한 게시물입니다.");
        }

        likeRepository.save(Like.builder()
                    .user(user)
                    .post(post)
                    .build());
    }

    // 좋아요 취소
    public void unlike(Long postId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        Like like = likeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new IllegalArgumentException("좋아요 하지 않은 게시물입니다."));

        likeRepository.delete(like);
    }
}
