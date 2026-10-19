package src.entities;
public class Producto {

    
    private String id;
    private String nombre;
    private double precio;
    private int cantidad;
    private String tiempopre;

    
    public Producto(String id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTiempopre() {
        return tiempopre;
    }

    public void setTiempopre(String tiempopre) {
        this.tiempopre = tiempopre;
    }

}
    

