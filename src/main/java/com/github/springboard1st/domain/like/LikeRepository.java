package com.github.springboard1st.domain.like;

import com.github.springboard1st.domain.post.Post;
import com.github.springboard1st.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    Optional<Like> findByUserAndPost(User user, Post post);
    boolean existsByUserAndPost(User user, Post post);
}
