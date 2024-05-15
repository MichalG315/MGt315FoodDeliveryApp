package pl.zajavka.infrastructure.security.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.domain.Role;
import pl.zajavka.util.DomainFixtures;
import pl.zajavka.util.EntityFixtures;
import pl.zajavka.infrastructure.security.entity.RoleEntity;
import pl.zajavka.infrastructure.security.jpa.RoleJpaRepository;
import pl.zajavka.infrastructure.security.mapper.RoleEntityMapper;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RoleRepositoryMockitoTest {

    @Mock
    private RoleJpaRepository roleJpaRepository;
    @Mock
    private RoleEntityMapper roleEntityMapper;

    @InjectMocks
    private RoleRepository roleRepository;

    @Test
    void findById() {
        // given
        Role role = DomainFixtures.someRole1();
        RoleEntity roleEntity = EntityFixtures.someRole1();
        int roleId = role.getId();

        Mockito.when(roleJpaRepository.findById(roleId))
                .thenReturn(Optional.ofNullable(roleEntity));
        Mockito.when(roleEntityMapper.map(roleEntity))
                .thenReturn(role);

        // when
        Optional<Role> result = roleRepository.findById(roleId);

        // then
        Assertions.assertThat(result.orElseThrow()).isEqualTo(role);
    }
}