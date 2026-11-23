package src.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/** Ventana visual para la gestión de productos. Los datos de esta pantalla son temporales. */
public class VentanaProductos extends JFrame {
    private final Color MORADO = new Color(83, 53, 150);
    private final Color AZUL = new Color(45, 111, 190);
    private final JTextField txtId = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtPrecio = new JTextField();
    private final JTextField txtCantidad = new JTextField();
    private final DefaultTableModel modelo;
    private final JTable tabla;

    public VentanaProductos() {
        setTitle("Poli Restaurante - Gestión de productos");
        setSize(950, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 246, 251));

        add(crearEncabezado(), BorderLayout.NORTH);
        add(crearFormulario(), BorderLayout.WEST);

        modelo = new DefaultTableModel(new Object[]{"ID", "Producto", "Precio", "Cantidad"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tabla = new JTable(modelo);
        tabla.setRowHeight(28);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(MORADO);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel panelTabla = new JPanel(new BorderLayout(8, 8));
        panelTabla.setOpaque(false);
        JLabel lblTabla = new JLabel("PRODUCTOS REGISTRADOS");
        lblTabla.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTabla.setForeground(MORADO);
        panelTabla.add(lblTabla, BorderLayout.NORTH);
        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panelTabla.setBorder(BorderFactory.createEmptyBorder(10, 0, 18, 18));
        add(panelTabla, BorderLayout.CENTER);

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() >= 0) {
                int fila = tabla.getSelectedRow();
                txtId.setText(modelo.getValueAt(fila, 0).toString());
                txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                txtPrecio.setText(modelo.getValueAt(fila, 2).toString());
                txtCantidad.setText(modelo.getValueAt(fila, 3).toString());
            }
        });
    }

    private JPanel crearEncabezado() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(MORADO);
        p.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        JLabel titulo = new JLabel("GESTIÓN DE PRODUCTOS");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 23));
        titulo.setForeground(Color.WHITE);
        p.add(titulo, BorderLayout.WEST);
        return p;
    }

    private JPanel crearFormulario() {
        JPanel p = new JPanel(new GridLayout(0, 1, 8, 7));
        p.setPreferredSize(new Dimension(275, 0));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(18, 18, 18, 5),
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 232)), "Datos del producto")));

        agregarCampo(p, "ID del producto", txtId);
        agregarCampo(p, "Nombre", txtNombre);
        agregarCampo(p, "Precio", txtPrecio);
        agregarCampo(p, "Cantidad disponible", txtCantidad);

        JButton btnRegistrar = boton("Registrar", AZUL);
        JButton btnActualizar = boton("Actualizar", MORADO);
        JButton btnEliminar = boton("Eliminar", new Color(190, 65, 75));
        JButton btnLimpiar = boton("Limpiar", new Color(100, 105, 120));
        p.add(btnRegistrar);
        p.add(btnActualizar);
        p.add(btnEliminar);
        p.add(btnLimpiar);

        btnRegistrar.addActionListener(e -> registrar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());
        return p;
    }

    private void agregarCampo(JPanel panel, String etiqueta, JTextField campo) {
        JPanel grupo = new JPanel(new BorderLayout(3, 4));
        grupo.setOpaque(false);
        JLabel label = new JLabel(etiqueta);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        campo.setPreferredSize(new Dimension(220, 29));
        grupo.add(label, BorderLayout.NORTH);
        grupo.add(campo, BorderLayout.CENTER);
        panel.add(grupo);
    }

    private JButton boton(String texto, Color color) {
        JButton b = new JButton(texto);
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setFocusPainted(false);
        return b;
    }

    private void registrar() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String nombre = txtNombre.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (nombre.isEmpty() || precio < 0 || cantidad < 0) throw new IllegalArgumentException();
            for (int i = 0; i < modelo.getRowCount(); i++) {
                if (Integer.parseInt(modelo.getValueAt(i, 0).toString()) == id) {
                    JOptionPane.showMessageDialog(this, "Ya existe un producto con ese ID.");
                    return;
                }
            }
            modelo.addRow(new Object[]{id, nombre, precio, cantidad});
            limpiar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Verifica los datos. ID y cantidad deben ser enteros, y precio debe ser numérico.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Selecciona un producto de la tabla."); return; }
        try {
            String nombre = txtNombre.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (nombre.isEmpty() || precio < 0 || cantidad < 0) throw new IllegalArgumentException();
            modelo.setValueAt(nombre, fila, 1);
            modelo.setValueAt(precio, fila, 2);
            modelo.setValueAt(cantidad, fila, 3);
            limpiar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Revisa el nombre, el precio y la cantidad.");
        }
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar."); return; }
        modelo.removeRow(fila);
        limpiar();
    }

    private void limpiar() {
        txtId.setText(""); txtNombre.setText(""); txtPrecio.setText(""); txtCantidad.setText("");
        tabla.clearSelection();
    }
}
