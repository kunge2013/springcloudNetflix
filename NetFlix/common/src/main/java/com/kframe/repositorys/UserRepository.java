package com.kframe.repositorys;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kframe.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Serializable> {

	/**
	 * 统计 当前用户是否已经注册
	 * @param mobile
	 * @param nation
	 * @return
	 */
	@Query(" select count(t) from UserInfo t where mobile = :mobile and nation = :nation")
	public int countRegisterUser(@Param("mobile") String mobile, @Param("nation") int nation);
	
}
