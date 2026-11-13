package src.vista;

/**
 * Interfaz que define el comportamiento
 * común para todos los métodos de pago.
 *
 * @author Michel Vanesa Romero
 */
public interface FormaPago {

    // Método que deben implementar todas
    // las formas de pago.
    boolean procesarPago(double monto);

}
