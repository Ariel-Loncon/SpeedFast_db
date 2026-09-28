package model;

/**
 * Clase que representa al personal encargado de realizar las entregas
 */

public class Repartidor {
    private int idRepartidor;
    private String nombreRepartidor;

    /**
     *
     * @param idRepartidor Identificador único del repartidor.
     * @param nombreRepartidor Nombre del repartidor
     */

    public Repartidor(int idRepartidor, String nombreRepartidor) {
        this.idRepartidor = idRepartidor;
        this.nombreRepartidor = nombreRepartidor;
    }


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



}
