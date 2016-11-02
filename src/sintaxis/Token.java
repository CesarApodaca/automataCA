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
public class Token {
    int token,linea;
    String descrip, lexema;

    public Token(int token, int linea, String descrip,String lexema) {
        this.token = token;
        this.linea = linea;
        this.descrip = descrip;
        this.lexema = lexema;
    }
    
    public Token(int token, String descripcion) {
        this.token = token;
        this.descrip = descripcion;
    }

    
}
