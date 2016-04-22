package com.mail.hunter.application.beans;

import com.mail.hunter.dataaccess.model.User;
import com.mail.hunter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name="userBean")
@SessionScoped
@Component
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService userService;

    private String userName;
    private String password;

    public void addUser() {
        if (!userName.isEmpty() &&  !password.isEmpty()) {
            userService.save(new User(this.userName,this.password,"ROLE_USER"));
        }
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}