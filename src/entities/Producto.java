package src.entities;
public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;

    /**
     * Constructor para inicializar un objeto Producto.
     * 
     * @param id Identificador único del producto
     * @param nombre Nombre comercial del producto
     * @param precio Precio unitario de venta
     * @param cantidad Cantidad disponible en inventario
     */
    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Métodos getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return String.format("ID: %d | Producto: %-15s | Precio: $%.2f | Stock: %d", 
                id, nombre, precio, cantidad);
    }
}
