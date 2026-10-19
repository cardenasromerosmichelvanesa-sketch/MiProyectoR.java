package src.entities;
public class Cocina {

    private int cocinerosDisponibles;
    private int cocinerosOcupados;

    public Cocina(int cocinerosDisponibles) {
        this.cocinerosDisponibles = cocinerosDisponibles;
        this.cocinerosOcupados =0;
    }

    public int getCocinerosDisponibles() {
        return cocinerosDisponibles;
    }
    public void setCocinerosDisponibles(int cocinerosDisponibles) {
        this.cocinerosDisponibles = cocinerosDisponibles;
    }

    public int getCocinerosOcupados() {
        return cocinerosOcupados;
    }

    public void setCocinerosOcupados(int cocinerosOcupados) {
        this.cocinerosOcupados = cocinerosOcupados;
    }

    public boolean disponibilidad(Cocina cocina){
        boolean aux=cocina.getCocinerosDisponibles()>cocina.getCocinerosOcupados();
        return aux;
    }
    
}