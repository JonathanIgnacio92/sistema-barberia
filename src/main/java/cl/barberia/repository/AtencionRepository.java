package cl.barberia.repository;

import cl.barberia.model.AtencionBarberia;
import java.util.List;

public class AtencionRepository {
    private final AtencionDAO dao = new AtencionDAOImpl();

    public void guardarAtencion(AtencionBarberia atencion) {
        dao.guardar(atencion);
    }

    public List<AtencionBarberia> obtenerTodas() {
        return dao.listarTodas();
    }

    public void actualizarAtencion(AtencionBarberia atencion) {
        dao.actualizar(atencion);
    }

    public void borrarAtencion(int id) {
        dao.eliminar(id);
    }
}