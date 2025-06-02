
package com.akihabara.market.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Constantes de conexión
    private static final String DB_URL = "jdbc:mysql://localhost:3306/akihabara_db";
    private static final String USER = "userAkihabara";
    private static final String PASSWORD = "Akihabara123.";

    // Objeto Connection
    private Connection conexion;

    // Constructor: carga el driver y conecta a la base de datos
    public DatabaseConnection() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Se ha cargado en memoria el driver de MySQL.");

            // Establecer la conexión
            conexion = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Se ha establecido con éxito la conexión a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }

    // Método que retorna la conexión activa
    public Connection getConexion() {
        return conexion;
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Se ha cerrado la conexión con la base de datos.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión:");
                e.printStackTrace();
            }
        }
    }
}
