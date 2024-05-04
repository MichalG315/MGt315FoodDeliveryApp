package pl.zajavka.infrastructure.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.domain.User;
import pl.zajavka.infrastructure.security.entity.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    UserEntity mapToEntity(User user);
}
