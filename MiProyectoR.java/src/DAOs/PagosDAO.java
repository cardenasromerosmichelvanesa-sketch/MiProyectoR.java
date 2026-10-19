package src.DAOs;

import src.entities.Pagos;
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

    /**
     * Registra un pago nuevo en el historial con id autogenerado
     */
    public void registrarPagoHistorico(int idPedido, double monto, String metodo) {
        crear(new Pagos(listaPagos.size() + 1, idPedido, monto, metodo));
    }

    /**
     * @return historial de pagos como lista de textos
     */
    public List<String> obtenerHistorial() {
        List<String> historial = new ArrayList<>();
        for (Pagos p : listaPagos) {
            historial.add(p.toString());
        }
        return historial;
    }
}
