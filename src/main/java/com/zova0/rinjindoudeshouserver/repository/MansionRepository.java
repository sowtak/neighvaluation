package com.zova0.rinjindoudeshouserver.repository;

import com.zova0.rinjindoudeshouserver.model.Mansion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MansionRepository extends JpaRepository<Mansion, Long> {
    Optional<Mansion> findByName(Mansion mansion);
}
