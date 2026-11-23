package src.vista;

import javax.swing.*;
import java.awt.*;

/** Pantalla principal visual para las funcionalidades de Menú, Productos y Pagos. */
public class VentanaMenuMichel extends JFrame {
    private final Color MORADO = new Color(83, 53, 150);
    private final Color AZUL = new Color(45, 111, 190);
    private final Color FONDO = new Color(245, 246, 251);

    public VentanaMenuMichel() {
        setTitle("Poli Restaurante - Menú de gestión");
        setSize(850, 540);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(FONDO);

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(MORADO);
        encabezado.setBorder(BorderFactory.createEmptyBorder(24, 30, 24, 30));

        JLabel titulo = new JLabel("POLI RESTAURANTE");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 27));
        JLabel subtitulo = new JLabel("Panel de gestión · Menú, productos y pagos");
        subtitulo.setForeground(new Color(230, 225, 245));
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel textos = new JPanel(new GridLayout(2, 1, 0, 5));
        textos.setOpaque(false);
        textos.add(titulo);
        textos.add(subtitulo);
        encabezado.add(textos, BorderLayout.WEST);
        add(encabezado, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(1, 2, 22, 22));
        centro.setBackground(FONDO);
        centro.setBorder(BorderFactory.createEmptyBorder(45, 45, 45, 45));
        centro.add(crearTarjeta("PRODUCTOS", "Registrar, consultar y administrar\nel inventario del restaurante.", "Abrir productos", AZUL,
                () -> new VentanaProductos().setVisible(true)));
        centro.add(crearTarjeta("PAGOS", "Procesar pagos y consultar\nel historial de transacciones.", "Abrir pagos", MORADO,
                () -> new VentanaPagos().setVisible(true)));
        add(centro, BorderLayout.CENTER);

        JLabel pie = new JLabel("  Sistema de gestión · Poli Restaurante", SwingConstants.CENTER);
        pie.setForeground(new Color(110, 110, 125));
        pie.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));
        add(pie, BorderLayout.SOUTH);
    }

    private JPanel crearTarjeta(String titulo, String descripcion, String textoBoton, Color color, Runnable accion) {
        JPanel tarjeta = new JPanel(new BorderLayout(12, 18));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 225, 235)),
                BorderFactory.createEmptyBorder(28, 25, 28, 25)));

        JLabel nombre = new JLabel(titulo);
        nombre.setFont(new Font("SansSerif", Font.BOLD, 22));
        nombre.setForeground(color);
        JTextArea detalle = new JTextArea(descripcion);
        detalle.setFont(new Font("SansSerif", Font.PLAIN, 15));
        detalle.setForeground(new Color(80, 80, 95));
        detalle.setOpaque(false);
        detalle.setEditable(false);
        detalle.setFocusable(false);
        detalle.setLineWrap(true);
        detalle.setWrapStyleWord(true);

        JButton boton = new JButton(textoBoton);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
        boton.addActionListener(e -> accion.run());

        tarjeta.add(nombre, BorderLayout.NORTH);
        tarjeta.add(detalle, BorderLayout.CENTER);
        tarjeta.add(boton, BorderLayout.SOUTH);
        return tarjeta;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaMenuMichel().setVisible(true));
    }
}
