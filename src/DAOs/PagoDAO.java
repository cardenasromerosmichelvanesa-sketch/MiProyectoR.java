package src.DAOs;

import java.util.ArrayList;
import java.util.List;
import src.entities.Pagos;

public class PagoDAO {

    private List<Pagos> listaPagos = new ArrayList<>();
    private int siguienteId = 1;

    // Registrar un pago
    public void crear(Pagos pago) {
        listaPagos.add(pago);
    }

    // Consultar todos los pagos
    public List<Pagos> obtenerTodos() {
        return listaPagos;
    }

    // Buscar pago por ID
    public Pagos obtenerPorId(int id) {
        for (Pagos p : listaPagos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Actualizar pago
    public boolean actualizar(Pagos pagoActualizado) {
        for (int i = 0; i < listaPagos.size(); i++) {
            if (listaPagos.get(i).getId() == pagoActualizado.getId()) {
                listaPagos.set(i, pagoActualizado);
                return true;
            }
        }
        return false;
    }

    // Eliminar pago
    public boolean eliminar(int id) {
        return listaPagos.removeIf(p -> p.getId() == id);
    }

    // Registrar pago en el historial
    public void registrarPagoHistorico(int idPedido, double monto, String metodo) {
        Pagos nuevoPago = new Pagos(siguienteId, idPedido, monto, metodo);
        listaPagos.add(nuevoPago);
        siguienteId++;

        System.out.println("Pago guardado en el historial.");
    }
}
