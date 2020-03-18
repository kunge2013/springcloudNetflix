package com.kframe.repositorys;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kframe.annotations.Comment;
import com.kframe.entity.UserInfo;


@Repository
public interface UserRepository extends JpaRepository<UserInfo, Serializable> {
	/**
	 * 统计 当前用户是否已经注册
	 * @param mobile
	 * @param nation
	 * @return
	 */
	@Comment("统计当前用户是否存在")
	@Query(" select count(t) from UserInfo t where mobile = :mobile and nation = :nation")
	public int countRegisterUser(@Param("mobile") String mobile, @Param("nation") int nation);

	@Comment("统计当前用户是否存在")
	@Query(" select count(t) from UserInfo t where username = :username")
	public int countByUsername(@Param("username") String username);

	@Query(" select t from UserInfo t where username = :username")
	public List<UserInfo> queryByUsername(@Param("username") String username);
	
	default public Optional<UserInfo> queryOneByUsername(@Param("username") String username) {
		List<UserInfo> users = queryByUsername(username);
		return Optional.ofNullable(users.isEmpty() ? null:users.get(0));
	}
	
	@Comment("查看当前用户是否存在")
	default boolean existsByUsername(String username) {
		return countByUsername(username) > 0;
	}
}
