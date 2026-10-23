package src.manager;
import java.util.Scanner;
import src.DAOs.*;
import src.entities.*;

public class ManagerSantiago {
    
    public void iniciar() {
        
        Scanner tcl=new Scanner(System.in);
        AdministradorDAO adminDAO=new AdministradorDAO();
        CocinaDAO cocinaDAO=new CocinaDAO();
        Cocina cocina=new Cocina(0);
        int opc=-1;

        Usuario usuario1 = new Usuario(1, "Juan", "Pérez");
        Usuario usuario2 = new Usuario(2, "María", "Gómez");
        Pedido pedido1 = new Pedido("P001", 1001, "Pendiente", 123);
        Pedido pedido2 = new Pedido("P002", 1002, "Pendiente", 456);
        cocinaDAO.cocinarPedido(pedido1, cocina);
        cocinaDAO.cocinarPedido(pedido2, cocina);

        System.out.println("=======================================================");
        System.out.println(" Bienvenido al sistema de gestión de usuarios y cocina");
        System.out.println("=======================================================");
        System.out.println("Ingrese la opción que desea realizar:");
        System.out.println("1. Gestionar usuarios (Registrar/Modificar/Consultar) ");
        System.out.println("2. Gestionar cocina  (Iniciar/Actualizar/Consultar) ");
        System.out.println("-. Salir");
        if (tcl.hasNextInt()) {
            opc=tcl.nextInt();
            switch (opc) {
                case 1:
                    menuUsuarios(adminDAO, tcl);
                    break;
                case 2:
                    menuCocina(adminDAO,tcl,cocina);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

    }

    private void menuUsuarios(AdministradorDAO adminDAO, Scanner tcl) {
        int opc=-1, idUsuario;
        Usuario usuario=null;
        String nombre, apellido;
        while (opc>=1 && opc<=3) {
            System.out.println("=====================");
            System.out.println(" Gestión de usuarios ");
            System.out.println("=====================");
            System.out.println("Ingrese la opción que desea realizar:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Consultar usuarios");
            System.out.println("3. Modificar usuario");
            System.out.println("-. Salir");
            if (tcl.hasNextInt()) {
                opc=tcl.nextInt();
                switch (opc) {
                    case 1:
                        System.out.println("Ingrese los datos del usuario a registrar:");
                        System.out.println("Ingrese el ID del usuario:");
                        if (tcl.hasNextInt()) {
                            idUsuario = tcl.nextInt();
                            System.out.println("Ingrese el nombre del usuario:");
                            nombre = tcl.nextLine();
                            System.out.println("Ingrese el apellido del usuario:");
                            apellido = tcl.nextLine();
                            adminDAO.añadirEmpleado(new Usuario(idUsuario, nombre, apellido));
                        }
                        else {
                            System.out.println("ID inválido. Debe ser un número entero.");
                            tcl.next(); // Limpiar el buffer
                            break;
                        }
                        break;
                    case 2:
                        System.out.println("Listado de usuarios registrados:");
                        adminDAO.verEmpleados();
                        break;
                    case 3:
                        System.out.println("Ingrese la opcion para buscar y modificar el usuario:");
                        System.out.println("1. Ingrese el ID del usuario");
                        System.out.println("2. Ingrese el nombre del usuario");
                        System.out.println("3. Ingrese el apellido del usuario");
                        System.out.println("-. Salir");
                        if (tcl.hasNextInt()) {
                            opc = tcl.nextInt();
                            switch (opc) {
                                case 1:
                                    System.out.println("Ingrese el ID del usuario a modificar:");
                                    if (tcl.hasNextInt()) {
                                        idUsuario= tcl.nextInt();
                                        usuario = adminDAO.getEmpleadoPorId(idUsuario);
                                        if (usuario != null) {
                                            System.out.println("Usuario encontrado: " + usuario);
                                        } else {
                                            System.out.println("Usuario no encontrado con ID: " + idUsuario);
                                            usuario = null;
                                        }
                                    }
                                    break;
                                case 2:
                                    System.out.println("Ingrese el nombre del usuario a modificar:");
                                    nombre = tcl.nextLine();
                                    usuario = adminDAO.getEmpleadoPorNombre(nombre);
                                    if (usuario != null) {
                                        System.out.println("Usuario encontrado: " + usuario);
                                    } else {
                                        System.out.println("Usuario no encontrado con nombre: " + nombre);
                                        usuario = null;
                                    }
                                    break;
                                case 3:
                                    System.out.println("Ingrese el apellido del usuario a modificar:");
                                    apellido = tcl.nextLine();
                                    usuario = adminDAO.getEmpleadoPorApellido(apellido);
                                    if (usuario != null) {
                                        System.out.println("Usuario encontrado: " + usuario);
                                    } else {
                                        System.out.println("Usuario no encontrado con apellido: " + apellido);
                                        usuario = null;
                                    }
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                            }
                        }
                        System.out.println("Ingrese la opción que desea hacer con el usuario encontrado:");
                        System.out.println("1. Cambiar ID");
                        System.out.println("2. Cambiar nombre");
                        System.out.println("3. Cambiar apellido");
                        System.out.println("4. Eliminar usuario");
                        System.out.println("-. Salir");
                        if (tcl.hasNextInt()) {
                            opc = tcl.nextInt();
                            switch (opc) {
                                case 1:
                                    System.out.println("Ingrese el nuevo ID:");
                                    if (tcl.hasNextInt()) {
                                        int newId = tcl.nextInt();
                                        adminDAO.setEmpleadoId(usuario, newId);
                                        System.out.println("ID actualizado a: " + newId);
                                    }
                                    break;
                                case 2:
                                    System.out.println("Ingrese el nuevo nombre:");
                                    String newNombre = tcl.nextLine();
                                    adminDAO.setEmpleadoNombre(usuario, newNombre);
                                    System.out.println("Nombre actualizado a: " + newNombre);
                                    break;
                                case 3:
                                    System.out.println("Ingrese el nuevo apellido:");
                                    String newApellido = tcl.nextLine();
                                    adminDAO.setEmpleadoApellido(usuario, newApellido);
                                    System.out.println("Apellido actualizado a: " + newApellido);
                                    break;
                                case 4:
                                    adminDAO.eliminarEmpleado(usuario);
                                    System.out.println("Usuario eliminado.");
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                            }
                        }
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        }
    }

    public void menuCocina(AdministradorDAO adminDAO, Scanner tcl, Cocina cocina) {
        int opc=0;
        String idPedido = tcl.nextLine();
        CocinaDAO cocinaDAO = new CocinaDAO();
        Pedido pedido = cocinaDAO.verPedidoCocina(cocina, idPedido);
        do {
            System.out.println("=====================");
            System.out.println(" Gestión de cocina ");
            System.out.println("=====================");
            System.out.println("Ingrese la opción que desea realizar:");
            System.out.println("1. Fijar número de cocineros disponibles");
            System.out.println("2. Ver estado de la cocina y pedidos en preparación");
            System.out.println("3. Comenzar a preparar pedido");
            System.out.println("4. Finalizar pedido");
            System.out.println("5. Cancelar pedido");
            System.out.println("-. Salir");
            if (tcl.hasNextInt()) {
                opc=tcl.nextInt();
                switch (opc) {
                    case 1:
                        System.out.println("Ingrese el número de cocineros disponibles:");
                        if (tcl.hasNextInt()) {
                            int numCocineros = tcl.nextInt();
                            adminDAO.setCocinerosDisponibles(cocina,numCocineros);
                            System.out.println("Número de cocineros disponibles actualizado a: " + numCocineros);
                        }
                        break;
                    case 2:
                        System.out.println("Estado de la cocina:");
                        System.out.println("Cocineros disponibles: " + cocina.getCocinerosDisponibles());
                        System.out.println("Cocineros ocupados: " + cocina.getCocinerosOcupados());
                        System.out.println("Pedidos en preparación:");
                        cocina.getPedidos();
                        break;
                    case 3:
                        System.out.println("Ingrese el ID del pedido a comenzar a preparar:");
                        if (tcl.hasNextLine()) {
                            idPedido = tcl.nextLine();
                            cocinaDAO = new CocinaDAO();
                            pedido = cocinaDAO.verPedidoCocina(cocina, idPedido);
                            if (pedido != null) {
                                cocinaDAO.cocinarPedido(pedido, cocina);
                                System.out.println("Pedido con ID " + idPedido + " ha comenzado a prepararse.");
                            } else {
                                System.out.println("Pedido no encontrado en la cocina.");
                            }
                        }
                        else {
                            System.out.println("ID de pedido inválido.");
                        }
                        break;
                    case 4:
                        System.out.println("Ingrese el ID del pedido a finalizar:");
                        if (tcl.hasNextLine()) {
                            idPedido = tcl.nextLine();
                            cocinaDAO = new CocinaDAO();
                            pedido = cocinaDAO.verPedidoCocina(cocina, idPedido);
                            if (pedido != null) {
                                cocinaDAO.entregarPedido(pedido, cocina);
                                System.out.println("Pedido con ID " + idPedido + " ha sido finalizado.");
                            } else {
                                System.out.println("Pedido no encontrado en la cocina.");
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Ingrese el ID del pedido a cancelar:");
                        if (tcl.hasNextLine()) {
                            idPedido = tcl.nextLine();
                            cocinaDAO = new CocinaDAO();
                            pedido = cocinaDAO.verPedidoCocina(cocina, idPedido);
                            if (pedido != null) {
                                cocinaDAO.cancelarPedido(pedido, cocina);
                                System.out.println("Pedido con ID " + idPedido + " ha sido cancelado.");
                            } else {
                                System.out.println("Pedido no encontrado en la cocina.");
                            }
                        }
                        case 6:
                        System.out.println("Saliendo del menú de cocina.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        }while (opc>=1 && opc<=6);
    }


}
