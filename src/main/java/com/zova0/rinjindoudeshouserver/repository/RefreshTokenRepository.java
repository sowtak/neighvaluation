package com.zova0.rinjindoudeshouserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenRepository, Long> {
    Optional<RefreshTokenRepository> findByToken(String token);

    void deleteByToken(String token);
}
