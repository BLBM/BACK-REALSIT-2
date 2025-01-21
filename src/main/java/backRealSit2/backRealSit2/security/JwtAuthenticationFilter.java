package backRealSit2.backRealSit2.security;

import backRealSit2.backRealSit2.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String nombreUsuario;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Si no hay token o el encabezado no empieza con "Bearer ", continúa la cadena de filtros.
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7); // Extrae el token sin el "Bearer ".
        nombreUsuario = jwtUtil.extractUsuario(jwtToken); // Extrae el nombre de usuario desde el token.

        if (nombreUsuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                // Carga los detalles del usuario desde la base de datos o la fuente que hayas configurado
                UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario);

                if (jwtUtil.isTokenValid(jwtToken, userDetails.getUsername())) {
                    // Si el token es válido, crea un objeto de autenticación y lo asigna al contexto de seguridad.
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                // Aquí podrías manejar las excepciones de manera más específica, por ejemplo, si el usuario no existe.
                logger.error("Error al validar el token JWT", e);
            }
        }

        // Continua con el siguiente filtro de la cadena
        filterChain.doFilter(request, response);
    }
}
