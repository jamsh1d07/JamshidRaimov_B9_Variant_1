package uz.pdp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;

import java.util.Collection;
import java.util.Set;


@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {

    public User(String fullName, Set<Role> roles, String userName, String password, boolean enabled) {
        this.fullName = fullName;
        this.roles = roles;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;


    @Column(unique = true, nullable = false)
    private String userName; //phone login
    private String password;
    @ManyToMany
    private Set<Role> roles;

    private boolean enabled = true; //tizimdan foydalanish huquqi
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
