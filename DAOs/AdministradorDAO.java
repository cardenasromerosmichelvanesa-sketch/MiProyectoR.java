import java.util.List;
import entities.*;
public class AdministradorDAO {
    
    private void verReporteVentas(List<Pedido> reporte) {
        System.out.println(reporte);
    }
    private void gestionMesas() {
        
    }

    private void setUsuarioId(Usuario usuario, int newId) {
        usuario.setId(newId);
    }
    private void setUsuarioNombre(Usuario usuario, String newNombre) {
        usuario.setNombre(newNombre);
    }
    private void setUsuarioApellido(Usuario usuario, String newApellido) {
        usuario.setApellido(newApellido);
    }

    private void tiempoPreparacion(Productos producto) {

    }

}
