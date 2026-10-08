package medihome;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProfesionalSalud extends Usuario implements Notificable {
    private String numeroRegistroProfesional;
    private String especialidad;
    private final List<ServicioDomiciliario> servicios = new ArrayList<>();

    public ProfesionalSalud() {
    }

    public ProfesionalSalud(String identificacion, String nombre, String correo,
                            String numeroRegistroProfesional, String especialidad) {
        super(identificacion, nombre, correo);
        this.numeroRegistroProfesional = numeroRegistroProfesional;
        this.especialidad = especialidad;
    }

    public String getNumeroRegistroProfesional() {
        return numeroRegistroProfesional;
    }

    public void setNumeroRegistroProfesional(String numeroRegistroProfesional) {
        this.numeroRegistroProfesional = numeroRegistroProfesional;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<ServicioDomiciliario> getServicios() {
        return servicios;
    }

    void agregarServicio(ServicioDomiciliario servicio) {
        servicios.add(servicio);
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("[Notificacion a profesional " + getNombre() + " <" + getCorreo() + ">] " + mensaje);
    }

    /** Un profesional esta disponible si no tiene un servicio activo programado en esa fecha. */
    public boolean estaDisponible(LocalDateTime fecha) {
        for (ServicioDomiciliario s : servicios) {
            boolean activo = !"CANCELADO".equals(s.getEstado()) && !"FINALIZADO".equals(s.getEstado());
            if (activo && fecha.equals(s.getFechaProgramada())) {
                return false;
            }
        }
        return true;
    }
}
