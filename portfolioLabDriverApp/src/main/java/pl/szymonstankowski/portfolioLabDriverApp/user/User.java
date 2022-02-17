package pl.szymonstankowski.portfolioLabDriverApp.user;

import lombok.Data;
import pl.szymonstankowski.portfolioLabDriverApp.role.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@Table(name = "admins")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public void setRoles(HashSet<Role> roles) {
    }
}
