package main.java.service.serviceImpl;

import main.java.dto.UserDto;
import main.java.entity.RoleEntity;
import main.java.entity.UserEntity;
import main.java.repository.RoleRepository;
import main.java.repository.UserRepository;
import main.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findUserByEmail(email);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new UserDetailsServiceImpl(user.get());
    }


    @Override
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findUserByEmail(email).map(UserEntity::toDto);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).toDto();
    }

    @Override
    public UserDto save(UserDto registrationRequestParams) {

        UserEntity user = new UserEntity();
        RoleEntity role = roleRepository.findRoleByName("ROLE_USER");
        user.setEmail(registrationRequestParams.getEmail());
        user.setFirstName(registrationRequestParams.getName());
        user.setLastName(registrationRequestParams.getLastName());
        user.setPassword(passwordEncoder.encode(registrationRequestParams.getPassword()));
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        return userRepository.save(user).toDto();
    }

    @Override
    public void activateUser(String email) {

        UserEntity user = userRepository.getUserByEmail(email);
        user.setEnabled(true);
        userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
