package cl.barberia.repository;

import cl.barberia.model.AtencionBarberia;
import java.util.List;

public interface AtencionDAO {
    void guardar(AtencionBarberia atencion);
    List<AtencionBarberia> listarTodas();
    void actualizar(AtencionBarberia atencion);
    void eliminar(int id);
}