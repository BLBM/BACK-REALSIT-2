package backRealSit2.backRealSit2.entity;


import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reportes_backrealsit2")
public class Reporte {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportesid;

    @OneToMany(mappedBy = "reporteId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReporteParametro> parametros = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private String descripcion;

    @Column(name = "consultasql",nullable = false)
    private String consultaSQL;

    @Column(nullable = false)
    private String estado;

    @Column(name = "fecha_creacion", nullable = false ,updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    public Reporte() {
    }

    public Reporte(long reportesid, String descripcion, String consultaSQL, String estado, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.reportesid = reportesid;
        this.descripcion = descripcion;
        this.consultaSQL = consultaSQL;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public long getReportesid() {
        return reportesid;
    }

    public Reporte setReportesid(long reportesid) {
        this.reportesid = reportesid;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Reporte setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public String getConsultaSQL() {
        return consultaSQL;
    }

    public Reporte setConsultaSQL(String consultaSQL) {
        this.consultaSQL = consultaSQL;
        return this;
    }

    public String getEstado() {
        return estado;
    }

    public Reporte setEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Reporte setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Reporte setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }
}
