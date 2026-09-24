/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restaurante;

/**
 *
 * @author salaG201
 */
public class Restaurante {

    /**
     * @param args the command line arguments
     */
   public interface FormaPago {
    void pagoEfectivo(double monto);
    void pagoTarjeta(String numTarjeta, double monto);
    void pagoTransferencia(String numCuenta, double monto);
}

    
}
