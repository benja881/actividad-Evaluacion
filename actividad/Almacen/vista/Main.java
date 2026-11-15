package vista;

import controlador.Inventario;
import java.util.List;
import java.util.Scanner;
import modelo.Producto;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();
        int opcion = 0;

        do {
            System.out.println("\n--- SISTEMA DE GESTION DE ALMACEN ---");
            System.out.println("1. Registrar / Reponer producto");
            System.out.println("2. Vender producto");
            System.out.println("3. Ver inventario completo");
            System.out.println("4. Ver reporte de stock critico (<= 5)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nombre del producto: ");
                        String nombreReg = scanner.nextLine();
                        System.out.print("Ingrese la cantidad a registrar/reponer: ");
                        int cantReg = Integer.parseInt(scanner.nextLine());
                        inventario.registrarProducto(nombreReg, cantReg);
                        System.out.println("Operacion realizada con exito!");
                        break;

                    case 2:
                        System.out.print("Ingrese el nombre del producto a vender: ");
                        String nombreVen = scanner.nextLine();
                        System.out.print("Ingrese la cantidad a vender: ");
                        int cantVen = Integer.parseInt(scanner.nextLine());
                        inventario.venderProducto(nombreVen, cantVen);
                        System.out.println("Venta registrada con exito!");
                        break;

                    case 3:
                        System.out.println("\n--- INVENTARIO GENERAL ---");
                        List<Producto> lista = inventario.listarInventario();
                        if (lista.isEmpty()) {
                            System.out.println("El inventario esta vacio.");
                        } else {
                            for (Producto p : lista) {
                                System.out.println("- " + p.getNombre() + " | Stock: " + p.getStock());
                            }
                        }
                        break;

                    case 4:
                        System.out.println("\n--- REPORTE DE STOCK CRITICO (<= 5) ---");
                        List<Producto> criticos = inventario.obtenerStockCritico();
                        if (criticos.isEmpty()) {
                            System.out.println("No hay productos en stock critico.");
                        } else {
                            for (Producto p : criticos) {
                                System.out.println("- " + p.getNombre() + " | Stock actual: " + p.getStock());
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opcion invalida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un valor numerico valido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error de validacion: " + e.getMessage());
            }

        } while (opcion != 5);

        scanner.close();
    }
}
