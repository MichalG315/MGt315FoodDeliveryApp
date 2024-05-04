package pl.zajavka.business.dao;

import pl.zajavka.domain.User;
import pl.zajavka.infrastructure.security.entity.UserEntity;

public interface UserDAO {
    void saveUser(User user);

    User findByUserName(String username);

}
