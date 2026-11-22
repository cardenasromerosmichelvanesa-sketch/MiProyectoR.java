package src.formularios.felipe;

import src.DAOs.DetallePedidoDAO;
import src.DAOs.MesaDAO;
import src.DAOs.MeseroDAO;
import src.DAOs.PedidoDAO;

/**
 * Guarda los DAOs que usa ManagerFelipe. Todas las ventanas reciben el MISMO
 * objeto, así lo que se registra en una ventana se ve en las demás.
 */
public class DatosFelipe {
    public final MesaDAO mesaDAO = new MesaDAO();
    public final MeseroDAO meseroDAO = new MeseroDAO();
    public final PedidoDAO pedidoDAO = new PedidoDAO();
    public final DetallePedidoDAO detalleDAO = new DetallePedidoDAO();
}
