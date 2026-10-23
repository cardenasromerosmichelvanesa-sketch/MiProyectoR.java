package src.DAOs;
import src.entities.*;
import java.util.List;
import java.util.ArrayList;
public class AdministradorDAO {
    
    public AdministradorDAO() {
    }

    public void listaPedidos(PedidoDAO pedidoDAO) {
        System.out.println(pedidoDAO.listaPedidos());
    }


    /**
     * Metodo que cambia el mesero asignado a una mesa
     * @param mesa Mesa a la que se le asigna el mesero
     * @param mesero Mesero que sera asignado a la mesa
     */
    public void meseroAsignadoMesas(Mesa mesa, Mesero mesero) {
        mesa.setMesero(mesero);
    }
    /**
     * Metodo para ver el listado de todas las mesas
     * @param mesas Lista que se usa para registrar las mesas
     */
    public void verMesa(List<Mesa> mesas) {
        System.out.println(mesas);
    }
    /**
     * Metodo que asigna un mesero a una mesa
     * @param pedido Pedido destinado a la mesa
     * @param mesa Mesa a la que va el pedido
     */
    public void asignarMesa(Pedido pedido, Mesa mesa) {
        if (mesa.isDisponible()) {
            pedido.setMesa(mesa.getNumMesa());
            mesa.setDisponible(false);
        }
        else {
            System.out.println("Mesa no disponible");
        }
    }
    /**
     * Metodo para ver el listado de empleados
     */
    public void verEmpleados() {
        System.out.println(Usuario.empleados);
    }
    /**
     * Metodo para añadir un empleado a la lista de empleados
     * @param usuario Usuario que se agrega a la lista
     */
    public void añadirEmpleado(Usuario usuario) {
        Usuario.empleados.add(usuario);
    }
    /**
     * Metodo para eliminar un empleado a la lista de empleados
     * @param usuario Usuario que se elimina de la lista
     */
    public void eliminarEmpleado(Usuario usuario) {
        Usuario.empleados.remove(usuario);
    }
    /**
     * Metodo para cambiar el id de un empleado
     * @param usuario Usuario al que se aplica el cambio
     * @param newId Nuevo id que reemplazara al antiguo
     */
    public void setEmpleadoId(Usuario usuario, int newId) {
        usuario.setId(newId);
    }
    /**
     * Metodo para cambiar el nombre de un empleado
     * @param usuario Usuario al que se aplica el cambio
     * @param newNombre Nuevo nombre que reemplazara al antiguo
     */
    public void setEmpleadoNombre(Usuario usuario, String newNombre) {
        usuario.setNombre(newNombre);
    }
    /**
     * Metodo para cambiar el apellido de un empleado
     * @param usuario Usuario al que se aplica el cambio
     * @param newApellido Nuevo apellido que reemplazara al antiguo
     */
    public void setEmpleadoApellido(Usuario usuario, String newApellido) {
        usuario.setApellido(newApellido);
    }
    /**
     * Metodo para obtener un empleado por su id
     * @param id Id del empleado que se desea obtener
     * @return Usuario con el id especificado, o null si no se encuentra
     */
    public Usuario getEmpleadoPorId(int id) {
        for (Usuario empleado : Usuario.empleados) {
            if (empleado.getIdo() == id) {
                return empleado;
            }
        }
        return null; // Retorna null si no se encuentra el empleado
    }
    /**
     * Metodo para obtener un empleado por su nombre
     * @param nombre Nombre del empleado que se desea obtener
     * @return Usuario con el nombre especificado, o null si no se encuentra
     */
    public Usuario getEmpleadoPorNombre(String nombre) {
        for (Usuario empleado : Usuario.empleados) {
            if (empleado.getNombre().equals(nombre)) {
                return empleado;
            }
        }
        return null; // Retorna null si no se encuentra el empleado
    }
    /**
     * Metodo para obtener un empleado por su apellido
     * @param apellido Apellido del empleado que se desea obtener
     * @return Usuario con el apellido especificado, o null si no se encuentra
     */
    public Usuario getEmpleadoPorApellido(String apellido) {
        for (Usuario empleado : Usuario.empleados) {
            if (empleado.getApellido().equals(apellido)) {
                return empleado;
            }
        }
        return null; // Retorna null si no se encuentra el empleado
    }

    /**
     * Metodo para añadir un producto a la lista menú
     * @param menu Lista usada como menú
     * @param producto Producto agregado a la lista
     * @return Lista usada mas el nuevo producto
     */
    public List<Producto> anadirProductoMenu(List<Producto> menu, Producto producto) {
        menu.add(producto);
        return menu;
    }
    /**
     * Metodo para eliminar un producto de la lista menú
     * @param menu Lista usada como menú
     * @param producto Producto a eliminar de la lista
     * @return Lista usada menos el producto enviado
     */
    public List<Producto> eliminarProductoMenu(List<Producto> menu, Producto producto) {
        menu.remove(producto);
        return menu;
    }

    
    /**
     * Metodo para cambiar los cocineros disponible en cocina
     * @param cocina Cocina en uso
     * @param disponibles Nuevo numero de cocineros disponibles
     */
    public void setCocinerosDisponibles(Cocina cocina, int disponibles){
        cocina.setCocinerosDisponibles(disponibles);
    }

}
