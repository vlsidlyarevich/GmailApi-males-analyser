package com.mail.hunter.services.impl;

import com.mail.hunter.dataaccess.model.User;
import com.mail.hunter.dataaccess.repository.UserRepository;
import com.mail.hunter.services.UserService;
import com.mail.hunter.services.common.AbstractGenericService;
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
