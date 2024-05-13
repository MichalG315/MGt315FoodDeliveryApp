package pl.zajavka.infrastructure.security.repository.util;

import pl.zajavka.infrastructure.security.entity.UserEntity;

public class EntityFixtures {

    public static UserEntity someUser1() {
        return UserEntity.builder()
                .userName("testUserName")
                .email("test@gmail.com")
                .password("testtesttest")
                .active(true)
                .build();
    }

}
