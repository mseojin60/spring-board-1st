package com.github.springboard1st.domain.post;

import com.github.springboard1st.domain.post.dto.PostRequest;
import com.github.springboard1st.domain.user.User;
import com.github.springboard1st.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 전체 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 이메일로 조회
    public List<Post> getPostsByEmail(String email) {
        return postRepository.findByUserEmail(email);
    }

    // 게시물 생성
    public void createPost(PostRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();

        postRepository.save(post);
    }

    // 게시물 수정
    public void updatePost(Long id, PostRequest request, String email) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        if (!post.getUser().getEmail().equals(email)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        post.update(request.getTitle(), request.getContent());
        postRepository.save(post);
    }

    // 게시물 삭제
    public void deletePost(Long id,String email) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        if (!post.getUser().getEmail().equals(email)) {
            throw new IllegalArgumentException("삭제 권한이 업습니다.");
        }

        postRepository.delete(post);
    }
}
