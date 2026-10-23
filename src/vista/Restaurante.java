/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package src.vista;

public interface FormaPago {
    /**
     * Procesa la transacción según la estrategia de pago seleccionada.
     * 
     * @param monto Valor total a pagar
     * @return true si el pago se completó con éxito, false en caso contrario
     */
    boolean procesarPago(double monto);
}
