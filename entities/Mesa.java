
package entities;

/**
 * Esta clase representa una mesa del restaurante
 * Aplica encapsulamiento con atributos privados y getters/setters
 */
public class Mesa {

    private int numMesa;
    private boolean disponible;
    private Mesero mesero;

    /**
     * @param numMesa número identificador de la mesa
     */
    public Mesa(int numMesa) {
        this.numMesa = numMesa;
        this.disponible = true;
        this.mesero = null;
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
     * Asigna un mesero a la mesa y la marca como ocupada
     * @param mesero mesero que atenderá la mesa
     */
    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
       /**
        * Si se asigna mesero, la mesa ya no está disponible
        */
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
}
