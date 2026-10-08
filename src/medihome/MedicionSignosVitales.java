package medihome;

import java.time.LocalDateTime;

public class MedicionSignosVitales {
    private LocalDateTime fechaHora;
    private double temperatura;
    private int frecuenciaCardiaca;
    private int presionSistolica;
    private int presionDiastolica;
    private double saturacionOxigeno;

    public MedicionSignosVitales() {
    }

    public MedicionSignosVitales(LocalDateTime fechaHora, double temperatura, int frecuenciaCardiaca,
                                 int presionSistolica, int presionDiastolica, double saturacionOxigeno) {
        this.fechaHora = fechaHora;
        this.temperatura = temperatura;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.presionSistolica = presionSistolica;
        this.presionDiastolica = presionDiastolica;
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public int getPresionSistolica() {
        return presionSistolica;
    }

    public void setPresionSistolica(int presionSistolica) {
        this.presionSistolica = presionSistolica;
    }

    public int getPresionDiastolica() {
        return presionDiastolica;
    }

    public void setPresionDiastolica(int presionDiastolica) {
        this.presionDiastolica = presionDiastolica;
    }

    public double getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(double saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    /** Registra la medicion con la fecha y hora actuales. */
    public void realizarMedicion() {
        this.fechaHora = LocalDateTime.now();
        System.out.println("Medicion registrada: " + this);
    }

    @Override
    public String toString() {
        return String.format("T=%.1f C, FC=%d lpm, PA=%d/%d mmHg, SpO2=%.1f%%",
                temperatura, frecuenciaCardiaca, presionSistolica, presionDiastolica, saturacionOxigeno);
    }
}
