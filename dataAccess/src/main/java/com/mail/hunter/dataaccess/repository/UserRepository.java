package com.mail.hunter.dataaccess.repository;

import com.mail.hunter.dataaccess.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
