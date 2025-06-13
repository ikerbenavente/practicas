package com.akihabara.market.view;
import java.util.Scanner;

public class InterfazConsola {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n===== Akihabara Market =====");
        System.out.println("1. Añadir producto");
        System.out.println("2. Consultar producto por ID");
        System.out.println("3. Listar todos los productos");
        System.out.println("4. Buscar productos por nombre");
        System.out.println("5. Buscar productos por categoría");
        System.out.println("6. Actualizar producto");
        System.out.println("7. Eliminar producto");
        System.out.println("8. Salir");
        System.out.print("Elige una opción: ");
    }

    public int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.print("Opción no válida. Intenta de nuevo: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public int leerId() {
        System.out.print("Introduce el ID del producto: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Debe ser un número entero. Intenta de nuevo: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public String leerNombre() {
        System.out.print("Introduce el nombre del producto: ");
        scanner.nextLine(); 
        return scanner.nextLine();
    }

    public String leerCategoria() {
        System.out.print("Introduce la categoría (Figura, Manga, Póster, Llavero, Ropa): ");
        scanner.nextLine();
        return scanner.nextLine();
    }


    public double leerPrecio() {
        System.out.print("Introduce el precio: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Debe ser un número decimal. Intenta de nuevo: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public int leerStock() {
        System.out.print("Introduce el stock: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Debe ser un número entero. Intenta de nuevo: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarProducto(String productoStr) {
        System.out.println(productoStr);
    }
}