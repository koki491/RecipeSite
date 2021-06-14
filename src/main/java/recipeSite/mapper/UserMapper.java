package recipeSite.mapper;

import recipeSite.domain.User;
import recipeSite.web.RegisterUserForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();

    User findById(User user);

    void save(RegisterUserForm registerUserForm);
}
