package com.mailHunter.security.Service.impl;


import com.mailHunter.security.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends com.mailHunter.security.Service.common.AbstractGenericService<com.mailHunter.security.DataAccess.model.User, Integer, com.mailHunter.security.DataAccess.Repository.UserRepository>
        implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
}