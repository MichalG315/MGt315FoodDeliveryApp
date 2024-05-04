package pl.zajavka.infrastructure.security.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zajavka.infrastructure.security.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {


    UserEntity findByUserName(String userName);

}
