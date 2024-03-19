package com.example.project_hotel.repos;

import com.example.project_hotel.models.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
    UserEntity findFirstByUsername(String username);


    @Modifying
    @Transactional
    @Query("update users set password=:pass where username=:username")
    void change(
            @Param("pass") String pass,
            @Param("username") String username);

}
