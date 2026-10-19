package entities;
import java.util.List;
import java.util.List;
public class Menu {

    private String idProd;
    private List<Producto> listaProductos;

    public Menu(String idProd, List<Producto> listaProductos) {
        this.idProd = idProd;
        this.listaProductos = listaProductos;
    }
    
    public String getIdProd() {
        return idProd;
    }

    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }

    public void registrarProducto(Producto producto) {
        this.listaProductos.add(producto);
    }

    public void consultarPrecios() {
        for (Producto p : listaProductos) {
            System.out.println("Producto: " + p.getNombre() + " - Precio: $" + p.getPrecio());
        }
    }
}
