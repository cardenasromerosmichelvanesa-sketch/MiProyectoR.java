
package DAOs;
import entities.Mesa;
import java.util.ArrayList;
import java.util.List;

/**
 * Se encarga de guardar, buscar y liberar mesas en la base de datos.
 */
public class MesaDAO {

    private List<Mesa> listaMesas = new ArrayList<>();

    /**
     * Registra una nueva mesa en el sistema
     * @param mesa mesa a registrar
     * @return true si se registró correctamente
     */
    public boolean registrarMesa(Mesa mesa) {
        try {
            listaMesas.add(mesa);
            System.out.println("Mesa " + mesa.getNumMesa() + " registrada");
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar mesa: " + e.getMessage());
            return false;
        }
    }

    /**
     *Lista de todas las mesas del restaurante
     * @return lista de mesas
     */
    public List<Mesa> ListaMesas() {
        return listaMesas;
    }

    /**
     * Busca una mesa por su número
     * @param numMesa número de la mesa a buscar
     * @return la mesa encontrada o null
     */
    public Mesa buscarMesa(int numMesa) {
        for (Mesa m : listaMesas) {
            if (m.getNumMesa() == numMesa) {
                return m;
            }
        }
        return null;
    }

    /**
     * Obtiene solo las mesas disponibles
     * @return lista de mesas libres
     */
    public List<Mesa> MesasDisponibles() {
        List<Mesa> disponibles = new ArrayList<>();
        for (Mesa m : listaMesas) {
            if (m.isDisponible()) {
                disponibles.add(m);
            }
        }
        return disponibles;
    }

    /**
     * Asigna un mesero a una mesa
     * @param numMesa número de la mesa
     * @param mesero mesero a asignar
     */
    public void asignarMesero(int numMesa, entities.Mesero mesero) {
        Mesa mesa = buscarMesa(numMesa);
        if (mesa != null) {
            mesa.setMesero(mesero);
            System.out.println("Mesero asignado a mesa " + numMesa);
        }
    }
}
    