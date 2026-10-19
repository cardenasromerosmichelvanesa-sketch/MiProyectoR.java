
package src.entities;

public class Cliente {
    private String tipoContacto;
    private String ubicacion;
    private String formaPago;
   /**
    * 1. Se organizaron los atributos privados con encapsulamiento
    * 2. Se crearon getters y setters para tipoContacto y ubicacion
    * 3. Se implementó el método formaPago() y registra() que usa el DAO
    */
    public Cliente(String tipoContacto, String ubicacion) {
        this.tipoContacto = tipoContacto;
        this.ubicacion = ubicacion;
    } 
    /**
     * Getters y Setters generados para que el DAO pueda modificar el cliente
     */

    public String getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(String tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Este método lo llama el ClienteDAO para registrar la forma de pago 
     */
     public void formaPago(String forma) {
        this.formaPago = forma;
        System.out.println("El cliente paga con : " + forma);
    }
     /**
      * Este simula el registro exitoso del cliente
      */

    public void registra() {
        System.out.println("Cliente se ha registrado correctamente en " + ubicacion);
    }
    
    
    public String toString() {
        return "Cliente Contacto=" + tipoContacto + ", Ubicacion" + ubicacion;
    }
}



    
    

