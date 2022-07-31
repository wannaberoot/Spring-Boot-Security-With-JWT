package com.example.springbootsecuritywithjwt.repositories;

import com.example.springbootsecuritywithjwt.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

    Boolean existsByUserName(String userName);
    UserInfo findByUserName(String userName);
}
