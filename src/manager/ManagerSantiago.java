package src.manager;
import java.util.Scanner;
import src.DAOs.*;
import src.entities.*;

import java.util.List;
public class ManagerSantiago {
    
    public static void main(String[] args) {
        
        Scanner tcl=new Scanner(System.in);
        
        // Creación lista de empleados, menu y reporte
        List<Usuario> Empleados=null;
        List<Pedido> Reporte=null;
        List<Producto> Menu=null;
        // Cración de Cocina
        Cocina cocina=new Cocina(5);
        // Creacion de mesas
        Mesa mesa1=new Mesa(1);
        Mesa mesa2=new Mesa(2);
        // Creación de empleados
        Mesero mesero1=new Mesero(201, "Juani", "Alvare", true); Empleados.add(mesero1);
        Mesero mesero2=new Mesero(202, "Anami", "Gonzale", true); Empleados.add(mesero2);
        Administrador admin1=new Administrador(101, "Angel","Parra"); Empleados.add(admin1);
        // Creacion del reporte
        Pedido pedido1=new Pedido("ped001", 1, "Entregado", 50550.00); Reporte.add(pedido1);
        Pedido pedido2=new Pedido("ped002", 2, "Entregado", 3482.51); Reporte.add(pedido2);
        // Creación del menu
        Producto prod1=new Producto("ccom001", "Milanes", 3498.21, 2); Menu.add(prod1);
        Producto prod2=new Producto("ccom002", "Pata de caballo", 54849, 3); Menu.add(prod2);

        System.out.println("Ver el reporte de ventas");
        

    }

}
