package pl.zajavka.infrastructure.security.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zajavka.infrastructure.security.entity.RoleEntity;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRole(String role);
}
