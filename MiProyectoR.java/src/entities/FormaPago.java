package src.entities;

/**
 * Interfaz para las formas de pago del restaurante.
 */
public interface FormaPago {
    /**
     * Procesa un pago por el monto indicado
     * @param monto monto a pagar
     * @return true si el pago fue exitoso
     */
    boolean procesarPago(double monto);
}
