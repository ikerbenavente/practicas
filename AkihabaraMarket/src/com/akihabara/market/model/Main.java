package com.akihabara.market.model;
import com.akihabara.market.dao.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        // Crear conexión a la base de datos
        DatabaseConnection db = new DatabaseConnection();

        // Cerrar la conexión (prueba)
        db.cerrarConexion();
    }
}
