package src.vista;

import src.manager.ManagerSantiago;
import src.manager.ManagerFelipe;

import java.util.Scanner;

public class mainPrincipal {

    public static void main(String[] args) {
        // Instancia de los 3 Managers individuales
          Scanner sc = new Scanner(System.in);
        ManagerSantiago managerSantiago = new ManagerSantiago();
        ManagerFelipe managerFelipe = new ManagerFelipe();
       
        System.out.println("Elige el manager que vas a usar ");
        System.out.println("Opciones: 1.Manager de Felipe, 2.Manager de Michel,  3.Manager de Santiago");
        int opciones = sc.nextInt();
           switch(opciones) {
            case 1:
            managerFelipe.mostrarMenuPrincipal();
            break;
            case 2:
            
            managerSantiago.mostrarMenuPrincipal();
            break;
    } 
        Scanner scanner = new Scanner(System.in);
        int opcionMain = -1;

        while (opcionMain <= 4 && opcionMain >= 1) {
          
            System.out.println(" SISTEMA DE GESTIÓN POLI RESTAURANTE ");
            System.out.println("1. Administración y Cocina (Ángel)");
            System.out.println("2. Meseros, Mesas y Pedidos (Felipe)");
            System.out.println("3. Menú, Productos y Pagos (Michel)");
            System.out.println("-. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcionMain = scanner.nextInt();
                
                    switch (opcionMain) {
                    case 1:
                        menuSantiago(managerSantiago, scanner);;
                        break;
                    case 2:
                        menuFelipe(managerFelipe, scanner);
                        break;
                    case 3:
                        menuMichel(managerMichel, scanner);
                        break;
                    default:
                      System.out.println("Opción no válida.");
                }
            }
        }
    }
    // MENÚ Santiago
    private static void menuSantiago(ManagerSantiago manager, Scanner scanner) {
        ManagerSantiago managerSantiago = new ManagerSantiago();
        managerSantiago.iniciar();

    }

    // MENÚ Felipe
    private static void menuFelipe(ManagerFelipe manager, Scanner scanner) {
        System.out.println(" MESEROS, MESAS Y PEDIDOS ");
        System.out.println("1. Registrar Cliente");
        System.out.println("2. Asignar Mesa y Mesero");
        System.out.println("3. Crear Nuevo Pedido");
        System.out.println("4. Agregar Producto a Pedido");
        System.out.println("5. Entregar Pedido");
        System.out.print("seleccione una opción");
        int op = scanner.nextInt();
       
    }

    // MENÚ Michel
    
}
