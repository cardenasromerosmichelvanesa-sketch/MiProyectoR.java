package entities;
public class Cliente {
    private String tipoContacto;
    private String ubicacion;

   
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
        System.out.println("El cliente paga con : " + forma);
    }

   
    public void registra() {
        System.out.println("Cliente se ha registrado correctamente");
    }
}
