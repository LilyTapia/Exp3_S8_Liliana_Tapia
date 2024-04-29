package teatromorooptimizado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class teatromorooptimizado {

    private static final int CAPACIDAD_TEATRO = 250;
    private static final int PRECIO_VIP = 25000;
    private static final int PRECIO_PALCOS = 7000;
    private static final int PRECIO_PLATEA_BAJA = 19000;
    private static final int PRECIO_PLATEA_ALTA = 11000;

    private static int[] ventasIDs = new int[CAPACIDAD_TEATRO];
    private static char[] tiposDescuento = new char[CAPACIDAD_TEATRO];

    private static List<String> asientos = new ArrayList<>();
    private static List<String> clientes = new ArrayList<>();

    private static double totalVentas = 0.0;
    private static int idCompra = 1;
    private static int contadorEntradas = 0; // Contador de entradas vendidas

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("======= Bienvenidos al sistema de ventas del teatro moro =======");
        System.out.println("                                                                ");
        System.out.println("Seleccione una de las siguientes opciones para comenzar: ");
        System.out.println("                                                                ");

        do {
            System.out.println("------ MENU PRINCIPAL ------ ");
            System.out.println("                             ");
            System.out.println("1. Venta de entradas");
            System.out.println("2. Eliminar venta");
            System.out.println("3. Actualizar venta");
            System.out.println("4. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    comprarEntrada();
                    break;
                case 2:
                    eliminarVenta();
                    break;
                case 3:
                    actualizarVenta();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    mostrarBoleta();
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor, seleccione nuevamente.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void comprarEntrada() {
        // Incrementar el contador de entradas vendidas
        contadorEntradas++;

        int idVenta = idCompra++;

        if (idVenta > CAPACIDAD_TEATRO) {
            System.out.println("Lo sentimos, el teatro esta lleno.");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("                              ");
        System.out.println("Seleccione el tipo de asiento: ");
        System.out.println("                              ");
        System.out.println("1. VIP (Precio: " + PRECIO_VIP + ")");
        System.out.println("2. Palcos (Precio: " + PRECIO_PALCOS + ")");
        System.out.println("3. Platea Baja (Precio: " + PRECIO_PLATEA_BAJA + ")");
        System.out.println("4. Platea Alta (Precio: " + PRECIO_PLATEA_ALTA + ")");

        int opcionAsiento = scanner.nextInt();

        int precioBase = 0;
        String tipoAsiento = "";
        switch (opcionAsiento) {
            case 1:
                precioBase = PRECIO_VIP;
                tipoAsiento = "VIP";
                break;
            case 2:
                precioBase = PRECIO_PALCOS;
                tipoAsiento = "Palcos";
                break;
            case 3:
                precioBase = PRECIO_PLATEA_BAJA;
                tipoAsiento = "Platea Baja";
                break;
            case 4:
                precioBase = PRECIO_PLATEA_ALTA;
                tipoAsiento = "Platea Alta";
                break;
            default:
                System.out.println("Opcion de asiento no valida.");
                return;
        }

        char esEstudiante;
        double descuento = 0.0;
        char tipoDescuento = 'N'; // Por defecto, no hay descuento

        do {
            System.out.print("Es estudiante? (S/N): ");
            esEstudiante = Character.toUpperCase(scanner.next().charAt(0));

            if (esEstudiante == 'S') {
                descuento = 0.1;
                tipoDescuento = 'S';
                break; // Salir del bucle si es estudiante
            } else if (esEstudiante == 'N') {
                char esTerceraEdad;
                do {
                    System.out.print("Es de la tercera edad? (S/N): ");
                    esTerceraEdad = Character.toUpperCase(scanner.next().charAt(0));
                    if (esTerceraEdad == 'S') {
                        descuento = 0.15;
                        tipoDescuento = 'T';
                        break; // Salir del bucle si es de tercera edad
                    } else if (esTerceraEdad == 'N') {
                        break; // Salir del bucle si no es de tercera edad
                    } else {
                        System.out.println("Opcion no valida. Por favor, responda con S o N.");
                    }
                } while (true);
            } else {
                System.out.println("Opcion no valida. Por favor, responda con S o N.");
            }

        } while (esEstudiante != 'S' && esEstudiante != 'N');

        double precioFinal = precioBase * (1 - descuento);

        System.out.println("                                         ");
        System.out.println("Precio final con descuento: $" + precioFinal);
        System.out.println("                                         ");
        System.out.print("Nombre del cliente: ");
        String nombreCliente = scanner.next();

        // Agregar la venta a los arreglos y listas
        ventasIDs[idVenta - 1] = idVenta;
        tiposDescuento[idVenta - 1] = tipoDescuento;
        asientos.add(tipoAsiento);
        clientes.add(nombreCliente);

        System.out.println("                              ");
        System.out.println("=========== BOLETA ===========");
        System.out.println("ID de Venta: " + idVenta);
        System.out.println("Cliente: " + nombreCliente);
        System.out.println("Tipo de Asiento: " + tipoAsiento);
        System.out.println("Precio Final: $" + precioFinal);
        totalVentas += precioFinal;
    }

    private static void eliminarVenta() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID de la venta a eliminar: ");
        int idVentaEliminar = scanner.nextInt();

        if (idVentaEliminar < 1 || idVentaEliminar > idCompra - 1) {
            System.out.println("ID de venta no valido.");
            scanner.close();
            return;
        }

        // Eliminar la venta de los arreglos y listas
        int indiceVentaEliminar = idVentaEliminar - 1;
        ventasIDs[indiceVentaEliminar] = 0;
        tiposDescuento[indiceVentaEliminar] = 0;
        asientos.remove(indiceVentaEliminar);
        clientes.remove(indiceVentaEliminar);

        System.out.println("La venta con el ID " + idVentaEliminar + " ha sido eliminada correctamente.");
    }

    private static void actualizarVenta() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID de la venta a actualizar: ");
        int idVentaActualizar = scanner.nextInt();

        if (idVentaActualizar < 1 || idVentaActualizar > idCompra - 1 || ventasIDs[idVentaActualizar - 1] == 0) {
            System.out.println("ID de venta no valido.");
            scanner.close();
            return;
        }
        System.out.println("                            ");
        System.out.println("Detalles de la venta actual:");
        System.out.println("ID de Venta: " + ventasIDs[idVentaActualizar - 1]);
        System.out.println("Cliente: " + clientes.get(idVentaActualizar - 1));
        System.out.println("Tipo de Asiento: " + asientos.get(idVentaActualizar - 1));

        System.out.println("Ingrese los nuevos detalles de la venta:");

        scanner.nextLine(); // Consumir la nueva línea pendiente después de nextInt()

        System.out.print("Nuevo nombre del cliente (deje en blanco para mantener el nombre actual): ");
        String nuevoNombreCliente = scanner.nextLine().trim();
        if (!nuevoNombreCliente.isEmpty()) {
            clientes.set(idVentaActualizar - 1, nuevoNombreCliente);
        }

        System.out.print("Nuevo tipo de asiento (deje en blanco para mantener el tipo de asiento actual): ");
        String nuevoTipoAsiento = scanner.nextLine().trim();
        if (!nuevoTipoAsiento.isEmpty()) {
            asientos.set(idVentaActualizar - 1, nuevoTipoAsiento);
        }

        System.out.println("La venta con el ID " + idVentaActualizar + " ha sido actualizada correctamente.");

    }

    private static void mostrarBoleta() {
        System.out.println("\n=========================================================================");
        System.out.println("\n                      BOLETA TEATRO MORO FINAL                           ");
        System.out.println("\n=========================================================================");
        System.out.println("\nID de Venta | Cliente | Tipo de Asiento | Precio Descuento | Precio Final");
        System.out.println("\n=========================================================================");

        for (int i = 0; i < clientes.size(); i++) {
            if (ventasIDs[i] != 0) {
                System.out.printf("\n%-12d%-9s%-17s     $%.2f       $%.2f", ventasIDs[i], clientes.get(i), asientos.get(i), calcularPrecioDescuento(i), calcularPrecioFinal(i));

            }
        }
        System.out.println("\n=========================================================================");
        System.out.println("Total de ventas del teatro moro: $" + totalVentas);
        System.out.println("Total de entradas vendidas: " + contadorEntradas);
        System.out.println("Entradas restantes: " + (CAPACIDAD_TEATRO - contadorEntradas));
        System.out.println("\n=========================================================================");
        
    }

    private static double calcularPrecioDescuento(int indice) {
        double descuento = 0.0;
        double precioBase = 0;

        switch (asientos.get(indice)) {
            case "VIP":
                precioBase = PRECIO_VIP;
                break;
            case "Palcos":
                precioBase = PRECIO_PALCOS;
                break;
            case "Platea Baja":
                precioBase = PRECIO_PLATEA_BAJA;
                break;
            case "Platea Alta":
                precioBase = PRECIO_PLATEA_ALTA;
                break;
        }
        char tipoDescuento = tiposDescuento[indice];
        switch (Character.toUpperCase(tipoDescuento)) {
            case 'S':
                descuento = 0.1;
                break;
            case 'T':
                descuento = 0.15;
                break;
        }

        double precioConDescuento = precioBase * (1 - descuento);

        return precioBase - precioConDescuento;
    }

    private static double calcularPrecioFinal(int indice) {
        double precioBase = 0;
        switch (asientos.get(indice)) {
            case "VIP":
                precioBase = PRECIO_VIP;
                break;
            case "Palcos":
                precioBase = PRECIO_PALCOS;
                break;
            case "Platea Baja":
                precioBase = PRECIO_PLATEA_BAJA;
                break;
            case "Platea Alta":
                precioBase = PRECIO_PLATEA_ALTA;
                break;
        }

        double descuento = 0.0;
        char tipoDescuento = tiposDescuento[indice];
        switch (Character.toUpperCase(tipoDescuento)) {
            case 'S':
                descuento = 0.1;
                break;
            case 'T':
                descuento = 0.15;
                break;
        }

        return precioBase * (1 - descuento);
    }
}
