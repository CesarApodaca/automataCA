/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintaxis;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CesarIvan
 */
public class TablaConteo extends JFrame {
    
    JScrollPane scroll;
    JTable tablaConteo, tabla;
    
    public TablaConteo(JTable tabla)
    {
        this.tabla=tabla;
        inicializador();
    }
    
    public void inicializador()
    {
        scroll = new JScrollPane();
        tablaConteo = new JTable();
        scroll.setViewportView(tablaConteo);
        tablaConteo.setModel(new DefaultTableModel());
        getContentPane().add(scroll);
        setTitle("Tabla de Conteo");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        tablaConteo.setModel(tabla.getModel());
    }
    
}
