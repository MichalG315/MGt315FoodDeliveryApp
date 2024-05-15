package pl.zajavka.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.business.dao.RoleDAO;
import pl.zajavka.domain.Role;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.util.DomainFixtures;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleDAO roleDAO;

    @InjectMocks
    private RoleService roleService;

    @Test
    void findRoleByRoleId() {
        // given
        Role role = DomainFixtures.someRole1();
        int id = role.getId();

        Mockito.when(roleDAO.findById(id)).thenReturn(Optional.of(role));

        // when
        Role result = roleService.findRoleByRoleId(id);

        Assertions.assertThat(result).isEqualTo(role);
    }
    @Test
    void findRoleByRoleIdExceptionTest() {
        // given
        Role role = DomainFixtures.someRole1();
        int roleId = role.getId();

        Mockito.when(roleDAO.findById(roleId)).thenReturn(Optional.empty());

        // when, then
        NotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                NotFoundException.class, () -> roleService.findRoleByRoleId(roleId));
        Assertions.assertThat("Could not find role with id: %s".formatted(roleId))
                .isEqualTo(exception.getMessage());
    }
}