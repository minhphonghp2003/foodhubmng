package com.minh.foodmng.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.minh.foodmng.order.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users", schema = "public")
@JsonIgnoreProperties(value = {
        "id",
        "password"
})

@ToString(exclude = {"tables"})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;
    @Column(name = "first_name", nullable = false)
    protected String firstname;
    @Column(name = "last_name", nullable = false)
    protected String lastname;
    @Column(unique = true)
    protected String email;
    @Column(nullable = false, unique = true)
    protected String username;
    @Column(length = 15)
    protected String phone;
    @Enumerated(EnumType.STRING)
    protected Role role;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<com.minh.foodmng.order.Table> tables;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Order> orders;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
