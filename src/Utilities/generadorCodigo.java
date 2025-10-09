/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jhosu
 */
public class generadorCodigo {

    

    public static String generarCodigoBase() {
        LocalDateTime ahora = LocalDateTime.now();
        return "CMP-" + ahora.format(DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm"));
    }

    public static String generarCodigoCompleto(int idUsuario, String tipoDocumento) {
        return generarCodigoBase() + "-" + idUsuario + "-" + tipoDocumento;
    }

    public static String generarNombrePDF(int idUsuario, String tipoDocumento) {
        return generarCodigoCompleto(idUsuario, tipoDocumento) + ".pdf";
    }


}
