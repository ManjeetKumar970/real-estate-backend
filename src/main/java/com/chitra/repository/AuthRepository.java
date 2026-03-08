package com.chitra.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.chitra.entity.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findByEmail(String email);  
}
