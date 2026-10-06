package view;

import dao.EntregaDAO;
import model.Entrega;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Clase que muestra el historial de entregas realizadas en una tabla
 */

public class VentanaListaEntregas extends JFrame {

    private JTable tablaEntregas;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private final EntregaDAO entregaDAO;

    public VentanaListaEntregas() {
        this.entregaDAO = new EntregaDAO();
        configurarVentana();
        crearComponentes();
        cargarEntregas();
    }

    private void configurarVentana() {
        setTitle("SpeedFast - Registro de Entregas");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void crearComponentes() {
        setLayout(new BorderLayout(10, 10));

        String[] columnas = {"ID Entrega", "ID Pedido", "ID Repartidor", "Fecha", "Hora"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEntregas = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaEntregas);
        btnActualizar = new JButton("Recargar");
        btnEliminar = new JButton("Eliminar Entrega");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnActualizar.addActionListener(e -> cargarEntregas());
        btnEliminar.addActionListener(e -> eliminarEntrega());
    }

    private void cargarEntregas() {
        modeloTabla.setRowCount(0);
        List<Entrega> lista = entregaDAO.listarTodos();

        for (Entrega e : lista) {
            Object[] fila = {
                    e.getId(),
                    e.getIdPedido(),
                    e.getIdRepartidor(),
                    e.getFecha(),
                    e.getHora()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void eliminarEntrega() {
        int fila = tablaEntregas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una entrega de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar el registro de entrega ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (entregaDAO.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "Entrega eliminada correctamente.");
                cargarEntregas();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la entrega.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
