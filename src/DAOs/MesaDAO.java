package src.DAOs;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.entities.Mesa;
import src.entities.Mesero;

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
        if (mesa == null) {
            System.out.println("Error al registrar mesa: la mesa es nula");
            return false;
        }
        if (buscarMesa(mesa.getNumMesa()) != null) {
            System.out.println("Error: ya existe la mesa " + mesa.getNumMesa());
            return false;
        }
        listaMesas.add(mesa);
        System.out.println("Mesa " + mesa.getNumMesa() + " registrada (capacidad: " + mesa.getCapacidad() + ")");
        return true;
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
    public void asignarMesero(int numMesa, Mesero mesero) {
    Mesa mesa = buscarMesa(numMesa);
    if (mesa != null && mesero != null) {
        mesero.asignarMesa(mesa);
    } else {
        System.out.println("Mesa o mesero no existe");
    }
    }

    /**
     * Mesas disponibles donde caben cierta cantidad de personas
     * @param personas cantidad de personas del grupo
     * @return lista de mesas libres con capacidad suficiente
     */
    public List<Mesa> mesasParaPersonas(int personas) {
        List<Mesa> resultado = new ArrayList<>();
        for (Mesa m : listaMesas) {
            if (m.isDisponible() && m.puedeAcomodar(personas)) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    /**
     * Pide por consola el número de mesa y la capacidad de personas,
     * crea la mesa y la registra.
     * @param sc Scanner para leer del teclado
     * @return true si la mesa se registró
     */
    public boolean crearMesaPidiendoDatos(Scanner sc) {
        int num = leerEntero(sc, "Número de la mesa: ");
        int capacidad = leerEntero(sc, "¿Cuántas personas caben en la mesa (capacidad)? ");
        while (capacidad <= 0) {
            System.out.println("La capacidad debe ser mayor que 0");
            capacidad = leerEntero(sc, "¿Cuántas personas caben en la mesa (capacidad)? ");
        }
        return registrarMesa(new Mesa(num, capacidad));
    }

    private int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido");
            }
        }
    }
}
    