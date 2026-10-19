
package entities;

public class Cliente {
    private String tipoContacto;
    private String ubicacion;
    private String formaPago;
   
    public Cliente(String tipoContacto, String ubicacion) {
        this.tipoContacto = tipoContacto;
        this.ubicacion = ubicacion;
    }

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

    
     public void formaPago(String forma) {
        this.formaPago = forma;
        System.out.println("El cliente paga con : " + forma);
    }

    public void registra() {
        System.out.println("Cliente se ha registrado correctamente en " + ubicacion);
    }
    
    
    public String toString() {
        return "Cliente [Contacto=" + tipoContacto + ", Ubicacion=" + ubicacion + "]";
    }
}



    
    

