package backRealSit2.backRealSit2.util;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    public Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode("c29tZS1yYW5kb20tc2VjcmV0LWtleS1nZW5lcmF0ZWQtYmFzZTY0");
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(String nombreUsuario) {
        String token = Jwts.builder()
                .setSubject(nombreUsuario)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
        System.out.println("Token generado: " + token);
        return token;
    }

    public String extractUsuario(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (SignatureException e) {
            // Manejo de excepción para firma no válida
            System.out.println("Firma no válida: " + e.getMessage()+" "+token);
            return null;
        } catch (ExpiredJwtException e) {
            // Manejo de excepción para token expirado
            System.out.println("Token expirado: " + e.getMessage()+" "+token);
            return null;
        } catch (Exception e) {
            // Manejo de otras excepciones
            System.out.println("Error al extraer el usuario: " + e.getMessage()+" "+token);
            return null;
        }
    }
        public boolean isTokenValid(String token, String nombreUsuario){
            return nombreUsuario.equals(extractUsuario(token)) && !isTokenExpired(token);
        }

        private boolean isTokenExpired(String token){
            return Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
        }


    }

