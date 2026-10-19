import java.util.List;

import entities.*;
public class AdministradorDAO {
    
    /**
     * Metodo para ver la lista usada para el reporte
     * @param reporte Lista usada para el reporte
     */
    private void verReporteVentas(List<Pedido> reporte) {
        System.out.println("|\t Id\t|\t Monto\t|");
        for (Pedido aux: reporte) {
            System.out.println("| "+aux.getId()+"\t|\t"+aux.getMonto()+"\t|");
        }
    }

    /**
     * Metodo que cambia el mesero asignado a una mesa
     * @param mesa Mesa a la que se le asigna el mesero
     * @param mesero Mesero que sera asignado a la mesa
     */
    private void meseroAsignadoMesas(Mesa mesa, Mesero mesero) {
        mesa.setMesero(mesero);
    }
    /**
     * Metodo para ver el listado de todas las mesas
     * @param mesas Lista que se usa para registrar las mesas
     */
    private void verMesa(List<Mesa> mesas) {
        System.out.println(mesas);
    }
    /**
     * Metodo que asigna un mesero a una mesa
     * @param pedido Pedido destinado a la mesa
     * @param mesa Mesa a la que va el pedido
     */
    private void asignarMesa(Pedido pedido, Mesa mesa) {
        if (mesa.isDisponible()) {
            pedido.setMesa(mesa.getNumMesa());
            mesa.setDisponible(false);
        }
        else {
            System.out.println("Mesa no disponible");
        }
    }

    /**
     * Metodo para añadir un empleado a la lista de empleados
     * @param empleados Lista usada para almacenar a los empleados
     * @param usuario Empleado que se agrega a la lista
     * @return La lista usada mas el empleado nuevo
     */
    private List<Usuario> añadirEmpleado(List<Usuario> empleados, Usuario usuario){
        empleados.add(usuario);
        return empleados;
    }
    /**
     * Metodo para eliminar un empleado a la lista de empleados
     * @param empleados Lista usada para almacenar a los empleados
     * @param usuario Empleado que se elimina de la lista
     * @return La lista usada menos el empleado eliminado
     */
    private List<Usuario> eliminarEmpleado(List<Usuario> empleados, Usuario usuario){
        empleados.remove(usuario);
        return empleados;
    }
    /**
     * Metodo para cambiar el id de un usuario
     * @param usuario Usuario al que se aplica el cambio
     * @param newId Nuevo id que reemplazara al antiguo
     */
    private void setUsuarioId(Usuario usuario, int newId) {
        usuario.setId(newId);
    }
    /**
     * Metodo para cambiar el nombre de un usuario
     * @param usuario Usuario al que se aplica el cambio
     * @param newNombre Nuevo nombre que reemplazara al antiguo
     */
    private void setUsuarioNombre(Usuario usuario, String newNombre) {
        usuario.setNombre(newNombre);
    }
    /**
     * Metodo que cambia el apellido de un usuario
     * @param usuario Usuario al que se aplica el cambio
     * @param newApellido Nuevo apellido que reemplazara al antiguo
     */
    private void setUsuarioApellido(Usuario usuario, String newApellido) {
        usuario.setApellido(newApellido);
    }

    /**
     * Metodo que muestra el tiempo de preparacion del producto
     * @param producto Producto del que se muestra el tiempo de preparación
     */
    private void verTiempoPreparacion(Producto producto) {
        producto.getTiempopre();
    }
    /**
     * Metodo para cambiar el tiempo de preparacion de un producto
     * @param producto Producto que sufrira el cambio en el tiemppo de preparación
     * @param tiempo Nuevo tiempo de preparación
     */
    private void cambiarTiempoPreparacion(Producto producto, String tiempo){
        producto.setTiempopre(tiempo);
    }

    /**
     * Metodo para añadir un producto a la lista menú
     * @param menu Lista usada como menú
     * @param producto Producto agregado a la lista
     * @return Lista usada mas el nuevo producto
     */
    private List<Producto> anadirProductoMenu(List<Producto> menu, Producto producto) {
        menu.add(producto);
        return menu;
    }
    /**
     * Metodo para eliminar un producto de la lista menú
     * @param menu Lista usada como menú
     * @param producto Producto a eliminar de la lista
     * @return Lista usada menos el producto enviado
     */
    private List<Producto> eliminarProductoMenu(List<Producto> menu, Producto producto) {
        menu.remove(producto);
        return menu;
    }

}







