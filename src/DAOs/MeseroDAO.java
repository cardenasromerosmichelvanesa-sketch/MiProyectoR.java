package src.DAOs;
import java.util.ArrayList;
import java.util.List;

import src.entities.Mesa;
import src.entities.Mesero;
import src.entities.Pedido;

/**
 * Se encarga de registrar, anotar y controlar la disponibilidad de los meseros.
 */
public class MeseroDAO {

    private List<Mesero> listaMeseros = new ArrayList<>();
    /** Pedidos entregados (el Mesero los agrega al entregar) */
    private List<Pedido> reporte = new ArrayList<>();

    /**
     * Registra un nuevo mesero en el sistema
     * @param mesero mesero a registrar
     * @return true si se registró correctamente
     */
    public boolean registrarMesero(Mesero mesero) {
        if (mesero == null) {
            System.out.println("Error al registrar mesero: el mesero es nulo");
            return false;
        }
        listaMeseros.add(mesero);
        System.out.println("Mesero registrado correctamente");
        return true;
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
        if (mesa == null) {
            System.out.println("Mesa inválida");
            return;
        }
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
            // Mesero.entregarPedido necesita también la lista de reporte
            mesero.entregarPedido(pedido, reporte);
        }
    }

    /**
     * Pedidos que ya fueron entregados
     * @return lista de pedidos entregados
     */
    public List<Pedido> getReporte() {
        return reporte;
    }
}