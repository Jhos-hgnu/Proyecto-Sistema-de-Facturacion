package Interfaces;

import Modelo.ModeloVistaInicio;

public interface ISesionInicio {

    public ModeloVistaInicio consultaUsuario(String usuario, String contrasenia);
}
