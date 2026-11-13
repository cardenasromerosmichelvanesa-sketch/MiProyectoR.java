package src.entities;

/**
 * Guarda qué producto se pidió y en qué cantidad.
 */
public class DetallePedido {

    private int idProducto;
    private String nombreProducto;
    private double precio;
    private int cantidad;

    /**
     * Constructor completo: producto con nombre y precio unitario
     * @param idProducto id del producto
     * @param nombreProducto nombre del producto
     * @param precio precio unitario (no negativo)
     * @param cantidad cantidad pedida (mayor que 0)
     * @throws IllegalArgumentException si el precio o la cantidad no son válidos
     */
    public DetallePedido(int idProducto, String nombreProducto, double precio, int cantidad) {
        this(idProducto, cantidad);
        this.nombreProducto = nombreProducto;
        setPrecio(precio);
    }

    /**
     * Constructor para crear un nuevo detalle de pedido
     * @param idProducto id del producto que se pidió
     * @param cantidad cantidad de ese producto (mayor que 0)
     * @throws IllegalArgumentException si la cantidad no es válida
     */
    public DetallePedido(int idProducto, int cantidad) {
        this.idProducto = idProducto;
        setCantidad(cantidad);
    }

    /**
     * Obtiene el id del producto
     * @return id del producto
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Asigna el id del producto
     * @param idProducto nuevo id del producto
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene la cantidad pedida
     * @return cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Asigna la cantidad pedida
     * @param cantidad nueva cantidad (mayor que 0)
     * @throws IllegalArgumentException si la cantidad es 0 o negativa
     */
    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
        this.cantidad = cantidad;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio precio unitario (no puede ser negativo)
     */
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    /**
     * @return precio por cantidad
     */
    public double getSubtotal() {
        return precio * cantidad;
    }

    /**
     * Muestra la información del detalle
     */
    @Override
    public String toString() {
        return "DetallePedido{idProducto=" + idProducto
                + (nombreProducto != null ? ", producto=" + nombreProducto : "")
                + ", precio=" + precio + ", cantidad=" + cantidad + "}";
    }
}
