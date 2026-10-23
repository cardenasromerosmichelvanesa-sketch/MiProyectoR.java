package src.entities;

/**
 * Cliente del restaurante: guarda su contacto, ubicación y forma de pago.
 */
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

    /**
     * @return la forma de pago (antes no había getter y el atributo no se podía leer)
     */
    public String getFormaPago() {
        return formaPago;
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

    @Override
    public String toString() {
        return "Cliente{contacto=" + tipoContacto + ", ubicacion=" + ubicacion
                + ", formaPago=" + formaPago + "}";
    }
}
