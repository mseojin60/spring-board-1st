package com.github.springboard1st.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
                                        //           <Entity 타입, PK 타입>
public interface UserRepository extends JpaRepository<User, Long> {
    // null 을 안전하게 처리하기 위한 Optional 사용
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);        // 존재여부
}
/* JpaRepository 가 제공하는것

    findById(Long id)      // id로 조회
    findAll()              // 전체 조회
    save(User user)        // 저장/수정
    delete(User user)      // 삭제
    existsById(Long id)    // 존재 여부
    count()                // 개수

  */
