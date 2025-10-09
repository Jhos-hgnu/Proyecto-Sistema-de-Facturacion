package Modelo;

import Vistas.VistaAdmin;

public class ModeloVistaAdmin {

    VistaAdmin vistaAdmin;

    public ModeloVistaAdmin() {
    }

    public ModeloVistaAdmin(VistaAdmin vistaAdmin) {
        this.vistaAdmin = vistaAdmin;
    }

    public VistaAdmin getVistaAdmin() {
        return vistaAdmin;
    }

    public void setVistaAdmin(VistaAdmin vistaAdmin) {
        this.vistaAdmin = vistaAdmin;
    }
}
