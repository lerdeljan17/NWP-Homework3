package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.repositories;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.Group;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.User;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("update User u set u.firstname = ?1, u.lastname = ?2, u.userType = ?3, u.group = ?4 where u.id = ?5")
    void setUserInfoById(String firstname, String lastname, UserType userType, Group group, Long userId);

    @Query( value = "select * from user_table u where " + "(u.firstname like ?1 or ?1 is null or ?1 like '') " + "and (u.lastname like ?2 or ?2 is null or ?2 like '') " + "and (u.usertype_id like ?3 or ?3 is null) " +
            "and (u.group_id like ?4 or ?4 is null)",
            nativeQuery = true
    )
    List<User> searchUsers(String name,String surname,Long groupId, Long userTypeId);
}
