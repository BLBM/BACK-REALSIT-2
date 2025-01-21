package backRealSit2.backRealSit2.entity;


import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_realsit2")

// Permite construir instancias con el patrón Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    @Column(nullable = false,unique = true)
    @Builder.Default
    private String correo;

    @Column(name = "nombre_usuario",nullable = false,unique = true)
    @Builder.Default
    private String nombreUsuario;

    @Column(nullable = false)
    @Builder.Default
    private String Clave;

    @Column(name = "fecha_creacion",updatable = false)
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_actualizacion")
    @Builder.Default
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @Column(nullable = false)
    @Builder.Default
    private boolean estado = true;

    @Column(nullable = false)
    @Builder.Default
    private String role;

    @PreUpdate
    public void preUpdate(){
        this.fechaActualizacion =LocalDateTime.now();
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Usuario setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
        return this;
    }

    public String getCorreo() {
        return correo;
    }

    public Usuario setCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Usuario setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        return this;
    }

    public String getClave() {
        return Clave;
    }

    public Usuario setClave(String clave) {
        Clave = clave;
        return this;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Usuario setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Usuario setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }

    public boolean isEstado() {
        return estado;
    }

    public Usuario setEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Usuario setRole(String role) {
        this.role = role;
        return this;
    }
}
