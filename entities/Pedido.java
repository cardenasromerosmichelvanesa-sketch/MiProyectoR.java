package entities;


public class Pedido {

    private String id;
    private int mesa;
    private String estado;
    private double monto;

    public Pedido(){
        
    }

    public Pedido(String id, int mesa, String estado, double monto) {
        this.id = id;
        this.mesa = mesa;
        this.estado = estado;
        this.monto = monto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
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
    
