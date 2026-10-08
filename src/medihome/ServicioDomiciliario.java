package medihome;

import java.time.LocalDateTime;

public class ServicioDomiciliario {
    private String codigo;
    private LocalDateTime fechaProgramada;
    private String direccionAtencion;
    private String motivo;
    private String estado;
    private Paciente paciente;
    private ProfesionalSalud profesional;
    private AtencionMedica atencion;

    public ServicioDomiciliario() {
    }

    public ServicioDomiciliario(String codigo, Paciente paciente, String direccionAtencion, String motivo) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.direccionAtencion = direccionAtencion;
        this.motivo = motivo;
        this.estado = "SOLICITADO";
        paciente.agregarServicio(this);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDateTime fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getDireccionAtencion() {
        return direccionAtencion;
    }

    public void setDireccionAtencion(String direccionAtencion) {
        this.direccionAtencion = direccionAtencion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public ProfesionalSalud getProfesional() {
        return profesional;
    }

    public AtencionMedica getAtencion() {
        return atencion;
    }

    public void programar(LocalDateTime fecha) {
        this.fechaProgramada = fecha;
        this.estado = "PROGRAMADO";
        paciente.notificar("Su servicio " + codigo + " fue programado para " + fecha);
    }

    public void asignarProfesional(ProfesionalSalud profesional) {
        if (fechaProgramada != null && !profesional.estaDisponible(fechaProgramada)) {
            throw new IllegalStateException("El profesional no esta disponible en " + fechaProgramada);
        }
        this.profesional = profesional;
        profesional.agregarServicio(this);
        profesional.notificar("Se le asigno el servicio " + codigo + " en " + direccionAtencion);
    }

    public void iniciarAtencion() {
        if (profesional == null) {
            throw new IllegalStateException("No se puede iniciar sin profesional asignado");
        }
        this.atencion = new AtencionMedica(LocalDateTime.now());
        this.estado = "EN_CURSO";
    }

    public void finalizar() {
        if (atencion == null) {
            throw new IllegalStateException("No hay una atencion en curso");
        }
        atencion.setFechaHoraFin(LocalDateTime.now());
        this.estado = "FINALIZADO";
        paciente.notificar("Su servicio " + codigo + " ha finalizado");
    }

    public void cancelar() {
        this.estado = "CANCELADO";
        paciente.notificar("Su servicio " + codigo + " fue cancelado");
    }
}
