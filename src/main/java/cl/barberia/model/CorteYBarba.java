package cl.barberia.model;

public class CorteYBarba extends AtencionBarberia {

    public CorteYBarba(String nombreCliente, String numeroTelefono, String fecha, String hora, String nombreBarbero) {
        super(nombreCliente, numeroTelefono, fecha, hora, nombreBarbero);
        this.precioBase = 27000;
        this.duracionMinutos = 60;
    }

    @Override
    public String obtenerInsumosUtilizados() {
        return "Toalla Caliente + crema de barbear + after shave.";
    }
}