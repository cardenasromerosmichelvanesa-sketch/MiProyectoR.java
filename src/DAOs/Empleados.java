package src.DAOs;
import src.entities.Usuario;
public class Empleados {

    /**
     * Metodo para ver el listado de empleados
     */
    public static void verEmpleados() {
        System.out.println("Listado de empleados:");
        for (Usuario empleado : Usuario.getEmpleados()) {
            System.out.println("ID: " + empleado.getId() +", Cargo: " + empleado.getCargo() + ", Nombre: " + empleado.getNombre() + ", Apellido: " + empleado.getApellido());
        }
    }

    /**
     * Metodo para buscar un empleado en la lista de empleados por su idUsuario
     * @param idUsuario Valor del idUsuario que se busca
     * @return true si el empleado se encuentra en la lista, false si no se encuentra
     */
    public static boolean buscarEmpleadoID(int idUsuario) {
        for (Usuario empleado : Usuario.getEmpleados()) {
            if (empleado.getId() == idUsuario) {
                return true;
            }
        }
        return false;
    }

    public static Usuario getEmpleadoID(int idUsuario) {
        for (Usuario empleado : Usuario.getEmpleados()) {
            if (empleado.getId() == idUsuario) {
                return empleado;
            }
        }
        return null;
    }

    /**
     * Metodo para añadir un empleado a la lista de empleados
     * @param usuario Usuario que se agrega a la lista
     */
    public static boolean añadirEmpleado(Usuario usuario) {
        if (usuario != null && !Usuario.getEmpleados().contains(usuario)) {
            Usuario.agregarEmpleado(usuario);
            return true;
        } else {
            System.out.println("El usuario no puede ser nulo o ya existe");
            return false;
        }
    }

    /**
     * Metodo para actualizar un empleado a la lista de empleados
     * @param usuario Usuario que se actualiza en la lista
     * @param index Indice del usuario que se actualiza
     */
    public static boolean actualizarEmpleado(Usuario usuario, int index) {
        if (usuario != null) {
            if (index >= 0 && index < Usuario.getEmpleados().size()) {
                Usuario.actualizarEmpleado(index, usuario);
                return true;                
            } else {
                System.out.println("El índice es inválido");
                return false;
            }
        } else {
            System.out.println("El usuario no puede ser nulo");
            return false;
        }
    }

    /**
     * Metodo para eliminar un empleado a la lista de empleados
     * @param usuario Usuario que se elimina de la lista
     */
    public static boolean eliminarEmpleado(Usuario usuario) {
        if (usuario != null && buscarEmpleadoID(usuario.getId())) {
            Usuario.eliminarEmpleado(usuario);
            return true;
        } else {
            System.out.println("El usuario no puede ser nulo o no existe");
            return false;
        }
    }

}