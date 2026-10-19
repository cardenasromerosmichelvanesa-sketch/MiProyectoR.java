import entities.Cocina;
import entities.Pedido;
public class CocinaDAO {

    /**
     * Metodo que confirma si la cocina esta dispobible
     * @param Cocina Cocina que se evalua
     * @return Cocineros disponibles (true) - Cocineros no dispobibles (false)
     */
    public boolean cocinerosDisponibles(Cocina Cocina){
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
    private void cocinarPedido(Pedido pedido, Cocina Cocina) {
        if (cocinerosDisponibles(Cocina) && pedido.getEstado().compareTo("Pendiente")==0) {
            System.out.println("La cocina comenzo con la preparación del pedido");
            pedido.setEstado("En preparación");
            Cocina.setCocinerosOcupados(Cocina.getCocinerosOcupados()+1);
        }
        else {
            System.out.println("No hay cocineros dispobibles");
        }
    }

    /**
     * Metodo para cambiar el estadod de pedido a listo
     * @param pedido Pedido que va cambiar el estado a listo
     * @param Cocina Cocina que cambia a listo el pedido
     */
    private void entregarPedido(Pedido pedido, Cocina Cocina) {
        if (pedido.getEstado().compareTo("En preparación")==0) {
            System.out.println("La cocina termino el pedido");
            pedido.setEstado("Listo");
            Cocina.setCocinerosOcupados(Cocina.getCocinerosOcupados()-1);
        }
        else{
            System.out.println("El pedido todavia no ha entrado a cocina");
        }
    }

}
