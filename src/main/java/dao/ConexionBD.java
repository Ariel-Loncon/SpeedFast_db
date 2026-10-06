package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión con la base de datos MySQL
 */
public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/speedfast_db";
    private static final String USUARIO = "root";
    private static final String CLAVE = "SWIER34ref3Dr34GT5dfq";
    public static Connection getConexion()
            throws SQLException {
        return DriverManager.getConnection(
                URL,
                USUARIO,
                CLAVE
        );
    }
}
