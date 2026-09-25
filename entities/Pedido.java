package entities;
public class Pedido {

    private Mesa mesa;
    private String estado;
    private double monto;

    public Pedido(Mesa mesa, String estado, double monto) {
        this.mesa = mesa;
        this.estado = estado;
        this.monto = monto;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
    
