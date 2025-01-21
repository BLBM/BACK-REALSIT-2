package backRealSit2.backRealSit2.service;

import backRealSit2.backRealSit2.entity.Usuario;
import backRealSit2.backRealSit2.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetallesUsuarioServicio implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DetallesUsuarioServicio(UsuarioRepository usuarioRepository){
        this.usuarioRepository= usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getClave()) // Asegúrate de que la contraseña está encriptada
                .roles(usuario.getRole()) // Asigna los roles
                .build();
    }
}
