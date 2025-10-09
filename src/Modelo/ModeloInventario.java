package Modelo;

import Implementacion.InventarioImp;
import Interfaces.Iinventario;
import Vistas.PanelInventario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData; 
import java.sql.SQLException;

public class ModeloInventario {
    private final Iinventario inventario;
    public final PanelInventario vista;

    public ModeloInventario(PanelInventario vista) {
        this.vista = vista;
        this.inventario = new InventarioImp();
    }

    public void cargarLotesEnTabla() {
        try {
            ResultSet rs = inventario.obtenerRegistroLotes();
            actualizarTabla(rs);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error al cargar lotes: " + ex.getMessage());
        }
    }

    public void cargarVentasDelDiaEnTabla() {
        try {
            ResultSet rs = inventario.obtenerRegistroVentasDelDia();
            actualizarTabla(rs);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error al cargar ventas: " + ex.getMessage());
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
        
        vista.tblInventario.setModel(model);
    }
}