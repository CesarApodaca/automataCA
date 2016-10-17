/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintaxis;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.*;
import jxl.read.biff.BiffException;

/**
 *
 * @author CesarIvan
 */
public class Matriz {
  Sheet hoja;   
  Cell celda;
  File archivo;
  Workbook libro;

  Matriz (){
      libro = lexico();
      hoja = libro.getSheet(0);
  }
  
  Matriz(int i){
      if(i==1)
        libro = lexico();
      else  
        libro = sintaxis();
      hoja = libro.getSheet(0);      
  }
  
  public Workbook lexico(){
      try {
           return Workbook.getWorkbook(new File("MatrizL.xls"));
        } catch (IOException | BiffException ex) {
            Logger.getLogger(Matriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
  }
  
  public Workbook sintaxis(){
      try {
           return Workbook.getWorkbook(new File("MatrizSintaxis.xls"));
        } catch (IOException | BiffException ex) {
            Logger.getLogger(Matriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
  }
}
