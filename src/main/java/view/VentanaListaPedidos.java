package view;

import dao.PedidoDAO;
import model.Pedido;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Ventana de consulta, que muestra todos los pedidos en una tabla
 */

public class VentanaListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;
    private final PedidoDAO pedidoDAO;

    public VentanaListaPedidos() {
        this.pedidoDAO = new PedidoDAO();
        configurarVentana();
        crearComponentes();
        cargarPedidos();
    }

    private void configurarVentana() {
        setTitle("SpeedFast - Lista de Pedidos en Base de Datos");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void crearComponentes() {
        setLayout(new BorderLayout(10, 10));

        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPedidos = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaPedidos);
        btnActualizar = new JButton("Recargar Tabla");

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnActualizar);

        add(scroll, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        btnActualizar.addActionListener(e -> cargarPedidos());
    }

    private void cargarPedidos() {
        modeloTabla.setRowCount(0);
        List<Pedido> lista = pedidoDAO.listarTodos();

        for (Pedido p : lista) {
            Object[] fila = {
                    p.getId(),
                    p.getDireccionEntrega(),
                    p.getTipoEntrega(),
                    p.getEstado()
            };
            modeloTabla.addRow(fila);
        }
    }
}
