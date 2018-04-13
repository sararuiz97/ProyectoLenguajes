
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jumarogu
 */
public class ExtraTableModel extends DefaultTableModel{
    
    public ExtraTableModel(int row, int column) {
        super(row, column);
    }
    @Override
    synchronized public Object getValueAt(int row, int column) {
        try {
            return super.getValueAt(row, column);
        }
        catch(java.lang.ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    @Override
    synchronized public void addRow(Object[] rowData) {
        super.addRow(rowData);
    }
    
    @Override
    synchronized public void insertRow(int row, Object[] rowData) {
        super.insertRow(row, rowData);
    }
    
    @Override
    synchronized public void removeRow(int row) {
        try {
            super.removeRow(row);
        }
        catch(java.lang.ArrayIndexOutOfBoundsException ex) {
            
        }
    }
}
