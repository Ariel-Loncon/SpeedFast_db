package model;

public class Pedido {
    private int id;
    private String direccionEntrega;
    private String tipoEntrega;
    private EstadoPedido estado;

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
