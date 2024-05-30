package pl.zajavka.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.UserCustomerDTO;
import pl.zajavka.api.dto.UserRestaurantDTO;
import pl.zajavka.domain.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T11:08:20+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User map(UserCustomerDTO userCustomerDTO) {
        if ( userCustomerDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userName( userCustomerDTO.getUserName() );
        user.email( userCustomerDTO.getEmail() );
        user.password( userCustomerDTO.getPassword() );

        return user.build();
    }

    @Override
    public User map(UserRestaurantDTO userRestaurantDTO) {
        if ( userRestaurantDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userName( userRestaurantDTO.getUserName() );
        user.email( userRestaurantDTO.getEmail() );
        user.password( userRestaurantDTO.getPassword() );

        return user.build();
    }
}
