package src.formularios.felipe;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Base de las ventanas de Felipe: comparte los datos, se cierra sin apagar el
 * programa y se actualiza sola cada vez que se vuelve a ella.
 */
public abstract class VentanaBase extends JFrame {

    protected final DatosFelipe datos;

    protected VentanaBase(String titulo, DatosFelipe datos) {
        super(titulo);
        this.datos = datos;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                refrescar();
            }
        });
    }

    /** Vuelve a leer los DAOs y repinta tablas y combos. */
    protected abstract void refrescar();

    protected void aviso(String texto) {
        JOptionPane.showMessageDialog(this, texto, getTitle(), JOptionPane.INFORMATION_MESSAGE);
    }

    protected void error(String texto) {
        JOptionPane.showMessageDialog(this, texto, getTitle(), JOptionPane.WARNING_MESSAGE);
    }

    /** Tabla de solo lectura con las columnas dadas. */
    protected static JTable tabla(String... columnas) {
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int col) {
                return false;
            }
        };
        JTable t = new JTable(modelo);
        t.setRowHeight(24);
        t.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        return t;
    }

    protected static DefaultTableModel modelo(JTable t) {
        return (DefaultTableModel) t.getModel();
    }

    protected static String formato(double v) {
        return v == Math.floor(v) ? String.valueOf((long) v) : String.format("%.2f", v);
    }
}
