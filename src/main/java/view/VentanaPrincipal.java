package view;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del sistema
 * Menù central para la navegación en la interfaz
 */

public class VentanaPrincipal extends JFrame {

    private JButton btnRegistrarPedido;
    private JButton btnRegistrarRepartidor;
    private JButton btnListarPedidos;
    private JButton btnRegistrarEntrega;

    public VentanaPrincipal() {
        configurarVentana();
        crearComponentes();
    }

    private void configurarVentana() {
        setTitle("SpeedFast - Gestión de Entregas y Pedidos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 20));
    }

    private void crearComponentes() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("SpeedFast", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));

        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        btnRegistrarPedido = new JButton("Registrar Pedido");
        btnRegistrarRepartidor = new JButton("Registrar Repartidor");
        btnListarPedidos = new JButton("Consultar Pedidos");
        btnRegistrarEntrega = new JButton("Asignar / Registrar Entrega");

        // Fila 1
        panelBotones.add(btnRegistrarPedido);
        panelBotones.add(btnRegistrarRepartidor);

        // Fila 2
        panelBotones.add(btnListarPedidos);
        panelBotones.add(btnRegistrarEntrega);

        add(panelBotones, BorderLayout.CENTER);

        // Eventos
        btnRegistrarPedido.addActionListener(e -> new view.VentanaRegistroPedido().setVisible(true));
        btnRegistrarRepartidor.addActionListener(e -> new view.VentanaRegistroRepartidor().setVisible(true));
        btnListarPedidos.addActionListener(e -> new view.VentanaListaPedidos().setVisible(true));
        btnRegistrarEntrega.addActionListener(e -> new VentanaRegistroEntrega().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
