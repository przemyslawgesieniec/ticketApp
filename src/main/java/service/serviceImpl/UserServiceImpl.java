package main.java.service.serviceImpl;

import main.java.dto.UserDto;
import main.java.model.UserDetailsImpl;
import main.java.model.entity.Role;
import main.java.model.entity.User;
import main.java.repository.UserRepository;
import main.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUserByEmail(email);
        System.out.println("buba");
        if (!user.isPresent()){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new UserDetailsImpl(user.get());
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User save(UserDto registrationRequestParams) {

        User user = new User();
        user.setEmail(registrationRequestParams.getEmail());
        user.setFirstName(registrationRequestParams.getName());
        user.setLastName(registrationRequestParams.getLastName());
        user.setPassword(passwordEncoder.encode(registrationRequestParams.getPassword()));
        user.setRoles(new HashSet<>(Collections.singletonList(new Role("ROLE_USER"))));
        return userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
