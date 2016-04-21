package com.mailHunter.security.service.impl;


import com.mailHunter.security.dataAccess.model.User;
import com.mailHunter.security.dataAccess.repository.UserRepository;
import com.mailHunter.security.service.UserService;
import com.mailHunter.security.service.common.AbstractGenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends AbstractGenericService<User, Integer, UserRepository>
        implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
}