package src.vista;

import src.manager.ManagerSantiago;
import src.manager.ManagerFelipe;
import src.manager.ManagerMichel;

import java.util.Scanner;

public class mainPrincipal {

    public static void main(String[] args) {

        // Instancias de los tres Managers
        ManagerSantiago managerSantiago = new ManagerSantiago();

        ManagerFelipe managerFelipe = new ManagerFelipe();

        Scanner scanner = new Scanner(System.in);

        ManagerMichel managerMichel = new ManagerMichel(scanner);

        int opcionMain;

        do {

            System.out.println("\n======================================");
            System.out.println("     SISTEMA DE GESTIÓN POLI RESTAURANTE");
            System.out.println("======================================");

            System.out.println("1. Administración y Cocina (Ángel)");
            System.out.println("2. Meseros, Mesas y Pedidos (Felipe)");
            System.out.println("3. Menú, Productos y Pagos (Michel)");
            System.out.println("0. Salir");

            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Ingrese un número válido.");
                scanner.next();
                System.out.print("Seleccione una opción: ");
            }

            opcionMain = scanner.nextInt();

            switch (opcionMain) {

                case 1:
                    menuSantiago(managerSantiago, scanner);
                    break;

                case 2:
                    menuFelipe(managerFelipe, scanner);
                    break;

                case 3:
                    menuMichel(managerMichel, scanner);
                    break;

                case 0:
                    System.out.println("Gracias por utilizar el sistema.");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        } while (opcionMain != 0);

        scanner.close();
    }

    // MENÚ SANTIAGO
    private static void menuSantiago(
            ManagerSantiago manager,
            Scanner scanner) {

        manager.iniciar();
    }

    // MENÚ FELIPE
    private static void menuFelipe(
            ManagerFelipe manager,
            Scanner scanner) {

        System.out.println(" MESEROS, MESAS Y PEDIDOS ");

        System.out.println("1. Registrar Cliente");
        System.out.println("2. Asignar Mesa y Mesero");
        System.out.println("3. Crear Nuevo Pedido");
        System.out.println("4. Agregar Producto a Pedido");
        System.out.println("5. Entregar Pedido");

        System.out.print("Seleccione una opción: ");

        int op = scanner.nextInt();

        // Aquí se conectan las funcionalidades de Felipe.
    }

    // MENÚ MICHEL
    private static void menuMichel(ManagerMichel manager, Scanner scanner) {

        manager.mostrarMenuPrincipal();

    }
}
