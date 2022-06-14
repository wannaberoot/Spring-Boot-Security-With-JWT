package com.example.springbootsecuritywithjwt.repository;

import com.example.springbootsecuritywithjwt.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

    Boolean existsByUserName(String userName);
    UserInfo findByUserName(String userName);
}
