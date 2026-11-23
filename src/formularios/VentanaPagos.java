package src.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/** Ventana visual para registrar pagos y mostrar un historial temporal. */
public class VentanaPagos extends JFrame {
    private final Color MORADO = new Color(83, 53, 150);
    private final Color AZUL = new Color(45, 111, 190);
    private final JTextField txtIdPedido = new JTextField();
    private final JTextField txtMonto = new JTextField();
    private final JTextField txtDatoMetodo = new JTextField();
    private final JComboBox<String> cmbMetodo = new JComboBox<>(new String[]{"Efectivo", "Tarjeta", "Transferencia"});
    private final JLabel lblDatoMetodo = new JLabel("Dato adicional");
    private final DefaultTableModel modelo;

    public VentanaPagos() {
        setTitle("Poli Restaurante - Gestión de pagos");
        setSize(920, 610);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 246, 251));

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(MORADO);
        encabezado.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        JLabel titulo = new JLabel("GESTIÓN DE PAGOS");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 23));
        encabezado.add(titulo, BorderLayout.WEST);
        add(encabezado, BorderLayout.NORTH);

        JPanel formulario = crearFormulario();
        add(formulario, BorderLayout.WEST);

        modelo = new DefaultTableModel(new Object[]{"ID pago", "ID pedido", "Monto", "Método"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(MORADO);
        tabla.getTableHeader().setForeground(Color.WHITE);

        JPanel historial = new JPanel(new BorderLayout(8, 8));
        historial.setOpaque(false);
        historial.setBorder(BorderFactory.createEmptyBorder(10, 0, 18, 18));
        JLabel lblHistorial = new JLabel("HISTORIAL DE PAGOS");
        lblHistorial.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblHistorial.setForeground(MORADO);
        historial.add(lblHistorial, BorderLayout.NORTH);
        historial.add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(historial, BorderLayout.CENTER);

        cmbMetodo.addActionListener(e -> actualizarCampoMetodo());
        actualizarCampoMetodo();
    }

    private JPanel crearFormulario() {
        JPanel p = new JPanel(new GridLayout(0, 1, 8, 9));
        p.setPreferredSize(new Dimension(290, 0));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(18, 18, 18, 5),
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 232)), "Datos del pago")));

        agregarCampo(p, "ID del pedido", txtIdPedido);
        agregarCampo(p, "Monto a pagar ($)", txtMonto);
        JLabel lblMetodo = new JLabel("Método de pago");
        lblMetodo.setFont(new Font("SansSerif", Font.BOLD, 12));
        p.add(lblMetodo);
        cmbMetodo.setPreferredSize(new Dimension(220, 30));
        p.add(cmbMetodo);
        lblDatoMetodo.setFont(new Font("SansSerif", Font.BOLD, 12));
        p.add(lblDatoMetodo);
        txtDatoMetodo.setPreferredSize(new Dimension(220, 29));
        p.add(txtDatoMetodo);

        JButton btnProcesar = new JButton("Procesar pago");
        btnProcesar.setBackground(AZUL);
        btnProcesar.setForeground(Color.WHITE);
        btnProcesar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnProcesar.setFocusPainted(false);
        btnProcesar.addActionListener(e -> procesarPagoVisual());
        JButton btnLimpiar = new JButton("Limpiar campos");
        btnLimpiar.addActionListener(e -> limpiar());
        p.add(btnProcesar);
        p.add(btnLimpiar);
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

    private void actualizarCampoMetodo() {
        String metodo = (String) cmbMetodo.getSelectedItem();
        if ("Efectivo".equals(metodo)) {
            lblDatoMetodo.setText("Observación (opcional)");
            txtDatoMetodo.setToolTipText("Dato opcional");
        } else if ("Tarjeta".equals(metodo)) {
            lblDatoMetodo.setText("Últimos 4 dígitos de tarjeta");
            txtDatoMetodo.setToolTipText("No ingreses el número completo de la tarjeta");
        } else {
            lblDatoMetodo.setText("Referencia de transferencia");
            txtDatoMetodo.setToolTipText("Código o referencia de la transferencia");
        }
    }

    private void procesarPagoVisual() {
        try {
            int idPedido = Integer.parseInt(txtIdPedido.getText().trim());
            double monto = Double.parseDouble(txtMonto.getText().trim());
            if (idPedido <= 0 || monto <= 0) throw new IllegalArgumentException();
            String metodo = (String) cmbMetodo.getSelectedItem();
            String dato = txtDatoMetodo.getText().trim();
            if (!"Efectivo".equals(metodo) && dato.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingresa el dato solicitado para el método de pago.");
                return;
            }
            int idPago = modelo.getRowCount() + 1;
            modelo.addRow(new Object[]{idPago, idPedido, String.format("$ %.2f", monto), metodo});
            JOptionPane.showMessageDialog(this, "Pago agregado a la tabla de demostración.\nMétodo: " + metodo);
            limpiar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ingresa un ID de pedido y un monto válido mayor que cero.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiar() {
        txtIdPedido.setText("");
        txtMonto.setText("");
        txtDatoMetodo.setText("");
        cmbMetodo.setSelectedIndex(0);
    }
}
