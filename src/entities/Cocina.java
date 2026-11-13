package src.entities;
import java.util.List;
import java.util.ArrayList;
public class Cocina {

    private static int cocinerosDisponibles=0;
    private static int cocinerosOcupados=0;
    private static List<Pedido> enEspera = new ArrayList<>();
    private static List<Pedido> enPreparación = new ArrayList<>();
    private static List<Pedido> pedidosEntregados = new ArrayList<>();
    private static List<Pedido> pedidosEliminados = new ArrayList<>();

    public static int getCocinerosDisponibles() {
        return cocinerosDisponibles;
    }
    public static void setCocinerosDisponibles(int cocinerosDisponibles) {
        Cocina.cocinerosDisponibles = cocinerosDisponibles;
    }

    public static int getCocinerosOcupados() {
        return cocinerosOcupados;
    }

    public static void setCocinerosOcupados(int cocinerosOcupados) {
        Cocina.cocinerosOcupados = cocinerosOcupados;
    }

    public static boolean disponibilidad(){
        boolean aux=Cocina.getCocinerosDisponibles()>Cocina.getCocinerosOcupados();
        return aux;
    }

    public static List<Pedido> getEnEspera() {
        return enEspera;
    }
    public static void añadirPedidoEnEspera(Pedido pedido) {
        enEspera.add(pedido);
    }
    public static void eliminarPedidoEnEspera(Pedido pedido) {
        enEspera.remove(pedido);
    }

    public static List<Pedido> getEnPreparacion() {
        return enPreparación;
    }
    public static void añadirEnPreparacion(Pedido pedido) {
        enPreparación.add(pedido);
    }
    public static void eliminarEnPreparacion(Pedido pedido) {
        enPreparación.remove(pedido);
    }

    public static List<Pedido> getPedidosEntregados() {
        return pedidosEntregados;
    }
    public static void añadirPedidosEntregados(Pedido pedido) {
        pedidosEntregados.add(pedido);
    }

    public static List<Pedido> getPedidosEliminados() {
        return pedidosEliminados;
    }
    public static void añadirPedidosEliminados(Pedido pedido) {
        Cocina.pedidosEliminados.add(pedido);
    }
    
}