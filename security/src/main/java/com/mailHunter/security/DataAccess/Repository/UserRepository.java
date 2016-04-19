package com.mailHunter.security.DataAccess.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<com.mailHunter.security.DataAccess.model.User,Integer> {
}
