package entities;
public class Cocina extends Usuario{

    private int cocinerosDisponibles;

    public Cocina(int cocinerosDisponibles) {
        this.cocinerosDisponibles = cocinerosDisponibles;
    }

    public int getCocinerosDisponibles() {
        return cocinerosDisponibles;
    }
    private void setCocinerosDisponibles(int cocinerosDisponibles) {
        this.cocinerosDisponibles = cocinerosDisponibles;
    }

    private void cocinarPedido() {

    }
    private void entregarPedido() {

    }

}
