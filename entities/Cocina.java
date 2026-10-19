package entities;
public class Cocina {

    private int cocinerosDisponibles;
    private int cocinerosOcupados=0;

    public Cocina(int cocinerosDisponibles) {
        this.cocinerosDisponibles = cocinerosDisponibles;
    }

    public int getCocinerosDisponibles() {
        return cocinerosDisponibles;
    }
    private void setCocinerosDisponibles(int cocinerosDisponibles) {
        this.cocinerosDisponibles = cocinerosDisponibles;
    }

    public int getCocinerosOcupados() {
        return cocinerosOcupados;
    }

    public void setCocinerosOcupados(int cocinerosOcupados) {
        this.cocinerosOcupados = cocinerosOcupados;
    }
    
}
