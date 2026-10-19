package DAOs;
import java.util.ArrayList;
import java.util.List;
import entities.Cliente;
public class ClienteDAO {
    private List<Cliente> listaClientes;

    public ClienteDAO() {
        listaClientes = new ArrayList<>();
    }

    public void registra(Cliente cliente) {
        listaClientes.add(cliente);
        cliente.registra();
    }

    public List<Cliente> get() {
        return listaClientes;
    }

    public void set(int posicion, Cliente clienteNuevo) {
        if (posicion >= 0 && posicion < listaClientes.size()) {
            Cliente actual = listaClientes.get(posicion);
            actual.setTipoContacto(clienteNuevo.getTipoContacto());
            actual.setUbicacion(clienteNuevo.getUbicacion());
        }
    }

    public void formaPago(int posicion, String forma) {
        if (posicion >= 0 && posicion < listaClientes.size()) {
            listaClientes.get(posicion).formaPago(forma);
        }
    }
}
    
