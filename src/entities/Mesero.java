package src.entities;

import java.util.List;

/**
 * Esta clase es el mesero del restaurante.
 * Se encarga de gestionar las mesas y los pedidos de los clientes.
 * Aplica POO y encapsulamiento.
 */
public class Mesero extends Usuario{
  
    private boolean meseroDisponible;

    /**
     * Constructor del mesero
     * @param meseroDisponible true si está libre, false si está ocupado
     */
    public Mesero(int idUsuario, String nombre, String apellido, boolean meseroDisponible) {
        super(idUsuario, nombre, apellido);
        this.meseroDisponible = meseroDisponible;
    }

    /**
     * Verifica si el mesero está disponible
     * @return true si está disponible
     */
    public boolean getMeseroDisponible() {
        return meseroDisponible;
    }

    /**
     * Cambia la disponibilidad del mesero
     * @param meseroDisponible nuevo estado
     */
    public void setMeseroDisponible(boolean meseroDisponible) {
        this.meseroDisponible = meseroDisponible;
    }

    /**
     * Crea un nuevo pedido para una mesa
     * Valida que el mesero esté disponible antes de crear el pedido
     * @param mesa mesa donde se va a crear el pedido
     * @return objeto Pedido creado o null si no está disponible
     */
    public Pedido crearPedido(Mesa mesa) {
        if (!meseroDisponible) {
            System.out.println("El mesero no está disponible");
            return null;
        }
        Pedido pedido = new Pedido();
        System.out.println("Pedido creado correctamente");
        return pedido;
    }
    
    /**
     * Agrega productos a un pedido existente
     * @param pedido pedido al que se agrega
     * @param idProducto id del producto
     * @param cantidad cantidad a agregar
     */
    public void agregarProductos(Pedido pedido, int idProducto, int cantidad) {
        if (pedido != null) {
            DetallePedido detalle = new DetallePedido(idProducto, cantidad);
            System.out.println("Producto agregado");
            System.out.println("ID Producto: " + detalle.getIdProducto());
            System.out.println("Cantidad: " + detalle.getCantidad());
        }
    }
    
    /**
     * Quita un producto del pedido
     * @param idProducto id del producto a quitar
     */
    public void quitarProductos(int idProducto) {
        System.out.println("El producto " + idProducto + " eliminado del pedido");
    }

    /**
     * Asigna una mesa al mesero si está disponible
     * @param mesa mesa a asignar
     */
    public void asignarMesa(Mesa mesa) {
        if (mesa.isDisponible()) {
            mesa.setMesero(this);
            mesa.setDisponible(false);
            System.out.println("Mesa " + mesa.getNumMesa() + " asignada al mesero");
        } else {
            System.out.println("La mesa no está disponible");
        }
    }

    /**
     * Cambia el estado del pedido a entregado
     * @param pedido pedido a entregar
     */
    public void entregarPedido(Pedido pedido, List<Pedido> Reporte) {
        if (pedido != null && pedido.getEstado().compareTo("Listo")==0) {
            pedido.setEstado("Entregado");
            Reporte.add(pedido);
            System.out.println("Pedido entregado");
        }
    }
}