package src.entities;

public class Menu {
    
    private int idProd;
    private double precioProd;

    public Menu(int idProd, double precioProd) {
        this.idProd = idProd;
        this.precioProd = precioProd;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public double getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(double precioProd) {
        this.precioProd = precioProd;
    }

    @Override
    public String toString() {
        return "Menu [ID Producto=" + idProd + ", Precio=" + precioProd + "]";
    }
}
