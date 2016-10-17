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
public class TablaSimbolos extends JFrame {
    
    JScrollPane scroll;
    JTable tablaSimbolos, tabla;
    
    public TablaSimbolos(JTable tabla)
    {
        this.tabla=tabla;
        inicializador();
    }
    
    public void inicializador()
    {
        scroll = new JScrollPane();
        tablaSimbolos = new JTable();
        scroll.setViewportView(tablaSimbolos);
        tablaSimbolos.setModel(new DefaultTableModel());
        getContentPane().add(scroll);
        setTitle("Tabla de Simbolos");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        tablaSimbolos.setModel(tabla.getModel());
    }
    
}
