package entities;

/**
 * Esta clase representa un pedido realizado por un cliente.
 * Contiene la mesa, el estado del pedido y el monto total.
 * Se relaciona con Mesa y DetallePedido.
 */
public class Pedido {

    private String id;
    private int mesa;
    private String estado;
    private double monto;

    /**
     * Constructor para crear un nuevo pedido
     * @param id Identificador del pedido
     * @param mesa mesa donde se realiza el pedido
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
     * Obtiene la mesa asociada al pedido
     * @return objeto Mesa
     */
    public int getMesa() {
        return mesa;
    }

    /**
     * Asigna una nueva mesa al pedido
     * @param mesa nueva mesa
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa.getNumMesa();
    }

    /**
     * Obtiene el estado actual del pedido
     * @return estado (Pendiente, En preparación, Entregado)
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
     * @param monto nuevo monto total
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
   
}
    
