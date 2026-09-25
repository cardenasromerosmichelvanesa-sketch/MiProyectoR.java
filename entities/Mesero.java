package entities;
public class Mesero {
  

    private boolean meseroDisponible;

  
    public Mesero(boolean meseroDisponible) {
        this.meseroDisponible = meseroDisponible;
    }

    public boolean getMeseroDisponible() {
        return meseroDisponible;
    }

 
    public void setMeseroDisponible(boolean meseroDisponible) {
        this.meseroDisponible = meseroDisponible;
    }

    public Pedido crearPedido(Mesa mesa) {

        if (!meseroDisponible) {
            System.out.println("El mesero no está disponible");
            return null;
        }

        Pedido pedido = new Pedido(mesa, "Pendiente", 0);
        System.out.println("Pedido creado correctamente");

        return pedido;
    }
    public void agregarProductos(Pedido pedido, int idProducto, int cantidad) {

        if (pedido != null) {
            DetallePedido detalle = new DetallePedido(idProducto, cantidad);
            System.out.println("Producto agregado");
            System.out.println("ID Producto: " + detalle.getIdProducto());
            System.out.println("Cantidad: " + detalle.getCantidad());
        }
    }
    public void quitarProductos(int idProducto) {
        System.out.println("El producto " + idProducto + " eliminado del pedido");
    }

    public void asignarMesa(Mesa mesa) {

        if (mesa.isDisponible()) {
            mesa.setMesero(this);
            mesa.setDisponible(false);

            System.out.println("Mesa " + mesa.getNumMesa()
                    + " asignada al mesero");
        } else {
            System.out.println("La mesa no está disponible");
        }
    }

    public void entregarPedido(Pedido pedido) {

        if (pedido != null) {
            pedido.setEstado("Entregado");
            System.out.println("Pedido entregado");
        }
    }
}
