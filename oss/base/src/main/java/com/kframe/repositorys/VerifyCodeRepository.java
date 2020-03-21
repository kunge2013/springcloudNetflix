package com.kframe.repositorys;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kframe.entity.VerifyCode;
/**
 * 验证码保存接口
 * @author fk
 *
 */
@Repository
public interface VerifyCodeRepository extends JpaRepository<VerifyCode, Serializable>{

	@Query(" select t from VerifyCode t where t.code =: code and expiretime <=: expiretime")
	public List<VerifyCode> queryVerifyCodes(String code, long expiretime);
	
	/**
	 * 校验验证码是否过期
	 * @param code
	 * @param expiretime
	 * @return
	 */
	public default  boolean exitsCode(String code, long expiretime) {
		List<VerifyCode> verifycodes = queryVerifyCodes(code, expiretime);
		return verifycodes != null && verifycodes.size() > 0;
	}
}
