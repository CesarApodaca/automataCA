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
public class Operando {
    String id, tipo, clase;
    
    public Operando(String id, String tipo)
    {
        this.id=id;
        this.tipo=tipo;
    }
    
    public Operando(String id, String tipo, String clase){
    this.id=id;
    this.tipo=tipo;
    this.clase=clase;
    }
    
}
