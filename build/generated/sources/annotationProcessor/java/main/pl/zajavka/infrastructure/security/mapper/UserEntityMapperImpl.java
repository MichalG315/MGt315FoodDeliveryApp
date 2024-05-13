package pl.zajavka.infrastructure.security.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.Role;
import pl.zajavka.domain.User;
import pl.zajavka.infrastructure.security.entity.RoleEntity;
import pl.zajavka.infrastructure.security.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-13T22:25:37+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserEntityMapperImpl implements UserEntityMapper {

    @Override
    public UserEntity mapToEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.userName( user.getUserName() );
        userEntity.email( user.getEmail() );
        userEntity.password( user.getPassword() );
        userEntity.active( user.getActive() );
        userEntity.roles( roleSetToRoleEntitySet( user.getRoles() ) );

        return userEntity.build();
    }

    @Override
    public User mapFromEntity(UserEntity saved) {
        if ( saved == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( saved.getId() );
        user.userName( saved.getUserName() );
        user.email( saved.getEmail() );
        user.password( saved.getPassword() );
        user.active( saved.getActive() );
        user.roles( roleEntitySetToRoleSet( saved.getRoles() ) );

        return user.build();
    }

    protected RoleEntity roleToRoleEntity(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleEntity.RoleEntityBuilder roleEntity = RoleEntity.builder();

        roleEntity.id( role.getId() );
        roleEntity.role( role.getRole() );

        return roleEntity.build();
    }

    protected Set<RoleEntity> roleSetToRoleEntitySet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleEntity> set1 = new LinkedHashSet<RoleEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleEntity( role ) );
        }

        return set1;
    }

    protected Role roleEntityToRole(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.id( roleEntity.getId() );
        role.role( roleEntity.getRole() );

        return role.build();
    }

    protected Set<Role> roleEntitySetToRoleSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleEntityToRole( roleEntity ) );
        }

        return set1;
    }
}
