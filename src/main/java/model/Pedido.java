package model;

/**
 * Clase que representa al Pedido dentro del sistema
 * Contiene la información sobre el destino, el tipo de entrega y su estado actual.
 */

public class Pedido {
    private int id;
    private String direccionEntrega;
    private String tipoEntrega;
    private EstadoPedido estado;

    /**
     *
     * @param id Identificador único del pedido
     * @param direccionEntrega Dirección de entrega del pedido
     * @param tipoEntrega Tipo de entrega del pedido
     * @param estado Estado actual del pedido
     */

    public Pedido(int id, String direccionEntrega, String tipoEntrega, EstadoPedido estado) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.tipoEntrega = tipoEntrega;
        this.estado = estado;
    }

    public Pedido(String direccionEntrega, String tipoEntrega, EstadoPedido estado) {
        this.direccionEntrega = direccionEntrega;
        this.tipoEntrega = tipoEntrega;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
}
