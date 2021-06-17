package recipeSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipeSite.domain.LoginUserDetails;
import recipeSite.domain.User;
import recipeSite.mapper.UserMapper;
import recipeSite.web.RegisterUserForm;

@Service
@Transactional
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;


    //Login user detail
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = new User();
        user.setUsername(username);
        user = this.userMapper.findById(user);
        if (user == null) {
            throw new UsernameNotFoundException("The requested user is not found.");
        }
        return new LoginUserDetails(user);
    }
}
