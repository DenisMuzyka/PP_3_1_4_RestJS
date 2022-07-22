package ru.kata.spring.boot_security.bootstrap.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(Long id, String role, Set<User> users) {
        this.id = id;
        this.role = role;
        this.users = users;
    }

    public String getRoleAsString() {
        String s = "";
        if (role.contains("ROLE_ADMIN")) {
            s = "Amin";
        } else if (role.contains("ROLE_USER")) {
            s = "User";
        }
        return s;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}
