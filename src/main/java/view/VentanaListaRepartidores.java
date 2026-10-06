package view;

import dao.RepartidorDAO;
import model.Repartidor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Clase que permite editar o eliminar a los repartidores mediante una tabla
 */

public class VentanaListaRepartidores extends JFrame {

    private JTable tablaRepartidores;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private final RepartidorDAO repartidorDAO;

    public VentanaListaRepartidores() {
        this.repartidorDAO = new RepartidorDAO();
        configurarVentana();
        crearComponentes();
        cargarRepartidores();
    }

    private void configurarVentana() {
        setTitle("SpeedFast - Gestión de Repartidores");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void crearComponentes() {
        setLayout(new BorderLayout(10, 10));

        String[] columnas = {"ID", "Nombre"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaRepartidores = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaRepartidores);
        btnActualizar = new JButton("Recargar");
        btnEditar = new JButton("Editar Nombre");
        btnEliminar = new JButton("Eliminar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnActualizar.addActionListener(e -> cargarRepartidores());
        btnEditar.addActionListener(e -> editarRepartidor());
        btnEliminar.addActionListener(e -> eliminarRepartidor());
    }

    private void cargarRepartidores() {
        modeloTabla.setRowCount(0);
        List<Repartidor> lista = repartidorDAO.listarTodos();

        for (Repartidor r : lista) {
            Object[] fila = { r.getIdRepartidor(), r.getNombreRepartidor() };
            modeloTabla.addRow(fila);
        }
    }

    private void editarRepartidor() {
        int fila = tablaRepartidores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un repartidor.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        String nombreActual = (String) modeloTabla.getValueAt(fila, 1);

        String nuevoNombre = JOptionPane.showInputDialog(this, "Modificar nombre:", nombreActual);

        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            Repartidor repartidor = new Repartidor(id, nuevoNombre.trim());
            if (repartidorDAO.actualizar(repartidor)) {
                JOptionPane.showMessageDialog(this, "Repartidor actualizado correctamente.");
                cargarRepartidores();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarRepartidor() {
        int fila = tablaRepartidores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un repartidor.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar al repartidor ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (repartidorDAO.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "Repartidor eliminado.");
                cargarRepartidores();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el repartidor (puede tener entregas asociadas).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
