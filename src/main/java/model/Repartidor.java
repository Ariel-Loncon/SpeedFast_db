package model;

public class Repartidor {
    private int idRepartidor;
    private String nombreRepartidor;

    public Repartidor(int idRepartidor, String nombreRepartidor) {
        this.idRepartidor = idRepartidor;
        this.nombreRepartidor = nombreRepartidor;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

}
