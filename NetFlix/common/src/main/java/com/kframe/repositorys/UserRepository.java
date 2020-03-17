package com.kframe.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kframe.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {

}
