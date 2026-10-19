package dao;

import model.Pagos;
import java.util.ArrayList;
import java.util.List;

public class PagosDAO {
    private List<Pagos> listaPagos = new ArrayList<>();

   
    public void crear(Pagos pago) {
        listaPagos.add(pago);
    }

    
    public List<Pagos> obtenerTodos() {
        return listaPagos;
    }

    public Pagos obtenerPorId(int id) {
        for (Pagos p : listaPagos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public boolean actualizar(Pagos pagoActualizado) {
        for (int i = 0; i < listaPagos.size(); i++) {
            if (listaPagos.get(i).getId() == pagoActualizado.getId()) {
                listaPagos.set(i, pagoActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        return listaPagos.removeIf(p -> p.getId() == id);
    }
}
