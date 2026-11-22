package src.formularios.felipe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import src.entities.Mesa;
import src.entities.Mesero;
import src.entities.Pedido;

/** Funcionalidades 2.4 y 3 de ManagerFelipe: crear pedidos y ver los pendientes. */
public class VentanaPedidos extends VentanaBase {

    private final JComboBox<String> cmbMesero = new JComboBox<>();
    private final JComboBox<String> cmbMesa = new JComboBox<>();
    private final JCheckBox chkPendientes = new JCheckBox("Solo pendientes", true);
    private final JTable tabla = tabla("Pedido", "Mesa", "Mesero", "Estado", "Total");
    private List<Pedido> mostrados = new ArrayList<>();
    private boolean actualizando = false;

    public VentanaPedidos(DatosFelipe datos) {
        super("Pedidos", datos);
        setSize(700, 480);
        setLocationRelativeTo(null);

        JPanel crear = new JPanel(new FlowLayout(FlowLayout.LEFT));
        crear.setBorder(BorderFactory.createTitledBorder("Crear pedido (mesero + una de sus mesas)"));
        JButton btnCrear = new JButton("Crear pedido");
        crear.add(new JLabel("Mesero:"));
        crear.add(cmbMesero);
        crear.add(new JLabel("Mesa:"));
        crear.add(cmbMesa);
        crear.add(btnCrear);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnDetalle = new JButton("Ver detalle / agregar productos");
        acciones.add(chkPendientes);
        acciones.add(btnDetalle);

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(crear, BorderLayout.NORTH);
        norte.add(acciones, BorderLayout.SOUTH);
        add(norte, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        cmbMesero.addActionListener(e -> {
            if (!actualizando) {
                cargarMesasDelMesero();
            }
        });
        btnCrear.addActionListener(e -> crear());
        chkPendientes.addActionListener(e -> refrescar());
        btnDetalle.addActionListener(e -> abrirDetalle());
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    abrirDetalle();
                }
            }
        });
        refrescar();
    }

    private Mesero meseroElegido() {
        Object item = cmbMesero.getSelectedItem();
        return item == null ? null
                : datos.meseroDAO.buscarMesero(Integer.parseInt(item.toString().split(" ")[0]));
    }

    private void cargarMesasDelMesero() {
        Mesero me = meseroElegido();
        List<String> items = new ArrayList<>();
        if (me != null) {
            for (Mesa m : me.getMesasAsignadas()) {
                items.add(m.getNumMesa() + " (" + m.getCapacidad() + " personas)");
            }
        }
        cmbMesa.setModel(new DefaultComboBoxModel<>(items.toArray(new String[0])));
    }

    private void crear() {
        Mesero mesero = meseroElegido();
        Object item = cmbMesa.getSelectedItem();
        if (mesero == null || item == null) {
            error("Elige un mesero que tenga mesas asignadas.");
            return;
        }
        Mesa mesa = datos.mesaDAO.buscarMesa(Integer.parseInt(item.toString().split(" ")[0]));
        Pedido pedido = mesero.crearPedido(mesa);
        if (pedido == null) {
            error("No se pudo crear el pedido.");
            return;
        }
        datos.pedidoDAO.registrarPedido(pedido);
        refrescar();
        int idx = mostrados.indexOf(pedido);
        if (idx >= 0) {
            tabla.setRowSelectionInterval(idx, idx);
        }
        new VentanaDetallePedido(datos, pedido.getId()).setVisible(true);
    }

    private void abrirDetalle() {
        int fila = tabla.getSelectedRow();
        if (fila < 0 || fila >= mostrados.size()) {
            error("Selecciona un pedido de la tabla.");
            return;
        }
        new VentanaDetallePedido(datos, mostrados.get(fila).getId()).setVisible(true);
    }

    @Override
    protected void refrescar() {
        if (tabla == null) {
            return;
        }
        actualizando = true;
        try {
            Object previo = cmbMesero.getSelectedItem();
            Object previaMesa = cmbMesa.getSelectedItem();
            Pedido sel = null;
            int f = tabla.getSelectedRow();
            if (f >= 0 && f < mostrados.size()) {
                sel = mostrados.get(f);
            }

            List<String> nombres = new ArrayList<>();
            for (Mesero me : datos.meseroDAO.listaMeseros()) {
                nombres.add(me.getId() + " - " + me.getNombre() + " " + me.getApellido());
            }
            cmbMesero.setModel(new DefaultComboBoxModel<>(nombres.toArray(new String[0])));
            if (previo != null) {
                cmbMesero.setSelectedItem(previo);
            }
            cargarMesasDelMesero();
            if (previaMesa != null) {
                cmbMesa.setSelectedItem(previaMesa);
            }

            modelo(tabla).setRowCount(0);
            mostrados = chkPendientes.isSelected()
                    ? datos.pedidoDAO.buscarPorEstado("Pendiente")
                    : new ArrayList<>(datos.pedidoDAO.listaPedidos());
            for (Pedido p : mostrados) {
                modelo(tabla).addRow(new Object[] {
                    p.getId(), p.getMesa(),
                    p.getMesero() != null ? p.getMesero().getNombre() : "-",
                    p.getEstado(), "$" + formato(p.getMonto())
                });
            }
            int idx = mostrados.indexOf(sel);
            if (idx >= 0) {
                tabla.setRowSelectionInterval(idx, idx);
            }
        } finally {
            actualizando = false;
        }
    }
}
