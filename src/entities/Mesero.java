package src.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es el mesero del restaurante.
 * Se encarga de gestionar las mesas y los pedidos de los clientes.
 * Aplica POO y encapsulamiento.
 */
public class Mesero extends Usuario{
  
    private boolean meseroDisponible;
    /** Mesas que atiende este mesero */
    private List<Mesa> mesasAsignadas = new ArrayList<>();

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
        if (mesa == null) {
            System.out.println("Se necesita una mesa para crear el pedido");
            return null;
        }
        Pedido pedido = new Pedido("ped" + System.currentTimeMillis(), mesa.getNumMesa(), "Pendiente", 0.0);
        System.out.println("Pedido creado correctamente");
        return pedido;
    }
    
    /**
     * Crea un pedido para una mesa que atiende este mesero, ligado a un cliente.
     * @param mesa mesa del pedido (debe estar asignada a este mesero)
     * @param cliente cliente que hace el pedido
     * @return el pedido creado o null si no se pudo
     */
    public Pedido crearPedido(Mesa mesa, Cliente cliente) {
        if (!meseroDisponible) {
            System.out.println("El mesero no está disponible");
            return null;
        }
        if (mesa == null || cliente == null) {
            System.out.println("Se necesita una mesa y un cliente para crear el pedido");
            return null;
        }
        if (mesa.getMesero() != this) {
            System.out.println("La mesa " + mesa.getNumMesa() + " no está asignada a este mesero");
            return null;
        }
        Pedido pedido = new Pedido("ped" + System.currentTimeMillis(), mesa.getNumMesa(), "Pendiente", 0.0);
        pedido.setMesero(this);
        pedido.setCliente(cliente);
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
        if (pedido != null && cantidad > 0) {
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
     * Asigna una mesa al mesero solo si la mesa está disponible.
     * La mesa queda ocupada (deja de aparecer como disponible).
     * @param mesa mesa a asignar
     * @return true si se asignó
     */
    public boolean asignarMesa(Mesa mesa) {
        if (mesa == null) {
            System.out.println("Mesa inválida");
            return false;
        }
        if (!meseroDisponible) {
            System.out.println("El mesero no está disponible");
            return false;
        }
        if (!mesa.isDisponible()) {
            System.out.println("La mesa " + mesa.getNumMesa() + " no está disponible");
            return false;
        }
        mesa.setMesero(this);          // la mesa queda ocupada
        mesasAsignadas.add(mesa);
        System.out.println("Mesa " + mesa.getNumMesa() + " asignada al mesero " + getNombre());
        return true;
    }

    /**
     * Quita una mesa de la lista del mesero (la usa Mesa.liberarMesa()).
     * @param mesa mesa a quitar
     */
    public void quitarMesa(Mesa mesa) {
        mesasAsignadas.remove(mesa);
    }

    /**
     * Mesas que atiende actualmente este mesero
     * @return copia de la lista de mesas asignadas
     */
    public List<Mesa> getMesasAsignadas() {
        return new ArrayList<>(mesasAsignadas);
    }

    /**
     * Cambia el estado del pedido a entregado
     * @param pedido pedido a entregar
     */
    public void entregarPedido(Pedido pedido, List<Pedido> Reporte) {
        if (pedido != null && "Listo".equalsIgnoreCase(pedido.getEstado())) {
            pedido.setEstado("Entregado");
            if (Reporte != null) {
                Reporte.add(pedido);
            }
            System.out.println("Pedido entregado");
        } else {
            System.out.println("El pedido no está listo para entregar");
        }
    }
}