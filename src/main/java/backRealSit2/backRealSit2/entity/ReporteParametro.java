package backRealSit2.backRealSit2.entity;


import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "reporte_param_backrealsit2")
public class ReporteParametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Builder.Default
    @Column(nullable = false)
    private String etiqueta;

    @Column(name = "tipo_objeto", nullable = false)
    private String tipoObjeto;

    @Column(nullable = false)
    private boolean obligatorio;

    @ManyToOne
    @JoinColumn(name = "reporte_id", nullable = false)
    private Reporte reporteId;

    @Column(nullable = false)
    private String codigo;

    @Column(name = "tipo_dato", nullable = false)
    private String tipo_dato;

    @Column(nullable = false)
    private int orden;

    @Column(nullable = false)
    private int descripcion;

    @Column(name = "valor_por_defecto", nullable = false)
    private String valorXDefecto;


    @Column(name = "fecha_creacion", nullable = false,updatable = false)
    private String fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private String fechaActualizacion;


    public ReporteParametro() {
    }

    public ReporteParametro(long id, String etiqueta, String tipoObjeto, boolean obligatorio, Reporte reporteId, String codigo, String tipo_dato, int orden, int descripcion, String valorXDefecto, String fechaCreacion, String fechaActualizacion) {
        this.id = id;
        this.etiqueta = etiqueta;
        this.tipoObjeto = tipoObjeto;
        this.obligatorio = obligatorio;
        this.reporteId = reporteId;
        this.codigo = codigo;
        this.tipo_dato = tipo_dato;
        this.orden = orden;
        this.descripcion = descripcion;
        this.valorXDefecto = valorXDefecto;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public long getId() {
        return id;
    }

    public ReporteParametro setId(long id) {
        this.id = id;
        return this;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public ReporteParametro setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
        return this;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public ReporteParametro setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
        return this;
    }

    public boolean isObligatorio() {
        return obligatorio;
    }

    public ReporteParametro setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
        return this;
    }

    public Reporte getReporteId() {
        return reporteId;
    }

    public ReporteParametro setReporteId(Reporte reporteId) {
        this.reporteId = reporteId;
        return this;
    }

    public String getCodigo() {
        return codigo;
    }

    public ReporteParametro setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getTipo_dato() {
        return tipo_dato;
    }

    public ReporteParametro setTipo_dato(String tipo_dato) {
        this.tipo_dato = tipo_dato;
        return this;
    }

    public int getOrden() {
        return orden;
    }

    public ReporteParametro setOrden(int orden) {
        this.orden = orden;
        return this;
    }

    public int getDescripcion() {
        return descripcion;
    }

    public ReporteParametro setDescripcion(int descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public String getValorXDefecto() {
        return valorXDefecto;
    }

    public ReporteParametro setValorXDefecto(String valorXDefecto) {
        this.valorXDefecto = valorXDefecto;
        return this;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public ReporteParametro setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public ReporteParametro setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }
}
