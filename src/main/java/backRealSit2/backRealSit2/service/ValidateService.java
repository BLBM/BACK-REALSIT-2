package backRealSit2.backRealSit2.service;

import backRealSit2.backRealSit2.entity.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class ValidateService {
    public String validarCredenciales(LoginRequest loginRequest) {
        // Validación: el cuerpo de la solicitud no debe ser nulo
        if (loginRequest == null) {
            return "El cuerpo de la solicitud está vacío. Debes enviar el nombre de usuario y la clave.";
        }

        // Validación: nombreUsuario no debe ser nulo ni vacío
        if (loginRequest.getNombreUsuario() == null || loginRequest.getNombreUsuario().trim().isEmpty()) {
            return "El nombre de usuario no puede estar vacío.";
        }

        // Validación: clave no debe ser nula ni vacía
        if (loginRequest.getClave() == null || loginRequest.getClave().trim().isEmpty()) {
            return "La clave no puede estar vacía.";
        }

        // Validación: no se permiten campos adicionales
        // Puedes implementar una validación adicional si tu lógica lo requiere

        // Si todo es válido
        return null;
    }
}
