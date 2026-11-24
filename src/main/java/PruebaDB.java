import cl.barberia.model.AtencionBarberia;
import cl.barberia.model.CorteYBarba;
import cl.barberia.repository.AtencionRepository;
import java.util.List;

public class PruebaDB {
    public static void main(String[] args) {
        AtencionRepository repo = new AtencionRepository();

        // 1. Creamos una atención de prueba
        AtencionBarberia prueba = new CorteYBarba(
                "Juan Pérez",
                "+56912345678",
                "2026-11-25",
                "15:30:00",
                "Barbero 1"
        );

        // 2. La guardamos en MySQL
        repo.guardarAtencion(prueba);

        // 3. Leemos todas las atenciones desde MySQL para confirmar
        List<AtencionBarberia> lista = repo.obtenerTodas();
        System.out.println("Total de atenciones en BD: " + lista.size());
        for (AtencionBarberia a : lista) {
            System.out.println("- " + a.getNombreCliente() + " | $" + a.getPrecioBase() + " | Insumos: " + a.obtenerInsumosUtilizados());
        }
    }
}