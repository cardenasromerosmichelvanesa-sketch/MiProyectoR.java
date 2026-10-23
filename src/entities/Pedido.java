package src.entities;
import java.util.List;
import java.util.ArrayList;
/**
 * Esta clase representa un pedido realizado por un cliente.
 * Contiene el número de mesa, el estado del pedido y el monto total.
 * Se relaciona con Mesa y DetallePedido.
 */
public class Pedido {

    private String id;
    private int mesa;
    private String estado;
    private double monto;
    private static final List<Pedido> reporte = new ArrayList<>();

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
        if (estado.equals("Entregado")) {
            reporte.add(this);
        }
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

    @Override
    public String toString() {
        return "Pedido{id=" + id + ", mesa=" + mesa + ", estado=" + estado + ", monto=" + monto + "}";
    }

    public static List<Pedido> getReporte() {
        return reporte;
    }
    
}
