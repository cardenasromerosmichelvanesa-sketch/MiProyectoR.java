package entities;
public class Cocina extends Usuario{

    private int cocinerosDisponibles;

    public Cocina(int idUsuario, String nombre, String apellido, int cocinerosDisponibles) {
        super(idUsuario, nombre, apellido);
        this.cocinerosDisponibles = cocinerosDisponibles;
    }

    public int getCocinerosDisponibles() {
        return cocinerosDisponibles;
    }
    private void setCocinerosDisponibles(int cocinerosDisponibles) {
        this.cocinerosDisponibles = cocinerosDisponibles;
    }

}
