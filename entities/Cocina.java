package entities;
import  Pedido;
public class Cocina extends Usuario{

    private int cocinerosDisponibles;
    private int cocinerosOcupados=0;

    public Cocina(int cocinerosDisponibles) {
        this.cocinerosDisponibles = cocinerosDisponibles;
    }

    public int getCocinerosDisponibles() {
        return cocinerosDisponibles;
    }
    private void setCocinerosDisponibles(int cocinerosDisponibles) {
        this.cocinerosDisponibles = cocinerosDisponibles;
    }

    private void cocinarPedido(Pedido PEDIDO) {
        if (cocinerosOcupados<cocinerosDisponibles) {
            System.out.println("La cocina comenzo con la preparación del pedido");
            PEDIDO.setEstado("En preparación");
            cocinerosOcupados++;
        }
        else {
            System.out.println("No hay cocineros dispobibles");
        }
    }
    private void entregarPedido(Pedido PEDIDO) {
        System.out.println("La cocina termino el pedido");
        PEDIDO.setEstado("Terminado");
        cocinerosOcupados--;
    }

}
