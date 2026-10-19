package src.entities;

/**
 * Implementación de FormaPago con los distintos medios de pago.
 */
public class Pago implements FormaPago {

    @Override
    public boolean procesarPago(double monto) {
        pagoEfectivo(monto);
        return true;
    }

    public void pagoEfectivo(double monto) {
        System.out.println("Pago en efectivo realizado por: $" + monto);
    }

    public void pagoTarjeta(String numTarjeta, double monto) {
        System.out.println("Pago de $" + monto + " procesado con la tarjeta " + numTarjeta);
    }

    public void pagoTransferencia(String numCuenta, double monto) {
        System.out.println("Transferencia de $" + monto + " realizada a la cuenta " + numCuenta);
    }
}
