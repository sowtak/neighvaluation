package com.zova0.rinjindoudeshouserver.repository;

import com.zova0.rinjindoudeshouserver.model.MansionForum;
import com.zova0.rinjindoudeshouserver.model.Post;
import com.zova0.rinjindoudeshouserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMansionForum(MansionForum mansionForum);

    List<Post> findByUser(User user);
}
