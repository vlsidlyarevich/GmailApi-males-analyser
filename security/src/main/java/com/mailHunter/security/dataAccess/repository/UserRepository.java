package com.mailHunter.security.dataAccess.repository;


import com.mailHunter.security.dataAccess.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {

}
