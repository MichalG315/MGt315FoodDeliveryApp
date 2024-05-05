package pl.zajavka.business.dao;

import pl.zajavka.domain.User;

public interface UserDAO {
    void saveUser(User user);

    User findByUserName(String username);

    Integer findUserId(String restaurantUserName);
}
