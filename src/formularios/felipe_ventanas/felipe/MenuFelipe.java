package src.formularios.felipe_ventanas.felipe;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Ventana principal del Manager de Felipe (equivale a mostrarMenuPrincipal()).
 * Cada botón abre una ventana; todas comparten los mismos datos.
 */
public class MenuFelipe extends JFrame {

    private final DatosFelipe datos = new DatosFelipe();

    public MenuFelipe() {
        super("Manager de Felipe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 1, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 24));
        panel.add(new JLabel("MI MANAGER - 4 FUNCIONALIDADES", SwingConstants.CENTER));
        panel.add(boton("1. Mesas", () -> new VentanaMesas(datos)));
        panel.add(boton("2. Meseros", () -> new VentanaMeseros(datos)));
        panel.add(boton("3. Pedidos", () -> new VentanaPedidos(datos)));
        panel.add(boton("4. Detalle de pedido", () -> new VentanaDetallePedido(datos, null)));
        JButton salir = new JButton("0. Salir");
        salir.addActionListener(e -> System.exit(0));
        panel.add(salir);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private JButton boton(String texto, java.util.function.Supplier<JFrame> ventana) {
        JButton b = new JButton(texto);
        b.addActionListener(e -> ventana.get().setVisible(true));
        return b;
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // se queda con el aspecto por defecto
        }
        java.awt.EventQueue.invokeLater(() -> new MenuFelipe().setVisible(true));
    }
}
