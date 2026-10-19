
package DAOs;
import entities.Mesero;
import entities.Mesa;
import entities.Pedido;
import java.util.ArrayList;
import java.util.List;

/**
 * Se encarga de registrar, anotar y controlar la disponibilidad de los meseros.
 */
public class MeseroDAO {

    private List<Mesero> listaMeseros = new ArrayList<>();

    /**
     * Registra un nuevo mesero en el sistema
     * @param mesero mesero a registrar
     * @return true si se registró correctamente
     */
    public boolean registrarMesero(Mesero mesero) {
        try {
            listaMeseros.add(mesero);
            System.out.println("Mesero registrado correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar mesero: " + e.getMessage());
            return false;
        }
    }

    /**
     * anota todos los meseros registrados
     * @return lista de meseros
     */
    public List<Mesero> listaMeseros() {
        return listaMeseros;
    }

    /**
     * anota solo los meseros disponibles
     * @return lista de meseros libres
     */
    public List<Mesero> listaDisponibles() {
        List<Mesero> disponibles = new ArrayList<>();
        for (Mesero m : listaMeseros) {
            if (m.getMeseroDisponible()) {
                disponibles.add(m);
            }
        }
        return disponibles;
    }

    /**
     * Busca y asigna una mesa a un mesero disponible
     * @param mesa mesa a asignar
     */
    public void asignarMesa(Mesa mesa) {
        for (Mesero m : listaMeseros) {
            if (m.getMeseroDisponible()) {
                m.asignarMesa(mesa);
                return;
            }
        }
        System.out.println("No hay meseros disponibles");
    }

    /**
     * Crea un pedido usando el primer mesero disponible
     * @param mesa mesa del pedido
     * @return pedido creado
     */
    public Pedido crearPedido(Mesa mesa) {
        for (Mesero m : listaMeseros) {
            if (m.getMeseroDisponible()) {
                return m.crearPedido(mesa);
            }
        }
        System.out.println("No hay meseros disponibles para crear pedido");
        return null;
    }

    /**
     * Registra la entrega de un pedido
     * @param mesero mesero que entrega
     * @param pedido pedido a entregar
     */
    public void entregarPedido(Mesero mesero, Pedido pedido) {
        if (mesero != null && pedido != null) {
            mesero.entregarPedido(pedido);
        }
    }
}