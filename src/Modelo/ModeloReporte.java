package Modelo;

import Implementacion.ReporteImp;
import Interfaces.IReportes;
import Utilities.GeneradorPDFreporte;
import Vistas.VistaReporte;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData; 
import javax.swing.JFileChooser;
import javax.swing.JTable;
import java.io.File;
public class ModeloReporte {
private final IReportes reportes;
    public final VistaReporte vista;

    public ModeloReporte(VistaReporte vista) {
        this.vista = vista;
        this.reportes = new ReporteImp();
    }

    public void cargarVentasDelDiaEnTabla() {
        try {
            ResultSet rs = reportes.obtenerVentasDelDia();
            actualizarTabla(rs);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error al cargar reportes: " + ex.getMessage());
        }
    }

    private void actualizarTabla(ResultSet rs) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();

        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            model.addColumn(metaData.getColumnName(i));
        }

        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }

        vista.tblReportesVentas.setModel(model);
    }

 public void exportarReporteADiaPDF(int idUsuario) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar Reporte Diario");
    fileChooser.setSelectedFile(new File("Reporte_Ventas_Dia.pdf"));
    int seleccion = fileChooser.showSaveDialog(vista);

    if (seleccion == JFileChooser.APPROVE_OPTION) {
        String ruta = fileChooser.getSelectedFile().getAbsolutePath();

        if (!ruta.toLowerCase().endsWith(".pdf")) {
            ruta += ".pdf";
        }

        GeneradorPDFreporte generador = new GeneradorPDFreporte();
        JTable tabla = vista.tblReportesVentas;
        generador.generarReporteDia(tabla, ruta, idUsuario);
    }
}
}