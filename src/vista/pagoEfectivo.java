
package src.vista;
public class PagoEfectivo implements FormaPago {

    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando pago en EFECTIVO por valor de: $" + monto);
        System.out.println("Pago verificado e ingresado en caja registradora.");
        return true;
    }
}
