package view;

import dao.PedidoDAO;
import model.EstadoPedido;
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
    private JButton btnEliminar;
    private JButton btnCambiarEstado;
    private final PedidoDAO pedidoDAO;

    public VentanaListaPedidos() {
        this.pedidoDAO = new PedidoDAO();
        configurarVentana();
        crearComponentes();
        cargarPedidos();
    }

    private void configurarVentana() {
        setTitle("SpeedFast - Lista y Gestión de Pedidos");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void crearComponentes() {
        setLayout(new BorderLayout(10, 10));

        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla de solo lectura mediante doble clic
            }
        };
        tablaPedidos = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaPedidos);
        btnActualizar = new JButton("Recargar Tabla");
        btnEliminar = new JButton("Eliminar Seleccionado");
        btnCambiarEstado = new JButton("Cambiar Estado");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnActualizar);
        panelBotones.add(btnCambiarEstado);
        panelBotones.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnActualizar.addActionListener(e -> cargarPedidos());
        btnEliminar.addActionListener(e -> eliminarPedido());
        btnCambiarEstado.addActionListener(e -> cambiarEstadoPedido());
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

    private void eliminarPedido() {
        int filaSeleccionada = tablaPedidos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el pedido ID " + id + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (pedidoDAO.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "Pedido eliminado correctamente.");
                cargarPedidos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cambiarEstadoPedido() {
        int filaSeleccionada = tablaPedidos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        String direccion = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
        String tipo = (String) modeloTabla.getValueAt(filaSeleccionada, 2);

        EstadoPedido nuevoEstado = (EstadoPedido) JOptionPane.showInputDialog(
                this,
                "Seleccione el nuevo estado:",
                "Actualizar Estado",
                JOptionPane.QUESTION_MESSAGE,
                null,
                EstadoPedido.values(),
                EstadoPedido.PENDIENTE
        );

        if (nuevoEstado != null) {
            Pedido pedido = new Pedido(id, direccion, tipo, nuevoEstado);
            if (pedidoDAO.actualizar(pedido)) {
                JOptionPane.showMessageDialog(this, "Estado actualizado con éxito.");
                cargarPedidos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el estado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
