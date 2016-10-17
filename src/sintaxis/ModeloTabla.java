
package sintaxis;

import javax.swing.table.AbstractTableModel;


public class ModeloTabla extends AbstractTableModel {
    String[] columnNames;
    Object[][] data; 
    
    public ModeloTabla(int i,Object[][] d)
    {
        if(i==1)
            columnNames  =new String[] {"No. Token", "Lexema", "Linea", "Descripción"};
        if(i==2)
            columnNames  =new String[] {"No. Error", "Descripcion", "Linea","Lexema","Tipo"};
        if(i==3)
            columnNames = new String[] {"ID","Tipo","Clase","Ambito","Tarr","DimArr","NoPar","TParr"};
        if(i==4)
            columnNames = new String[] {"Ambito","Var","Fun","Proc","Par"};
        data=d;
        
        
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
      //  fireTableCellUpdated(row, col);
    }
}
