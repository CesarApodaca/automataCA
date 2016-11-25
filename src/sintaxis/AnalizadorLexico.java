/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintaxis;

import static sintaxis.TextLineNumber.gris;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.LookAndFeel;
import javax.swing.Painter;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableModel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 *
 * @author CesarIvan
 */
public class AnalizadorLexico extends JFrame{
    
Control c;    
File texto;    
JFileChooser explorador;    
int status;
JTextArea textoCargado, tokens, errores, listaTokens, listaBarrido;
JScrollPane scrollPanel, scrollTokens, scrollBarrido;
TextLineNumber tln;
JPanel fondo;
JTable vistaErrores;
JButton btnCargar, btnCompilar, btnBorrar, btnListaTokens, btnListaBarrido, btnTablaSimbolos, btnConteo;
Color gris = new Color(hex("5F5F5F"));
Color azul = new Color(hex("0C2838"));
    
    public AnalizadorLexico(){ 
      status=0;
      setTitle("Analizador Lexico");
      setSize(1600,1000);
      fondo = new JPanel();
      fondo.setLayout(null);
      getContentPane().add(fondo);
      fondo.setBackground(Color.WHITE);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      UIManager.put("TabbedPane.selected", azul);
      UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
      UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);
      interfaz();
      setVisible(true);
    }


    
    public void interfaz(){
        
      
      explorador = new JFileChooser();
        explorador.setCurrentDirectory(new java.io.File("C:\temp"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (txt)", "txt", "text");
        explorador.setDialogTitle("Explorador");
        explorador.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        explorador.setAcceptAllFileFilterUsed(true);
        explorador.setFileFilter(filter);
        
        
    //TextArea
     textoCargado = new JTextArea();
     textoCargado.setEditable(true);
      c = new Control(textoCargado);
     scrollPanel = new JScrollPane();
     scrollPanel.setViewportView(textoCargado);
     fondo.add(scrollPanel);
     tln = new TextLineNumber(textoCargado);
     scrollPanel.setRowHeaderView(tln);
     scrollPanel.setBounds(20, 50, 800, 500);
     
     listaTokens = new JTextArea();
     listaTokens.setEditable(false);
     scrollTokens = new JScrollPane();
     scrollTokens.setViewportView(listaTokens);
     fondo.add(scrollTokens);
     scrollTokens.setBounds(860, 641, 600, 240);
     
     listaBarrido = new JTextArea();
     listaBarrido.setEditable(false);
     scrollBarrido = new JScrollPane();
     scrollBarrido.setViewportView(listaBarrido);
     fondo.add(scrollBarrido);
     scrollBarrido.setBounds(860, 300, 600, 240);
     
     
     //Botones
    btnCargar = new JButton("Cargar");
    fondo.add(btnCargar);
    btnCargar.setBounds(860, 50, 100, 30);
  //btnCargar.setBorderPainted(false);
    btnCargar.setFocusPainted(false);
    btnCargar.setContentAreaFilled(true);
    btnCargar.setForeground(Color.WHITE);
    btnCargar.setBackground(gris);
    btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
    
    
    btnCompilar = new JButton("Compilar");
    fondo.add(btnCompilar);
    btnCompilar.setBounds(860, 90, 100, 30);
    btnCompilar.setFocusPainted(false);
    btnCompilar.setContentAreaFilled(true);
    btnCompilar.setForeground(Color.WHITE);
    btnCompilar.setBackground(gris);
    btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnCompilarActionPerformed(evt);
                } catch (WriteException ex) {
                    Logger.getLogger(AnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BiffException ex) {
                    Logger.getLogger(AnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    
    btnBorrar = new JButton("Borrar");
    fondo.add(btnBorrar);
    btnBorrar.setBounds(860, 130, 100, 30);
    btnBorrar.setFocusPainted(false);
    btnBorrar.setContentAreaFilled(true);
    btnBorrar.setForeground(Color.WHITE);
    btnBorrar.setBackground(gris);
    btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
    
    btnTablaSimbolos = new JButton("Simbolos");
    fondo.add(btnTablaSimbolos);
    btnTablaSimbolos.setBounds(860, 170, 100, 30);
    btnTablaSimbolos.setFocusPainted(false);
    btnTablaSimbolos.setContentAreaFilled(true);
    btnTablaSimbolos.setForeground(Color.WHITE);
    btnTablaSimbolos.setBackground(gris);
    btnTablaSimbolos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablaSimbolosActionPerformed(evt);
            }
        });
    
    btnConteo = new JButton("Conteo");
    fondo.add(btnConteo);
    btnConteo.setBounds(860, 210, 100, 30);
    btnConteo.setFocusPainted(false);
    btnConteo.setContentAreaFilled(true);
    btnConteo.setForeground(Color.WHITE);
    btnConteo.setBackground(gris);
    btnConteo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConteoActionPerformed(evt);
            }
        });
    
    btnListaTokens = new JButton("Cantidad de Tokens | Errores");
    fondo.add(btnListaTokens);
    btnListaTokens.setBounds(860, 610, 599, 31);
    btnListaTokens.setBorderPainted(false);
    btnListaTokens.setFocusPainted(false);
   // btnListaTokens.setFocusable(false);
    btnListaTokens.setContentAreaFilled(true);
    btnListaTokens.setForeground(Color.WHITE);
    btnListaTokens.setBackground(gris);
    
    btnListaBarrido = new JButton("Barrido");
    fondo.add(btnListaBarrido);
    btnListaBarrido.setBounds(860, 270, 599, 31);
    btnListaBarrido.setBorderPainted(false);
    btnListaBarrido.setFocusPainted(false);
   // btnListaTokens.setFocusable(false);
    btnListaBarrido.setContentAreaFilled(true);
    btnListaBarrido.setForeground(Color.WHITE);
    btnListaBarrido.setBackground(gris);
    
    
    //JTable
    vistaErrores = new JTable();
    
    //Tokens y Errores
    JTabbedPane listas = new JTabbedPane();
    JScrollPane scrolltoken = new JScrollPane();
    JScrollPane scrollerror = new JScrollPane();
    tokens = new JTextArea();
    tokens.setEditable(false);
    tokens.setEditable(false);
    errores = new JTextArea();
    errores.setEditable(false);
    scrolltoken.setViewportView(tokens);
   // scrollerror.setViewportView(errores);
   scrollerror.setViewportView(vistaErrores);
    
    listas.addTab("Tokens", scrolltoken);
    listas.addTab("Errores", scrollerror);
    fondo.add(listas);
    listas.setBounds(20, 620, 800, 260);
    listas.setBackground(gris);
    listas.setForeground(Color.WHITE);
    listas.setBackgroundAt(0, gris);
    listas.setUI(new BasicTabbedPaneUI() {
   @Override
   protected void installDefaults() {
       super.installDefaults();

   }
}); 
    
    }
    
    
    //Listeners
     private void btnListaTokensMousePressed(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
         btnListaTokens.setBackground(gris);
    } 
     
     
     private void  btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {    
        listaTokens.setText("");
        tokens.setText("");
        textoCargado.setText("");
        vistaErrores.setModel(new DefaultTableModel());
        listaBarrido.setText("");
        
     c.borrarTodo();
     }
     
     private void  btnTablaSimbolosActionPerformed(java.awt.event.ActionEvent evt) {    
     //c.borrarTodo();
     if(!textoCargado.getText().isEmpty())
        {
            new TablaSimbolos(c.ambitoTabla);
        }
     }
     
     private void  btnConteoActionPerformed(java.awt.event.ActionEvent evt) {    
     //c.borrarTodo();
     if(!textoCargado.getText().isEmpty())
        {
            new TablaConteo(c.conteoTabla);
        }
     }
    
    
    private void  btnCargarActionPerformed(java.awt.event.ActionEvent evt) {      
       // c.borrarTodo();
        status = explorador.showOpenDialog(fondo);
        if (status == JFileChooser.APPROVE_OPTION) {
            texto = explorador.getSelectedFile();
            if(tokens.getText().isEmpty()==false)//&&error.getText().isEmpty()==false
            {
                tokens.setText("");
              
                //error.setText("");
            }
               
            listaTokens.setText("");
        textoCargado.setText("");
        c.areaTexto.setText("");
        c.contMat =0;
        c.contMono=0;
        c.contAsig=0;
        c.contRel=0;
        c.contLog=0;
        c.contDes=0;
        c.contAgrup=0;
        c.contCadena=0;
        c.contPuntua=0;
        c.contEntero=0;
        c.contDecimal=0;
        c.contCarac=0;
        c.contBar=0;
        c.contTok=0;
        c.contComentarioV=0;
        c.contIde=0;
        c.contComentL=0;
        c.contExponencial=0;
        vistaErrores.setModel(new DefaultTableModel());
        c.errorTabla.setModel(new DefaultTableModel());
        c.ListaError.clear();
     
            c.abrirArchivo(texto);
        }
    }  
    private void  btnCompilarActionPerformed(java.awt.event.ActionEvent evt) throws WriteException, IOException, BiffException {     
        if(!textoCargado.getText().isEmpty())
        {
        listaTokens.setText("");
        tokens.setText("");
        vistaErrores.setModel(new DefaultTableModel());
        tablas();
        }
    }
    

    
    
    public void tablas() throws WriteException, IOException, BiffException
    {
     c.abrirArchivoComp(textoCargado);   
     c.lexico();   
     
     tokens.setText("Linea\t"+"Token\t"+"Dato\n");
            //error.setText("Linea\t"+"No. Error\t"+"Descripcion\t"+"\tLexema\n");
            listaBarrido.setText("Linea\t"+"Token|Error\t"+"Tipo Error\t"+"Descripcion\t\t"+"Lexema\n");
            for(int i=0;c.palabrasPresentes[i]!=null;i++)
                tokens.setText(tokens.getText()+c.palabrasPresentes[i]+"\n");
     

     listaTokens.setText("Linea\t\t"+"Token\t"+"Dato\n");       
            
                listaTokens.setText("Token\t\t"+"Cantidad\t"+"Descripcion\n");
                listaTokens.setText(listaTokens.getText()+"-1\t\t"+c.contIde+"\t"+"Identificadores.\n");
                listaTokens.setText(listaTokens.getText()+"-1\t\t"+c.contPaRes+"\t"+"Palabras Reservadas.\n");
                listaTokens.setText(listaTokens.getText()+"-6 -9 -11 -13 -38\t"+c.contMat+"\t"+"Operador Matematico.\n");
                listaTokens.setText(listaTokens.getText()+"-7 -10\t\t"+c.contMono+"\t"+"Operador Monogamo.\n");
                listaTokens.setText(listaTokens.getText()+"-25 -5 -8 -12 -40\t"+c.contAsig+"\t"+"Asignacion.\n"); 
                listaTokens.setText(listaTokens.getText()+"-20 -19 -23 -15 -22 -26\t"+c.contRel+"\t"+"Relacionales.\n");
                listaTokens.setText(listaTokens.getText()+"-14 -16 -17 -18\t\t"+c.contLog+"\t"+"Logicos.\n");
                listaTokens.setText(listaTokens.getText()+"-21 -24\t\t"+c.contDes+"\t"+"Desplazamiento.\n");
                listaTokens.setText(listaTokens.getText()+"-30 -31 -32 -33 -34 -35\t"+c.contAgrup+"\t"+"Agrupacion.\n");
                listaTokens.setText(listaTokens.getText()+"-27 -28 -29\t\t"+c.contPuntua+"\t"+"Signo de puntuacion.\n");
                listaTokens.setText(listaTokens.getText()+"-2\t\t"+c.contEntero+"\t"+"Entero.\n");
                listaTokens.setText(listaTokens.getText()+"-3\t\t"+c.contDecimal+"\t"+"Decimal.\n");
                listaTokens.setText(listaTokens.getText()+"-4\t\t"+c.contExponencial+"\t"+"Exponencial.\n");
                listaTokens.setText(listaTokens.getText()+"-36\t\t"+c.contCadena+"\t"+"Cadena.\n");
                listaTokens.setText(listaTokens.getText()+"-37\t\t"+c.contCarac+"\t"+"Caracter.\n");
                listaTokens.setText(listaTokens.getText()+"-39\t\t"+c.contComentL+"\t"+"Comentario Lineal.\n");
                listaTokens.setText(listaTokens.getText()+"-41\t\t"+c.contComentarioV+"\t"+"Comentario de varias lineas.\n");
                
                listaBarrido.setText("");
                
                     for(int i=0;(i<c.barrido.length)&&c.barrido[i]!=null;i++)
                    listaBarrido.setText(listaBarrido.getText()+c.barrido[i]+"\n");    
              if(c.errorTabla!=null)
            vistaErrores.setModel(c.errorTabla.getModel());
    }
    
     private int hex( String color_hex )
        {
            return Integer.parseInt(color_hex,  16 );
        }
    
 
    
 

    
}
