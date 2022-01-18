package com.zova0.neighvaluation.repository;

import com.zova0.neighvaluation.model.Comment;
import com.zova0.neighvaluation.model.Post;
import com.zova0.neighvaluation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
