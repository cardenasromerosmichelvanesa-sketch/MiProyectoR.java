package src.DAOs;
import java.util.ArrayList;
import java.util.List;
import src.entities.Producto;

public class ProductoDAO {
    private List<Producto> listaProducto = new ArrayList<>();

    public void crear(Producto p) {
        listaProducto.add(p);
    }

    public List<Producto> obtenerTodos() {
        return listaProducto;
    }

    public Producto obtenerPorId(String id) {
        for (Producto p : listaProducto) {
            if (p.getId().compareTo(id)==0) return p;
        }
        return null;
    }

  
    public boolean actualizar(Producto pModificado) {
        for (int i = 0; i < listaProducto.size(); i++) {
            if (listaProducto.get(i).getId().equals(pModificado.getId())) {
                listaProducto.set(i, pModificado);
                return true;
            }
        }
        return false;
    }


    public boolean eliminar(String id) {
        return listaProducto.removeIf(p -> p.getId().compareTo(id)==0);
    }
}
