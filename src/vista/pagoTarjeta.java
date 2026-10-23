package src.vista;

public class PagoTarjeta implements FormaPago {
    private long numTarjeta;

    public PagoTarjeta(long numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Conectando...");
        System.out.println("Procesando transacción con tarjeta terminada en " + (numTarjeta % 10000));
        System.out.println("Monto cobrado: $" + monto + " - Transacción APROBADA.");
        return true;
    }

    public long getNumTarjeta() {
        return numTarjeta;
    }
}
