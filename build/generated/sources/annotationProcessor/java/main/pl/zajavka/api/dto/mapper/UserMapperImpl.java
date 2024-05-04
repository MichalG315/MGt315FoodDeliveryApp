package pl.zajavka.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.UserDTO;
import pl.zajavka.domain.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T13:06:50+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User map(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userName( userDTO.getUserName() );
        user.email( userDTO.getEmail() );
        user.password( userDTO.getPassword() );
        user.role( userDTO.getRole() );
        user.name( userDTO.getName() );
        user.surname( userDTO.getSurname() );
        user.phone( userDTO.getPhone() );

        return user.build();
    }
}
