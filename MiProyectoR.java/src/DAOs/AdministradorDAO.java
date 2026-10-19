package src.DAOs;
import java.util.List;

import src.entities.*;
public class AdministradorDAO {
    
    /**
     * Metodo para ver la lista usada para el reporte
     * @param reporte Lista usada para el reporte
     */
    public void verReporteVentas(List<Pedido> reporte) {
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
     * Metodo para añadir un empleado a la lista de empleados
     * @param empleados Lista usada para almacenar a los empleados
     * @param usuario Empleado que se agrega a la lista
     * @return La lista usada mas el empleado nuevo
     */
    public List<Usuario> añadirEmpleado(List<Usuario> empleados, Usuario usuario){
        empleados.add(usuario);
        return empleados;
    }
    /**
     * Metodo para eliminar un empleado a la lista de empleados
     * @param empleados Lista usada para almacenar a los empleados
     * @param usuario Empleado que se elimina de la lista
     * @return La lista usada menos el empleado eliminado
     */
    public List<Usuario> eliminarEmpleado(List<Usuario> empleados, Usuario usuario){
        empleados.remove(usuario);
        return empleados;
    }
    /**
     * Metodo para cambiar el id de un usuario
     * @param usuario Usuario al que se aplica el cambio
     * @param newId Nuevo id que reemplazara al antiguo
     */
    public void setUsuarioId(Usuario usuario, int newId) {
        usuario.setId(newId);
    }
    /**
     * Metodo para cambiar el nombre de un usuario
     * @param usuario Usuario al que se aplica el cambio
     * @param newNombre Nuevo nombre que reemplazara al antiguo
     */
    public void setUsuarioNombre(Usuario usuario, String newNombre) {
        usuario.setNombre(newNombre);
    }
    /**
     * Metodo que cambia el apellido de un usuario
     * @param usuario Usuario al que se aplica el cambio
     * @param newApellido Nuevo apellido que reemplazara al antiguo
     */
    public void setUsuarioApellido(Usuario usuario, String newApellido) {
        usuario.setApellido(newApellido);
    }

    /**
     * Metodo que muestra el tiempo de preparacion del producto
     * @param producto Producto del que se muestra el tiempo de preparación
     */
    public void verTiempoPreparacion(Producto producto) {
        producto.getTiempopre();
    }
    /**
     * Metodo para cambiar el tiempo de preparacion de un producto
     * @param producto Producto que sufrira el cambio en el tiemppo de preparación
     * @param tiempo Nuevo tiempo de preparación
     */
    public void cambiarTiempoPreparacion(Producto producto, String tiempo){
        producto.setTiempopre(tiempo);
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
     * Metodo para ver cuantos cocineros tiene disponibles la cocina
     * @param cocina Cocina en uso
     */
    public void verCocinerosDisponibles(Cocina cocina){
        cocina.getCocinerosDisponibles();
    }
    /**
     * Metodo para cambiar los cocineros disponible en cocina
     * @param cocina Cocina en uso
     * @param disponibles Nuevo numero de cocineros disponibles
     */
    public void setCocinerosDisponibles(Cocina cocina, int disponibles){
        cocina.setCocinerosDisponibles(disponibles);
    }
    /**
     * Metodo para ver cuantos cocineros tiene ocupados la cocina
     * @param cocina Cocina en uso
     */
    public void verCocinerosOcupados(Cocina cocina){
        cocina.getCocinerosOcupados();
    }
    /**
     * Metodo para cambiar los cocineros ocupados en cocina
     * @param cocina Cocina en uso
     * @param ocupados Nuevo numero de cocineros ocupados
     */
    public void setCocinerosOcupados(Cocina cocina, int ocupados){
        if (cocina.getCocinerosDisponibles()>ocupados && cocina.disponibilidad(cocina)) {
            cocina.setCocinerosOcupados(ocupados);
        }
        else {
            System.out.println("Error");
        }
    }

}
