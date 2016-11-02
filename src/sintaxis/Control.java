/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintaxis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import jxl.Cell;
import jxl.Sheet;

/**
 *
 * @author CesarIvan
 */
public class Control {
    public String lexema, barridoLineas;
    public String[] lineasTexto, palabrasReservadas, erroresPresentes,barrido, palabrasPresentes;
    JTextArea areaTexto;
    Error[] ErroresSistema;
    palabraReservada[] listaPalabrasR;
    public Sheet hoja;
    public Cell celda;
    public String[][] tokens,errores;
    Matriz M;
    palabraReservada palR;
    ModeloTabla mT,mE,mB,mA,mC;
    public LinkedList<Error> ListaError;
    public LinkedList<Token> ListaToken;
    JTable tokenTabla,errorTabla,barridoTabla, ambitoTabla, conteoTabla;
    boolean noToken, pr;
    int aux =0;
    public int estado,posCol,contErr,contTok,contLog,contDes,contMat, contEntero, contPuntua, contComentarioV,contExponencial, contComentL, contCadena, contCarac, contDecimal, contAgrup,contMono,contAsig,contRel,contPaRes,contIde,contCar,contStr,contEnt,contReal,contDo,contCom,contBar,lineaTablaToken,lineaTablaError;
String [] producciones = {"801,802,803,2000,-34,838,804,-35,2001", "810", "1000", "805", "1000", "809,803", "1000",
        "-27,838,804", "1000", "-102,-1,-42,813,806,-27,807", "-32,-2,808,-33", "1000", "-1,-42,813,806,-27,807",
        "1000", "-28,-2,808", "1000", "-103,-1,2002,816,-42,813,800,2003", "-104,-1,2002,816,-42,813,800,2003", "-1,-25,812,-27,811",
        "-1,-25,812,-27,811", "1000", "-37", "-2", "-3", "-36", "-4", "-100", "-101", "-105", "-106", "-107", "-108", "-109",
        "-110", "-111", "-112", "-32,827,2016,815,2021,-33", "-28,827,2016,815", "1000", "-30,817,-31", "813,-42,-1,818", "1000",
        "-28,-1,818", "-27,813,-42,-1,818", "1000", "-25", "-5", "-40", "-12", "-44", "-8", "812", "-1,821", "-7,-1,2006,821",
        "-10,-1,2006,821", "-30,827,-31", "-14,-30,827,-31", "837", "2008,814,822", "2010,-30,823,2015,-31", "1000", "819,827,2004", "1000",
        "827,2014,824", "1000", "-28,827,2014,824", "1000", "829,826", "-16,829,2005,826", "1000", "825,828", "-17,825,2005,828", "1000",
        "831,830", "-20,831,2005,830", "-19,831,2005,830", "-26,831,2005,830", "-15,831,2005,830", "-22,831,2005,830", "-23,831,2005,830", "1000",
        "835,832", "-9,835,2005,832", "-6,835,2005,832", "1000", "820,834", "-43,820,2005,834", "1000", "833,836", "-11,833,2005,836",
        "-38,833,2005,836", "-13,833,2005,836", "1000", "-113,-30,827,2024,-28,827,2025,-31", "-114,-30,827,2024,-28,827,2009,2025,-31",
        "-115,-30,827,2024,-28,827,2022,-28,827,2023,-31", "-116,-30,827,2006,-31", "-117,-30,827,2024,-31", "-118,-30,827,2023,-31",
        "-119,-30,827,2022,-28,827,2022,-31", "-120,-30,827,2009,2022,-28,827,2022,-31", "-121,-30,827,2009,2022,-28,827,2022,-31", "-122,-30,827,2022,-31",
        "-123,-30,827,2025,839,-31", "-124,-30,-1,840,2009,2025,841,-31", "-125,827,2007,-126,838,842", "-34,838,843,-35", "-128,827,2013",
        "-129,2017,827,2018,844", "827", "-132,827,2007,-133,838", "-134,827,2011,-135,812,2012,845,-42,838,-136,846,-137", "-133,838,847,-132,827,2007",
        "1000", "-28,827,2025,839", "1000", "814", "1000", "-28,-1,840,2009,2025,841", "1000", "-127,838", "1000", "-27,838,843",
        "1000", "-130,827,2019,838", "-131,827,2020,838", "-28,812,2012,845", "1000", "812,2012,845,-42,838,-136,846",
        "-127,838,-136", "1000", "-27,827,847", "1000", "819,2009,827,2004"
    }; 

    //Variables de Ambito
    LinkedList<Integer> pilaAmbito;
    LinkedList<Ambito> tablaAmbito;
    int contAmbito;
    boolean banderaAmbito;
    String tipo;
    String clase;
    String clasePadre;
    int contPar;
    String creador;
    boolean esperoTipo;
    String [][] ArrAmbito, ArrConteo;
    int contVar=0, contFun=0, contProc=0, contPar1=0, contDimension;
    String tamArreglo;
    //semantica
    LinkedList<String> pilaOperadores;
    LinkedList<Operando> pilaOperando;
    LinkedList<String> pilaTemporal;
    matricesDatos mDatos;
    
    //semantica 2
    LinkedList<Ambito> auxA;
    int esperandoReturn, valorFor;
    boolean dentroFor;
    String tipoCase, tipoFor;
    
    
    
    public Control(JTextArea areaTexto)
    {
        
        //ambito
        pilaAmbito = new LinkedList();
        tablaAmbito = new LinkedList();
        contAmbito = 0;
        banderaAmbito = false;
        tipo = new String();
        clase = "const";
        clasePadre = new String();
        contPar = 0;
        creador = new String();
        esperoTipo = false;
        pilaAmbito.addFirst(0);
        contDimension=0;
        tamArreglo = "";
        
        //semantica
        pilaOperadores= new LinkedList();
        pilaOperando = new LinkedList();
        mDatos = new matricesDatos();
        pilaTemporal = new LinkedList();
        
        auxA= new LinkedList<Ambito>();
        esperandoReturn=0;
        valorFor=0;
        dentroFor=false;
        tipoCase="";
        tipoFor="";
        
        //cochinero
        this.areaTexto = areaTexto;
        lineasTexto = new String[3500];
        ErroresSistema = new Error[89];
        erroresPresentes = new String[3500];
        palabrasPresentes = new String[3500];
        palabrasReservadas = new String[15];
        barrido= new String[3500];
        listaPalabrasR = new palabraReservada[38];
        ListaError = new LinkedList();
        tokenTabla = new JTable();
        ListaToken = new LinkedList();
        barridoLineas = new String();
        estado = 0;
        contComentarioV=0;
       contErr = 0;
       contTok = 0;
       contEntero=0;
       pr=false;
       noToken = true;
       contLog=0;
       contCadena=0;
       contCar=0;
       contCarac=0;
        contComentL=0;
       contStr=0;
       contDecimal=0;
       M = new Matriz(1);
       hoja = M.hoja;
       contEnt=0;
       contReal=0;
       contDo=0;
       contAgrup=0;
       contCom=0;
       contDes=0;
       contPuntua=0;
       contMat=0;
       contMono=0;
       contAsig=0;
       contRel=0;
       contPaRes=0;
       contIde=0;
       contBar=0;
       errorTabla = new JTable();
       ambitoTabla = new JTable();
       conteoTabla = new JTable();
       iniciarArreglos();
    }
    
    
    public void abrirArchivo(File texto)
    {
        try {
            byte []b=new byte[1000];
            FileInputStream fis=new FileInputStream(texto);
            int i,contLineas=0;
            areaTexto.setText("");
            lexema="";
            contLineas=0;
            for(int e=0; e <= aux; e++)
            {
                lineasTexto[e]= "";
            }
            do{
             
            	 i=fis.read(b);
            	 for(int x=0;x<i;x++)
                 {   
                     if((char)b[x]!=13)
                     {
                        if(lexema==null)
                             lexema=""+(char)b[x];
                     else
                        lexema+=(char)b[x];
                     }
                     if(lexema!=null)
                     {
                         if(lexema.contains("\n"))   {
                             lineasTexto[contLineas] = lexema;
                       
                         }
                        else {
                            lineasTexto[contLineas] =lexema+"\n";
                   
                         }
                     }
                     else {
                        lineasTexto[contLineas] ="\n";
                   
                     }
                     if(b[x]==10)
                     { 
                         contLineas++;
                         lexema = "";
                     }    
                 }
            	}while(i>0);
            aux =contLineas;
            	fis.close();
            } 
            catch(FileNotFoundException e){
            	System.out.println ("Archivo no encontrado.");
            }
            catch(IOException e){
            	System.out.println ("Problema de conexion.");
            }	
       llenarArea();
    }
    
    
    public void abrirArchivoComp(JTextArea texto)
    {

            borrarTodo();
           String [] lineasTexto2 = texto.getText().split("\n");  
            for(int i=0; i < lineasTexto2.length;i++)
            {
                lineasTexto[i] = lineasTexto2[i];
                lineasTexto[i] = lineasTexto[i] + "\n";
            }
    }
     
    public void llenarArea()
    {
        if(areaTexto.getText().isEmpty()==false)
            areaTexto.setText("");
        for(int i=0;i<lineasTexto.length;i++)
        {
            if(lineasTexto[i]!=null)
                areaTexto.setText(areaTexto.getText()+lineasTexto[i]);
            else i=lineasTexto.length;
        }
    }
    
    public void lexico()
    {   
        String lexema1 ="";
       int j=0;
       int poss;
        if(lexema1!=null)
            lexema1 ="";
        for(int i=0;lineasTexto[i]!=null;i++)
        {
            char c=00;
            j=0;
            for(;j<lineasTexto[i].length();j++)
            {
                if(noToken==false)
                    j--;
               //   System.out.println("jota" + j);
                c = lineasTexto[i].charAt(j);
               // System.out.println("carac" + c);
                if(!(c==32&&estado==0)&&!(c==10&&estado==0)&&!(estado==0&&c==9))
                {
                    posCol = posColumnaLexico(c, estado);
                    poss = posCol;
                    if(posCol<=33)
                    {
                        celda = hoja.getCell(posCol,estado+1);
                        int datoCelda = Integer.parseInt(celda.getContents());
                        if(datoCelda>0)
                        {
                            if(c!=255||c!=32||c!=00||c!=9)//c!=9
                                if(c!=10) 
                                    lexema1+= c;
                          
                            
                            if(datoCelda>=500)
                            {
                                erroresPresentes[contErr] = (i+1)+"\t"+buscarError(datoCelda).id+"\tLexico\t"+buscarError(datoCelda).Descrip+"\t"+lexema1;
                                ListaError.add(new Error(buscarError(datoCelda).id,i+1,lexema1,buscarError(datoCelda).Descrip,"Lexico"));
                                barrido[contBar] = erroresPresentes[contErr];
                                 contBar++;
                                 estado = 0;
                                if(lexema1.length()==1)
                                    noToken = true;
                                else 
                                    noToken=false;
                                
                                if(buscarError(datoCelda).id == 505 && c == 10);
                                    noToken = true;
                                    
                              //  if(buscarError(datoCelda).id == 506);
                               //     noToken = false;
                                
                                lexema1 = "";
                                contErr++;
                            }
                            else 
                            {
                                estado = datoCelda;
                                noToken = true;
                            }
                        }
                        else
                        {
                            if(datoCelda==-1)
                            {
                                for(int o=0;o<listaPalabrasR.length;o++)
                                {
                                    if(listaPalabrasR[o].lexema.equalsIgnoreCase(lexema1))
                                    {   
                                        pr=true;
                                        lexema1 = listaPalabrasR[o].lexema;
                                        palR = listaPalabrasR[o];
                                        o=listaPalabrasR.length;
                                        
                                    }
                                }
                                if(pr) 
                                { 
                                    palabrasPresentes[contTok] = (i+1)+"\t-1\t"+lexema1;
                                    barrido[contBar]=(i+1)+"\t-1\t\t"+"Palabra Res."+"\t\t"+lexema1;
                                    ListaToken.add(new Token(palR.id,i+1,"Palabra Res.",lexema1));
                                    contBar++;
                                    contTok++;
                                    contPaRes++;
                                }
                                else
                                {
                                    palabrasPresentes[contTok] = (i+1)+"\t"+"-1"+"\t"+lexema1;
                                    barrido[contBar]=(i+1)+"\t"+"-1"+"\t\t"+"Identificador"+"\t\t"+lexema1;
                                    ListaToken.add(new Token(-1,i+1,"Identificador",lexema1));
                                    contBar++;
                                    contTok++;
                                    contIde++;
                                }
                            }
                           
                            else if (!(datoCelda == -10 || datoCelda == -7 || datoCelda == -5 || datoCelda == -8 || datoCelda == -12  || datoCelda == -40 || datoCelda == -26 || datoCelda == -15
                                || datoCelda == -19 || datoCelda == -22 || datoCelda == -17 || datoCelda == -16  || datoCelda == -44 || datoCelda == -42 || datoCelda == -43
                                || datoCelda == -30 || datoCelda == -31 || datoCelda == -32 || datoCelda == -33 || datoCelda == -34 || datoCelda == -35 
                                || datoCelda == -28 || datoCelda == -27  || datoCelda == -36 || datoCelda == -37 || datoCelda == -39 || datoCelda == -41))
                            {
                                palabrasPresentes[contTok] = (i+1)+"\t"+datoCelda+"\t"+lexema1;
                                barrido[contBar]=(i+1)+"\t"+datoCelda+"\t\t"+tokenDes(datoCelda,0)+"\t"+lexema1;
                                ListaToken.add(new Token(datoCelda,i+1,tokenDes(datoCelda,1),lexema1));

                                contBar++;
                                contTok++;

                            }
                            
                            if(c!=10)
                            noToken=false;
                        if(datoCelda == -10 || datoCelda == -7 || datoCelda == -5 || datoCelda == -8 || datoCelda == -12 || datoCelda == -40 || datoCelda == -26 || datoCelda == -15
                                || datoCelda == -19 || datoCelda == -22 || datoCelda == -17 || datoCelda == -16 || datoCelda == -44 || datoCelda == -42 || datoCelda == -43
                                || datoCelda == -30 || datoCelda == -31 || datoCelda == -32 || datoCelda == -33 || datoCelda == -34 || datoCelda == -35 
                                || datoCelda == -28 || datoCelda == -27  || datoCelda == -36 || datoCelda == -37 || datoCelda == -39 || datoCelda == -41){
                            
                            if(datoCelda !=-39){
                              lexema1+=c;
                                palabrasPresentes[contTok] = (i+1)+"\t"+datoCelda+"\t"+lexema1;
                                barrido[contBar]=(i+1)+"\t"+datoCelda+"\t\t"+tokenDes(datoCelda,0)+"\t"+lexema1;
                                ListaToken.add(new Token(datoCelda,i+1,tokenDes(datoCelda,1),lexema1));
                           //     System.out.println(""+ListaToken.getLast().linea + "token " +ListaToken.getLast().token);
                                contBar++;
                                contTok++;
                            }
                           else{
                            //  lexema1+=c;
                                palabrasPresentes[contTok] = (i+1)+"\t"+datoCelda+"\t"+lexema1;
                                barrido[contBar]=(i+1)+"\t"+datoCelda+"\t\t"+tokenDes(datoCelda,0)+"\t"+lexema1;
                                ListaToken.add(new Token(datoCelda,i+1,tokenDes(datoCelda,1),lexema1));
                               
                                contBar++;
                                contTok++;
                            }
                            
                        noToken=true;
                        }
                        estado=0;
                        lexema1="";
                        pr=false;
                        
                        }
                    }
                }
                 else noToken=true;
            }
        }
        sintaxis();
        tablas();
        noToken=true;
        
        
        /* prueba */
        
        
        
    }
    
    
    public void sintaxis()
    {
      //  LinkedList <Token> listaT = (LinkedList<Token>)ListaToken.clone(); 
        ListaToken.addLast(new Token(-50,"$"));
        LinkedList <Integer> produccion = new LinkedList<>();
        produccion.addFirst(800);
        produccion.addLast(-50);
        Matriz sint = new Matriz(2);
        Sheet sheet = sint.hoja;
        Cell celda;
        int v = 0;
        int renglon;
        int columna;
        int p=0;
        while(!ListaToken.isEmpty() &&  !produccion.isEmpty())
        {
            
            if(produccion.get(0) >= 2000)
            {
                switch(produccion.get(0))
                {
                    case 2000: banderaAmbito=true;
                        break; 
                    case 2001: banderaAmbito=false;
                        break;
                    case 2002: contAmbito++;
                               pilaAmbito.addFirst(contAmbito);
                               clasePadre=clase;
                               clase="par";
                        break;
                    case 2003: pilaAmbito.removeFirst();
                        break;
                    case 2004: Operando aux = asignacion();
                               if(!pilaOperando.get(1).tipo.equals(aux.tipo) && !aux.tipo.equals("variant"))
                               {
                                   ListaError.add(new Error(646, ListaToken.getFirst().linea, pilaOperando.get(1).id, "Error de Asignacion: No puedes guardar un "+pilaOperando.get(0).tipo+" en un "+pilaOperando.get(1).tipo, "Semantica"));
                               }
                               pilaOperadores.removeAll(pilaOperadores);
                               pilaOperando.removeAll(pilaOperando);
                        break;
                    case 2005: Operando aux1 = asignacionOper();
                               
                               if(aux1.tipo.equals("variant"))
                               {
                                   String lexemaL ="";
                                   if(pilaOperando.get(0).id.equals("temp"))
                                   {
                                       lexemaL = pilaOperando.get(1).id;
                                   }
                                   else
                                       lexemaL = pilaOperando.get(0).id;
                                   
                                   
                                   ListaError.add(new Error(647, ListaToken.getFirst().linea, lexemaL, "Error de ejecucion: No puedes hacer una operacion de "+ pilaOperadores.get(0) + " con " + pilaOperando.get(1).tipo + " y un " + pilaOperando.get(0).tipo , "Semantica"));
                               }
                               pilaOperadores.removeFirst();
                               pilaOperando.removeFirst();
                               pilaOperando.removeFirst();
                               pilaOperando.addFirst(aux1);
                        break;
                    case 2006: Tipo(new String [] {"integer"},636);
                                break;
                    case 2007: Tipo(new String [] {"boolean"},637);
                                break;
                    case 2008: Clase(new String [] {"arr"}, 638);
                                break;
                    case 2009: Clase(new String [] {"var", "par", "arr"}, 639);
                                break;
                    case 2010: Clase(new String [] {"fun", "proc"}, 640);
                                break;
                    case 2011: revisaCase(641);
                                break;
                    case 2012: Tipo(new String [] {tipoCase},642);
                                break;
                    case 2013: revisaReturn(643);
                                break;
                    case 2014: Parametros(644);
                                break;
                    case 2015: totalParametros(645);
                                break;
                    case 2016: Dimensiones(646);
                                break;
                    case 2017: dentroFor=true;
                                break;
                    case 2018: revisaFor(647);
                                break;
                    case 2019: revisaTo(648);
                                break;
                    case 2020: revisadownTo(649);
                                break;
                    case 2021: TotalDimensiones(650);
                                break;
                    case 2022: Tipo(new String[]{"cadena"},651);
                                break;
                    case 2023: Tipo(new String[]{"char"},652);
                                break;
                    case 2024: Tipo(new String[]{"file"},653);
                                break;
                    case 2025: TipoInverso(new String[]{"file","void"},654);
                                break;
                }
                produccion.removeFirst();
            }
            else
            {
                if(produccion.get(0)>0 && ListaToken.get(0).token<0)
            {
                if(ListaToken.getFirst().token==-41){
                    ListaToken.removeFirst();
                }else{
                    int xxx = ListaToken.get(0).token;
                    //System.out.println(""+xxx);
                    int columnaX = calcularColumnaS(ListaToken.get(0).token);
                    //System.out.println(""+columnaX);
                    int filaX = produccion.getFirst()-800;
                   // if(ListaToken.get(0).token )
                    p = Integer.parseInt(sheet.getCell(columnaX, filaX).getContents());
                    if(p >= 800)
                    {
                        if(p == 1000)
                        {
                            produccion.removeFirst();
                        }
                        else
                        {
                            produccion.removeFirst();
                            String [] prod = producciones[p-800].split(",");
                            for(int k=prod.length-1;k>=0;k--)
                            {
                                produccion.push(Integer.parseInt(prod[k]));
                 //               System.out.print(produccion);
                            }

                        }
                    }
                    else
                    {
                        if(p >= 600)
                        {
                            int lin=0;
                            Token t = ListaToken.get(0);
                            lin= t.linea;
                       //     System.out.println("hola");

                            ListaError.add(new Error(p, lin, t.lexema, buscarError(p).Descrip, "Sintaxis"));
                     //       System.out.println("error: "+p);
                            contErr++;
                            ListaToken.remove(0);

                        }
                    }
                }
            }
  //          if(produccion.getFirst()<0 && listaT.getFirst().token<0)
  //          {
               
   //         }
            else
            {
                if(produccion.get(0) == ListaToken.get(0).token)
                {
                    metoditoAmbito(ListaToken.get(0));
                  //  System.out.println("en sintaxis: "+ListaToken.get(0).lexema);
                    produccion.remove(0);
                    ListaToken.remove(0);
                }
                else
                {
                    if(ListaToken.getFirst().token==-41){
                        ListaToken.removeFirst();
                    }else{
                        int lin=0;
                        Token t = ListaToken.remove(0);
                        lin= t.linea;


                        System.out.println("linea"+ t.linea +" token" + t.token );
                        ListaError.add(new Error(700, lin, t.lexema, "Se esperaba " + convertirProd(produccion.get(0)), "Sintaxis"));
                        contErr++;
                        break;
                    }
                    
                }
            }
            }
           System.out.println("\nLista: ");
            for(Token t:ListaToken){
                System.out.print(t.lexema+" ");
            }
           System.out.println("\nPila: ");
            for(int t:produccion){
                System.out.print(t+" ");
           }
           // System.out.println("PilaOperando: ");
        //    for(Operando op:pilaOperando){
          //      System.out.print(op.id+"-"+op.tipo+" ");
        //    }
         //   System.out.println("PilaOperadores: ");
         //   for(String op:pilaOperadores){
           //     System.out.print(op+" ");
       //     }
        }
    }
    

    

    public Operando asignacion()
    {
        Operando aux = null;
        switch(pilaOperadores.getFirst())
        {
            case "=":
                aux = new Operando(pilaOperando.getFirst().id,pilaOperando.getFirst().tipo);
                if(dentroFor) 
                    valorFor=Integer.parseInt(pilaOperando.getFirst().id);
                break;
            case "+=":
                aux = new Operando("temp",mDatos.leerSuma(pilaOperando.get(0),pilaOperando.get(1)),"res");
                break;
            case "-=":
                aux = new Operando("temp",mDatos.leerRestayMult(pilaOperando.get(0),pilaOperando.get(1)),"res");
                break;
            case "*=":
                aux = new Operando("temp",mDatos.leerRestayMult(pilaOperando.get(0),pilaOperando.get(1)),"res");
                break;
            case "/=":
                aux = new Operando("temp",mDatos.leerDivision(pilaOperando.get(0),pilaOperando.get(1)),"res");
                break;
            case "%=":
                aux = new Operando("temp",mDatos.leerModulo(pilaOperando.get(0),pilaOperando.get(1)),"res");
                break;
        }
        return aux;
    }
    
    public Operando asignacionOper()
    {  // System.out.println(""+pilaOperadores.getFirst());
        Operando aux = null;
        switch(pilaOperadores.getFirst())
        {
            case "+":
                aux = new Operando(pilaOperando.getFirst().id,mDatos.leerSuma(pilaOperando.get(0),pilaOperando.get(1)));
                break;
            case "-":
                aux = new Operando(pilaOperando.getFirst().id,mDatos.leerRestayMult(pilaOperando.get(0),pilaOperando.get(1)));
                break;
            case "*":
                aux = new Operando(pilaOperando.getFirst().id,mDatos.leerRestayMult(pilaOperando.get(0),pilaOperando.get(1)));
                break;
            case "^":
            case "/":
                aux = new Operando(pilaOperando.getFirst().id,mDatos.leerDivision(pilaOperando.get(0),pilaOperando.get(1)));
                break;
            case "%":
                aux = new Operando(pilaOperando.getFirst().id,mDatos.leerModulo(pilaOperando.get(0),pilaOperando.get(1)));
                break;
            case "<":
            case "<=":
            case ">":
            case ">=":
            case "==":
            case "!=":
                 aux = new Operando("temp",mDatos.leerComparacion(pilaOperando.get(0),pilaOperando.get(1)));
                break;
            case "&&":
            case "||":
                aux = new Operando("temp",mDatos.leerAndyOr(pilaOperando.get(0),pilaOperando.get(1)));
                break;
        }
        return aux;
    }
    
    public boolean TipoInverso(String ar [], int nError){
        boolean temp=false;
        String tipos = ""; 
        for(int i=0;i<ar.length;i++){
            tipos+=ar[i];
            if(pilaOperando.getFirst().tipo.equals(ar[i])){
                temp=true;
                ListaError.add(new Error(nError,ListaToken.getFirst().linea,pilaOperando.getFirst().id,"Se esperaba una expresion de tipo " + tipos, "Semantica 3"));
                contErr++;
            }
        }
        pilaOperando.removeFirst();
        return temp;
    }
    
     public boolean Tipo(String ar [], int nError){
        boolean temp=true;
        String tipos = ""; 
        for(int i=0;i<ar.length;i++){
            tipos+=ar[i];
            if(pilaOperando.getFirst().tipo.equals(ar[i])){
                temp=false;
            }
        }
        if(temp)
            ListaError.add(new Error(nError,ListaToken.getFirst().linea,pilaOperando.getFirst().id,"Se esperaba una expresion de tipo " + tipos, "Semantica 2"));
        pilaOperando.removeFirst();
        return temp;
    }
    
        public boolean Clase(String ar [], int nError){
        boolean temp=true;
        String clases = ""; 
        for(int i=0;i<ar.length;i++){
            clases+=ar[i];
            if(pilaOperando.getFirst().clase.equals(ar[i])){
                temp=false;
            }
        }
        if(temp)
            ListaError.add(new Error(nError,ListaToken.getFirst().linea,pilaOperando.getFirst().id,"Se esperaba un id de clase " + clases, "Semantica 2"));
        return temp;
    }
     
        
    public boolean Clase(String ar [], int nError, Operando op ){
        boolean temp=true;
        String clases = ""; 
        for(int i=0;i<ar.length;i++){
            clases+=ar[i];
            if(op.clase.equals(ar[i])){
                temp=false;
            }
        }
        if(temp)
            ListaError.add(new Error(nError,ListaToken.getFirst().linea,op.id,"Se esperaba un id de clase " + clases, "Semantica 2"));
        return temp;
    }
    
        public void Parametros(int nError){
        contPar++;
        if(auxA.getFirst()!=null){
            if(contPar<=auxA.getFirst().npar){
                Ambito nuevo = buscarParametro(auxA.getFirst().lexema,contPar,auxA.getFirst().ambito+1);
                if(!nuevo.tipo.equals(pilaOperando.getFirst().tipo)){
                    ListaError.add(new Error(nError,ListaToken.getFirst().linea,auxA.getFirst().lexema,"El parametro " + contPar + "debe ser de tipo " + nuevo.tipo, "Semantica 2"));
                }
            }
            else{
                ListaError.add(new Error(nError,ListaToken.getFirst().linea,auxA.getFirst().lexema,"La funcion o procedimiento solo tiene " + auxA.getFirst().npar + "parametros", "Semantica 2"));
            }
        }
    }
        
            public void totalParametros(int nError){
        if(auxA.getFirst().npar!=contPar){
            ListaError.add(new Error(nError,ListaToken.getFirst().linea,auxA.getFirst().lexema,"Se esperaban " + auxA.getFirst().npar + "parametros", "Semantica 2"));
        }
        auxA.removeFirst();
        contPar=0;
    }
        
            public Ambito buscarParametro(String tpar, int npar, int ambito){
        Ambito aux = null;
        for(int i=0;i<tablaAmbito.size();i++){
            if(tablaAmbito.get(i).tparr==tpar /*.equals(tpar)*/ && tablaAmbito.get(i).npar==npar && tablaAmbito.get(i).ambito==ambito){
                aux=tablaAmbito.get(i);
            }
        }
        return aux;
    }
    
        public void Dimensiones(int nError){
        contDimension++;
        if(contDimension<=auxA.getFirst().darr){ 
            Operando nuevo = pilaOperando.getFirst();
            if(!Tipo(new String [] {"integer"}, nError)){
                if(nuevo.clase==null){
                    int valor=Integer.parseInt(nuevo.id);
                    int maximo=calcularTDimension();
                    if(valor>=maximo||valor<=-1){
                        ListaError.add(new Error(nError,ListaToken.getFirst().linea,auxA.getFirst().lexema,"El valor de la dimension no puede ser mayor que " + maximo + "ni menor que 0", "Semantica 2"));
                    }
                }
            }
        }
        else {
            ListaError.add(new Error(nError,ListaToken.getFirst().linea,auxA.getFirst().lexema,"El arreglo solo tiene " + auxA.getFirst().darr + "dimensiones", "Semantica 2"));
        }
    
        }
        
        public int calcularTDimension(){
        int tAux=0,dAux=0;
        String tArr=auxA.getFirst().tarr;
        String valor="";
        for(int i=0;i<tArr.length();i++){
            if(tArr.charAt(i)==','){
                dAux++;
                if(dAux==contDimension)
                    tAux=Integer.parseInt(valor);
                else
                    valor="";
            }
            else
                valor+=tArr.charAt(i);
        }
        dAux++;
        if(dAux==contDimension)
            tAux=Integer.parseInt(valor);
        return tAux;
    }
     
        public void revisaCase(int nError){
        tipoCase=pilaOperando.getFirst().tipo;
        if(Tipo(new String []{"integer","char","cadena"},nError)){
            tipoCase="";
        }
    }
    
    public void revisaReturn(int nError){
        if(!auxA.isEmpty()){
        if(auxA.getFirst()!=null && auxA.getFirst().clase.equals("fun")){
            Tipo(new String [] {auxA.getFirst().tipo},nError);
            esperandoReturn--;
            auxA.removeFirst();
        }
        else{
            ListaError.add(new Error(nError,ListaToken.getFirst().linea,auxA.getFirst().lexema,"Solo las funciones pueden tener return", "Semantica 2"));
        }
      }
    }        
       
        public void revisadownTo(int nError){
        Operando valorTo= pilaOperando.getFirst();
        if(!Tipo(new String []{"integer"},nError)){
            if(valorTo.clase==null){
                int valor=Integer.parseInt(valorTo.id);
                if(valor>=valorFor){
                    ListaError.add(new Error(nError,ListaToken.getFirst().linea,"downto","El valor debe ser menor que al iniciar el for", "Semantica 2"));
                }
            }
        }
        valorFor=-100;
    }
    
    public void revisaFor(int nError){
        if(tipoFor.equals("integer")){
            if(valorFor==-100){
                ListaError.add(new Error(nError,ListaToken.getFirst().linea,auxA.getFirst().lexema,"Se esperaba una asignacion al iniciar el for", "Semantica 2"));
                if(!pilaOperando.isEmpty()){
                    pilaOperando.clear();
                }
            }
        }
        else{
            ListaError.add(new Error(nError,ListaToken.getFirst().linea,"for","Se esperaba una expresión integer al iniciar el for", "Semantica 2"));
        }
        dentroFor=false;
        tipoFor="";
    }
    
        public void TotalDimensiones(int nError){
        if(auxA.getFirst().darr!=contDimension){
            ListaError.add(new Error(nError,ListaToken.getFirst().linea,auxA.getFirst().lexema,"Se esperaba " + auxA.getFirst().darr + "dimensiones", "Semantica 2"));
        }
        auxA.removeFirst();
        contDimension=0;
    }
    
    public void revisaTo(int nError){
        Operando valorTo= pilaOperando.getFirst();
        if(!Tipo(new String []{"integer"},nError)){
            if(valorTo.clase==null){
                int valor=Integer.parseInt(valorTo.id);
                if(valor<=valorFor){
                    ListaError.add(new Error(nError,ListaToken.getFirst().linea,"to","El valor debe ser mayor que al iniciar el for", "Semantica 2"));
                }
            }
        }
        valorFor=-100;
    }
    
    
    
    public void metoditoAmbito(Token tokensito)
    {   //System.out.println("es lexema: "+tokensito.lexema);
        switch(tokensito.token)
        {
            case -1:
                if(!banderaAmbito) //Area de Declaracion
                {
                    
                    if(busquedaAmbito(tokensito.lexema, pilaAmbito.getFirst()) != null)
                    {
                         ListaError.add(new Error(640, tokensito.linea, tokensito.lexema, "Variable Duplicada", "Ambito"));
                    }
                    else if(clase.equals("par"))
                    {
                        contPar++;
                        tablaAmbito.add(new Ambito(tokensito.lexema,tipo,clase,pilaAmbito.getFirst(),contPar,creador));
               //         System.out.println("PRUEBITITITITITITITA");
                    }
                    else
                    {
                        Ambito temp = new Ambito(tokensito.lexema,clase,pilaAmbito.getFirst());
                        tablaAmbito.add(temp);
                        esperoTipo=true;
                        if(!clase.equals("var")){
                            creador = tokensito.lexema;
                        if(clase.equals("fun")){
                          esperandoReturn++;
                          auxA.addFirst(temp);
                        }
                       }
                    }
                }
                else
                {
                    boolean declarado=false;
                    String tipo ="";
                    String clase="";
                    Ambito aux=null;
                    for(int i:pilaAmbito)
                    {
                        if(busquedaAmbito(tokensito.lexema,i) != null)
                        {
                            declarado=true;
                            tipo = busquedaAmbito(tokensito.lexema,i).tipo;
                            clase=busquedaAmbito(tokensito.lexema,i).clase;
                            aux=busquedaAmbito(tokensito.lexema,i);
                            break;
                        }
                    }
                    if(!declarado)
                    {
                        ListaError.add(new Error(641, tokensito.linea, tokensito.lexema, "Variable no Declarada", "Ambito"));
                    }
                    else
                    {
                        pilaOperando.addFirst(new Operando(tokensito.lexema,tipo));
                    }
                    if(clase.equals("arr")||clase.equals("fun")||clase.equals("proc")){
                                    auxA.addFirst(aux);
                    }
                }
                break;
            case -31:
                    if(!banderaAmbito)
                    {
                        clase=clasePadre;
                    }
                break;
            case -102:
                    clase="var";
                break;
            case -103:
                    clase="fun";
                break;
            case -104:
                    clase="proc";
                break;
            case -105:
            case -106:
            case -107:
            case -108:
            case -109:
            case -110:
            case -111:
            case -112:
                if(!banderaAmbito)
                {
                    if(clase.equals("par"))
                    {
                        tipo=tokensito.lexema;
                    }
                    else if(esperoTipo)
                    {   
                        esperoTipo = false;
                        if(clase.equals("var"))
                        {
                            Ambito aux = esperando();
                            if(aux != null)
                            {
                                aux.tipo = tokensito.lexema;
                            }
                        }
                        else
                        {
                            Ambito aux = esperando();
                            aux.tipo = tokensito.lexema;
                            aux.npar = contPar;
                            aux.tparr = pilaAmbito.getFirst()+"";
                            contPar=0;
                            clase="const";
                        }
                        esperoTipo=false;
                    }
                }
                break;
            case -32://corchete [
                if(!banderaAmbito)
                {
                    clasePadre=clase;
                    clase="arr";
                }
                break;
            case -33://corchete ]
                if(!banderaAmbito)
                {
                    if(clase.equals("arr"))
                    {
                        Ambito aux = ultimoVar();
                        aux.clase=clase;
                        aux.darr=contDimension;//contador de dimensiones
                        aux.tarr=tamArreglo;
                        clase=clasePadre;
                        contDimension=0;
                        tamArreglo="";
                    }
                }
                break;
            case -2:// CE
                if(!banderaAmbito)
                {
                    if(clase.equals("arr"))
                    {
                        contDimension++;
                        if(tamArreglo.isEmpty())
                        {
                            tamArreglo = tokensito.lexema;
                        }
                        else
                        {
                            tamArreglo+=","+tokensito.lexema;
                        }
                    }
                }
            case -3://CR
            case -4://CB
            case -36://CS
            case -37://CC
            case -100://true
            case -101: //false
                if(!banderaAmbito)
                {
                    if(esperoTipo)
                    {
                       esperoTipo=false;
                       esperando().tipo=tipoImplicito(tokensito.token);
                    }
                }
                else
                {
                    pilaOperando.addFirst(new Operando(tokensito.lexema,tipoImplicito(tokensito.token)));
                }
                
                break;
            case -6: //+
            case -9: //-
            case -11: //*
            case -38: // /
            case -13: // %
            case -25: // =
            case -5: // +=
            case -8: // -=
            case -12: // *=
            case -40: // /=
            case -44:// %=
            case -16:// &&
            case -17:// ||
            case -43:// ^
            case -20:// <     
            case -19:// <=    
            case -23:// >    
            case -22:// >=
            case -26:// ==
            case -15:// !=
                
                if(banderaAmbito)
                {
                    pilaOperadores.addFirst(tokensito.lexema);
                }
                break;
        }
    }
    
    public String tipoImplicito(int tokeo)
    {
        switch(tokeo)
        {
            case -2:
                return "integer";
                
            case -3://CR
                return "real";
            case -4://CB
                return "single";
            case -36://CS
                return "cadena";
            case -37://CC
                return "char";
            case -100://true
                return "boolean";
            case -101: //false
                return "boolean";
           case -113://put
                return "boolean";
            case -114://get
                return "boolean";
            case -115://open
                return "boolean";
            case -116://asc
                return "char";
            case -117: //reset
                return "boolean";
            case -118: //val
                return "integer";
            case -119://strcmp
                return "integer";
            case -120://strcpy
                return "integer";
            case -121://strcat
                return "boolean";
            case -122://strlen
                return "integer";
            case -123://print
                return "void";
            case -124://rd
                return "void";
        }
        return "";
    }
    
    
    public Ambito ultimoVar()
    {
        Ambito auxiliar1= null;
        for(int i=tablaAmbito.size()-1;i >= 0;i--)
        {
            if(tablaAmbito.get(i).clase.equals("var"))
            {
                auxiliar1 = tablaAmbito.get(i);
                break;
            }
        }
        return auxiliar1;
    }
    
    public Ambito busquedaAmbito(String lexema, int pilita)
    {
        Ambito aux = null;
        boolean existe=false;
        if(!tablaAmbito.isEmpty()){
            for(Ambito a: tablaAmbito)
            {
                if(a.lexema.equals(lexema) && a.ambito == pilita)
                {
                    existe=true;
                    aux = a;

                }
            }
        }
        
        return aux;
    }
    
    public Ambito esperando()
    {
        Ambito a = null;
        for(Ambito am: tablaAmbito)
        {
            if(am.tipo == null)
            {
                a=am;
            }
        }
        return a;
    }
    
    
    
    private String convertirProd(Integer first) {
        switch( first )
        {
           case -25 : return "="; 
           case -5: return "+=";
           case -6: return "+";
           case -7: return "++";
           case -8: return "-=";
           case -9: return "-";
           case -10: return "--"; 
           case -11: return "*";
           case -12: return "*=";
           case -13: return "%";
           case -14: return "!";
           case -15: return "!=";
           case -16: return "&&";
           case -17: return "||";
           case -19: return "<=";
           case -20: return "<";
           case -22: return ">=";
           case -23: return ">"; 
           case -26: return "==";    
           case -27: return ";";  
           case -28: return ",";   
           case -30: return "(";  
           case -31: return ")";
           case -32: return "[";
           case -33: return "]";
           case -34: return "{";
           case -35: return "}"; 
           case -38: return "/";
           case -40: return "/="; 
           case -42: return ":";   
           case -43: return "^";  
           case -44: return "%=";    
        }
        return "";
    }
    
    

    
    public void tablas()
    {
        if(ListaToken.isEmpty()!=true) 
            
        {
             tokens= new String[ListaToken.size()][3];
            for(int i=0;i<ListaToken.size()&&ListaToken.get(i).token!=51;i++)
            {
                    tokens[i][0] = ListaToken.get(i).token+"";
                    tokens[i][1] = ListaToken.get(i).descrip;
                    tokens[i][2] = ListaToken.get(i).linea+"";
            }
            mT = new ModeloTabla(1,tokens);
            tokenTabla.setModel(mT);
        }
        if(ListaError.isEmpty()!=true)
        {
            errores= new String[ListaError.size()][5];
            for(int i=0;i<ListaError.size();i++)
            {
                    errores[i][0] = ListaError.get(i).id+"";
                    errores[i][1] = ListaError.get(i).Descrip;
                    errores[i][2] = ListaError.get(i).linea+"";
                    errores[i][3] = ListaError.get(i).lexema;
                    errores[i][4] = ListaError.get(i).Tipo;
            }
            mE = new ModeloTabla(2,errores);
            errorTabla.setModel(mE);
        }
        if(tablaAmbito.isEmpty()!=true)
        {
            ArrAmbito = new String[tablaAmbito.size()][8];
            for(int i=0;i<tablaAmbito.size();i++)
            {
                    ArrAmbito[i][0] = tablaAmbito.get(i).lexema;
                    ArrAmbito[i][1] = tablaAmbito.get(i).tipo;
                    ArrAmbito[i][2] = tablaAmbito.get(i).clase;
                    ArrAmbito[i][3] = tablaAmbito.get(i).ambito+"";
                    ArrAmbito[i][4] = tablaAmbito.get(i).tarr;
                    ArrAmbito[i][5] = tablaAmbito.get(i).darr+"";
                    ArrAmbito[i][6] = tablaAmbito.get(i).npar+"";
                    ArrAmbito[i][7] = tablaAmbito.get(i).tparr;
            }
            mA = new ModeloTabla(3,ArrAmbito);
            ambitoTabla.setModel(mA);
        }
        if(tablaAmbito.isEmpty()!=true)
        {
            ArrConteo = new String[contAmbito+2][5];
            for(int i=0;i<contAmbito+1;i++)
            {       
                    
                    ArrConteo[i][0] = i+"";
                    ArrConteo[i][1] = conteoAmbito(i,"var")+"";
                    ArrConteo[i][2] = conteoAmbito(i,"fun")+"";
                    ArrConteo[i][3] = conteoAmbito(i,"proc")+"";
                    ArrConteo[i][4] = conteoAmbito(i,"par")+"";
            }
                    ArrConteo[contAmbito+1][0] = "Total";
                    ArrConteo[contAmbito+1][1] = contVar+"";
                    ArrConteo[contAmbito+1][2] = contFun+"";
                    ArrConteo[contAmbito+1][3] = contProc+"";
                    ArrConteo[contAmbito+1][4] = contPar1+"";
            mC = new ModeloTabla(4,ArrConteo);
            conteoTabla.setModel(mC);
        }
    }
    
     public int conteoAmbito(int ambito, String palabra)
    {
       
        int cont=0;
        if(!tablaAmbito.isEmpty()){
            for(Ambito a: tablaAmbito)
            {   
                String tipillo = a.clase;
                if(tipillo != null)
                {
                    if( a.ambito == ambito && tipillo.equals(palabra))
                    {   
                        cont++;
                        if(palabra.equals("var"))
                            contVar++;
                        if(palabra.equals("proc"))
                            contProc++;
                        if(palabra.equals("fun"))
                            contFun++;
                        if(palabra.equals("par"))
                            contPar1++;
                        
                    }
                }
            }
        }
        
        return cont;
    }
    
    
    
    public int calcularColumnaS(int n){
            switch (n){
                case -1: return 0;
                case -2: return 1;
                case -3: return 2;
                case -4: return 3;
                case -5: return 33;
                case -6: return 6; 
                case -7: return 11;
                case -8: return 34;
                case -9: return 7;
                case -10: return 12;
                case -11: return 8;
                case -12: return 35;
                case -13: return 10;
                case -14: return 13;
                case -15: return 22;
                case -16: return 14;
                case -17: return 15;
                //case -18: return 0;
                case -19: return 18;
                case -20: return 17;
                //case -21: return 17;
                case -22: return 20;
                case -23: return 19;
                //case -24: return 17;
                case -25: return 32;
                case -26: return 21;
                case -27: return 23;
                case -28: return 24;
                //case -29: return ;
                case -30: return 26;  
                case -31: return 27;
                case -32: return 28;
                case -33: return 29;
                case -34: return 30;
                case -35: return 31;
                case -36: return 4;
                case -37: return 5;
                case -38: return 9;
                //case -39: return ;
                case -40: return 36;
                case -41: return 17;
                case -42: return 25;
                case -43: return 16;
                case -44: return 37;    
                case -100: return 38;
                case -101: return 39;
                case -102: return 40;
                case -103: return 41;
                case -104: return 42;
                case -105: return 43;
                case -106: return 44;
                case -107: return 45;
                case -108: return 46;
                case -109: return 47;            
                case -110: return 48;
                case -111: return 49;
                case -112: return 50;    
                case -113: return 51;    
                case -114: return 52;    
                case -115: return 53;
                case -116: return 54;    
                case -117: return 55;    
                case -118: return 56;    
                case -119: return 57;    
                case -120: return 58;    
                case -121: return 59;    
                case -122: return 60;
                case -123: return 61;    
                case -124: return 62;    
                case -125: return 63;    
                case -126: return 64;    
                case -127: return 65;
                case -128: return 66;    
                case -129: return 67;    
                case -130: return 68;    
                case -131: return 69;    
                case -132: return 70;    
                case -133: return 71;    
                case -134: return 72;    
                case -135: return 73;    
                case -136: return 74;    
                case -137: return 75;    

                case -50: return 76;

            
        }
        return 0;
    }
    
    
    public String tokenDes(int t,int j)
    {
            if(t == -6 || t == -9 || t == -11 || t == -38 || t == -13 || t == -43)
            {  if(j==0)
                contMat++;
                return "Operador Mat.";
            }
            else if(t == -7 || t== -10)
            {if(j==0)
                contMono++;
                return "Monogamo.";
            }
            else if(t == -5 || t == -8 || t == -12 || t == -25 || t == -40)
            {if(j==0)
                contAsig++;
                return "Asignacion";
            }
            else if(t == -20 || t == -19 || t == -23 || t == -22 || t == -40 || t == -26 || t == -15)
            {if(j==0)
                contRel++;
                return "Relacionales.";
            }
            else if(t == -44)
            {if(j==0)
                contRel++;
                return "Resto de division.";
            }
            else if(t == -14 || t == -16 || t == -17)
            {if(j==0)
                contLog++;
                return "Logicos.";
            }
            else if(t == -21 || t == -24)
            {if(j==0)
                contDes++;
                return "Desplazamiento";
            }
            else if(t == -30 || t == -31 || t == -32 || t == -33 || t == -34 || t == -35)
            {if(j==0)
                contAgrup++;
                return "Agrupacion";
            }
            else if(t == -27 || t == -28 || t == -42)
            {if(j==0)
                contPuntua++;
                return "Signo de puntuacion";
            }
             else if(t == -2)
            {if(j==0)
                contEntero++;
                return "Entero";
            }
              else if(t == -3)
            {if(j==0)
                contDecimal++;
                return "Decimal";
            }
               else if(t == -36)
            {if(j==0)
                contCadena++;
                return "Cadena";
            }
             else if(t == -37)
            {if(j==0)
                contCarac++;
                return "Caracter";
            }
            else if(t == -39)
            {if(j==0)
                contComentL++;
                return "Comentario Lineal";
            }
            else if(t == -4)
            {if(j==0)
                contExponencial++;
                return "Exponencial";
            }
            else if(t == -41)
            {if(j==0)
                contComentarioV++;
                return "Comentario de Varias lineas";
            }

        

        return "";
    }
    
    
    public void iniciarArreglos()
    {
        ErroresSistema[0] = new Error(500,"Caracter no esperado.","Lexico");
         ErroresSistema[1] = new Error(501,"Se esperaba digito.","Lexico");
         ErroresSistema[2] = new Error(502,"Se esperaba digito o guion.","Lexico");
         ErroresSistema[3] = new Error(503,"Se esperaba un &","Lexico");
         ErroresSistema[4] = new Error(504,"Se esperaba un |","Lexico");
         ErroresSistema[5] = new Error(505,"Se esperaba una comilla simple, comilla doble u otra cosa","Lexico");
         ErroresSistema[6] = new Error(506,"Se esperaba una comilla doble u otra cosa.","Lexico");
         ErroresSistema[7] = new Error(600,"Error de sincronización","Sintaxis");
         ErroresSistema[8] = new Error(601,"Se esperaba un identificador,{,var,proc o fun","Sintaxis");
         ErroresSistema[9] = new Error(602,"Se esperaba {,var,proc o fun","Sintaxis");
         ErroresSistema[10] = new Error(603,"Se esperaba {,proc o fun","Sintaxis");
         ErroresSistema[11] = new Error(604,"Se esperaba ; o }","Sintaxis");
         ErroresSistema[12] = new Error(605,"Se esperaba var","Sintaxis");
         ErroresSistema[13] = new Error(606,"Se esperaba ; o [","Sintaxis");
         ErroresSistema[14] = new Error(607,"Se esperaba un identificador,{,proc o fun","Sintaxis");
         ErroresSistema[15] = new Error(608,"Se esperaba ,o ]","Sintaxis");
         ErroresSistema[16] = new Error(609,"Se esperaba proc o fun","Sintaxis");
         ErroresSistema[17] = new Error(610,"Se esperaba un identificador","Sintaxis");
         ErroresSistema[18] = new Error(611,"Se esperaba una constante","Sintaxis");
         ErroresSistema[19] = new Error(612,"Se esperaba un tipo de dato","Sintaxis");
         ErroresSistema[20] = new Error(613,"Se esperaba [","Sintaxis");
         ErroresSistema[21] = new Error(614,"Se esperaba (","Sintaxis");
         ErroresSistema[22] = new Error(615,"Se esperaba ) o un tipo de dato","Sintaxis");
         ErroresSistema[23] = new Error(616,"Se esperaba ; ,o )","Sintaxis");
         ErroresSistema[24] = new Error(617,"Se esperaba un signo de asignación","Sintaxis");
         ErroresSistema[25] = new Error(618,"Se esperaba un identificador,una constante,++,--,(,!,put,get,open,asc,reset,val,strcmp,strcpy,strcat o strlen","Sintaxis");
         ErroresSistema[26] = new Error(619,"Se esperaba un identificador,una constante,un operador matematico,un operador monogamo,un operador logico,un operador relacional,un signo de puntuacion,un signo de agrupacion,put,get,open,asc,reset,val,strcmp,strcpy,strcat,strlen,print,rd,if,then,else,return,for,to,downto,while,do,case,of o brk","Sintaxis");
         ErroresSistema[27] = new Error(620,"Se esperaba un identificador,una constante,un operador matematico,un operador monogamo,un operador logico,un operador relacional,un signo de puntuacion,un signo de agrupacion,un signo de asignacion,put,get,open,asc,reset,val,strcmp,strcpy,strcat,strlen,print,rd,if,then,else,return,for,to,downto,while,do,case,of o brk","Sintaxis");
         ErroresSistema[28] = new Error(621,"Se esperaba un identificador,una constante,un operador monogamo,!,(,),put,get,open,asc,reset,val,strcmp,strcpy,strcat o strlen","Sintaxis");
         ErroresSistema[29] = new Error(622,"Se esperaba ,o )","Sintaxis");
         ErroresSistema[30] = new Error(623,"Se esperaba un identificador,una constante,un operador matematico,un operador monogamo,un operador logico,un operador relacional,un signo de puntuacion,un signo de agrupacion excepto [,put,get,open,asc,reset,val,strcmp,strcpy,strcat,strlen,print,rd,if,then,else,return,for,to,downto,while,do,case,of o brk","Sintaxis");
         ErroresSistema[31] = new Error(624,"Se esperaba put,get,open,asc,reset,val,strcmp,strcpy,strcat o strlen","Sintaxis");
         ErroresSistema[32] = new Error(625,"Se esperaba un identificador,una constante,un operador monogamo,!,; (,{,},put,get,open,asc,reset,val,strcmp,strcpy,strcat,strlen,print,rd,if,else,return,for,while,do,case,brk","Sintaxis");
         ErroresSistema[33] = new Error(626,"Se esperaba [","Sintaxis");
         ErroresSistema[34] = new Error(627,"Se esperaba ," ,"Sintaxis");
         ErroresSistema[35] = new Error(628,"Se esperaba else","Sintaxis");
         ErroresSistema[36] = new Error(629,"Se esperaba ;","Sintaxis");
         ErroresSistema[37] = new Error(630,"Se esperaba to o downto","Sintaxis");
         ErroresSistema[38] = new Error(631,"Se esperaba una constante,true,false o else","Sintaxis");
         ErroresSistema[39] = new Error(632,"Error","Sintaxis");
         ErroresSistema[40] = new Error(700,"Error","Sintaxis");
         
         listaPalabrasR [0] = new palabraReservada(-100,"true"); 
         listaPalabrasR [1] = new palabraReservada(-101,"false"); 
         listaPalabrasR [2] = new palabraReservada(-102,"var");
         listaPalabrasR [3] = new palabraReservada(-103,"fun");
         listaPalabrasR [4] = new palabraReservada(-104,"proc");
         listaPalabrasR [5] = new palabraReservada(-105,"char");
         listaPalabrasR [6] = new palabraReservada(-106,"integer");
         listaPalabrasR [7] = new palabraReservada(-107,"real");
         listaPalabrasR [8] = new palabraReservada(-108,"cadena");
         listaPalabrasR [9] = new palabraReservada(-109,"single");
         listaPalabrasR [10] = new palabraReservada(-110,"file");
         listaPalabrasR [11] = new palabraReservada(-111,"void");
         listaPalabrasR [12] = new palabraReservada(-112,"boolean");
         listaPalabrasR [13] = new palabraReservada(-113,"put");
         listaPalabrasR [14] = new palabraReservada(-114,"get");
         listaPalabrasR [15] = new palabraReservada(-115,"open");
         listaPalabrasR [16] = new palabraReservada(-116,"asc");
         listaPalabrasR [17] = new palabraReservada(-117,"reset");
         listaPalabrasR [18] = new palabraReservada(-118,"val");
         listaPalabrasR [19] = new palabraReservada(-119,"strcmp");
         listaPalabrasR [20] = new palabraReservada(-120,"strcpy");
         listaPalabrasR [21] = new palabraReservada(-121,"strcat");
         listaPalabrasR [22] = new palabraReservada(-122,"strlen");
         listaPalabrasR [23] = new palabraReservada(-123,"print");
         listaPalabrasR [24] = new palabraReservada(-124,"rd");
         listaPalabrasR [25] = new palabraReservada(-125,"if");
         listaPalabrasR [26] = new palabraReservada(-126,"then");
         listaPalabrasR [27] = new palabraReservada(-127,"else");
         listaPalabrasR [28] = new palabraReservada(-128,"return");
         listaPalabrasR [29] = new palabraReservada(-129,"for");
         listaPalabrasR [30] = new palabraReservada(-130,"to");
         listaPalabrasR [31] = new palabraReservada(-131,"downto");
         listaPalabrasR [32] = new palabraReservada(-132,"while");
         listaPalabrasR [33] = new palabraReservada(-133,"do");
         listaPalabrasR [34] = new palabraReservada(-134,"case");
         listaPalabrasR [35] = new palabraReservada(-135,"of");
         listaPalabrasR [36] = new palabraReservada(-136,"brk");
         listaPalabrasR [37] = new palabraReservada(-137,"end");
         //If, then, while, do, for, func, Proc Switch, int, String, float, char, Boolean, true, false//
    }
    
    
    public Error buscarError(int d)
    {
        for(int i=0;i<ErroresSistema.length;i++)
        {
            if(ErroresSistema[i].id==d)
                return ErroresSistema[i];
        }
        return null;
    }   
    
    
    public int posColumnaLexico(char c, int est)
    {
       
            switch(c)
            {
                 case '0' : return 3;
                 case '1' : return 3;
                 case '2' : return 3;
                 case '3' : return 3;
                 case '4' : return 3;
                 case '5' : return 3;
                 case '6' : return 3;
                 case '7' : return 3;
                 case '8' : return 3;
                 case '9' : return 3;
                 case '/' : return 15;
                 case '*' : return 13;
                 case '-' : return 14;
                 case '+' : return 11;
                 case '=' : return 12;
                 case '.' : return 6;
                 case '(' : return 24;
                 case ')' : return 25;
                 case '[' : return 26;
                 case ']' : return 27;
                 case '{' : return 28;
                 case '}' : return 29;
                 case '_' : return 4;
                 case '~' : return 31;
                 case '%' : return 16;
                 case '!' : return 17;
                 case '&' : return 18;
                 case '|' : return 19;
                 case '<' : return 20;
                 case '>' : return 21;
                 case 'a' : return 1;
                 case 'b' : return 1;
                 case 'c' : return 1;
                 case 'd' : return 1;
                 case 'e' : if(estado == 2 || estado == 4) 
                                return 7;
                            else
                                return 1;
                 case 'f' : return 1;
                 case 'g' : return 1;
                 case 'h' : return 1;
                 case 'i' : return 1;
                 case 'j' : return 1;
                 case 'k' : return 1;
                 case 'l' : return 1;
                 case 'm' : return 1;
                 case 'n' : return 1;
                 case 'o' : return 1;
                 case 'p' : return 1;
                 case 'q' : return 1;
                 case 'r' : return 1;
                 case 's' : return 1;
                 case 't' : return 1;
                 case 'u' : return 1;
                 case 'v' : return 1;
                 case 'x' : return 1;   
                 case 'w' : return 1;
                 case 'y' : return 1;
                 case 'z' : return 1;
                 case 'A' : return 2;
                 case 'B' : return 2;
                 case 'C' : return 2;
                 case 'D' : return 2;
                 case 'E' : if(estado == 2 || estado == 4) 
                                return 7;
                            else
                                return 2;
                 case 'F' : return 2;
                 case 'G' : return 2;
                 case 'H' : return 2;
                 case 'I' : return 2;
                 case 'J' : return 2;
                 case 'K' : return 2;
                 case 'L' : return 2;
                 case 'M' : return 2;
                 case 'N' : return 2;
                 case 'O' : return 2;
                 case 'P' : return 2;
                 case 'Q' : return 2;
                 case 'R' : return 2;
                 case 'S' : return 2;
                 case 'T' : return 2;
                 case 'U' : return 2;
                 case 'V' : return 2;
                 case 'W' : return 2;
                 case 'X' : return 2;
                 case 'Y' : return 2;
                 case 'Z' : return 2;
                 case ';' : return 22;
                 case ',' : return 23;
                 case  39 : return 10;
                 case '"' : return 9;
                 case 'ñ' : return 1;
                 case 'Ñ' : return 2;
                 case 10  : return 30;
                 case '^' : return 32;
                 case ':' : return 33;
                 //case  32 : return 100;    
                 default  : return 5; 
            }
        
    }
    
    public void borrarTodo()
    { //palabrasReservadas, erroresPresentes,barrido, palabrasPresentes
        for(int e=0; e<lineasTexto.length; e++)
            {
                lineasTexto[e]= null;
            }
        for(int e=0; e<erroresPresentes.length; e++)
            {
                erroresPresentes[e]= null;
            }
        for(int e=0; e<barrido.length; e++)
            {
                barrido[e]= null;
            }
        for(int e=0; e<palabrasPresentes.length; e++)
            {
                palabrasPresentes[e]= null;
            }
        
            estado = 0;
         contComentarioV=0;
       contErr = 0;
       lexema="";
     //  lexema1="";
       contTok = 0;
       contEntero=0;
       pr=false;
       noToken = true;
       contLog=0;
       contCadena=0;
       contCar=0;
       contCarac=0;
        contComentL=0;
       contStr=0;
       contDecimal=0;
       contEnt=0;
       contReal=0;
       contDo=0;
       contAgrup=0;
       contCom=0;
       contDes=0;
       contPuntua=0;
       contMat=0;
       contMono=0;
       contAsig=0;
       contRel=0;
       contPaRes=0;
       contIde=0;
       contBar=0;
       contMat =0;
        contMono=0;
        contAsig=0;
        contRel=0;
        contLog=0;
        contDes=0;
        contAgrup=0;
        contCadena=0;
        contPuntua=0;
        contEntero=0;
        contDecimal=0;
        contCarac=0;
        contBar=0;
        contTok=0;
        contComentarioV=0;
        contIde=0;
       contComentL=0;
        contExponencial=0;
        ListaToken.clear();
        errorTabla.setModel(new DefaultTableModel());
       
        
        //ambito
        contVar=0; 
        contFun=0;
        contProc=0;
        contPar1=0;
        ambitoTabla.setModel(new DefaultTableModel());
        conteoTabla.setModel(new DefaultTableModel());
        pilaAmbito.clear();
        tablaAmbito.clear();
        contAmbito = 0;
        banderaAmbito = false;
        tipo = "";
        clase = "const";
        clasePadre = "";
        contPar = 0;
        creador = "";
        esperoTipo = false;
        pilaAmbito.addFirst(0);
        ListaError.clear();
        contDimension=0;
        tamArreglo = "";
        mDatos = new matricesDatos();
        auxA= new LinkedList<Ambito>();
        esperandoReturn=0;
        valorFor=0;
        dentroFor=false;
        tipoCase="";
        tipoFor="";
        pilaTemporal.clear();
        
         LinkedList<Ambito> auxA;
    esperandoReturn=0;
    valorFor=0;
     dentroFor=false;
    tipoCase="";
    tipoFor="";
    }
    
    
    
    
}
