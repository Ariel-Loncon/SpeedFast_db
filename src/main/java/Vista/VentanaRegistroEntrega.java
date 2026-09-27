package Vista;

import dao.EntregaDAO;
import dao.PedidoDAO;
import dao.RepartidorDAO;
import model.Entrega;
import model.Pedido;
import model.Repartidor;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VentanaRegistroEntrega extends JFrame {

    private JComboBox<String> cmbPedidos;
    private JComboBox<String> cmbRepartidores;
    private JButton btnGuardar;

    private List<Pedido> listaPedidos;
    private List<Repartidor> listaRepartidores;

    private final PedidoDAO pedidoDAO;
    private final RepartidorDAO repartidorDAO;
    private final EntregaDAO entregaDAO;

    public VentanaRegistroEntrega() {
        this.pedidoDAO = new PedidoDAO();
        this.repartidorDAO = new RepartidorDAO();
        this.entregaDAO = new EntregaDAO();

        configurarVentana();
        crearComponentes();
        cargarDatos();
    }

    private void configurarVentana() {
        setTitle("SpeedFast - Registrar Entrega");
        setSize(450, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void crearComponentes() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        cmbPedidos = new JComboBox<>();
        cmbRepartidores = new JComboBox<>();
        btnGuardar = new JButton("Asignar Entrega");

        panel.add(new JLabel("Seleccionar Pedido:"));
        panel.add(cmbPedidos);

        panel.add(new JLabel("Seleccionar Repartidor:"));
        panel.add(cmbRepartidores);

        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(e -> guardarEntrega());
    }

    private void cargarDatos() {
        listaPedidos = pedidoDAO.listarTodos();
        cmbPedidos.removeAllItems();
        for (Pedido p : listaPedidos) {
            cmbPedidos.addItem("ID: " + p.getId() + " - " + p.getDireccionEntrega() + " (" + p.getTipoEntrega() + ")");
        }

        listaRepartidores = repartidorDAO.listarTodos();
        cmbRepartidores.removeAllItems();
        for (Repartidor r : listaRepartidores) {
            cmbRepartidores.addItem("ID: " + r.getIdRepartidor() + " - " + r.getNombreRepartidor());
        }
    }

    private void guardarEntrega() {
        int idxPedido = cmbPedidos.getSelectedIndex();
        int idxRepartidor = cmbRepartidores.getSelectedIndex();

        if (idxPedido == -1 || idxRepartidor == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido y un repartidor.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPedido = listaPedidos.get(idxPedido).getId();
        int idRepartidor = listaRepartidores.get(idxRepartidor).getIdRepartidor();

        Entrega entrega = new Entrega(idPedido, idRepartidor, LocalDate.now(), LocalTime.now());

        boolean exito = entregaDAO.guardar(entrega);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Entrega asignada y registrada con éxito.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar la entrega.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
