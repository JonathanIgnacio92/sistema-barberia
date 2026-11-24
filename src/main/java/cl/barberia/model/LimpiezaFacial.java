package cl.barberia.model;

public class LimpiezaFacial extends AtencionBarberia {

    public LimpiezaFacial(String nombreCliente, String numeroTelefono, String fecha, String hora, String nombreBarbero) {
        super(nombreCliente, numeroTelefono, fecha, hora, nombreBarbero);
        this.precioBase = 15000;
        this.duracionMinutos = 30;
    }

    @Override
    public String obtenerInsumosUtilizados() {
        return "Agua micelar + Toalla caliente + Crema Exfoliante + Crema hidratante ligera.";
    }
}