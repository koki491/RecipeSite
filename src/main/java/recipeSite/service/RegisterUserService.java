package recipeSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipeSite.mapper.UserMapper;
import recipeSite.web.RegisterUserForm;

@Service
@Transactional
public class RegisterUserService {

    @Autowired
    private UserMapper userMapper;

    public void create(RegisterUserForm registerUserForm) {
        userMapper.save(registerUserForm);
    }
}
