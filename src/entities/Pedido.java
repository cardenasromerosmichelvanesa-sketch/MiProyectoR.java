package src.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa un pedido realizado en una mesa.
 * Contiene el número de mesa, el estado del pedido y el monto total.
 * Se relaciona con Mesa y DetallePedido.
 */
public class Pedido {

    private String id;
    private int mesa;
    private String estado;
    private double monto;
    private Mesero mesero;
    private List<DetallePedido> detalles = new ArrayList<>();

    /**
     * Crea un pedido vacío con estado inicial "Pendiente"
     * (antes quedaba en null y provocaba NullPointerException).
     */
    public Pedido() {
        this.estado = "Pendiente";
    }

    /**
     * Constructor para crear un nuevo pedido
     * @param id Identificador del pedido
     * @param mesa número de la mesa donde se realiza el pedido
     * @param estado estado inicial del pedido
     * @param monto monto total del pedido
     */
    public Pedido(String id, int mesa, String estado, double monto) {
        this.id = id;
        this.mesa = mesa;
        this.estado = estado;
        this.monto = monto;
    }

    /**
     * Obtiene el número de la mesa asociada al pedido
     * @return número de mesa
     */
    public int getMesa() {
        return mesa;
    }

    /**
     * Asigna una nueva mesa al pedido
     * @param mesa número de la nueva mesa
     */
    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    /**
     * Obtiene el estado actual del pedido
     * @return estado (Pendiente, En preparación, Listo, Entregado)
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Cambia el estado del pedido
     * @param estado nuevo estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el monto total del pedido
     * @return monto total
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Asigna un nuevo monto al pedido
     * @param monto nuevo monto total (no puede ser negativo)
     */
    public void setMonto(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        this.monto = monto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Mesero getMesero() {
        return mesero;
    }

    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
    }

    /**
     * Agrega un detalle (producto y cantidad) al pedido
     * @param detalle detalle a agregar
     */
    public void agregarDetalle(DetallePedido detalle) {
        if (detalle != null) {
            detalles.add(detalle);
        }
    }

    /**
     * @return copia de la lista de detalles del pedido
     */
    public List<DetallePedido> getDetalles() {
        return new ArrayList<>(detalles);
    }

    @Override
    public String toString() {
        return "Pedido{id=" + id + ", mesa=" + mesa + ", estado=" + estado + ", monto=" + monto
                + (mesero != null ? ", mesero=" + mesero.getNombre() : "")
                + ", productos=" + detalles.size() + "}";
    }
}
