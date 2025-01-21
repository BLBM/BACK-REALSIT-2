package backRealSit2.backRealSit2.controller;

import backRealSit2.backRealSit2.config.SecurityConfig;
import backRealSit2.backRealSit2.entity.LoginRequest;
import backRealSit2.backRealSit2.entity.Usuario;
import backRealSit2.backRealSit2.repository.UsuarioRepository;
import backRealSit2.backRealSit2.service.ValidateService;
import backRealSit2.backRealSit2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/backRealsit2/Auth")
public class AuhtController {


    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  JwtUtil jwtUtil;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private ValidateService validateService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        String validacion = validateService.validarCredenciales(loginRequest);
        if (validacion != null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Credenciales incorrectas");
            response.put("message", "El nombre de usuario o la clave no son correctos.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        try {
            // Buscar al usuario en la base de datos
            Usuario usuario = usuarioRepository.findByNombreUsuario(loginRequest.getNombreUsuario())
                    .orElseThrow(() -> {
                        return new RuntimeException("Usuario no encontrado");
                    });

            // Verificar si la contraseña es correcta
            if (!securityConfig.passwordEncoder().matches(loginRequest.getClave(), usuario.getClave())) {
                throw new RuntimeException("Credenciales inválidas");
            }

            // Autenticación de usuario

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getNombreUsuario(),
                            loginRequest.getClave()
                    )
            );

            String token = jwtUtil.generateToken(authentication.getName());
            System.out.println("token que se envia "+token);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);


        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales inválidas", e);
        }
    }

}
