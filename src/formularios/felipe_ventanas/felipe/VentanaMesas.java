package src.formularios.felipe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import src.entities.Mesa;

/** Funcionalidad 1 de ManagerFelipe: CRUD de mesas. */
public class VentanaMesas extends VentanaBase {

    private final JTextField txtNum = new JTextField(5);
    private final JTextField txtCapacidad = new JTextField(5);
    private final JTextField txtBuscar = new JTextField(5);
    private final JCheckBox chkSoloLibres = new JCheckBox("Solo disponibles");
    private final JTable tabla = tabla("Mesa", "Capacidad", "Estado", "Mesero");

    public VentanaMesas(DatosFelipe datos) {
        super("Mesas", datos);
        setSize(640, 460);
        setLocationRelativeTo(null);

        JPanel registrar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registrar.setBorder(BorderFactory.createTitledBorder("Registrar mesa"));
        JButton btnRegistrar = new JButton("Registrar");
        registrar.add(new JLabel("Núm. mesa:"));
        registrar.add(txtNum);
        registrar.add(new JLabel("Capacidad:"));
        registrar.add(txtCapacidad);
        registrar.add(btnRegistrar);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnBuscar = new JButton("Buscar");
        JButton btnLiberar = new JButton("Liberar mesa seleccionada");
        acciones.add(new JLabel("Buscar mesa #:"));
        acciones.add(txtBuscar);
        acciones.add(btnBuscar);
        acciones.add(chkSoloLibres);
        acciones.add(btnLiberar);

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(registrar, BorderLayout.NORTH);
        norte.add(acciones, BorderLayout.SOUTH);

        add(norte, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> registrar());
        txtCapacidad.addActionListener(e -> registrar());
        btnBuscar.addActionListener(e -> buscar());
        txtBuscar.addActionListener(e -> buscar());
        btnLiberar.addActionListener(e -> liberar());
        chkSoloLibres.addActionListener(e -> refrescar());
        refrescar();
    }

    private void registrar() {
        try {
            int num = Integer.parseInt(txtNum.getText().trim());
            int cap = Integer.parseInt(txtCapacidad.getText().trim());
            if (datos.mesaDAO.registrarMesa(new Mesa(num, cap))) {
                txtNum.setText("");
                txtCapacidad.setText("");
                refrescar();
            } else {
                error("Ya existe la mesa " + num + ".");
            }
        } catch (NumberFormatException ex) {
            error("Escribe números válidos en Mesa y Capacidad.");
        } catch (IllegalArgumentException ex) {
            error(ex.getMessage());
        }
    }

    private void buscar() {
        try {
            int num = Integer.parseInt(txtBuscar.getText().trim());
            Mesa m = datos.mesaDAO.buscarMesa(num);
            if (m == null) {
                error("No existe la mesa " + num + ".");
                return;
            }
            chkSoloLibres.setSelected(false);
            refrescar();
            for (int i = 0; i < tabla.getRowCount(); i++) {
                if ((Integer) tabla.getValueAt(i, 0) == num) {
                    tabla.setRowSelectionInterval(i, i);
                    tabla.scrollRectToVisible(tabla.getCellRect(i, 0, true));
                }
            }
        } catch (NumberFormatException ex) {
            error("Escribe el número de la mesa a buscar.");
        }
    }

    private void liberar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            error("Selecciona una mesa de la tabla.");
            return;
        }
        Mesa m = datos.mesaDAO.buscarMesa((Integer) tabla.getValueAt(fila, 0));
        if (m == null || m.isDisponible()) {
            error("Esa mesa ya está disponible.");
            return;
        }
        m.liberarMesa();
        refrescar();
    }

    @Override
    protected void refrescar() {
        if (tabla == null) {
            return;
        }
        modelo(tabla).setRowCount(0);
        var mesas = chkSoloLibres.isSelected() ? datos.mesaDAO.MesasDisponibles() : datos.mesaDAO.ListaMesas();
        for (Mesa m : mesas) {
            modelo(tabla).addRow(new Object[] {
                m.getNumMesa(), m.getCapacidad(),
                m.isDisponible() ? "Disponible" : "Ocupada",
                m.getMesero() != null ? m.getMesero().getNombre() + " " + m.getMesero().getApellido() : "-"
            });
        }
    }
}
