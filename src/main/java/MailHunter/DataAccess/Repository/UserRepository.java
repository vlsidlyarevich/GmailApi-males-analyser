package MailHunter.DataAccess.Repository;


import MailHunter.DataAccess.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
