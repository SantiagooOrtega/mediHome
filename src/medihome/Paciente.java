package medihome;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Usuario implements Notificable {
    private String telefono;
    private String direccion;
    private final List<ServicioDomiciliario> servicios = new ArrayList<>();

    public Paciente() {
    }

    public Paciente(String identificacion, String nombre, String correo, String telefono, String direccion) {
        super(identificacion, nombre, correo);
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<ServicioDomiciliario> getServicios() {
        return servicios;
    }

    void agregarServicio(ServicioDomiciliario servicio) {
        servicios.add(servicio);
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("[Notificacion a paciente " + getNombre() + " <" + getCorreo() + ">] " + mensaje);
    }
}
