package backRealSit2.backRealSit2.service;

import backRealSit2.backRealSit2.entity.Usuario;
import backRealSit2.backRealSit2.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario crearUsuario(Usuario usuario){
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodosLosUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorID(Long id){
        return usuarioRepository.findByUsuarioId(id);
    }
    public Usuario obtenerUsuarioPorCorreo(String correo){
        return usuarioRepository.findByCorreo(correo);
    }

    public Optional<Usuario> obtenerUsuarioPorNombreUsuario(String nombreUsuario){
        System.out.println("Buscando usuario: " + nombreUsuario);
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public Usuario actulizarUsuario(Long id,Usuario usuario){
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsuarioId(id);
        if(usuarioExistente.isPresent()) {
            Usuario usuarioToUpdate = usuarioExistente.get();
            usuarioToUpdate.setNombreUsuario(usuario.getNombreUsuario());
            usuarioToUpdate.setCorreo(usuario.getCorreo());
            usuarioToUpdate.setClave(usuario.getClave());
            usuarioToUpdate.setEstado(usuario.isEstado());
            return usuarioRepository.save(usuarioToUpdate);
        }else{
            throw new EntityNotFoundException("Usuario no encontrado");
        }
    }

    public void EliminarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
