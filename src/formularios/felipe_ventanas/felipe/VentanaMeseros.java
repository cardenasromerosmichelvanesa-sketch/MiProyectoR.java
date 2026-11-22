package src.formularios.felipe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import src.entities.Mesa;
import src.entities.Mesero;
import src.entities.Pedido;

/** Funcionalidad 2 de ManagerFelipe: CRUD de meseros, asignar mesas y ver sus pedidos. */
public class VentanaMeseros extends VentanaBase {

    private final JTextField txtId = new JTextField(5);
    private final JTextField txtNombre = new JTextField(10);
    private final JTextField txtApellido = new JTextField(10);
    private final JComboBox<String> cmbMesas = new JComboBox<>();
    private final JComboBox<String> cmbMeseros = new JComboBox<>();
    private final JTable tablaMeseros = tabla("ID", "Nombre", "Disponible", "Mesas asignadas");
    private final JTable tablaPedidos = tabla("Pedido", "Mesa", "Estado", "Total");

    public VentanaMeseros(DatosFelipe datos) {
        super("Meseros", datos);
        setSize(720, 600);
        setLocationRelativeTo(null);

        JPanel registrar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registrar.setBorder(BorderFactory.createTitledBorder("Registrar mesero"));
        JButton btnRegistrar = new JButton("Registrar");
        registrar.add(new JLabel("ID:"));
        registrar.add(txtId);
        registrar.add(new JLabel("Nombre:"));
        registrar.add(txtNombre);
        registrar.add(new JLabel("Apellido:"));
        registrar.add(txtApellido);
        registrar.add(btnRegistrar);

        JPanel asignar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        asignar.setBorder(BorderFactory.createTitledBorder("Asignar mesa disponible a un mesero"));
        JButton btnAsignar = new JButton("Asignar");
        asignar.add(new JLabel("Mesa:"));
        asignar.add(cmbMesas);
        asignar.add(new JLabel("Mesero:"));
        asignar.add(cmbMeseros);
        asignar.add(btnAsignar);

        JPanel norte = new JPanel(new GridLayout(2, 1));
        norte.add(registrar);
        norte.add(asignar);

        JPanel pedidos = new JPanel(new BorderLayout());
        pedidos.setBorder(BorderFactory.createTitledBorder("Pedidos del mesero seleccionado"));
        pedidos.add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);

        JPanel centro = new JPanel(new GridLayout(2, 1));
        centro.add(new JScrollPane(tablaMeseros));
        centro.add(pedidos);

        add(norte, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> registrar());
        txtApellido.addActionListener(e -> registrar());
        btnAsignar.addActionListener(e -> asignar());
        tablaMeseros.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarPedidos();
            }
        });
        refrescar();
    }

    private void registrar() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                error("Escribe el nombre del mesero.");
                return;
            }
            if (datos.meseroDAO.buscarMesero(id) != null) {
                error("Ya existe un mesero con el ID " + id + ".");
                return;
            }
            datos.meseroDAO.registrarMesero(new Mesero(id, nombre, txtApellido.getText().trim(), true));
            txtId.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            refrescar();
        } catch (NumberFormatException ex) {
            error("El ID del mesero debe ser un número.");
        }
    }

    private void asignar() {
        Mesa mesa = mesaElegida();
        Mesero mesero = meseroDe(cmbMeseros);
        if (mesero == null) {
            error("Primero registra un mesero.");
            return;
        }
        if (mesa == null) {
            error("No hay mesas disponibles para asignar.");
            return;
        }
        if (datos.meseroDAO.asignarMesa(mesero, mesa)) {
            aviso("Mesa " + mesa.getNumMesa() + " asignada a " + mesero.getNombre() + ".");
        } else {
            error("No se pudo asignar la mesa " + mesa.getNumMesa() + ".");
        }
        refrescar();
    }

    private Mesa mesaElegida() {
        Object item = cmbMesas.getSelectedItem();
        if (item == null) {
            return null;
        }
        return datos.mesaDAO.buscarMesa(Integer.parseInt(item.toString().split(" ")[0]));
    }

    private Mesero meseroDe(JComboBox<String> combo) {
        Object item = combo.getSelectedItem();
        if (item == null) {
            return null;
        }
        return datos.meseroDAO.buscarMesero(Integer.parseInt(item.toString().split(" ")[0]));
    }

    private void cargarPedidos() {
        modelo(tablaPedidos).setRowCount(0);
        int fila = tablaMeseros.getSelectedRow();
        if (fila < 0) {
            return;
        }
        Mesero me = datos.meseroDAO.buscarMesero((Integer) tablaMeseros.getValueAt(fila, 0));
        if (me == null) {
            return;
        }
        for (Pedido p : datos.pedidoDAO.buscarPorMesero(me)) {
            modelo(tablaPedidos).addRow(new Object[] {
                p.getId(), p.getMesa(), p.getEstado(), "$" + formato(p.getMonto())
            });
        }
    }

    @Override
    protected void refrescar() {
        if (tablaPedidos == null) {
            return;
        }
        Object previaMesa = cmbMesas.getSelectedItem();
        Object previoMesero = cmbMeseros.getSelectedItem();
        int filaSel = tablaMeseros.getSelectedRow();

        modelo(tablaMeseros).setRowCount(0);
        java.util.List<String> nombres = new java.util.ArrayList<>();
        for (Mesero me : datos.meseroDAO.listaMeseros()) {
            StringBuilder mesas = new StringBuilder();
            for (Mesa m : me.getMesasAsignadas()) {
                mesas.append(mesas.length() > 0 ? ", " : "").append(m.getNumMesa());
            }
            modelo(tablaMeseros).addRow(new Object[] {
                me.getId(), me.getNombre() + " " + me.getApellido(),
                me.getMeseroDisponible() ? "Sí" : "No",
                mesas.length() > 0 ? mesas.toString() : "-"
            });
            nombres.add(me.getId() + " - " + me.getNombre() + " " + me.getApellido());
        }
        java.util.List<String> libres = new java.util.ArrayList<>();
        for (Mesa m : datos.mesaDAO.MesasDisponibles()) {
            libres.add(m.getNumMesa() + " (" + m.getCapacidad() + " personas)");
        }
        cmbMesas.setModel(new DefaultComboBoxModel<>(libres.toArray(new String[0])));
        cmbMeseros.setModel(new DefaultComboBoxModel<>(nombres.toArray(new String[0])));
        if (previaMesa != null) {
            cmbMesas.setSelectedItem(previaMesa);
        }
        if (previoMesero != null) {
            cmbMeseros.setSelectedItem(previoMesero);
        }
        if (filaSel >= 0 && filaSel < tablaMeseros.getRowCount()) {
            tablaMeseros.setRowSelectionInterval(filaSel, filaSel);
        }
        cargarPedidos();
    }
}
