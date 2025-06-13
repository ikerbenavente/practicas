package com.akihabara.market.dao;

import com.akihabara.market.model.ProductoOtaku;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public void agregarProducto(ProductoOtaku producto) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = new DatabaseConnection().getConexion();
            String sql = "INSERT INTO producto (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCategoria());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());

            stmt.executeUpdate();
            System.out.println("Producto agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, conn);
        }
    }

    public ProductoOtaku obtenerProductoPorId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProductoOtaku producto = null;

        try {
            conn = new DatabaseConnection().getConexion();
            String sql = "SELECT * FROM producto WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                producto = new ProductoOtaku(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener producto: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, stmt, conn);
        }

        return producto;
    }

    public List<ProductoOtaku> obtenerTodosLosProductos() {
        List<ProductoOtaku> productos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseConnection().getConexion();
            String sql = "SELECT * FROM producto";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                productos.add(new ProductoOtaku(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, stmt, conn);
        }

        return productos;
    }

    public boolean actualizarProducto(ProductoOtaku producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean actualizado = false;

        try {
            conn = new DatabaseConnection().getConexion();
            String sql = "UPDATE producto SET nombre = ?, categoria = ?, precio = ?, stock = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCategoria());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());
            stmt.setInt(5, producto.getId());

            actualizado = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(" Error al actualizar producto: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, conn);
        }

        return actualizado;
    }

    public boolean eliminarProducto(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean eliminado = false;

        try {
            conn = new DatabaseConnection().getConexion();
            String sql = "DELETE FROM producto WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            eliminado = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, conn);
        }

        return eliminado;
    }

    public List<ProductoOtaku> buscarProductosPorNombre(String nombre) {
        List<ProductoOtaku> productos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseConnection().getConexion();
            String sql = "SELECT * FROM producto WHERE nombre LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nombre + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                productos.add(new ProductoOtaku(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar por nombre: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, stmt, conn);
        }

        return productos;
    }

    public List<ProductoOtaku> buscarProductoPorCategoria(String categoria) {
        List<ProductoOtaku> productos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseConnection().getConexion();
            String sql = "SELECT * FROM producto WHERE categoria = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria);
            rs = stmt.executeQuery();

            while (rs.next()) {
                productos.add(new ProductoOtaku(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar por categoría: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, stmt, conn);
        }

        return productos;
    }

    // Métodos de utilidad para cerrar recursos
    private void cerrarRecursos(AutoCloseable... recursos) {
        for (AutoCloseable recurso : recursos) {
            if (recurso != null) {
                try {
                    recurso.close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar recurso: " + e.getMessage());
                }
            }
        }
    }
}
