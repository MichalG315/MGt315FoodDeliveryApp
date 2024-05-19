package pl.zajavka.infrastructure.security.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.Role;
import pl.zajavka.infrastructure.security.entity.RoleEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-19T21:32:38+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class RoleEntityMapperImpl implements RoleEntityMapper {

    @Override
    public Role map(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.id( roleEntity.getId() );
        role.role( roleEntity.getRole() );

        return role.build();
    }
}
