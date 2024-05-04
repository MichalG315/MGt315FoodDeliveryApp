package pl.zajavka.business.dao;

import pl.zajavka.domain.Role;

import java.util.Optional;

public interface RoleDAO {
    Optional<Role> findById(Integer roleId);
}
