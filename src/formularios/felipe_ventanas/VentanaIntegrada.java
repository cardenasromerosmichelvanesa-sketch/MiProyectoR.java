package src.vista;

import src.DAOs.*;
import src.entities.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Aplicación gráfica del sistema de restaurante.
 * Ejecutar esta clase para abrir la interfaz Swing.
 */
public class RestauranteSwing extends JFrame {
    private final ProductoDAO productoDAO = new ProductoDAO();
    private final MesaDAO mesaDAO = new MesaDAO();
    private final MeseroDAO meseroDAO = new MeseroDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final DetallePedidoDAO detalleDAO = new DetallePedidoDAO();
    private final CocinaDAO cocinaDAO = new CocinaDAO();
    private final ReporteDAO reporteDAO = new ReporteDAO();
    private final Cocina cocina = new Cocina(5);
    private final java.util.List<String[]> pagos = new ArrayList<>();

    private final Color azul = new Color(28, 48, 75);
    private final Color azulClaro = new Color(47, 111, 157);
    private final Color fondo = new Color(244, 247, 251);
    private final Font fuente = new Font("Segoe UI", Font.PLAIN, 13);

    public RestauranteSwing() {
        setTitle("Restaurante | Sistema de administración");
        setSize(1100, 700);
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(fondo);
        cargarDatosDemo();
        construirInterfaz();
    }

    private void cargarDatosDemo() {
        productoDAO.crear(new Producto(1, "Hamburguesa", 18000, 20));
        productoDAO.crear(new Producto(2, "Gaseosa", 4500, 50));
        productoDAO.crear(new Producto(3, "Papas medianas", 6000, 30));
        mesaDAO.registrarMesa(new Mesa(1, 4));
        mesaDAO.registrarMesa(new Mesa(2, 2));
        mesaDAO.registrarMesa(new Mesa(3, 6));
        meseroDAO.registrarMesero(new Mesero(201, "Juan", "Álvarez", true));
        meseroDAO.registrarMesero(new Mesero(202, "Ana", "González", true));
    }

    private void construirInterfaz() {
        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(fondo);
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(azul);
        encabezado.setBorder(BorderFactory.createEmptyBorder(18, 24, 18, 24));
        JLabel titulo = new JLabel("RESTAURANTE  |  Panel de administración");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        JLabel subtitulo = new JLabel("Gestión de productos, mesas, pedidos y ventas");
        subtitulo.setForeground(new Color(210, 222, 235));
        subtitulo.setFont(fuente);
        JPanel textos = new JPanel(new GridLayout(2,1,0,4));
        textos.setOpaque(false); textos.add(titulo); textos.add(subtitulo);
        encabezado.add(textos, BorderLayout.WEST);
        raiz.add(encabezado, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabs.addTab("Inicio", panelInicio());
        tabs.addTab("Productos", panelProductos());
        tabs.addTab("Mesas", panelMesas());
        tabs.addTab("Meseros", panelMeseros());
        tabs.addTab("Pedidos y cocina", panelPedidos());
        tabs.addTab("Clientes", panelClientes());
        tabs.addTab("Pagos", panelPagos());
        tabs.addTab("Reportes", panelReportes());
        raiz.add(tabs, BorderLayout.CENTER);
        setContentPane(raiz);
    }

    private JPanel basePanel(String titulo, String descripcion) {
        JPanel p = new JPanel(new BorderLayout(12,12));
        p.setBackground(fondo);
        p.setBorder(BorderFactory.createEmptyBorder(20,22,20,22));
        JPanel head = new JPanel(new GridLayout(2,1,0,5)); head.setOpaque(false);
        JLabel t = new JLabel(titulo); t.setFont(new Font("Segoe UI",Font.BOLD,20)); t.setForeground(azul);
        JLabel d = new JLabel(descripcion); d.setFont(fuente); d.setForeground(Color.DARK_GRAY);
        head.add(t); head.add(d); p.add(head,BorderLayout.NORTH);
        return p;
    }

    private JPanel panelInicio() {
        JPanel p=basePanel("Bienvenido al sistema","Selecciona una pestaña para administrar el restaurante.");
        JPanel cards=new JPanel(new GridLayout(2,3,14,14)); cards.setOpaque(false);
        cards.add(card("Productos", "Inventario: "+productoDAO.obtenerTodos().size()));
        cards.add(card("Mesas", "Registradas: "+mesaDAO.ListaMesas().size()));
        cards.add(card("Meseros", "Personal: "+meseroDAO.listaMeseros().size()));
        cards.add(card("Pedidos", "Registrados: "+pedidoDAO.listaPedidos().size()));
        cards.add(card("Cocina", "Cocineros disponibles: "+cocina.getCocinerosDisponibles()));
        cards.add(card("Clientes", "Registrados: "+clienteDAO.get().size()));
        p.add(cards,BorderLayout.CENTER);
        JTextArea info=new JTextArea("Aplicación de escritorio desarrollada con Java Swing.\\nLos datos se mantienen en memoria mientras la aplicación está abierta.");
        info.setEditable(false); info.setFont(fuente); info.setBackground(Color.WHITE); info.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        p.add(info,BorderLayout.SOUTH); return p;
    }

    private JPanel card(String nombre,String valor) {
        JPanel c=new JPanel(new GridLayout(2,1,0,8)); c.setBackground(Color.WHITE);
        c.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(220,228,237)),BorderFactory.createEmptyBorder(18,18,18,18)));
        JLabel a=new JLabel(nombre); a.setForeground(azulClaro); a.setFont(new Font("Segoe UI",Font.BOLD,16));
        JLabel b=new JLabel(valor); b.setFont(new Font("Segoe UI",Font.PLAIN,14)); c.add(a);c.add(b);return c;
    }

    private JButton boton(String texto) {
        JButton b=new JButton(texto); b.setFont(new Font("Segoe UI",Font.BOLD,12));
        b.setForeground(Color.WHITE); b.setBackground(azulClaro); b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(9,14,9,14)); return b;
    }

    private DefaultTableModel modelo(String... columnas) {
        return new DefaultTableModel(columnas,0) { public boolean isCellEditable(int r,int c){return false;} };
    }
    private JTable tabla(DefaultTableModel m) {
        JTable t=new JTable(m); t.setRowHeight(25); t.setFont(fuente); t.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); return t;
    }
    private void mostrarTabla(JPanel p,JTable t) {
        JScrollPane sp=new JScrollPane(t); sp.setBorder(BorderFactory.createLineBorder(new Color(220,228,237))); p.add(sp,BorderLayout.CENTER);
    }
    private String pedir(String mensaje) { return JOptionPane.showInputDialog(this,mensaje); }
    private int entero(String s,String campo) {
        try { return Integer.parseInt(s.trim()); } catch(Exception e){ throw new IllegalArgumentException(campo+" debe ser un número entero."); }
    }
    private double decimal(String s,String campo) {
        try { return Double.parseDouble(s.trim().replace(",", ".")); } catch(Exception e){ throw new IllegalArgumentException(campo+" debe ser numérico."); }
    }
    private void error(Exception e){ JOptionPane.showMessageDialog(this,e.getMessage(),"Validación",JOptionPane.WARNING_MESSAGE); }
    private void exito(String s){ JOptionPane.showMessageDialog(this,s,"Operación realizada",JOptionPane.INFORMATION_MESSAGE); }

    private JPanel panelProductos() {
        JPanel p=basePanel("Gestión de productos","Registra, modifica, consulta y elimina productos del inventario.");
        DefaultTableModel m=modelo("ID","Producto","Precio","Stock"); JTable t=tabla(m);
        Runnable refrescar=()->{m.setRowCount(0);for(Producto x:productoDAO.obtenerTodos())m.addRow(new Object[]{x.getId(),x.getNombre(),x.getPrecio(),x.getCantidad()});};
        JPanel acciones=new JPanel(new FlowLayout(FlowLayout.LEFT)); acciones.setOpaque(false);
        JButton agregar=boton("Registrar"), editar=boton("Modificar seleccionado"), stock=boton("Actualizar stock"), eliminar=boton("Eliminar");
        acciones.add(agregar);acciones.add(editar);acciones.add(stock);acciones.add(eliminar);p.add(acciones,BorderLayout.SOUTH);mostrarTabla(p,t);refrescar.run();
        agregar.addActionListener(e->{try{int id=entero(pedir("ID del producto:"),"ID");String n=pedir("Nombre:");double pr=decimal(pedir("Precio:"),"Precio");int c=entero(pedir("Cantidad disponible:"),"Cantidad");if(n==null||n.isBlank())return;productoDAO.crear(new Producto(id,n,pr,c));refrescar.run();}catch(Exception ex){error(ex);}});
        editar.addActionListener(e->{try{int r=t.getSelectedRow();if(r<0)throw new IllegalArgumentException("Selecciona un producto.");int id=(int)m.getValueAt(r,0);Producto old=productoDAO.obtenerPorId(id);String n=pedir("Nuevo nombre (actual: "+old.getNombre()+"):");if(n==null)return;double pr=decimal(pedir("Nuevo precio (actual: "+old.getPrecio()+"):"),"Precio");int c=entero(pedir("Nuevo stock (actual: "+old.getCantidad()+"):"),"Stock");productoDAO.actualizar(new Producto(id,n,pr,c));refrescar.run();}catch(Exception ex){error(ex);}});
        stock.addActionListener(e->{try{int r=t.getSelectedRow();if(r<0)throw new IllegalArgumentException("Selecciona un producto.");int id=(int)m.getValueAt(r,0);Producto x=productoDAO.obtenerPorId(id);int c=entero(pedir("Nueva cantidad (actual: "+x.getCantidad()+"):"),"Cantidad");x.setCantidad(c);refrescar.run();}catch(Exception ex){error(ex);}});
        eliminar.addActionListener(e->{int r=t.getSelectedRow();if(r<0){error(new Exception("Selecciona un producto."));return;}int id=(int)m.getValueAt(r,0);if(JOptionPane.showConfirmDialog(this,"¿Eliminar producto?")==JOptionPane.YES_OPTION){productoDAO.eliminar(id);refrescar.run();}});
        return p;
    }

    private JPanel panelMesas() {
        JPanel p=basePanel("Gestión de mesas","Registra mesas, revisa su disponibilidad y libera mesas ocupadas.");
        DefaultTableModel m=modelo("Número","Capacidad","Estado","Mesero asignado");JTable t=tabla(m);
        Runnable refresh=()->{m.setRowCount(0);for(Mesa x:mesaDAO.ListaMesas())m.addRow(new Object[]{x.getNumMesa(),x.getCapacidad(),x.isDisponible()?"Disponible":"Ocupada",x.getMesero()==null?"Sin asignar":x.getMesero().getNombre()});};
        JPanel a=new JPanel(new FlowLayout(FlowLayout.LEFT));a.setOpaque(false);JButton add=boton("Registrar mesa"),lib=boton("Liberar mesa"),asig=boton("Asignar mesero");a.add(add);a.add(asig);a.add(lib);p.add(a,BorderLayout.SOUTH);mostrarTabla(p,t);refresh.run();
        add.addActionListener(e->{try{int n=entero(pedir("Número de mesa:"),"Número");int c=entero(pedir("Capacidad:"),"Capacidad");if(!mesaDAO.registrarMesa(new Mesa(n,c)))throw new IllegalArgumentException("La mesa ya existe.");refresh.run();}catch(Exception ex){error(ex);}});
        lib.addActionListener(e->{try{int r=t.getSelectedRow();if(r<0)throw new IllegalArgumentException("Selecciona una mesa.");Mesa x=mesaDAO.buscarMesa((int)m.getValueAt(r,0));x.liberarMesa();refresh.run();}catch(Exception ex){error(ex);}});
        asig.addActionListener(e->{try{int r=t.getSelectedRow();if(r<0)throw new IllegalArgumentException("Selecciona una mesa.");Mesa x=mesaDAO.buscarMesa((int)m.getValueAt(r,0));if(!x.isDisponible())throw new IllegalArgumentException("La mesa no está disponible.");meseroDAO.asignarMesa(x);refresh.run();}catch(Exception ex){error(ex);}});
        return p;
    }

    private JPanel panelMeseros() {
        JPanel p=basePanel("Gestión de meseros","Registra empleados y consulta su disponibilidad.");
        DefaultTableModel m=modelo("ID","Nombre","Apellido","Disponibilidad");JTable t=tabla(m);
        Runnable refresh=()->{m.setRowCount(0);for(Mesero x:meseroDAO.listaMeseros())m.addRow(new Object[]{x.getIdUsuario(),x.getNombre(),x.getApellido(),x.getMeseroDisponible()?"Disponible":"Ocupado"});};
        JButton add=boton("Registrar mesero");JPanel a=new JPanel(new FlowLayout(FlowLayout.LEFT));a.setOpaque(false);a.add(add);p.add(a,BorderLayout.SOUTH);mostrarTabla(p,t);refresh.run();
        add.addActionListener(e->{try{int id=entero(pedir("ID:"),"ID");String n=pedir("Nombre:");String ap=pedir("Apellido:");if(n==null||ap==null)return;if(!meseroDAO.registrarMesero(new Mesero(id,n,ap,true)))throw new IllegalArgumentException("No se pudo registrar. Verifica que el ID no esté repetido.");refresh.run();}catch(Exception ex){error(ex);}});
        return p;
    }

    private JPanel panelPedidos() {
        JPanel p=basePanel("Pedidos y cocina","Crea pedidos y administra su recorrido desde cocina hasta la entrega.");
        DefaultTableModel m=modelo("ID","Mesa","Estado","Monto");JTable t=tabla(m);
        Runnable refresh=()->{m.setRowCount(0);for(Pedido x:pedidoDAO.listaPedidos())m.addRow(new Object[]{x.getId(),x.getMesa(),x.getEstado(),x.getMonto()});};
        JPanel a=new JPanel(new FlowLayout(FlowLayout.LEFT));a.setOpaque(false);
        JButton crear=boton("Crear pedido"),detalle=boton("Agregar detalle"),cocinar=boton("Iniciar preparación"),listo=boton("Marcar listo"),entregar=boton("Entregar pedido");
        for(JButton b:new JButton[]{crear,detalle,cocinar,listo,entregar})a.add(b);p.add(a,BorderLayout.SOUTH);mostrarTabla(p,t);refresh.run();
        crear.addActionListener(e->{try{int mesa=entero(pedir("Número de mesa:"),"Mesa");Mesa x=mesaDAO.buscarMesa(mesa);if(x==null)throw new IllegalArgumentException("La mesa no existe.");if(!x.isDisponible())throw new IllegalArgumentException("La mesa está ocupada.");String id="PED-"+System.currentTimeMillis();Pedido pd=new Pedido(id,mesa,"Pendiente",0);pedidoDAO.registrarPedido(pd);x.setDisponible(false);refresh.run();exito("Pedido "+id+" creado.");}catch(Exception ex){error(ex);}});
        detalle.addActionListener(e->{try{Pedido pd=seleccionPedido(t,m);int id=entero(pedir("ID del producto:"),"Producto");int cant=entero(pedir("Cantidad:"),"Cantidad");Producto pr=productoDAO.obtenerPorId(id);if(pr==null)throw new IllegalArgumentException("Producto no encontrado.");if(cant<=0||cant>pr.getCantidad())throw new IllegalArgumentException("Cantidad no disponible en inventario.");detalleDAO.registrar(new DetallePedido(id,cant));pd.setMonto(pd.getMonto()+pr.getPrecio()*cant);pr.setCantidad(pr.getCantidad()-cant);refresh.run();exito("Detalle agregado. Total actualizado: $"+pd.getMonto());}catch(Exception ex){error(ex);}});
        cocinar.addActionListener(e->{try{Pedido pd=seleccionPedido(t,m);if(!cocinaDAO.cocinarPedido(pd,cocina))throw new IllegalArgumentException("No se puede iniciar: verifica estado y disponibilidad de cocina.");refresh.run();}catch(Exception ex){error(ex);}});
        listo.addActionListener(e->{try{Pedido pd=seleccionPedido(t,m);if(!cocinaDAO.marcarListo(pd,cocina))throw new IllegalArgumentException("El pedido no está en preparación.");refresh.run();}catch(Exception ex){error(ex);}});
        entregar.addActionListener(e->{try{Pedido pd=seleccionPedido(t,m);if(!"Listo".equalsIgnoreCase(pd.getEstado()))throw new IllegalArgumentException("El pedido debe estar listo antes de entregarlo.");pd.setEstado("Entregado");Mesa mesa=mesaDAO.buscarMesa(pd.getMesa());if(mesa!=null)mesa.liberarMesa();refresh.run();}catch(Exception ex){error(ex);}});
        return p;
    }
    private Pedido seleccionPedido(JTable t,DefaultTableModel m){int r=t.getSelectedRow();if(r<0)throw new IllegalArgumentException("Selecciona un pedido.");String id=String.valueOf(m.getValueAt(r,0));for(Pedido p:pedidoDAO.listaPedidos())if(p.getId().equals(id))return p;throw new IllegalArgumentException("Pedido no encontrado.");}

    private JPanel panelClientes() {
        JPanel p=basePanel("Gestión de clientes","Registra los datos de contacto, ubicación y forma de pago preferida.");
        DefaultTableModel m=modelo("Contacto","Ubicación","Forma de pago");JTable t=tabla(m);
        Runnable refresh=()->{m.setRowCount(0);for(Cliente x:clienteDAO.get())m.addRow(new Object[]{x.getTipoContacto(),x.getUbicacion(),x.getFormaPago()});};
        JButton add=boton("Registrar cliente");JPanel a=new JPanel(new FlowLayout(FlowLayout.LEFT));a.setOpaque(false);a.add(add);p.add(a,BorderLayout.SOUTH);mostrarTabla(p,t);refresh.run();
        add.addActionListener(e->{String c=pedir("Teléfono o correo:");String u=pedir("Ubicación:");if(c==null||u==null)return;clienteDAO.registra(new Cliente(c,u));refresh.run();});return p;
    }

    private JPanel panelPagos() {
        JPanel p=basePanel("Pagos","Registra pagos asociados a pedidos que ya estén listos.");
        DefaultTableModel m=modelo("Pedido","Monto","Método","Fecha");JTable t=tabla(m);
        Runnable refresh=()->{m.setRowCount(0);for(String[] x:pagos)m.addRow(x);};
        JButton registrar=boton("Registrar pago");JPanel a=new JPanel(new FlowLayout(FlowLayout.LEFT));a.setOpaque(false);a.add(registrar);p.add(a,BorderLayout.SOUTH);mostrarTabla(p,t);refresh.run();
        registrar.addActionListener(e->{try{Pedido pd=seleccionPedidoDesdeLista();if(!"Listo".equalsIgnoreCase(pd.getEstado()))throw new IllegalArgumentException("Solo se pueden pagar pedidos listos.");String[] metodos={"Efectivo","Tarjeta","Transferencia"};String metodo=(String)JOptionPane.showInputDialog(this,"Selecciona el método de pago:","Método de pago",JOptionPane.QUESTION_MESSAGE,null,metodos,metodos[0]);if(metodo==null)return;pagos.add(new String[]{pd.getId(),String.format("$%.2f",pd.getMonto()),metodo,new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date())});pd.setEstado("Entregado");Mesa mesa=mesaDAO.buscarMesa(pd.getMesa());if(mesa!=null)mesa.liberarMesa();refresh.run();exito("Pago registrado correctamente.");}catch(Exception ex){error(ex);}});
        return p;
    }
    private Pedido seleccionPedidoDesdeLista(){List<Pedido> candidatos=new ArrayList<>();for(Pedido p:pedidoDAO.listaPedidos())if("Listo".equalsIgnoreCase(p.getEstado()))candidatos.add(p);if(candidatos.isEmpty())throw new IllegalArgumentException("No hay pedidos listos para pagar.");Object elegido=JOptionPane.showInputDialog(this,"Selecciona el pedido:","Pedidos listos",JOptionPane.QUESTION_MESSAGE,null,candidatos.stream().map(x->x.getId()+" | Mesa "+x.getMesa()+" | $"+x.getMonto()).toArray(),null);if(elegido==null)throw new IllegalArgumentException("Operación cancelada.");String id=elegido.toString().split(" \\\\|")[0];for(Pedido p:candidatos)if(p.getId().equals(id))return p;throw new IllegalArgumentException("Pedido no encontrado.");}

    private JPanel panelReportes() {
        JPanel p=basePanel("Reportes de ventas","Consulta el historial de pedidos y el total de ventas entregadas.");
        DefaultTableModel m=modelo("Pedido","Mesa","Estado","Monto");JTable t=tabla(m);
        Runnable refresh=()->{m.setRowCount(0);for(Pedido x:reporteDAO.pedidosEntregados(pedidoDAO.listaPedidos()))m.addRow(new Object[]{x.getId(),x.getMesa(),x.getEstado(),x.getMonto()});};
        JLabel total=new JLabel();total.setFont(new Font("Segoe UI",Font.BOLD,17));total.setForeground(azul);
        JButton actualizar=boton("Actualizar reporte");JPanel sur=new JPanel(new BorderLayout());sur.setOpaque(false);sur.add(total,BorderLayout.WEST);sur.add(actualizar,BorderLayout.EAST);p.add(sur,BorderLayout.SOUTH);mostrarTabla(p,t);
        Runnable calc=()->{refresh.run();total.setText(String.format("Total de ventas: $%,.2f",reporteDAO.totalVentas(pedidoDAO.listaPedidos())));};actualizar.addActionListener(e->calc.run());calc.run();return p;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RestauranteSwing().setVisible(true));
    }
}
