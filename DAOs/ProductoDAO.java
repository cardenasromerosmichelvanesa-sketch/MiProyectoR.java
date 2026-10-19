package dao;

import model.Productos;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAO {
    private List<Productos> listaProductos = new ArrayList<>();

    public void crear(Productos p) {
        listaProductos.add(p);
    }

    public List<Productos> obtenerTodos() {
        return listaProductos;
    }

    public Productos obtenerPorId(int id) {
        for (Productos p : listaProductos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

  
    public boolean actualizar(Productos pModificado) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == pModificado.getId()) {
                listaProductos.set(i, pModificado);
                return true;
            }
        }
        return false;
    }


    public boolean eliminar(int id) {
        return listaProductos.removeIf(p -> p.getId() == id);
    }
}
