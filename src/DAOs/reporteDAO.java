package src.DAOs;
import java.util.List;
import src.entities.Pedido;
public class ReporteDAO {
    
    
    /**
    * Metodo para agregar pedidos al reporte
    * @param Pedidio Pedido que se va a guardar en el reporte
    * @param reporte Lista del reporte que se usa
    * @return Retorna la lista que se uso como reporte
    */
    public List<Pedido> registrarPedido(Pedido Pedido, List<Pedido> reporte) {
        reporte.add(Pedido);
        return reporte;
    }

    /**
     * Método que permite buscar un pedido por id en el reporte
     * @param reporte Lista del reporte que se usa
     * @param id id del pedido a buscar
     * @return El objeto de tipo pedido encontrado
     */
    public Pedido buscarPedidoPorId(List<Pedido> reporte, String id){
    Pedido encontrado = new Pedido();
    for(Pedido m: reporte){
        if(m.getId().compareTo(id)==0){
            encontrado = m;
            break;
        }
    }
    return encontrado;
    }

    /**
     * Método que permite obtener el índice de un objeto en la colección
     * @param reporte Lista del reporte que se usa
     * @param id id del pedido a buscar
     * @return el índice en la lista de reporte
     */
    public int buscarIndiceDePedido(List<Pedido> reporte, String id){
    Pedido encontrado = new Pedido();
    for(Pedido m: reporte){
        if(m.getId().compareTo(id)==0){
            encontrado = m;
            break;
        }
    }
    return reporte.indexOf(encontrado);
    }

    /**
     * Método para actualizar un pedido
     * @param pedido Pedido a actualizar
     * @param newPedido Nuevos datos para pedido
     * @param reporte Lista del reporte que se usa
     * @return 
     */
    public List<Pedido> actualizarPedido(Pedido pedido, Pedido newPedido , List<Pedido> reporte){
        int indice = reporte.indexOf(pedido);
        reporte.set(indice, newPedido);
        return reporte;
    }
    
    /**
     * Método para eliminar un pedido del reporte
     * @param id nombre de la marca a remover
     * @param reporte colección de marcas
     * @return la lista acualizada
     */
    public List<Pedido> eliminarMarca(String id, List<Pedido> reporte){
        int indice = buscarIndiceDePedido(reporte, id);
        reporte.remove(indice);
        return reporte;
    }
    
    public void verReporte(List<Pedido> reporte){
        System.out.println(reporte);
    }

}
