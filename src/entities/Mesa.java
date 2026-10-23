package src.entities;

/**
 * Esta clase representa una mesa del restaurante.
 * Aplica encapsulamiento con atributos privados y getters/setters.
 */
public class Mesa {

    private int numMesa;
    private int capacidad;
    private boolean disponible;
    private Mesero mesero;

    /**
     * Constructor principal: la mesa exige su capacidad de personas.
     * @param numMesa número identificador de la mesa
     * @param capacidad cuántas personas caben en la mesa (mayor que 0)
     * @throws IllegalArgumentException si la capacidad no es válida
     */
    public Mesa(int numMesa, int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0");
        }
        this.numMesa = numMesa;
        this.capacidad = capacidad;
        this.disponible = true;
        this.mesero = null;
    }

    /**
     * Constructor de compatibilidad con código anterior (ej. los managers).
     * Usa una capacidad por defecto de 4 personas.
     * @param numMesa número identificador de la mesa
     */
    public Mesa(int numMesa) {
        this(numMesa, 4);
    }

    /**
     * Obtiene el número de la mesa
     * @return número de mesa
     */
    public int getNumMesa() {
        return numMesa;
    }

    /**
     * Asigna un nuevo número a la mesa
     * @param numMesa nuevo número
     */
    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    /**
     * Obtiene la capacidad de personas de la mesa
     * @return capacidad máxima de personas
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Cambia la capacidad de personas de la mesa
     * @param capacidad nueva capacidad (mayor que 0)
     * @throws IllegalArgumentException si la capacidad no es válida
     */
    public void setCapacidad(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0");
        }
        this.capacidad = capacidad;
    }

    /**
     * Verifica si en la mesa caben cierta cantidad de personas
     * @param personas cantidad de personas
     * @return true si personas está entre 1 y la capacidad
     */
    public boolean puedeAcomodar(int personas) {
        return personas > 0 && personas <= capacidad;
    }

    /**
     * Verifica si la mesa está disponible
     * @return true si está libre, false si está ocupada
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Cambia el estado de disponibilidad de la mesa
     * @param disponible true para libre, false para ocupada
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Obtiene el mesero asignado a esta mesa
     * @return objeto Mesero o null si no tiene
     */
    public Mesero getMesero() {
        return mesero;
    }

    /**
     * Asigna un mesero a la mesa. Si se asigna uno, la mesa queda ocupada.
     * @param mesero mesero que atenderá la mesa (null para quitarlo)
     */
    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
        if (mesero != null) {
            this.disponible = false;
        }
    }

    /**
     * Libera la mesa, la deja disponible y sin mesero
     */
    public void liberarMesa() {
        this.disponible = true;
        this.mesero = null;
        System.out.println("Mesa " + numMesa + " liberada");
    }

    @Override
    public String toString() {
        return "Mesa " + numMesa + " (capacidad: " + capacidad + ", "
                + (disponible ? "disponible" : "ocupada") + ")";
    }
}
