package src.manager;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ManagerMichel managerMichel = new ManagerMichel();
        ManagerFelipe managerFelipe = new ManagerFelipe();
        ManagerSantiago managerSantiago = new ManagerSantiago();
        System.out.println("Elige el manager que vas a usar ");
        int opcion = sc.nextInt();
    switch(opcion) {
            case 1:
            managerFelipe.mostrarMenuPrincipal();
            break;
            case 2:
            managerMichel.mostrarMenuPrincipal();
            break;
            case 3:
            managerSantiago.mostrarMenuPrincipal();
            break;
    }
   }
}        
    
