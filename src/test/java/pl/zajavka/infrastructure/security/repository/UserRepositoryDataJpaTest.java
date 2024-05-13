package pl.zajavka.infrastructure.security.repository;

import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.zajavka.configuration.PersistenceContainerTestConfiguration;
import pl.zajavka.infrastructure.security.entity.UserEntity;
import pl.zajavka.infrastructure.security.jpa.UserJpaRepository;
import pl.zajavka.infrastructure.security.repository.util.EntityFixtures;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserRepositoryDataJpaTest {

    private final UserJpaRepository userJpaRepository;

    @Test
    void findByUserNameWorkCorrectly(){
        // given
        UserEntity user = userJpaRepository.saveAndFlush(EntityFixtures.someUser1());

        // when
        UserEntity result = userJpaRepository.findByUserName(user.getUserName());

        // then
        Assertions.assertThat(result).isEqualTo(user);
    }
}