package com.chitra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chitra.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {

}