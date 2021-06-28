package com.zova0.rinjindoudeshouserver.repository;

import com.zova0.rinjindoudeshouserver.model.MansionForum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MansionForumRepository extends JpaRepository<MansionForum, Long> {
    Optional<MansionForum> findByName(String mansionForumName);
}
