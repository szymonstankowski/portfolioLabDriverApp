package pl.szymonstankowski.portfolioLabDriverApp.user;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.szymonstankowski.portfolioLabDriverApp.role.Role;
import pl.szymonstankowski.portfolioLabDriverApp.role.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    public static final int ENABLED = 1;
    public static final String ROLE_USER = "ROLE_USER";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUserName(String name) {
        return userRepository.findByUsername(name);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(ENABLED);
        Role userRole = roleRepository.findByName(ROLE_USER);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
}
