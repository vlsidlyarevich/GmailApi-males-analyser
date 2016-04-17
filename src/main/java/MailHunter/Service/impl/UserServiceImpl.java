package MailHunter.Service.impl;


import MailHunter.DataAccess.Repository.UserRepository;
import MailHunter.DataAccess.model.User;
import MailHunter.Service.UserService;
import MailHunter.Service.common.AbstractGenericService;
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