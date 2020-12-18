package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.repositories;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GroupRepository extends JpaRepository<Group,Long> {
    @Modifying
    @Transactional
    @Query("update Group g set g.name = ?1 where g.id = ?2")
    void setGroupInfoByID(String name, Long userId);

}
