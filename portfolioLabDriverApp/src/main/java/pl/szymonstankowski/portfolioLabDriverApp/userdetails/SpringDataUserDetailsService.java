package pl.szymonstankowski.portfolioLabDriverApp.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.szymonstankowski.portfolioLabDriverApp.role.Role;
import pl.szymonstankowski.portfolioLabDriverApp.user.User;
import pl.szymonstankowski.portfolioLabDriverApp.user.UserService;

import java.util.HashSet;
import java.util.Set;

public class SpringDataUserDetailsService implements UserDetailsService {

    private UserService userService;

    public SpringDataUserDetailsService() {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> grantedAuthority = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthority.add(new SimpleGrantedAuthority((role.getName())));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthority);
    }
}

