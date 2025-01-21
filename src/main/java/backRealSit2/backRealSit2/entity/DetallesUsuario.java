package backRealSit2.backRealSit2.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class DetallesUsuario implements UserDetails {

    private final Usuario usuario;
    public DetallesUsuario(Usuario usuario){
        this.usuario= usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority>getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_"+usuario.getRole()));
    }

    @Override
    public String getPassword(){
        return usuario.getClave();
    }

    @Override
    public String getUsername(){
        return usuario.getNombreUsuario();
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
