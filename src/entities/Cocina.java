package src.entities;
import java.util.List;
import java.util.ArrayList;
public class Cocina {

    private int cocinerosDisponibles;
    private int cocinerosOcupados;
    private List<Pedido> enPreparación = new ArrayList<>();

    public Cocina(int cocinerosDisponibles) {
        this.cocinerosDisponibles = cocinerosDisponibles;
        this.cocinerosOcupados =0;
    }

    public int getCocinerosDisponibles() {
        return cocinerosDisponibles;
    }
    public void setCocinerosDisponibles(int cocinerosDisponibles) {
        this.cocinerosDisponibles = cocinerosDisponibles;
    }

    public int getCocinerosOcupados() {
        return cocinerosOcupados;
    }

    public void setCocinerosOcupados(int cocinerosOcupados) {
        this.cocinerosOcupados = cocinerosOcupados;
    }

    public boolean disponibilidad(Cocina cocina){
        boolean aux=cocina.getCocinerosDisponibles()>cocina.getCocinerosOcupados();
        return aux;
    }

    public List<Pedido> getPedidos() {
        return enPreparación;
    }
    public void añadirPedido(Pedido pedido) {
        enPreparación.add(pedido);
    }
    public void eliminarPedido(Pedido pedido) {
        enPreparación.remove(pedido);
    }
    
}