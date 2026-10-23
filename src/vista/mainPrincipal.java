package src.vista;

import manager.ManagerSantiago;
import manager.MiManagerMesero;
import manager.ManagerMichel;
import java.util.Scanner;

public class mainPrincipal {

    public static void main(String[] args) {
        // Instancia de los 3 Managers individuales
        ManagerSantiago managerSantiago = new ManagerSantiago();
        MiManagerMesero managerFelipe = new MiManagerMesero();
        ManagerMichel managerMichel = new ManagerMichel();

        Scanner scanner = new Scanner(System.in);
        int opcionMain = -1;

        while (opcionMain != 4) {
          
            System.out.println(" SISTEMA DE GESTIÓN POLI RESTAURANTE ");
            System.out.println("1. Administración y Cocina (Ángel)");
            System.out.println("2. Meseros, Mesas y Pedidos (Felipe)");
            System.out.println("3. Menú, Productos y Pagos (Michel)");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcionMain = scanner.nextInt();
                
                    switch (opcionMain) {
                    case 1 
                        menuAngel(managerSantiago, scanner);
                        break;
                    case 2 
                        menuFelipe(managerFelipe, scanner);
                        break;
                    case 3 
                        menuMichel(managerMichel, scanner);
                        break;
                    case 4  
                        System.out.println("Saliendo...");
                    default 
                      System.out.println("Opción no válida.");
                }
         }

    // MENÚ Santiago
    private static void menuSantiago(ManagerSantiago manager, Scanner scanner) {
        System.out.println(" ADMINISTRACIÓN Y COCINA ");
        System.out.println("1. Registrar Usuario");
        System.out.println("2. Consultar Usuarios");
        System.out.println("3. Iniciar Tiempo de Preparación (Cocina)");
        System.out.println("4. Finalizar Tiempo de Preparación (Cocina)");
        System.out.println("5. Generar Reporte de Ventas");
        System.out.print("Seleccione una opción");
        int op = scanner.nextInt();

    }

    // MENÚ Felipe
    private static void menuFelipe(MiManagerMesero manager, Scanner scanner) {
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
    private static void menuMichel(ManagerMichel manager, Scanner scanner) {
        System.out.println("MÓDULO MENÚ, PRODUCTOS Y PAGOS");
        System.out.println("1. Registrar Producto");
        System.out.println("2. Modificar Producto");
        System.out.println("3. Consultar Precios");
        System.out.println("4. Controlar Cantidades");
        System.out.println("5. Procesar Pago");
        System.out.print("Seleccione una opción ");
        int op = scanner.nextInt();
        
    }
}
