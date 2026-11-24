package cl.barberia.model;

public class CorteCabello extends AtencionBarberia {

    public CorteCabello(String nombreCliente, String numeroTelefono, String fecha, String hora, String nombreBarbero) {
        super(nombreCliente, numeroTelefono, fecha, hora, nombreBarbero);
        this.precioBase = 17000;
        this.duracionMinutos = 30;
    }

    @Override
    public String obtenerInsumosUtilizados() {
        return "Aplicación de productos, ceras o cremas para el cabello.";
    }
}