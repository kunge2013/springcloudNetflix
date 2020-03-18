package com.kframe.auth.codec;

import org.springframework.security.crypto.password.PasswordEncoder;

public class KframePasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence arg0) {
		return arg0.toString();
	}

	@Override
	public boolean matches(CharSequence arg0, String arg1) {
		return arg1.equals(arg0.toString());
	}

}
