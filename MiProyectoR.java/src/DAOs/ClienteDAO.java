package src.DAOs;
import java.util.ArrayList;
import java.util.List;

import src.entities.Cliente;
/**
 * 1. Se corrigió el package: antes decía 'package dao;' y la carpeta se llama 'DAOs'
 * 2. Se agregó 'import entities.Cliente;' porque Cliente está en otro paquete y marcaba error en rojo
 * 3. Se inicializó la lista en el constructor para evitar error Null
 */
public class ClienteDAO {
    private List<Cliente> listaClientes;

    public ClienteDAO() {
            /**
             * Antes la lista era null y al hacer add() se caía el programa
             */
        listaClientes = new ArrayList<>();
    }

    public void registra(Cliente cliente) {
        /**
         *  el DAO guarda y delega la lógica a la entidad
         */
        listaClientes.add(cliente);
        cliente.registra();
    }

    public List<Cliente> get() {
        return listaClientes;
    }

    public void set(int posicion, Cliente clienteNuevo) {
        /**
         * verificamos que la posición exista antes de editar
         */
        if (posicion >= 0 && posicion < listaClientes.size()) {
            Cliente actual = listaClientes.get(posicion);
            actual.setTipoContacto(clienteNuevo.getTipoContacto());
            actual.setUbicacion(clienteNuevo.getUbicacion());
        }
    }

    public void formaPago(int posicion, String forma) {
        /**
         *  Asigna forma de pago según la posición del cliente en la lista
         */
        if (posicion >= 0 && posicion < listaClientes.size()) {
            listaClientes.get(posicion).formaPago(forma);
        }
    }
}
    
