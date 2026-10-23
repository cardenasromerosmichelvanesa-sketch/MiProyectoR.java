package src.vista;

/**
 * Implementación de pago por transferencia bancaria (Nequi, Daviplata, etc.).
 * 
 * @author Michel Vanesa Romero
 */
public class PagoTransferencia implements FormaPago {
    private String numCuenta;

    public PagoTransferencia(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Validando comprobante de transferencia a la cuenta " + numCuenta);
        System.out.println("Transferencia por $" + monto + " confirmada.");
        return true;
    }

    public String getNumCuenta() {
        return numCuenta;
    }
}
