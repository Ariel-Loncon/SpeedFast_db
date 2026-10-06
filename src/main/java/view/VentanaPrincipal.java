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
    private JButton btnListarRepartidores;
    private JButton btnRegistrarEntrega;
    private JButton btnListarEntregas;

public VentanaPrincipal() {
    configurarVentana();
    crearComponentes();
}

private void configurarVentana() {
    setTitle("SpeedFast - Sistema de Gestión de Pedidos y Entregas");
    setSize(500, 350);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout(10, 20));
}

private void crearComponentes() {
    JLabel lblTitulo = new JLabel("SpeedFast", SwingConstants.CENTER);
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
    lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));
    add(lblTitulo, BorderLayout.NORTH);

    JPanel panelBotones = new JPanel(new GridLayout(3, 2, 10, 10));
    panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

    btnRegistrarPedido = new JButton("Registrar Pedido");
    btnListarPedidos = new JButton("Gestionar Pedidos");

    btnRegistrarRepartidor = new JButton("Registrar Repartidor");
    btnListarRepartidores = new JButton("Gestionar Repartidores");

    btnRegistrarEntrega = new JButton("Asignar Entrega");
    btnListarEntregas = new JButton("Ver Entregas");

    panelBotones.add(btnRegistrarPedido);
    panelBotones.add(btnListarPedidos);

    panelBotones.add(btnRegistrarRepartidor);
    panelBotones.add(btnListarRepartidores);

    panelBotones.add(btnRegistrarEntrega);
    panelBotones.add(btnListarEntregas);

    add(panelBotones, BorderLayout.CENTER);

    btnRegistrarPedido.addActionListener(e -> new VentanaRegistroPedido().setVisible(true));
    btnListarPedidos.addActionListener(e -> new VentanaListaPedidos().setVisible(true));
    btnRegistrarRepartidor.addActionListener(e -> new VentanaRegistroRepartidor().setVisible(true));
    btnListarRepartidores.addActionListener(e -> new VentanaListaRepartidores().setVisible(true));
    btnRegistrarEntrega.addActionListener(e -> new VentanaRegistroEntrega().setVisible(true));
    btnListarEntregas.addActionListener(e -> new VentanaListaEntregas().setVisible(true));
    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
 }
}

