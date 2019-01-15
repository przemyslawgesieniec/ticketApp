package main.java.service.serviceImpl;

import main.java.dto.UserDto;
import main.java.entity.Role;
import main.java.entity.User;
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

        Optional<User> user = userRepository.findUserByEmail(email);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new UserDetailsServiceImpl(user.get());
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User save(UserDto registrationRequestParams) {

        User user = new User();
        Role role = roleRepository.findRoleByName("ROLE_USER");
        user.setEmail(registrationRequestParams.getEmail());
        user.setFirstName(registrationRequestParams.getName());
        user.setLastName(registrationRequestParams.getLastName());
        user.setPassword(passwordEncoder.encode(registrationRequestParams.getPassword()));
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        return userRepository.save(user);
    }

    @Override
    public void activateUser(String email) {

        User user = getUserByEmail(email);
        user.setEnabled(true);
        userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
