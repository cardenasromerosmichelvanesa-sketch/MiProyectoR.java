package src.entities;

/**
 * Entidad encargada de almacenar la información
 * de los pagos realizados en el restaurante.
 *
 * @author Michel Vanesa Romero
 */
public class Pagos {

    private int id;
    private int idPedido;
    private double monto;
    private String metodo;

    // Constructor
    public Pagos(int id, int idPedido, double monto, String metodo) {
        this.id = id;
        this.idPedido = idPedido;
        this.monto = monto;
        this.metodo = metodo;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public double getMonto() {
        return monto;
    }

    public String getMetodo() {
        return metodo;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    // Mostrar información del pago
    @Override
    public String toString() {

        return "ID Pago: " + id
                + "  ID Pedido: " + idPedido
                + "  Monto: $" + monto
                + "  Método de pago: " + metodo;
    }
}
