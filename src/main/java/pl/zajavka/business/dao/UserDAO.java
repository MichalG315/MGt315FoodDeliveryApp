package pl.zajavka.business.dao;

import pl.zajavka.domain.User;
import pl.zajavka.infrastructure.security.entity.UserEntity;

public interface UserDAO {
    UserEntity saveUser(User user);

    UserEntity findByUserName(String username);

}
