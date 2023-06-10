package com.example.demo.domain.user.dao;

import com.example.demo.domain.user.model.SocialType;
import com.example.demo.domain.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String id);

    Optional<User> findByRefreshToken(String refreshToken);
}
