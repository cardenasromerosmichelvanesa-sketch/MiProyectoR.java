
package src.entities;

/**
 * Guarda que producto se pidió y en qué cantidad..
 */
public class DetallePedido {
 
    private int idProducto;
    private int cantidad;

    /**
     * Constructor para crear un nuevo detalle de pedido
     * @param idProducto id del producto que se pidió
     * @param cantidad cantidad de ese producto
     */
    public DetallePedido(int idProducto, int cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
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
     * @param cantidad nueva cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    /**
     * Muestra la información del detalle
     */
    @Override
    public String toString() {
        return "DetallePedido{" + "idProducto " + idProducto + ", cantidad " + cantidad + '}';
    }
}

