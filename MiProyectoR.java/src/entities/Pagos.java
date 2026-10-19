package src.entities;

/**
 * Registro de un pago realizado sobre un pedido.
 */
public class Pagos {

    private int id;
    private int idPedido;
    private double monto;
    private String metodo;

    public Pagos(int id, int idPedido, double monto, String metodo) {
        this.id = id;
        this.idPedido = idPedido;
        this.monto = monto;
        this.metodo = metodo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }

    @Override
    public String toString() {
        return "Pago #" + id + " | Pedido " + idPedido + " | $" + monto + " | " + metodo;
    }
}
