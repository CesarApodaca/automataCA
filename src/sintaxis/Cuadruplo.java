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
public class Cuadruplo {
    String etiqueta, actividad, valor1, valor2,resultado;
    
    public Cuadruplo(){
        
    }
    
    public Cuadruplo(String etiqueta, String actividad, String valor1, String valor2, String resultado){
        this.etiqueta = etiqueta;
        this.actividad = actividad;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.resultado = resultado;
    }
}