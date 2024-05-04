package pl.zajavka.infrastructure.security.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.UserDAO;
import pl.zajavka.infrastructure.security.entity.RoleEntity;
import pl.zajavka.infrastructure.security.entity.UserEntity;
import pl.zajavka.infrastructure.security.mapper.UserEntityMapper;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class FoodAppUserDetailsService implements UserDetailsService {

    private final UserDAO userDAO;
    private final UserEntityMapper userEntityMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userEntityMapper.mapToEntity(userDAO.findByUserName(username));
        List<SimpleGrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private List<SimpleGrantedAuthority> getUserAuthority(Set<RoleEntity> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .distinct()
                .toList();
    }

    private UserDetails buildUserForAuthentication(UserEntity user, List<SimpleGrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getActive(),
                true,
                true,
                true,
                authorities
        );
    }
}
