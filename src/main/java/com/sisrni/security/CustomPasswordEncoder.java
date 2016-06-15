package com.sisrni.security;

import com.sisrni.utils.Md5Generator;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence password) {
        return Md5Generator.generar(password.toString());
    }

    @Override
    public boolean matches(CharSequence password, String userInput) {
        return (password != null && Md5Generator.generar(password.toString()).equals(userInput));
    }
}
