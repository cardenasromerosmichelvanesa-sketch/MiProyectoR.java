/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author salaG201
 */
public class menú {

    import java.util.ArrayList;
import java.util.List;

   
    private String idProd;
    private double precioProd;
    private List<Producto> listaProductos;


    public Menu(String idProd, double precioProd) {
        this.idProd = idProd;
        this.precioProd = precioProd;
        this.listaProductos = new ArrayList<>();
    }

    
    public String getIdProd() {
        return idProd;
    }

    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }

    public double getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(double precioProd) {
        this.precioProd = precioProd;
    }


    public void registrarProducto(Producto producto) {
        this.listaProductos.add(producto);
    }

    public void consultarPrecios() {
        for (Producto p : listaProductos) {
            System.out.println("Producto: " + p.getNombre() + " - Precio: $" + p.getPrecio());
        }
    }
}
