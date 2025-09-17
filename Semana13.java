package com.mycompany.semana13;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Clase para  cada producto
class Producto {
    String nombre;
    double precio;
    //aqui guardo los datos
    Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
}

public class Semana13 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //aqui creo una lista para el carrito
        List<Producto> carrito = new ArrayList<>();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt(sc);

            switch (opcion) {
                case 1 -> {
                    // Caso 1 Se puede agregar un producto al carrito
                    agregarProducto(carrito, sc);
                }
                case 2 -> {
                    // Caso 2 Muestra los productos que ya estan en el carrito
                    mostrarCarrito(carrito);
                }
                case 3 -> {
                    // Caso 3 Pagar los productos en el carrito
                    pagar(carrito, sc);
                }
                case 4 -> {
                    // Caso 4  Salir del programa
                    System.out.println("Saliendo de la tienda");
                }
                default -> {
                    // Este de aqui sirve para un caso que no e puesto aqui
                    System.out.println("Opcion invalida.");
                }
            }

        } while (opcion != 4); // Aqui hace que termine el bucle y finaliza todo
    }

    static void mostrarMenu() {
        // \n Salto de linea
        System.out.println("\n--- Tienda Express ---");
        System.out.println("(1)Agregar producto");
        System.out.println("(2)Ver el carrito");
        System.out.println("(3)Pagar");
        System.out.println("(4)Salir");
        System.out.print("Seleccione una opcion: ");
    }

    static int leerInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un numero valido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    static double leerDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.print("Ingrese un numero valido: ");
            sc.next();
        }
        return sc.nextDouble();
    }

    static void agregarProducto(List<Producto> carrito, Scanner sc) {
        System.out.print("Ingrese nombre del producto: ");
        String nombre = sc.next();
        System.out.print("Ingrese precio del producto: ");
        double precio = leerDouble(sc);

        if (precio > 0) {
            carrito.add(new Producto(nombre, precio));
        } else {
            System.out.println("El precio debe ser positivo.");
        }
    }

    static double total(double base, double imp) {
        return base + imp;
    }

    static void confirmarCompra(String correo, double total, List<Producto> carrito) {
        // Aqui chequea si el correo contiene @ y .
        if (correo.contains("@") && correo.contains(".")) {
            System.out.println("Compra confirmada para el correo: " + correo);
            //%.2f ayuda poner dos decimales para mas seria %.4f" o mas
            System.out.println("Total pagado: $" + String.format("%.2f", total));
            carrito.clear(); // aqui se elimina el carrito solo si el correo es valido
        } else {
            System.out.println("Correo invalido.");
        }
    }

    static void pagar(List<Producto> carrito, Scanner sc) {
        if (carrito.isEmpty()) {
            System.out.println("El carrito esta vacio");
            return;
        }

        double suma = 0;
        System.out.println("\n--- REPORTE DE COMPRA ---");
        for (Producto p : carrito) {
            System.out.println(p.nombre + " - $" + String.format("%.2f", p.precio));
            suma += p.precio;
        }

        double impuesto = suma * 0.15; //IVA extraño el 12
        double totalFinal = total(suma, impuesto);

        System.out.println("Subtotal: $" + String.format("%.2f", suma));
        System.out.println("Impuesto (15%): $" + String.format("%.2f", impuesto));
        System.out.println("Total a pagar: $" + String.format("%.2f", totalFinal));

        System.out.print("Ingrese su correo para confirmar: ");
        String correo = sc.next();
        confirmarCompra(correo, totalFinal, carrito);
    }

    // Metodo para mostrar el carrito
    static void mostrarCarrito(List<Producto> carrito) {
        if (carrito.isEmpty()) {
            System.out.println("El carrito esta vacio.");
        } else {
            System.out.println("\n--- PRODUCTOS EN EL CARRITO ---");
            for (Producto p : carrito) {
                System.out.println(p.nombre + " - $" + String.format("%.2f", p.precio));
            }
        }
    }
}
