package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.RoleDAO;
import pl.zajavka.domain.Role;
import pl.zajavka.exception.NotFoundException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleDAO roleDAO;

    public Role findRoleByRoleId(Integer roleId) {
        Optional<Role> role = roleDAO.findById(roleId);
        if (role.isEmpty()){
            throw new NotFoundException("Could not find role with id: %s".formatted(roleId));
        }
        return role.get();
    }
}
