package pl.zajavka.infrastructure.security.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    }

    @Test
    void findByUserName() {
    }

    @Test
    void findUserId() {
    }
}