package view;

import dao.RepartidorDAO;
import model.Repartidor;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana para ingresar un nuevo repartidor al sistema.
 */

public class VentanaRegistroRepartidor extends JFrame {

    private JTextField txtNombre;
    private JButton btnGuardar;
    private final RepartidorDAO repartidorDAO;

    public VentanaRegistroRepartidor() {
        this.repartidorDAO = new RepartidorDAO();
        configurarVentana();
        crearComponentes();
    }

    private void configurarVentana() {
        setTitle("SpeedFast - Registrar Repartidor");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void crearComponentes() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtNombre = new JTextField();
        btnGuardar = new JButton("Guardar");

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);

        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(e -> guardarRepartidor());
    }

    private void guardarRepartidor() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del repartidor.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Repartidor repartidor = new Repartidor(0, nombre);
        boolean exito = repartidorDAO.guardar(repartidor);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Repartidor registrado correctamente.");
            txtNombre.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar repartidor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
