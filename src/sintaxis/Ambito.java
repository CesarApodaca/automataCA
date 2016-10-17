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
public class Ambito {
    String lexema; //lexema del identificador
    String tipo; //tipo de identificador
    String clase; // clase del identificado
    int ambito; //ambito al que pertenece
    String tarr; //no disponible
    int darr;//no disponible
    int npar;//numero del parametro
    String tparr;//tparr Que ambito crea la funcion
    
    
    public Ambito(String lexema, String clase, int ambito)
    {
        this.lexema=lexema;
        this.clase=clase;
        this.ambito=ambito;
        
    }
    
    public Ambito(String lexema, String tipo, String clase, int ambito, int npar, String tparr)
    {
        this.lexema=lexema;
        this.tipo=tipo;
        this.clase=clase;
        this.ambito=ambito;
        this.npar=npar;
        this.tparr=tparr;
    }
    
    
    
}
