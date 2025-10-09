/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;



/**
 *
 * @author anyi4
 */
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JTable;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneradorPDFreporte {
    public void generarReporteDia(JTable tablaReporte, String rutaCompleta, int idUsuarioGenerador) {
        try {
            Document document = new Document(PageSize.A4.rotate()); // Horizontal
            PdfWriter.getInstance(document, new FileOutputStream(rutaCompleta));
            document.open();
            
            // Encabezado
            agregarEncabezado(document, idUsuarioGenerador);
            
            // Tabla de datos
            PdfPTable tablaPDF = crearTablaPDF(tablaReporte);
            document.add(tablaPDF);
            
            // Totalización
            agregarTotalizacion(document, tablaReporte);
            
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void agregarEncabezado(Document document, int idUsuario) throws DocumentException {
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph titulo = new Paragraph("REPORTE DIARIO DE VENTAS", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        
        Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA, 12);
        Paragraph farmacia = new Paragraph("FARMACIA SOCIAL", fontSubtitulo);
        farmacia.setAlignment(Element.ALIGN_CENTER);
        document.add(farmacia);
        
        String fechaGeneracion = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        Paragraph fecha = new Paragraph("Generado el: " + fechaGeneracion, fontSubtitulo);
        fecha.setAlignment(Element.ALIGN_CENTER);
        document.add(fecha);
        
        Paragraph usuario = new Paragraph("Generado por: Usuario #" + idUsuario, fontSubtitulo);
        usuario.setAlignment(Element.ALIGN_CENTER);
        document.add(usuario);
        
        document.add(Chunk.NEWLINE);
    }

    private PdfPTable crearTablaPDF(JTable tablaReporte) {
        PdfPTable tablaPDF = new PdfPTable(tablaReporte.getColumnCount());
        tablaPDF.setWidthPercentage(100);
        
        // Encabezados
        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        for (int i = 0; i < tablaReporte.getColumnCount(); i++) {
            PdfPCell cell = new PdfPCell(new Phrase(tablaReporte.getColumnName(i), fontHeader));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tablaPDF.addCell(cell);
        }
        
        // Datos
        for (int row = 0; row < tablaReporte.getRowCount(); row++) {
            for (int col = 0; col < tablaReporte.getColumnCount(); col++) {
                Object value = tablaReporte.getValueAt(row, col);
                tablaPDF.addCell(value != null ? value.toString() : "");
            }
        }
        
        return tablaPDF;
    }

    private void agregarTotalizacion(Document document, JTable tablaReporte) throws DocumentException {
        double totalVentas = 0;
        int columnaTotal = 5; // Asumiendo que la columna 5 es el total
        
        for (int row = 0; row < tablaReporte.getRowCount(); row++) {
            Object value = tablaReporte.getValueAt(row, columnaTotal);
            if (value instanceof Number) {
                totalVentas += ((Number) value).doubleValue();
            }
        }
        
        document.add(Chunk.NEWLINE);
        Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Paragraph total = new Paragraph(
            "TOTAL VENTAS DEL DÍA: Q" + String.format("%.2f", totalVentas), 
            fontTotal
        );
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);
    }
}
