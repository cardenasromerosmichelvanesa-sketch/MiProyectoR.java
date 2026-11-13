package src.DAOs;
import src.entities.*;
public class AdministradorDAO {
    
    public AdministradorDAO() {
    }

    public void listaPedidos(PedidoDAO pedidoDAO) {
        System.out.println(pedidoDAO.listaPedidos());
    }

    /**
     * Metodo para ver el listado de empleados
     */
    public void verEmpleados() {
        Empleados.verEmpleados();
    }
    /**
     * Metodo para añadir un empleado a la lista de empleados
     * @param usuario Usuario que se agrega a la lista
     */
    public void añadirEmpleado(Usuario usuario) {
        if (usuario != null) {
            if (Empleados.añadirEmpleado(usuario)) {
                System.out.println("Empleado añadido correctamente");
            } else {
                System.out.println("No se pudo añadir el empleado");
            }
        } else {
            System.out.println("El usuario no puede ser nulo");
        }
    }

    public Usuario getEmpleadoPorId(int idUsuario) {
        if (Empleados.buscarEmpleadoID(idUsuario)) {
            if (Empleados.getEmpleadoID(idUsuario) != null) {
                return Empleados.getEmpleadoID(idUsuario);
            } else {
                System.out.println("El empleado no se encuentra en la lista");
                return null;
            }
        } else {
            System.out.println("El empleado no se encuentra en la lista");
            return null;
        }
    }

    /**
     * Metodo para eliminar un empleado a la lista de empleados
     * @param usuario Usuario que se elimina de la lista
     */
    public void eliminarEmpleado(Usuario usuario) {
        
        if (usuario != null && Empleados.buscarEmpleadoID(usuario.getId())) {
            if (Empleados.eliminarEmpleado(usuario)) {
                System.out.println("Empleado eliminado correctamente");
            }
            else {
                System.out.println("No se pudo eliminar el empleado");
            }
        } else {
            System.out.println("No se pudo eliminar el empleado");
        }
    }
    /**
     * Metodo para cambiar el id de un empleado
     * @param usuario Usuario al que se aplica el cambio
     * @param newId Nuevo id que reemplazara al antiguo
     */
    public void setEmpleadoId(Usuario usuario, int newId) {
        if (usuario != null) {
            if (newId > 0) {
               if (Empleados.buscarEmpleadoID(usuario.getId())) {
                    usuario.setId(newId);
                    if (Empleados.actualizarEmpleado(usuario, Usuario.getEmpleados().indexOf(usuario))) {
                        System.out.println("ID del empleado actualizado correctamente");
                    } else {
                        System.out.println("No se pudo actualizar el ID del empleado");
                    }
                } else {
                    System.out.println("El empleado no se encuentra en la lista");
                }
            } else {
                System.out.println("El nuevo id no puede ser nulo o vacío");
            }
        } else {
            System.out.println("El usuario no puede ser nulo");
        }
    }
    /**
     * Metodo para cambiar el nombre de un empleado
     * @param usuario Usuario al que se aplica el cambio
     * @param newNombre Nuevo nombre que reemplazara al antiguo
     */
    public void setEmpleadoNombre(Usuario usuario, String newNombre) {
        if (usuario != null) {
            if (newNombre != null && !newNombre.isEmpty()) {
                usuario.setNombre(newNombre);
                if (Empleados.actualizarEmpleado(usuario, Usuario.getEmpleados().indexOf(usuario))) {
                    System.out.println("Nombre del empleado actualizado correctamente");
                } else {
                    System.out.println("No se pudo actualizar el nombre del empleado");
                }
            } else {
                System.out.println("El nuevo nombre es inválido");
            }
        } else {
            System.out.println("El usuario no puede ser nulo");
        }
    }
    /**
     * Metodo para cambiar el apellido de un empleado
     * @param usuario Usuario al que se aplica el cambio
     * @param newApellido Nuevo apellido que reemplazara al antiguo
     */
    public void setEmpleadoApellido(Usuario usuario, String newApellido) {
        if (usuario != null) {
            if (newApellido != null && !newApellido.isEmpty()) {
                usuario.setApellido(newApellido);
                if (Empleados.actualizarEmpleado(usuario, Usuario.getEmpleados().indexOf(usuario))) {
                    System.out.println("Apellido del empleado actualizado correctamente");
                } else {
                    System.out.println("No se pudo actualizar el apellido del empleado");
                }
            } else {
                System.out.println("El nuevo apellido es inválido");
            }
        } else {
            System.out.println("El usuario no puede ser nulo");
        }
    }
    /**
     * Metodo para cambiar el cargo de un empleado
     * @param usuario Usuario al que se aplica el cambio
     * @param newCargo Nuevo cargo que reemplazara al antiguo
     */
    public void setEmpleadoCargo(Usuario usuario, String newCargo) {
        if (usuario != null) {
            if (newCargo != null && !newCargo.isEmpty() && (newCargo.equals("Mesero") || newCargo.equals("Administrador"))) {
                usuario.setCargo(newCargo);
                if (Empleados.actualizarEmpleado(usuario, Usuario.getEmpleados().indexOf(usuario))) {
                    System.out.println("Cargo del empleado actualizado correctamente");
                } else {
                    System.out.println("No se pudo actualizar el cargo del empleado");
                }
            } else {
                System.out.println("El nuevo cargo es inválido");
            }
        } else {
            System.out.println("El usuario no puede ser nulo");
        }
    }
    
    /**
     * Metodo para cambiar los cocineros disponible en cocina
     * @param cocina Cocina en uso
     * @param disponibles Nuevo numero de cocineros disponibles
     */
    public void setCocinerosDisponibles(int newDisponibles){
        if (newDisponibles >= Cocina.getCocinerosOcupados()) {
            Cocina.setCocinerosDisponibles(newDisponibles);
        } else {
            System.out.println("El número de cocineros disponibles no puede ser menor al número de cocineros ocupados");
        }
    }

}
