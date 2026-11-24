package cl.barberia.repository;

import cl.barberia.model.AtencionBarberia;
import cl.barberia.model.CorteYBarba;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtencionDAOImpl implements AtencionDAO {

    @Override
    public void guardar(AtencionBarberia atencion) {
        String sql = "INSERT INTO atenciones (nombre_cliente, numero_telefono, fecha, hora, nombre_barbero, tipo_servicio, precio_base) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, atencion.getNombreCliente());
            stmt.setString(2, atencion.getNumeroTelefono());
            stmt.setString(3, atencion.getFecha());
            stmt.setString(4, atencion.getHora());
            stmt.setString(5, atencion.getNombreBarbero());
            stmt.setString(6, atencion.getTipoServicio());
            stmt.setInt(7, atencion.getPrecioBase());
            stmt.executeUpdate();
            System.out.println("Atención guardada en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AtencionBarberia> listarTodas() {
        List<AtencionBarberia> lista = new ArrayList<>();
        String sql = "SELECT * FROM atenciones";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                AtencionBarberia atencion = new CorteYBarba(
                        rs.getString("nombre_cliente"),
                        rs.getString("numero_telefono"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("nombre_barbero")
                );
                atencion.setId(rs.getInt("id"));
                lista.add(atencion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizar(AtencionBarberia atencion) {
        String sql = "UPDATE atenciones SET nombre_cliente = ?, numero_telefono = ?, fecha = ?, hora = ?, nombre_barbero = ?, tipo_servicio = ?, precio_base = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, atencion.getNombreCliente());
            stmt.setString(2, atencion.getNumeroTelefono());
            stmt.setString(3, atencion.getFecha());
            stmt.setString(4, atencion.getHora());
            stmt.setString(5, atencion.getNombreBarbero());
            stmt.setString(6, atencion.getTipoServicio());
            stmt.setInt(7, atencion.getPrecioBase());
            stmt.setInt(8, atencion.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM atenciones WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}