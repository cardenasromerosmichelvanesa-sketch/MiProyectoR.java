package src.DAOs;
import java.util.ArrayList;
import java.util.List;

import src.entities.DetallePedido;

/**
 * Esta clase se encarga de todo el acceso a datos de DetallePedido.
 * Simula el guardado en base de datos. Aquí iría la conexión JDBC real.
 */
public class DetallePedidoDAO  {

    private List<DetallePedido> listaDetalles = new ArrayList<>();

    /**
     * Registra un nuevo detalle de pedido en la Base de datos
     * @param detalle objeto DetallePedido a guardar
     * @return true si se guardó correctamente
     */
    public boolean registrar(DetallePedido detalle) {
        if (detalle == null) {
            System.out.println("Error al registrar detalle: el detalle es nulo");
            return false;
        }
        listaDetalles.add(detalle);
        System.out.println("Detalle registrado: Producto " + detalle.getIdProducto() + " Cantidad: " + detalle.getCantidad());
        return true;
    }

    /**
     * Obtiene todos los detalles registrados
     * @return lista de todos los detalles de pedido
     */
    public List<DetallePedido> listarTodos() {
        return listaDetalles;
    }

    /**
     * Busca un detalle por el id del producto
     * @param idProducto id a buscar
     * @return el DetallePedido encontrado o null si no existe
     */
    public DetallePedido buscarPorIdProducto(int idProducto) {
        for (DetallePedido d : listaDetalles) {
            if (d.getIdProducto() == idProducto) {
                return d;
            }
        }
        return null;
    }

    /**
     * Actualiza la cantidad de un producto
     * @param idProducto id del producto a actualizar
     * @param nuevaCantidad nueva cantidad
     */
    public void actualizarCantidad(int idProducto, int nuevaCantidad) {
        DetallePedido detalle = buscarPorIdProducto(idProducto);
        if (detalle == null) {
            System.out.println("No existe detalle para el producto " + idProducto);
        } else if (nuevaCantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que 0");
        } else {
            detalle.setCantidad(nuevaCantidad);
            System.out.println("Cantidad actualizada");
        }
    }
}
    
