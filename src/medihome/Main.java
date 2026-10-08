package medihome;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        Paciente paciente = new Paciente("1085123456", "Maria Lopez", "maria.lopez@correo.com",
                "3001234567", "Calle 10 # 5-20, Pasto");
        ProfesionalSalud profesional = new ProfesionalSalud("1085987654", "Dr. Carlos Perez",
                "carlos.perez@medihome.com", "RM-45821", "Medicina General");

        EquipoAtencion equipo = new EquipoAtencion("EQ-01", "Equipo Norte", "Norte de Pasto");
        equipo.agregarProfesional(profesional);

        ServicioDomiciliario servicio = new ServicioDomiciliario("SD-001", paciente,
                paciente.getDireccion(), "Control de presion arterial");
        servicio.programar(LocalDateTime.of(2026, 10, 15, 9, 0));
        servicio.asignarProfesional(profesional);

        servicio.iniciarAtencion();
        AtencionMedica atencion = servicio.getAtencion();
        atencion.setFechaHoraInicio(LocalDateTime.of(2026, 10, 15, 9, 5));

        MedicionSignosVitales medicion = new MedicionSignosVitales();
        medicion.setTemperatura(36.8);
        medicion.setFrecuenciaCardiaca(78);
        medicion.setPresionSistolica(125);
        medicion.setPresionDiastolica(82);
        medicion.setSaturacionOxigeno(97.0);
        medicion.realizarMedicion();
        medicion.setFechaHora(LocalDateTime.of(2026, 10, 15, 9, 15));
        atencion.agregarMedicion(medicion);

        atencion.setObservaciones("Paciente estable, sin sintomas de alarma.");
        atencion.setRecomendaciones("Mantener dieta baja en sodio y control en 15 dias.");
        servicio.finalizar();
        atencion.setFechaHoraFin(LocalDateTime.of(2026, 10, 15, 9, 40));

        imprimirReporte(paciente, profesional, equipo, servicio);
    }

    private static void imprimirReporte(Paciente paciente, ProfesionalSalud profesional,
                                        EquipoAtencion equipo, ServicioDomiciliario servicio) {
        AtencionMedica atencion = servicio.getAtencion();
        System.out.println();
        System.out.println("=========== REPORTE DE ATENCION MEDIHOME ===========");
        System.out.println("PACIENTE");
        System.out.println("  Identificacion: " + paciente.getIdentificacion());
        System.out.println("  Nombre:         " + paciente.getNombre());
        System.out.println("  Telefono:       " + paciente.getTelefono());
        System.out.println("  Direccion:      " + paciente.getDireccion());
        System.out.println("PROFESIONAL");
        System.out.println("  Nombre:         " + profesional.getNombre());
        System.out.println("  Registro:       " + profesional.getNumeroRegistroProfesional());
        System.out.println("  Especialidad:   " + profesional.getEspecialidad());
        System.out.println("  Equipo:         " + equipo.getNombre() + " (" + equipo.getZonaCobertura() + ")");
        System.out.println("SERVICIO DOMICILIARIO");
        System.out.println("  Codigo:         " + servicio.getCodigo());
        System.out.println("  Motivo:         " + servicio.getMotivo());
        System.out.println("  Programado:     " + servicio.getFechaProgramada().format(FMT));
        System.out.println("  Direccion:      " + servicio.getDireccionAtencion());
        System.out.println("  Estado:         " + servicio.getEstado());
        System.out.println("ATENCION MEDICA");
        System.out.println("  Inicio:         " + atencion.getFechaHoraInicio().format(FMT));
        System.out.println("  Fin:            " + atencion.getFechaHoraFin().format(FMT));
        System.out.println("  Observaciones:  " + atencion.getObservaciones());
        System.out.println("  Recomendaciones:" + " " + atencion.getRecomendaciones());
        System.out.println("SIGNOS VITALES");
        for (MedicionSignosVitales m : atencion.getMediciones()) {
            System.out.println("  " + m.getFechaHora().format(FMT) + " -> " + m);
        }
        System.out.println("====================================================");
    }
}
