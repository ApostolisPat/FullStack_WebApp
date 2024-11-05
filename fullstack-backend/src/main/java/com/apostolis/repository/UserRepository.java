package com.apostolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apostolis.model.User;
//control and left click on JpaRepository to see all methods
public interface UserRepository extends JpaRepository<User, Long>{
}
