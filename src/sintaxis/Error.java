/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintaxis;

/**
 *
 * @author CesarIvan
 */
public class Error {
    int id,linea;
    String Descrip,lexema,Tipo;

   
    Error(int id,int linea,String lexema,String d,String Tipo)
    {
        this.id = id;
        this.linea = linea;
        Descrip = d;
        this.lexema = lexema;
        this.Tipo = Tipo;
    }

    public Error(int id, String Descrip,String Tipo) {
        this.id = id;
        this.Descrip = Descrip;
        this.Tipo=Tipo;
    }

   
    
}

