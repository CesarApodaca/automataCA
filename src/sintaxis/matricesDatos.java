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
public class matricesDatos {
    
    String[][] suma = {
        {"integer","real","single","integer","cadena","variant","integer","variant"},
        {"real","real","single","real","cadena","variant","real","variant"},
        {"single","single","single","single","cadena","variant","single","variant"},
        {"integer","real","single","variant","cadena","variant","char","variant"},
        {"cadena","cadena","cadena","cadena","cadena","variant","cadena","variant"},
        {"variant","variant","variant","variant","variant","boolean","boolean","variant"},
        {"integer","real","single","char","cadena","boolean","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"}
    };
    String[][] restaMul = {
        {"integer","real","single","variant","variant","variant","integer","variant"},
        {"real","real","single","variant","variant","variant","real","variant"},
        {"single","single","single","variant","variant","variant","single","variant"},
        {"variant","variant","variant","variant","variant","variant","char","variant"},
        {"variant","variant","variant","variant","variant","variant","cadena","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"integer","real","single","char","cadena","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant","variant"}
    };
    String[][] modulo = {
        {"integer","variant","variant","variant","variant","variant","integer","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"integer","variant","variant","variant","variant","variant","variant","variant"},
         {"variant","variant","variant","variant","variant","variant","variant","variant","variant"}

    };
    
    String [][] division = {
        {"real","real","single","variant","variant","variant","real","variant"},
        {"real","single","single","variant","variant","variant","real","variant"},
        {"single","single","single","variant","variant","variant","single","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"real","real","single","variant","variant","variant","variant","variant"},
         {"variant","variant","variant","variant","variant","variant","variant","variant","variant"}
    };
    
    String [][] comparacion = {
        {"boolean","boolean","boolean","boolean","variant","variant","boolean","variant","variant"},
        {"boolean","boolean","boolean","variant","variant","variant","boolean","variant","variant"},
        {"boolean","boolean","boolean","variant","variant","variant","boolean","variant","variant"},
        {"boolean","variant","variant","boolean","variant","variant","boolean","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","boolean","boolean","variant","variant"},
        {"boolean","boolean","boolean","boolean","variant","boolean","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant","variant"},
         {"variant","variant","variant","variant","variant","variant","variant","variant","variant"}

    };
    
    String [][] andyOr = {
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"},
        {"variant","variant","variant","variant","variant","boolean","boolean","variant"},
        {"variant","variant","variant","variant","variant","boolean","variant","variant"},
        {"variant","variant","variant","variant","variant","variant","variant","variant"}
         

    };
    public matricesDatos()
    {
        
    }
    
       public String leerSuma(Operando par1, Operando par2)
    {
        return suma[posicion(par1.tipo)][posicion(par2.tipo)];
    }
    public String leerRestayMult(Operando par1, Operando par2)
    {
        return restaMul[posicion(par1.tipo)][posicion(par2.tipo)];
    }
    public String leerDivision(Operando par1, Operando par2)
    {
         return division[posicion(par1.tipo)][posicion(par2.tipo)];
    }
    public String leerModulo(Operando par1, Operando par2)
    {
       return modulo[posicion(par1.tipo)][posicion(par2.tipo)];
    }
    
    public String leerComparacion(Operando par1, Operando par2)
    {
       return comparacion[posicion(par1.tipo)][posicion(par2.tipo)];
    }
    
    public String leerAndyOr(Operando par1, Operando par2)
    {
       return andyOr[posicion(par1.tipo)][posicion(par2.tipo)];
    }
    
        public int posicion(String tipo)
    {
        switch(tipo)
        {
            case "integer":
                return 0;
            case "real":
                return 1;
            case "single":
                return 2;
            case "char":
                return 3;
            case "cadena":
                return 4;
            case "boolean":
                return 5;
            case "variant":
                return 6;
            case "void":
                return 7;
            default:
                return -1;
        }
    }
}

