package src.DAOs;
import java.util.ArrayList;
import java.util.List;

import src.entities.Pedido;

/**
 * Encargado de registrar, buscar, actualizar estado y listar pedidos.
 */
public class PedidoDAO {

    private List<Pedido> listaPedidos = new ArrayList<>();

    /**
     * Registra un nuevo pedido en la base de datos
     * @param pedido pedido a guardar
     * @return true si se guardó bien
     */
    public boolean registrarPedido(Pedido pedido) {
        if (pedido == null) {
            System.out.println("Error al registrar pedido: el pedido es nulo");
            return false;
        }
        listaPedidos.add(pedido);
        // getMesa() devuelve un int (número de mesa), no un objeto Mesa
        System.out.println("Pedido registrado para mesa " + pedido.getMesa());
        return true;
    }

    /**
     * Anota todos los pedidos registrados
     * @return lista completa de pedidos
     */
    public List<Pedido> listaPedidos() {
        return listaPedidos;
    }

    /**
     * Busca pedidos por estado
     * @param estado estado a buscar (Pendiente, Entregado)
     * @return lista de pedidos con ese estado
     */
    public List<Pedido> buscarPorEstado(String estado) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : listaPedidos) {
            if (p.getEstado() != null && p.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Actualiza el estado de un pedido
     * @param pedido pedido a actualizar
     * @param nuevoEstado nuevo estado
     */
    public void actualizarEstado(Pedido pedido, String nuevoEstado) {
        if (pedido != null) {
            pedido.setEstado(nuevoEstado);
            System.out.println("Estado actualizado a: " + nuevoEstado);
        }
    }

    /**
     * Calcula el monto total de todos los pedidos
     * @return suma total de ventas
     */
    public double calcularTotalVentas() {
        double total = 0;
        for (Pedido p : listaPedidos) {
            total += p.getMonto();
        }
        return total;
    }
}
    
