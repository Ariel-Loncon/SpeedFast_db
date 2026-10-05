package app;

import dao.ConexionBD;
import view.VentanaPrincipal;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (Connection con = ConexionBD.getConexion()) {
            if (con != null && !con.isClosed()) {
                System.out.println(">>> Conexión a la base de datos 'speedfast_db' establecida correctamente.");
            }
        } catch (SQLException e) {
            System.err.println(">>> Error de conexión a la base de datos: " + e.getMessage());
            System.err.println(">>> Asegúrate de que MySQL esté en ejecución y las credenciales en ConexionBD.java sean correctas.");
        }

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}




/*package app;
import dao.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.getConexion()) {
            System.out.println("Conexión exitosa con MySQL.");
        } catch (SQLException e) {
            System.out.println(
                    "Error de conexión: " + e.getMessage());}}}


 */
