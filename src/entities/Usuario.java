package src.entities;
import java.util.List;
import java.util.ArrayList;
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellido;
    private String cargo;
    private static List<Usuario> empleados = new ArrayList<>();

    public Usuario(int idUsuario, String nombre, String apellido, String cargo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        empleados.add(this);
    }

    public int getId() {
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

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public static void agregarEmpleado(Usuario usuario) {
        empleados.add(usuario);
    }
    public static void actualizarEmpleado(int index, Usuario usuario) {
        empleados.set(index, usuario);
    }
    public static void eliminarEmpleado(Usuario usuario) {
        empleados.remove(usuario);
    }
    public static List<Usuario> getEmpleados() {
        return empleados;
    }

}
