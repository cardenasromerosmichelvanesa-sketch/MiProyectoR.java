
public class reporteVentasDAO {
   
    public List<Pedido> pedidoEntregado(Pedido PEDIDO, List<Pedido> reporte){
        reporte.add(miMarca);
        return reporte;
    }
    public Pedido buscarPedidoPorId(List<Pedido> reporte, String ID){
    Pedido encontrado = new Pedido();
    for(Pedido m: reporte){
        if(m.getNombre().compareTo(ID)==0){
            encontrado = m;
            break;
        }
    }
    return encontrado;
    }
    
    public int buscarIndiceDeMarca(List<Pedido> reporte, String ID){
    Pedido PEDIDO = new Pedido();
    for(Pedido m: reporte){
        if(m.getNombre().compareTo(ID)==0){
            miMarca = m;
            break;
        }
    }
    return reporte.indexOf(miMarca);
    }
    public List<Pedido> actualizarMarca(Pedido miMarca, List<Pedido> reporte){
       int indice = miMarca.getIdMarca() -1;
        reporte.set(indice, miMarca);
        return reporte;
    }
    public List<Pedido> eliminarMarca(String nombre, List<Pedido> reporte){
        Pedido miMarca = buscarMarcaPorNombre(reporte, nombre);
        reporte.remove(miMarca);
        return reporte;
    }
    
    public void verLista(List<Pedido> reporte){
        System.out.println(reporte);
    }
}
