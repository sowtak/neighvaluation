package com.zova0.neighvaluation.repository;

import com.zova0.neighvaluation.model.Like;
import com.zova0.neighvaluation.model.Post;
import com.zova0.neighvaluation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findTopByPostAndUserOrderByLikeIdDesc(Post post, User currentUser);
}
