package pl.zajavka.infrastructure.security.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.domain.User;
import pl.zajavka.util.DomainFixtures;
import pl.zajavka.util.EntityFixtures;
import pl.zajavka.infrastructure.security.entity.UserEntity;
import pl.zajavka.infrastructure.security.jpa.UserJpaRepository;
import pl.zajavka.infrastructure.security.mapper.UserEntityMapper;

@ExtendWith(MockitoExtension.class)
class UserRepositoryMockitoTest {

    @Mock
    private UserJpaRepository userJpaRepository;
    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserRepository userRepository;

    @Test
    void saveUser() {
        // given
        User user = DomainFixtures.someUser1();
        UserEntity userEntity = EntityFixtures.someUser1();

        Mockito.when(userEntityMapper.mapToEntity(user)).thenReturn(userEntity);

        // when
        userRepository.saveUser(user);

        // then
        Mockito.verify(userJpaRepository, Mockito.times(1))
                .save(userEntity);
    }

    @Test
    void findByUserName() {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        UserEntity userEntity = EntityFixtures.someUser1();

        Mockito.when(userJpaRepository.findByUserName(userName)).thenReturn(userEntity);
        Mockito.when(userEntityMapper.mapFromEntity(userEntity)).thenReturn(user);

        // when
        User result = userRepository.findByUserName(userName);

        // then
        Assertions.assertThat(result).isEqualTo(user);
    }

    @Test
    void findUserId() {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        UserEntity userEntity = EntityFixtures.someUser1();

        Mockito.when(userJpaRepository.findByUserName(userName)).thenReturn(userEntity);
        Mockito.when(userEntityMapper.mapFromEntity(userEntity)).thenReturn(user);

        // when
        Integer result = userRepository.findUserId(userName);

        // then
        Assertions.assertThat(result).isEqualTo(user.getUserId());
    }
}