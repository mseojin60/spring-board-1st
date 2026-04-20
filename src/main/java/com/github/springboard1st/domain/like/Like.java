package com.github.springboard1st.domain.like;

import com.github.springboard1st.domain.post.Post;
import com.github.springboard1st.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "post_id"}))
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
