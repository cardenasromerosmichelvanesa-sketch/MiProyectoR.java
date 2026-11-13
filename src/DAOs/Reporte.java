package src.DAOs;
import src.entities.Pedido;
import java.util.List;
import java.util.ArrayList;

public class Reporte {
    
    private static List<Pedido> reporte = new ArrayList<>();

    public static List<Pedido> getReporte() {
        return reporte;
    }

    public static void verReporte() {
        System.out.println("Reporte de pedidos entregados:");
        for (Pedido p : reporte) {
            System.out.println("Pedido entregado: " + p.getId() + ", Mesa: " + p.getMesa() + ", Monto: " + p.getMonto());
        }
    }

    public static void annadirReporte(Pedido pedido) {
        reporte.add(pedido);
    }

}
