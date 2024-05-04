package pl.zajavka.infrastructure.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.domain.Role;
import pl.zajavka.infrastructure.security.entity.RoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {
    Role map(RoleEntity roleEntity);
}
