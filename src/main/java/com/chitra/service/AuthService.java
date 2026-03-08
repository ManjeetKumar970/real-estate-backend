package com.chitra.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chitra.entity.Auth;
import com.chitra.repository.AuthRepository;

@Service
public class AuthService {
   private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
   private final AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }
    // Implement registration logic here
    public Auth register(Auth auth) {
        auth = authRepository.save(auth);
        return auth;
    }
    // Implement login logic here
    public Auth login(String email, String password) {
        Auth auth = authRepository.findByEmail(email);
        if (auth != null && passwordMatches(password, auth.getPassword())) {
            return auth;
        }
        return null;
    }
    // Implement password hashing logic here
     public String hashPassword(String password) {
        return passwordEncoder.encode(password); 
     }
     // Implement email validation logic here
    public boolean emailValidation(String email) {

    if (email == null || !email.contains("@")) {
        return false;
    }

    Auth existingAuth = authRepository.findByEmail(email);

    return existingAuth == null;
}

public Auth chheckEmail(String email) {
    return authRepository.findByEmail(email);
}
public boolean passwordMatches(String rawPassword, String hashedPassword) {
    return passwordEncoder.matches(rawPassword, hashedPassword);
}

}
