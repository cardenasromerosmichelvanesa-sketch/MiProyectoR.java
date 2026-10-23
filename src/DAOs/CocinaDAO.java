package src.DAOs;
import src.entities.Cocina;
import src.entities.Pedido;
public class CocinaDAO {

    /**
     * Metodo que muestra los pedidos que estan en la cocina
     * @param Cocina Cocina que se evalua
     * @return Lista de pedidos en preparación
     */
    public void verPedidosCocina(Cocina cocina){
        System.out.println(cocina.getPedidos());
    }
    /**
     * Metodo que busca un pedido en la cocina
     * @param Cocina Cocina que se evalua
     * @param idPedido Id del pedido que se busca
     * @return Pedido buscado
     */
    public Pedido verPedidoCocina(Cocina cocina, String idPedido){
        for (Pedido p : cocina.getPedidos()) {
            if (p.getId()==idPedido) {
                return p;
            }
        }
        return null;
    }

    /**
     * Metodo que confirma si la cocina esta dispobible
     * @param Cocina Cocina que se evalua
     * @return Cocineros disponibles (true) - Cocineros no dispobibles (false)
     */
    public boolean disponibilidad(Cocina Cocina){
        boolean disponibilidad = false;
        if (Cocina.getCocinerosOcupados()<Cocina.getCocinerosDisponibles()) {
            disponibilidad=true;
            return disponibilidad;
        }
        return disponibilidad;
    }

    /**
     * Metodo para comenzar a preparar el pedido
     * @param pedido Pedido que se va a cocinar
     * @param Cocina Cocina que va a comenzar a preparar el pedido
     */
    public void cocinarPedido(Pedido pedido, Cocina Cocina) {
        if (disponibilidad(Cocina) && pedido.getEstado().compareTo("Pendiente")==0) {
            System.out.println("La cocina comenzo con la preparación del pedido");
            pedido.setEstado("En preparación");
            Cocina.setCocinerosOcupados(Cocina.getCocinerosOcupados()+1);
            Cocina.añadirPedido(pedido);
        }
        else {
            System.out.println("No hay cocineros disponibles");
        }
    }

    /**
     * Metodo para cambiar el estadod de pedido a listo
     * @param pedido Pedido que va cambiar el estado a listo
     * @param Cocina Cocina que cambia a listo el pedido
     */
    public void entregarPedido(Pedido pedido, Cocina Cocina) {
        if (pedido.getEstado().compareTo("En preparación")==0) {
            System.out.println("La cocina termino el pedido");
            pedido.setEstado("Listo");
            Cocina.setCocinerosOcupados(Cocina.getCocinerosOcupados()-1);
            Cocina.eliminarPedido(pedido);
        }
        else{
            System.out.println("El pedido todavia no ha entrado a cocina");
        }
    }

    /**
     * Metodo para cambiar el estado de pedido a cancelado
     * @param pedido Pedido que va cambiar el estado a cancelado
     * @param Cocina Cocina que cambia a cancelado el pedido
     */
    public void cancelarPedido(Pedido pedido, Cocina Cocina) {
        if (pedido.getEstado().compareTo("En preparación")==0) {
            System.out.println("La cocina cancelo el pedido");
            pedido.setEstado("Cancelado");
            Cocina.setCocinerosOcupados(Cocina.getCocinerosOcupados()-1);
            Cocina.eliminarPedido(pedido);
        }
        else{
            System.out.println("El pedido todavia no ha entrado a cocina");
        }
    }
}
