package view;

import dao.PedidoDAO;
import model.EstadoPedido;
import model.Pedido;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana para ingresar un nuevo pedido al sistema.
 */

public class VentanaRegistroPedido extends JFrame {

    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;
    private JComboBox<EstadoPedido> cmbEstado;
    private JButton btnGuardar;
    private final PedidoDAO pedidoDAO;

    public VentanaRegistroPedido() {
        this.pedidoDAO = new PedidoDAO();
        configurarVentana();
        crearComponentes();
    }

    private void configurarVentana() {
        setTitle("SpeedFast - Registrar Pedido");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void crearComponentes() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtDireccion = new JTextField();
        cmbTipo = new JComboBox<>(new String[]{"COMIDA", "ENCOMIENDA", "EXPRESS"});
        cmbEstado = new JComboBox<>(EstadoPedido.values());
        btnGuardar = new JButton("Guardar Pedido");

        panel.add(new JLabel("Dirección:"));
        panel.add(txtDireccion);

        panel.add(new JLabel("Tipo:"));
        panel.add(cmbTipo);

        panel.add(new JLabel("Estado:"));
        panel.add(cmbEstado);

        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(e -> guardarPedido());
    }

    private void guardarPedido() {
        String direccion = txtDireccion.getText().trim();
        String tipo = cmbTipo.getSelectedItem().toString();
        EstadoPedido estado = (EstadoPedido) cmbEstado.getSelectedItem();

        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una dirección.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pedido pedido = new Pedido(direccion, tipo, estado);
        boolean exito = pedidoDAO.guardar(pedido);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Pedido registrado exitosamente en la base de datos.");
            txtDireccion.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
