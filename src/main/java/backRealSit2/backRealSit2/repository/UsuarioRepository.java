package backRealSit2.backRealSit2.repository;

import backRealSit2.backRealSit2.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCorreo(String correo);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    Optional<Usuario> findByUsuarioId(Long id);
}
