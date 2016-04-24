package com.mail.hunter.dataaccess.model;


import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uc_user_username", columnNames = {"username"})
        }
)
public class User implements Persistable<Integer> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",unique = true,nullable = false)
    private Integer id;


    @Column(name = "username",length = 45,nullable = false,unique = true)
    private String username;
    @Column(name ="password", length = 45,nullable = false)
    private String password;
    @Column(name="role",length = 45,nullable = false)
    private String role;

    public User(){
        super();
    }

    public User( String username, String password, String role) {
        super();
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Transient
    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
