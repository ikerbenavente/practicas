package com.akihabara.market.model;

import com.akihabara.market.dao.ProductoDAO;
import com.akihabara.market.view.InterfazConsola;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InterfazConsola vista = new InterfazConsola();
        ProductoDAO dao = new ProductoDAO();

        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();

            switch (opcion) {
                case 1 -> {
                    String nombre = vista.leerNombre();
                    String categoria = vista.leerCategoria();
                    double precio = vista.leerPrecio();
                    int stock = vista.leerStock();
                    ProductoOtaku nuevo = new ProductoOtaku(nombre, categoria, precio, stock);
                    dao.agregarProducto(nuevo);
                }
                case 2 -> {
                    int id = vista.leerId();
                    ProductoOtaku prod = dao.obtenerProductoPorId(id);
                    if (prod != null)
                        vista.mostrarProducto(prod.toString());
                    else
                        vista.mostrarMensaje("Producto no encontrado.");
                }
                case 3 -> {
                    List<ProductoOtaku> lista = dao.obtenerTodosLosProductos();
                    lista.forEach(p -> vista.mostrarProducto(p.toString()));
                }
                case 4 -> {
                    String nombre = vista.leerNombre();
                    List<ProductoOtaku> resultados = dao.buscarProductosPorNombre(nombre);
                    resultados.forEach(p -> vista.mostrarProducto(p.toString()));
                }
                case 5 -> {
                    String cat = vista.leerCategoria();
                    List<ProductoOtaku> porCategoria = dao.buscarProductoPorCategoria(cat);
                    porCategoria.forEach(p -> vista.mostrarProducto(p.toString()));
                }
                case 6 -> {
                    int id = vista.leerId();
                    ProductoOtaku p = dao.obtenerProductoPorId(id);
                    if (p == null) {
                        vista.mostrarMensaje("Producto no encontrado.");
                        break;
                    }
                    vista.mostrarMensaje("Introduce los nuevos datos:");
                    String nombre = vista.leerNombre();
                    String categoria = vista.leerCategoria();
                    double precio = vista.leerPrecio();
                    int stock = vista.leerStock();

                    p.setNombre(nombre);
                    p.setCategoria(categoria);
                    p.setPrecio(precio);
                    p.setStock(stock);

                    boolean actualizado = dao.actualizarProducto(p);
                    if (actualizado)
                        vista.mostrarMensaje("Producto actualizado correctamente.");
                    else
                        vista.mostrarMensaje("Error al actualizar producto.");
                }
                case 7 -> {
                    int idEliminar = vista.leerId();
                    boolean eliminado = dao.eliminarProducto(idEliminar);
                    if (eliminado)
                        vista.mostrarMensaje("Producto eliminado correctamente.");
                    else
                        vista.mostrarMensaje("No se pudo eliminar el producto.");
                }
                case 8 -> vista.mostrarMensaje("Saliendo del programa...");
                default -> vista.mostrarMensaje("Opción no válida.");
            }
        } while (opcion != 8);
    }
}
