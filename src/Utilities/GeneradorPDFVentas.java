/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

/**
 *
 * @author jhosu
 */
import Modelo.ModeloClientesVentas;
import Modelo.ModeloDetalleVenta;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JTable;

public class GeneradorPDFVentas {

    public void generarFacturaPDF(List<ModeloDetalleVenta> carrito, String nombreArchivo, String codigoArchivo, ModeloClientesVentas venta, String usuarioActivo, int idVenta, String subTotal, String total, String descuento, String ruta) {
        try {
            Document document = new Document();
            
            String rutaCompleta = ruta + File.separator + nombreArchivo;
            
            PdfWriter.getInstance(document, new FileOutputStream(rutaCompleta));
            document.open();

            //Encabezado para PDF
            Image logo = Image.getInstance("src/Imagen/Pill MT 10-100 BlancoNegro");
            logo.scaleToFit(100, 100);
            logo.setAlignment(Image.ALIGN_CENTER);

            document.add(logo);

            document.add(new Paragraph("FARMACIA SOCIAL", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20)));

            document.add(new Paragraph("Dirección: Calle principal,Guastatoya"));
            document.add(Chunk.NEWLINE);

            //Fecha y número de venta Datos de la venta
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd&/MM/yyyy HH:mm");
            document.add(new Paragraph("No. de Venta: " + idVenta));
            document.add(new Paragraph("Tipo Pago: " + venta.getTipoPago()));
            document.add(new Paragraph("Fecha: " + LocalDateTime.now().format(formatter)));
            document.add(new Paragraph("Código Comprobante: " + codigoArchivo));
            document.add(new Paragraph("Vendedor: " + usuarioActivo));
            document.add(new Paragraph(""));
            document.add(new Paragraph("Cliente: " + venta.getNombre() + venta.getApellido()));
            document.add(new Paragraph("NIT: " + venta.getNit()));
            document.add(new Paragraph("Dirección: " + venta.getDireccion()));
            document.add(Chunk.NEWLINE);

            //Tabla del Carrito
            PdfPTable tablaPDF = new PdfPTable(5);
            tablaPDF.setWidthPercentage(100);
            tablaPDF.addCell("Nombre");
            tablaPDF.addCell("Precio");
            tablaPDF.addCell("Cantidad");
            tablaPDF.addCell("SubTotal");

            //Agregar filas de la tabla
            for (ModeloDetalleVenta item : carrito) {
                tablaPDF.addCell(crearCelda(item.getNombreProducto(), Element.ALIGN_LEFT, false));
                tablaPDF.addCell(crearCelda(String.format("Q%.2f", item.getPrecioUnitario()), Element.ALIGN_RIGHT, false));
                tablaPDF.addCell(crearCelda(String.valueOf(item.getCantidad()), Element.ALIGN_CENTER, false));
                tablaPDF.addCell(crearCelda(String.format("Q%.2f", item.getSubtotal()), Element.ALIGN_RIGHT, false));
            }
//            for(int i = 0; i  < tablaCarrito.getRowCount(); i++) {
//            tablaPDF.addCell(tablaCarrito.getValueAt(i, 0).toString()); // Nombre
//            tablaPDF.addCell(tablaCarrito.getValueAt(i, 1).toString()); // Precio
//            tablaPDF.addCell(tablaCarrito.getValueAt(i, 2).toString()); // Cantidad
//            tablaPDF.addCell(tablaCarrito.getValueAt(i, 3).toString()); // Subtotal
//            tablaPDF.addCell(tablaCarrito.getValueAt(i, 4).toString());
//            }

            document.add(tablaPDF);
            document.add(new Paragraph(""));
            document.add(new Paragraph("SubTotal: Q" + subTotal));
            Paragraph paragraphTotal = new Paragraph("Total: Q" + total);
            paragraphTotal.setAlignment(Element.ALIGN_RIGHT);
            document.add(paragraphTotal);

            //document.add(new Paragraph("Total: " + venta));
            //Totales
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Descuento: Q" + venta.getDescuento()));
            document.add(new Paragraph("Total: Q" + venta.getTotal()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("¡Gracias por su compra!", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private PdfPCell crearCelda(String texto, int alineacion, boolean negrita) {
    Font font = negrita ? FontFactory.getFont(FontFactory.HELVETICA_BOLD) : 
                         FontFactory.getFont(FontFactory.HELVETICA);
    PdfPCell cell = new PdfPCell(new Phrase(texto, font));
    cell.setHorizontalAlignment(alineacion);
    cell.setBorder(PdfPCell.NO_BORDER);
    return cell;
    }
    
    public static String obtenerRutaComprobantes() {
        String escritorio = System.getProperty("user.home") + File.separator + "Desktop";
        String carpetaComprobantes = escritorio + File.separator + "comprobantesVentas";

        //Crear carpeta si no existe
        File directorio = new File(carpetaComprobantes);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        return carpetaComprobantes;
    }
}