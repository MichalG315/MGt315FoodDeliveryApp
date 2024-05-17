package pl.zajavka.infrastructure.security.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.RoleDAO;
import pl.zajavka.domain.Role;
import pl.zajavka.infrastructure.security.jpa.RoleJpaRepository;
import pl.zajavka.infrastructure.security.mapper.RoleEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleRepository implements RoleDAO {

    private final RoleJpaRepository roleJpaRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Optional<Role> findById(Integer roleId) {
        return roleJpaRepository.findById(roleId).map(roleEntityMapper::map);

    }
}
