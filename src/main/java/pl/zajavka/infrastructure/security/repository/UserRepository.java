package pl.zajavka.infrastructure.security.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.UserDAO;
import pl.zajavka.domain.User;
import pl.zajavka.infrastructure.security.jpa.UserJpaRepository;
import pl.zajavka.infrastructure.security.mapper.UserEntityMapper;

@Repository
@AllArgsConstructor
public class UserRepository implements UserDAO {

    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        userJpaRepository.save(userEntityMapper.mapToEntity(user));
    }

    @Override
    public User findByUserName(String username) {
        return userEntityMapper.mapFromEntity(userJpaRepository.findByUserName(username));
    }

    @Override
    public Integer findUserId(String restaurantUserName) {
        return findByUserName(restaurantUserName).getUserId();
    }


}
