package src.entities;
import java.util.List;
import java.util.ArrayList;
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellido;
    public static final List<Usuario> empleados=new ArrayList<Usuario>();
    

    public Usuario(int idUsuario, String nombre, String apellido) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        empleados.add(this);
    }

    public int getIdo() {
        return idUsuario;
    }
    public void setId(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
}
