package main.java.service.serviceImpl;

import main.java.dto.UserDto;
import main.java.model.UserDetailsImpl;
import main.java.model.entity.Role;
import main.java.model.entity.User;
import main.java.repository.UserRepository;
import main.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUserByEmail(email);
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
}
