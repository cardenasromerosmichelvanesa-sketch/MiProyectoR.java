package src.manager;

import dao.ProductoDAO;
import dao.PagoDAO;
import model.*;

import java.util.List;

/**
 * Manager encargado de las 5 funcionalidades que se asignaron
 * 1. Registrar productos
 * 2. Modificar productos
 * 3. Consultar precios
 * 4. Controlar cantidades de productos
 * 5. Procesar pagos mediante la interfaz FormaPago
 * 
 * @author Michel Vanesa Romero
 */
public class ManagerMichel {

    private ProductoDAO productoDAO;
    private PagoDAO pagoDAO;

    public ManagerMichel() {
        this.productoDAO = new ProductoDAO();
        this.pagoDAO = new PagoDAO();

        // Datos iniciales de prueba para el menú del restaurante
        productoDAO.crear(new Producto(1, "Hamburguesa Poli", 18000, 20));
        productoDAO.crear(new Producto(2, "Gaseosa cocacola original", 4500, 50));
        productoDAO.crear(new Producto(3, "Papas Medianas", 6000, 30));
    }
  
    /**
  * método que realiza la función 1 registrarProducto
  * registra según los datos ingresados por el cliente
  * param id Id persona
  * param nombre Nombre persona 
  * param precio Precio producto
  * param cantidad Cantidad Productos 
  */
    
    public void registrarProducto(int id, String nombre, double precio, int cantidad) {
        Producto nuevo = new Producto(id, nombre, precio, cantidad);
        productoDAO.crear(nuevo);
        System.out.println("Producto registrado correctamente.");
    }
      /**
  * método que realiza la función 2 modificar producto
  * modifica según eleción del cliente
  * param id Id peroducto
  * param nuevoNombre nombre del producto 
  * param nuevoPrecio Precio producto
  * param nuevaCantidad Cantidad Productos 
  */

  
  public boolean modificarProducto(int id, String nuevoNombre, double nuevoPrecio, int nuevaCantidad) {
        Producto actualizado = new Producto(id, nuevoNombre, nuevoPrecio, nuevaCantidad);
        boolean exito = productoDAO.actualizar(actualizado);
        if (exito) {
            System.out.println("Producto actualizado correctamente.");
        } else {
            System.out.println("No se encontró un producto con el ID ingresado.");
        }
        return exito;
    }

        /**
  * método que realiza la función 3 consultar precios y productos
  * mediante lista se logra visualizar de forma clara los productos ingresados
  */
    public List<Producto> consultarPreciosYProductos() {
        return productoDAO.obtenerTodos();
    }

        /**
  * método que realiza la función 4 controla las cantidades 
  * registra según las que se kngresaron
  * param id Id producto
  * param int muevaCantidad
  */
    public boolean controlarCantidades(int idProducto, int nuevaCantidad) {
        Producto p = productoDAO.obtenerPorId(idProducto);
        if (p != null) {
            p.setCantidad(nuevaCantidad);
            productoDAO.actualizar(p);
            System.out.println("Stock actualizado a " + nuevaCantidad + " unidades.");
            return true;
        }
        System.out.println("Producto no encontrado para ajustar stock.");
        return true;
    }
      /**
  * método que realiza la función 5 procesar pago
  * param id Id pedido
  * param monto monto total
  * param metodo pago 
  * param nombre
  */
          
    public boolean procesarPago(int idPedido, double monto, FormaPago metodoPago, String nombreMetodo) {
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor a cero.");
            return false;
        }

        boolean resultado = metodoPago.procesarPago(monto);
        if (resultado) {
            pagoDAO.registrarPagoHistorico(idPedido, monto, nombreMetodo);
            System.out.println("se registro el pago correctamente");
        }
        return resultado;
    }

  
