
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.EstadoPedido;
import model.Pedido;

public class PedidoDAO {

    public boolean guardar(Pedido pedido) {
        String sql = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccionEntrega());
            ps.setString(2, pedido.getTipoEntrega());
            ps.setString(3, pedido.getEstado() != null ? pedido.getEstado().name() : "PENDIENTE");

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar pedido: " + e.getMessage());
            return false;
        }
    }

    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT id, direccion, tipo, estado FROM pedido";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Convertir la cadena almacenada en BD al tipo Enum EstadoPedido
                String estadoStr = rs.getString("estado");
                EstadoPedido estadoEnum = EstadoPedido.valueOf(estadoStr);

                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getString("direccion"),
                        rs.getString("tipo"),
                        estadoEnum
                );
                lista.add(pedido);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar pedidos: " + e.getMessage());
        }

        return lista;
    }
}
