package src.formularios.felipe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import src.entities.DetallePedido;
import src.entities.Pedido;

/** Funcionalidad 4 de ManagerFelipe: ver el detalle de un pedido y agregarle productos. */
public class VentanaDetallePedido extends VentanaBase {

    private final JTextField txtPedido = new JTextField(8);
    private final JTextField txtIdProd = new JTextField(5);
    private final JTextField txtNombre = new JTextField(10);
    private final JTextField txtPrecio = new JTextField(6);
    private final JTextField txtCantidad = new JTextField(4);
    private final JLabel lblMesero = new JLabel(" ");
    private final JLabel lblEstado = new JLabel(" ");
    private final JLabel lblTotal = new JLabel("Total: $0");
    private final JButton btnAgregar = new JButton("Agregar");
    private final JTable tabla = tabla("Producto", "ID", "Precio", "Cantidad", "Subtotal");
    private Pedido pedido;

    /** @param idPedido id con el que se abre (puede ser null para escribirlo a mano) */
    public VentanaDetallePedido(DatosFelipe datos, String idPedido) {
        super("Detalle de pedido", datos);
        setSize(680, 500);
        setLocationRelativeTo(null);

        JPanel buscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnBuscar = new JButton("Buscar");
        buscar.add(new JLabel("ID del pedido:"));
        buscar.add(txtPedido);
        buscar.add(btnBuscar);

        JPanel info = new JPanel(new GridLayout(2, 1));
        info.add(lblMesero);
        info.add(lblEstado);

        JPanel agregar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        agregar.setBorder(BorderFactory.createTitledBorder("Agregar producto (solo pedidos pendientes)"));
        agregar.add(new JLabel("ID:"));
        agregar.add(txtIdProd);
        agregar.add(new JLabel("Nombre:"));
        agregar.add(txtNombre);
        agregar.add(new JLabel("Precio:"));
        agregar.add(txtPrecio);
        agregar.add(new JLabel("Cant.:"));
        agregar.add(txtCantidad);
        agregar.add(btnAgregar);

        lblTotal.setFont(lblTotal.getFont().deriveFont(Font.BOLD, 16f));
        JPanel sur = new JPanel(new BorderLayout());
        sur.add(agregar, BorderLayout.NORTH);
        sur.add(lblTotal, BorderLayout.SOUTH);
        lblTotal.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(buscar, BorderLayout.NORTH);
        norte.add(info, BorderLayout.SOUTH);
        info.setBorder(BorderFactory.createEmptyBorder(0, 10, 6, 10));

        add(norte, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscar());
        txtPedido.addActionListener(e -> buscar());
        btnAgregar.addActionListener(e -> agregarProducto());
        txtCantidad.addActionListener(e -> agregarProducto());

        if (idPedido != null) {
            txtPedido.setText(idPedido);
            pedido = datos.pedidoDAO.buscarPorId(idPedido);
        }
        refrescar();
    }

    private void buscar() {
        if (datos.pedidoDAO.listaPedidos().isEmpty()) {
            error("No hay pedidos registrados.");
            return;
        }
        pedido = datos.pedidoDAO.buscarPorId(txtPedido.getText().trim());
        if (pedido == null) {
            error("El pedido no existe.");
        }
        refrescar();
    }

    private void agregarProducto() {
        if (pedido == null) {
            error("Primero busca un pedido.");
            return;
        }
        if (!"Pendiente".equalsIgnoreCase(pedido.getEstado())) {
            error("Solo se pueden agregar productos a pedidos pendientes.");
            return;
        }
        try {
            DetallePedido d = new DetallePedido(
                    Integer.parseInt(txtIdProd.getText().trim()),
                    txtNombre.getText().trim(),
                    Double.parseDouble(txtPrecio.getText().trim()),
                    Integer.parseInt(txtCantidad.getText().trim()));
            pedido.agregarDetalle(d);
            datos.detalleDAO.registrar(d);
            txtIdProd.setText("");
            txtNombre.setText("");
            txtPrecio.setText("");
            txtCantidad.setText("");
            refrescar();
        } catch (NumberFormatException ex) {
            error("ID del producto, precio y cantidad deben ser números.");
        } catch (IllegalArgumentException ex) {
            error(ex.getMessage());
        }
    }

    @Override
    protected void refrescar() {
        if (tabla == null) {
            return;
        }
        modelo(tabla).setRowCount(0);
        if (pedido == null) {
            lblMesero.setText("Pedido: -");
            lblEstado.setText("Estado: -");
            lblTotal.setText("Total: $0");
            btnAgregar.setEnabled(false);
            return;
        }
        lblMesero.setText("Pedido " + pedido.getId() + " | Mesa " + pedido.getMesa() + " | Mesero: "
                + (pedido.getMesero() != null
                        ? pedido.getMesero().getNombre() + " " + pedido.getMesero().getApellido() : "sin mesero"));
        lblEstado.setText("Estado: " + pedido.getEstado());
        for (DetallePedido d : pedido.getDetalles()) {
            modelo(tabla).addRow(new Object[] {
                d.getNombreProducto() != null ? d.getNombreProducto() : "-", d.getIdProducto(),
                "$" + formato(d.getPrecio()), d.getCantidad(), "$" + formato(d.getSubtotal())
            });
        }
        lblTotal.setText("Total: $" + formato(pedido.getMonto()));
        btnAgregar.setEnabled("Pendiente".equalsIgnoreCase(pedido.getEstado()));
    }
}
