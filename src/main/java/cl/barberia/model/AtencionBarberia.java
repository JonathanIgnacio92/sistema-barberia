package cl.barberia.model;

// Implementa la interfaz Descontable. Es abstracta porque no crearemos "Atenciones" genéricas, sino servicios específicos.
public abstract class AtencionBarberia implements Descontable {
    private int id;
    private String nombreCliente;
    private String numeroTelefono;
    private String fecha;
    private String hora;
    private String nombreBarbero;
    protected int precioBase; // 'protected' para que las clases hijas puedan usarlo
    protected int duracionMinutos;

    public AtencionBarberia(String nombreCliente, String numeroTelefono, String fecha, String hora, String nombreBarbero) {
        this.nombreCliente = nombreCliente;
        this.numeroTelefono = numeroTelefono;
        this.fecha = fecha;
        this.hora = hora;
        this.nombreBarbero = nombreBarbero;
    }

    // Método Abstracto para Polimorfismo: Cada servicio tendrá insumos distintos.
    public abstract String obtenerInsumosUtilizados();

    // Implementación de la Interfaz: 20% de descuento
    @Override
    public double aplicarDescuentoCumpleanos() {
        return precioBase * 0.80;
    }

    // Getters y Setters básicos
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTipoServicio() { return this.getClass().getSimpleName(); }
    public String getNombreCliente() { return nombreCliente; }
    public String getNumeroTelefono() { return numeroTelefono; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getNombreBarbero() { return nombreBarbero; }
    public int getPrecioBase() { return precioBase; }
    public int getDuracionMinutos() { return duracionMinutos; }
}