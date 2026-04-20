package com.github.springboard1st.domain.comment;

import com.github.springboard1st.domain.comment.dto.CommentRequest;
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
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 댓글 생성
    public void createComment(Long postId, CommentRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .user(user)
                .post(post)
                .build();

        commentRepository.save(comment);
    }

    // 댓글 수정
    public void updateComment(Long id, CommentRequest request, String email) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        if (!comment.getUser().getEmail().equals(email)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        comment.update(request.getContent());
        commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long id, String email) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        if (!comment.getUser().getEmail().equals(email)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        commentRepository.delete(comment);
    }
}
