package cn.edu.bupt.springsecurity.userdetails.demo.bo;

import cn.edu.bupt.springsecurity.userdetails.demo.entity.Authorities;
import cn.edu.bupt.springsecurity.userdetails.demo.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private Boolean enabled;
    private List<GrantedAuthority> auths;

    public CustomUserDetails(Users users, List<GrantedAuthority> auths) {
        this.username = users.getUsername();
        this.password = users.getPassword();
        this.enabled = users.getEnabled();
        this.auths = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.auths;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
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
        return enabled;
    }
}
