/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Code;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author gato
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws Code.ExceptionClaveNoExiste
     */
    public static void main(String[] args) throws ExceptionClaveNoExiste, ExceptionOrdenInvalido {
        // TODO code application logic here
        IArbolBusqueda<Integer,String> arbol;
        
        Scanner entrada2=new Scanner(System.in);
        System.out.println("Elija un ejercicio(1-18)");
        String tipoEjercicio=entrada2.next();
        
        switch(tipoEjercicio){
            case "1":

                System.out.println("RECONSTRUCCION DE ARBOL BINARIO BUSQUEDA"
                        + "CON LISTAS DE INORDEN Y NO INORDEN: ");

                
                Scanner entrada4=new Scanner(System.in);
                System.out.println("Es recorrido PreOrden? (SI/NO):");
                String tipoRecorrido=entrada4.next();
                tipoRecorrido=tipoRecorrido.toUpperCase();
  
                arbol=new ArbolMViasBusqueda<>(4);
                
                switch(tipoRecorrido){
                    case "SI":
                    
                        List<Integer> listaClavesInOrden=new LinkedList<>();
                        listaClavesInOrden.add(5);
                        listaClavesInOrden.add(10);
                        listaClavesInOrden.add(15);
                        listaClavesInOrden.add(20);
                        listaClavesInOrden.add(25);
                        List<String> listaValoresInOrden=new LinkedList<>();
                        listaValoresInOrden.add("juanito");
                        listaValoresInOrden.add("juanito");
                        listaValoresInOrden.add("juanito");
                        listaValoresInOrden.add("juanito");
                        listaValoresInOrden.add("juanito");
                        List<Integer> listaClavesPreOrden=new LinkedList<>();
                        listaClavesPreOrden.add(20);
                        listaClavesPreOrden.add(10);
                        listaClavesPreOrden.add(5);
                        listaClavesPreOrden.add(15);
                        listaClavesPreOrden.add(25);
                        List<String> listaValoresPreOrden=new LinkedList<>();
                        listaValoresPreOrden.add("juanito");
                        listaValoresPreOrden.add("juanito");
                        listaValoresPreOrden.add("juanito");
                        listaValoresPreOrden.add("juanito");
                        listaValoresPreOrden.add("juanito");
                        arbol=new ArbolBinarioBusqueda<>(listaClavesInOrden,
                            listaValoresInOrden,listaClavesPreOrden,listaValoresPreOrden,true);
                        System.out.println("LISTA DE CLAVES PREORDEN: "+listaClavesPreOrden);
                        System.out.println("LISTA DE VALORES PREORDEN: "+listaValoresPreOrden);
                        System.out.println("LISTA DE CLAVES INORDEN: "+listaClavesInOrden);
                        System.out.println("LISTA DE VALORES INORDEN: "+listaValoresInOrden);
                        System.out.println("RECORRIDO PREORDEN DEL ARBOL RECONSTRUIDO:"+arbol.recorridoEnPreOrden());
                    break;
                    
                    case "NO":
                        List<Integer> listaClavesInOrden2=new LinkedList<>();
                        listaClavesInOrden2.add(20);
                        listaClavesInOrden2.add(30);
                        listaClavesInOrden2.add(40);
                        listaClavesInOrden2.add(42);
                        listaClavesInOrden2.add(45);
        
                        listaClavesInOrden2.add(50);
                        listaClavesInOrden2.add(60);
                        listaClavesInOrden2.add(80);
        
                        List<String> listaValoresInOrden2=new LinkedList<>();
                        listaValoresInOrden2.add("juanito");
                        listaValoresInOrden2.add("juanito");
                    listaValoresInOrden2.add("juanito");
                    listaValoresInOrden2.add("juanito");
                    listaValoresInOrden2.add("juanito");
        
                    listaValoresInOrden2.add("juanito");
                    listaValoresInOrden2.add("juanito");
                    listaValoresInOrden2.add("juanito");
                
                    List<Integer> listaClavesPostOrden=new LinkedList<>();
                    listaClavesPostOrden.add(20);
                    listaClavesPostOrden.add(30);
                    listaClavesPostOrden.add(42);
                    listaClavesPostOrden.add(45);
                    listaClavesPostOrden.add(40);
        
                    listaClavesPostOrden.add(60);
                    listaClavesPostOrden.add(80);
                    listaClavesPostOrden.add(50);
        
                    List<String> listaValoresPostOrden=new LinkedList<>();
                    listaValoresPostOrden.add("juanito");
                    listaValoresPostOrden.add("juanito");
                    listaValoresPostOrden.add("juanito");
                    listaValoresPostOrden.add("juanito");
                    listaValoresPostOrden.add("juanito");
        
                    listaValoresPostOrden.add("juanito");
                    listaValoresPostOrden.add("juanito");
                    listaValoresPostOrden.add("juanito");
                    arbol=new ArbolBinarioBusqueda<>(listaClavesInOrden2,
                        listaValoresInOrden2,listaClavesPostOrden,listaValoresPostOrden,false);
                    System.out.println("LISTA DE CLAVES POSTORDEN: "+listaClavesPostOrden);
                    System.out.println("LISTA DE VALORES POSTORDEN: "+listaValoresPostOrden);
                    System.out.println("LISTA DE CLAVES INORDEN: "+listaClavesInOrden2);
                    System.out.println("LISTA DE VALORES INORDEN: "+listaValoresInOrden2);
                    System.out.println("RECORRIDO POSTORDEN DEL ARBOL RECONSTRUIDO:"+arbol.recorridoEnPostOrden());
                    break;
                }
                
                break;
            case "2":
                System.out.println("2.- RECORRIDO POSTORDEN ITERATIVO EN ARBOL BINARIO BUSQUEDA");
                arbol = new ArbolBinarioBusqueda<>();
                
                arbol.Insertar(41, "juan");
                arbol.Insertar(20, "juan");
                arbol.Insertar(65, "juan");
                arbol.Insertar(11, "juan");
                arbol.Insertar(29, "juan");
                arbol.Insertar(32, "juan");
                arbol.Insertar(50, "juan");
                arbol.Insertar(91, "juan");
                arbol.Insertar(72, "juan");
                arbol.Insertar(99, "juan");

                System.out.println("RECORRIDO POSTORDEN: " + arbol.recorridoEnPostOrdenI());
                break;
                
            case "3":
                Scanner entrada=new Scanner(System.in);
                System.out.println("2.- RECORRIDO POSTORDEN PARA ArbolMVias(AMV) O ArbolB(AB)?");


                String tipoArbol=entrada.next();
                tipoRecorrido=tipoArbol.toUpperCase();
                switch(tipoArbol){
                    case "AMV":
                        arbol = new ArbolMViasBusqueda<>(3);
                        arbol.Insertar(100, "juan");
                        arbol.Insertar(300, "juan");
                        arbol.Insertar(500, "juan");

                        arbol.Insertar(50, "juan");

                        arbol.Insertar(400, "juan");
                        arbol.Insertar(800, "juan");

                        arbol.Insertar(420, "juan");
                        arbol.Insertar(450, "juan");
                        arbol.Insertar(410, "juan");

                        arbol.Insertar(850, "juan");
                        arbol.Insertar(900, "juan");
                        arbol.Insertar(870, "juan");
                        System.out.println("RECORRIDO POSTORDEN: " + arbol.recorridoEnPostOrden());
                        break;
                    case "AB":
                        arbol=new ArbolB<>(4);
                        arbol.Insertar(100, "juan");
                        arbol.Insertar(300, "juan");
                        arbol.Insertar(500, "juan");

                        arbol.Insertar(50, "juan");

                        arbol.Insertar(400, "juan");
                        arbol.Insertar(800, "juan");

                        arbol.Insertar(420, "juan");
                        arbol.Insertar(450, "juan");
                        arbol.Insertar(410, "juan");

                        arbol.Insertar(850, "juan");
                        arbol.Insertar(900, "juan");
                        arbol.Insertar(870, "juan");
                        System.out.println("RECORRIDO POSTORDEN: " + arbol.recorridoEnPostOrden());
                        break;
                }
            case "4":
                Scanner entrada5=new Scanner(System.in);
                System.out.println("2.- RECORRIDO PREORDEN PARA ArbolMVias(AMV) O ArbolB(AB)?");


                String tipoArbol1=entrada5.next();
                tipoRecorrido=tipoArbol1.toUpperCase();
                switch(tipoArbol1){
                    case "AMV":
                        arbol = new ArbolMViasBusqueda<>(3);
                        arbol.Insertar(100, "juan");
                        arbol.Insertar(300, "juan");
                        arbol.Insertar(500, "juan");

                        arbol.Insertar(50, "juan");

                        arbol.Insertar(400, "juan");
                        arbol.Insertar(800, "juan");

                        arbol.Insertar(420, "juan");
                        arbol.Insertar(450, "juan");
                        arbol.Insertar(410, "juan");

                        arbol.Insertar(850, "juan");
                        arbol.Insertar(900, "juan");
                        arbol.Insertar(870, "juan");
                        System.out.println("RECORRIDO PREORDEN: " + arbol.recorridoEnPreOrden());
                        break;
                    case "AB":
                        arbol=new ArbolB<>(4);
                        arbol.Insertar(100, "juan");
                        arbol.Insertar(300, "juan");
                        arbol.Insertar(500, "juan");

                        arbol.Insertar(50, "juan");

                        arbol.Insertar(400, "juan");
                        arbol.Insertar(800, "juan");

                        arbol.Insertar(420, "juan");
                        arbol.Insertar(450, "juan");
                        arbol.Insertar(410, "juan");

                        arbol.Insertar(850, "juan");
                        arbol.Insertar(900, "juan");
                        arbol.Insertar(870, "juan");
                        System.out.println("RECORRIDO PREORDEN: " + arbol.recorridoEnPreOrden());
                        break;
                }
                break;
            case "5":
                Scanner entrada6=new Scanner(System.in);
                System.out.println("2.- RECORRIDO INORDEN PARA ArbolMVias(AMV) O ArbolB(AB)?");


                String tipoArbol2=entrada6.next();
                tipoRecorrido=tipoArbol2.toUpperCase();
                switch(tipoArbol2){
                    case "AMV":
                        arbol = new ArbolMViasBusqueda<>(3);
                        arbol.Insertar(100, "juan");
                        arbol.Insertar(300, "juan");
                        arbol.Insertar(500, "juan");

                        arbol.Insertar(50, "juan");

                        arbol.Insertar(400, "juan");
                        arbol.Insertar(800, "juan");

                        arbol.Insertar(420, "juan");
                        arbol.Insertar(450, "juan");
                        arbol.Insertar(410, "juan");

                        arbol.Insertar(850, "juan");
                        arbol.Insertar(900, "juan");
                        arbol.Insertar(870, "juan");
                        System.out.println("RECORRIDO INORDEN: " + arbol.recorridoEnInOrden());
                        break;
                    case "AB":
                        arbol=new ArbolB<>(4);
                        arbol.Insertar(100, "juan");
                        arbol.Insertar(300, "juan");
                        arbol.Insertar(500, "juan");

                        arbol.Insertar(50, "juan");

                        arbol.Insertar(400, "juan");
                        arbol.Insertar(800, "juan");

                        arbol.Insertar(420, "juan");
                        arbol.Insertar(450, "juan");
                        arbol.Insertar(410, "juan");

                        arbol.Insertar(850, "juan");
                        arbol.Insertar(900, "juan");
                        arbol.Insertar(870, "juan");
                        System.out.println("RECORRIDO INORDEN: " + arbol.recorridoEnInOrden());
                        break;
                }
                break;
            case "6":
                System.out.println("6.- METODO INSERTAR PARA ARBOL B");
                arbol = new ArbolB<>(4);
                arbol.Insertar(100, "juan");
                arbol.Insertar(300, "juan");
                arbol.Insertar(500, "juan");

                arbol.Insertar(50, "juan");

                arbol.Insertar(400, "juan");
                arbol.Insertar(800, "juan");

                arbol.Insertar(420, "juan");
                arbol.Insertar(450, "juan");
                arbol.Insertar(410, "juan");

                arbol.Insertar(850, "juan");
                arbol.Insertar(900, "juan");
                arbol.Insertar(870, "juan");
                System.out.println("RECORRIDO INORDEN: " + arbol.recorridoEnInOrden());
                
                Scanner entrada7=new Scanner(System.in);
                System.out.println("Escriba una Clave a insertar: ");


                String claveInsertar=entrada7.next();
                int clave=Integer.parseInt(claveInsertar);
                arbol.Insertar(clave,"juan");
                System.out.println("Clave Insertado");
                System.out.println("RECORRIDO INORDEN: " + arbol.recorridoEnInOrden());
                break;
            case "7":
                System.out.println("7.- METODO ELIMINAR PARA ARBOL B");
                arbol = new ArbolB<>(4);
                arbol.Insertar(100, "juan");
                arbol.Insertar(300, "juan");
                arbol.Insertar(500, "juan");

                arbol.Insertar(50, "juan");

                arbol.Insertar(400, "juan");
                arbol.Insertar(800, "juan");

                arbol.Insertar(420, "juan");
                arbol.Insertar(450, "juan");
                arbol.Insertar(410, "juan");

                arbol.Insertar(850, "juan");
                arbol.Insertar(900, "juan");
                arbol.Insertar(870, "juan");
                System.out.println("RECORRIDO INORDEN: " + arbol.recorridoEnInOrden());
                
                Scanner entrada8=new Scanner(System.in);
                System.out.println("Escriba una Clave a eliminar");


                String claveAEliminar=entrada8.next();
                int clave1=Integer.parseInt(claveAEliminar);
                arbol.eliminar(clave1);
                System.out.println("Clave Eliminado");
                System.out.println("RECORRIDO INORDEN: " + arbol.recorridoEnInOrden());
                break;
            case "8":
                System.out.println("8.- METODO INSERTAR PARA ARBOL AVL");
                arbol = new AVL<>();
                arbol.Insertar(100, "juan");
                arbol.Insertar(300, "juan");
                arbol.Insertar(500, "juan");

                arbol.Insertar(50, "juan");

                arbol.Insertar(400, "juan");
                arbol.Insertar(800, "juan");

                arbol.Insertar(420, "juan");
                arbol.Insertar(450, "juan");
                arbol.Insertar(410, "juan");

                arbol.Insertar(850, "juan");
                arbol.Insertar(900, "juan");
                arbol.Insertar(870, "juan");
                System.out.println("RECORRIDO A NIVELES: " + arbol.recorridoPorNiveles());
                
                Scanner entrada9=new Scanner(System.in);
                System.out.println("Escriba una Clave a insertar: ");


                String claveInsertar2=entrada9.next();
                int clave2=Integer.parseInt(claveInsertar2);
                arbol.Insertar(clave2,"juan");
                System.out.println("Clave Insertado");
                System.out.println("RECORRIDO A NIVELES: " + arbol.recorridoPorNiveles());
                break;
            case "9":
                System.out.println("9.- METODO ELIMINAR PARA ARBOL AVL");
                arbol = new AVL<>();
                arbol.Insertar(100, "juan");
                arbol.Insertar(300, "juan");
                arbol.Insertar(500, "juan");

                arbol.Insertar(50, "juan");

                arbol.Insertar(400, "juan");
                arbol.Insertar(800, "juan");

                arbol.Insertar(420, "juan");
                arbol.Insertar(450, "juan");
                arbol.Insertar(410, "juan");

                arbol.Insertar(850, "juan");
                arbol.Insertar(900, "juan");
                arbol.Insertar(870, "juan");
                System.out.println("RECORRIDO A NIVELES: " + arbol.recorridoPorNiveles());
                
                Scanner entrad=new Scanner(System.in);
                System.out.println("Escriba una Clave a Eliminar: ");


                String claveInsert=entrad.next();
                int clave3=Integer.parseInt(claveInsert);
                arbol.eliminar(clave3);
                System.out.println("Clave Eliminado");
                System.out.println("RECORRIDO A NIVELES: " + arbol.recorridoPorNiveles());
                break;
            case "10":
                System.out.println("10.- METODO INSERTAR PARA ARBOL M-VIAS\n ORDEN:3");
                arbol = new ArbolMViasBusqueda<>(3);
                arbol.Insertar(100, "juan");
                arbol.Insertar(300, "juan");
                arbol.Insertar(500, "juan");

                arbol.Insertar(50, "juan");

                arbol.Insertar(400, "juan");
                arbol.Insertar(800, "juan");

                arbol.Insertar(420, "juan");
                arbol.Insertar(450, "juan");
                arbol.Insertar(410, "juan");

                arbol.Insertar(850, "juan");
                arbol.Insertar(900, "juan");
                arbol.Insertar(870, "juan");
                System.out.println("RECORRIDO A NIVELES: " + arbol.recorridoPorNiveles());
                
                Scanner entrad1=new Scanner(System.in);
                System.out.println("Escriba una Clave a Insertar: ");


                String claveInsert1=entrad1.next();
                int clave4=Integer.parseInt(claveInsert1);
                arbol.Insertar(clave4,"juan");
                System.out.println("Clave Insertado");
                System.out.println("RECORRIDO A NIVELES: " + arbol.recorridoPorNiveles());
                break;
            case "11":
                System.out.println("11.- METODO ELIMINAR PARA ARBOL M-VIAS\n ORDEN:3");
                arbol = new ArbolMViasBusqueda<>(3);
                arbol.Insertar(100, "juan");
                arbol.Insertar(300, "juan");
                arbol.Insertar(500, "juan");

                arbol.Insertar(50, "juan");

                arbol.Insertar(400, "juan");
                arbol.Insertar(800, "juan");

                arbol.Insertar(420, "juan");
                arbol.Insertar(450, "juan");
                arbol.Insertar(410, "juan");

                arbol.Insertar(850, "juan");
                arbol.Insertar(900, "juan");
                arbol.Insertar(870, "juan");
                System.out.println("RECORRIDO A NIVELES: " + arbol.recorridoPorNiveles());
                
                Scanner entrad2=new Scanner(System.in);
                System.out.println("Escriba una Clave a Insertar: ");


                String claveInsert2=entrad2.next();
                int clave5=Integer.parseInt(claveInsert2);
                arbol.eliminar(clave5);
                System.out.println("Clave Eliminado");
                System.out.println("RECORRIDO A NIVELES: " + arbol.recorridoPorNiveles());
                break;
            case "12":
                System.out.println("12.- METODO RECURSIVO CANTIDAD DE NODOS CON UN SOLO HIJO NO VACIO"
                        + "\n PARA ARBOL BINARIO BUSQUEDA");
                arbol = new ArbolBinarioBusqueda<>();
                arbol.Insertar(41, "juan");
                arbol.Insertar(20, "juan");
                arbol.Insertar(65, "juan");

                arbol.Insertar(11, "juan");

                arbol.Insertar(29, "juan");
                arbol.Insertar(32, "juan");

                arbol.Insertar(50, "juan");
                arbol.Insertar(91, "juan");
                arbol.Insertar(72, "juan");

                arbol.Insertar(99, "juan");
                
                System.out.println("RECORRIDO A NIVELES: " + arbol.recorridoPorNiveles());
               
                System.out.println("Cantidad: " + arbol.cantidadNodosConUnHijoNoVacio());
                break;
            case "13":
                System.out.println("13.- METODO ITERATIVO CANTIDAD DE HIJOS VACIOS"
                        + "\n PARA ARBOL BINARIO BUSQUEDA **USANDO RECORRIDO INORDEN**");
                arbol = new ArbolBinarioBusqueda<>();
                arbol.Insertar(41, "juan");
                arbol.Insertar(20, "juan");
                arbol.Insertar(65, "juan");

                arbol.Insertar(11, "juan");

                arbol.Insertar(29, "juan");
                arbol.Insertar(32, "juan");

                arbol.Insertar(50, "juan");
                arbol.Insertar(91, "juan");
                arbol.Insertar(72, "juan");

                arbol.Insertar(99, "juan");
                
                System.out.println("RECORRIDO A NIVELES: " + arbol.recorridoPorNiveles());
               
                System.out.println("Cantidad: " + arbol.numeroHijosVacioLogicaInOrden());
                break;
            case "14":
                System.out.println("14.- METODO RECIBE UN NODO Y RETORNA SU PREDECESOR");
                arbol = new ArbolBinarioBusqueda<>();
                
                arbol.Insertar(41, "juan");
                arbol.Insertar(20, "juan");
                arbol.Insertar(65, "juan");

                arbol.Insertar(11, "juan");

                arbol.Insertar(29, "juan");
                arbol.Insertar(32, "juan");

                arbol.Insertar(50, "juan");
                arbol.Insertar(91, "juan");
                arbol.Insertar(72, "juan");

                arbol.Insertar(99, "juan");
                System.out.println("Recibe la raiz");
                //arbol.predecesorInOrden(arbol.getRaiz());
                System.out.println("Cantidad: "  );
                break;
            case "15":
                System.out.println("15.- METODO DEVUELVE TRUE SI EN EL NIVEL DE ARBOL MVIAS \n"
                        + "ESTAN LOS NODOS COMPLETOS DE CLAVES");
                arbol = new ArbolMViasBusqueda<>(3);
                arbol.Insertar(100, "juan");
                arbol.Insertar(300, "juan");
                arbol.Insertar(500, "juan");

                arbol.Insertar(50, "juan");

                arbol.Insertar(400, "juan");
                arbol.Insertar(800, "juan");

                arbol.Insertar(420, "juan");
                arbol.Insertar(450, "juan");
                arbol.Insertar(410, "juan");

                arbol.Insertar(850, "juan");
                arbol.Insertar(900, "juan");
                arbol.Insertar(870, "juan");
                System.out.println("RECORRIDO A NIVELES: " + arbol.recorridoPorNiveles());
               
                System.out.println("ESTAN COMPLETOS?: " + arbol.nodosCompletosEnElNivelN(2));
                break;
            case "16":
                break;
            case "17":
                System.out.println("17.- METODO DEVUELVE TRUE SI SON IGUALES DOS ARBOL MVIAS \n"
                        + "CASO CONTRARIO FALSE");
                arbol = new ArbolMViasBusqueda<>(3);
                
                arbol.Insertar(100, "juan");
                arbol.Insertar(300, "juan");
                arbol.Insertar(500, "juan");
                arbol.Insertar(50, "juan");
                arbol.Insertar(400, "juan");
                arbol.Insertar(800, "juan");
                
                ArbolMViasBusqueda<Integer,String> arbol2 = new ArbolMViasBusqueda<>(3);
                
                arbol2.Insertar(420, "juan");
                arbol2.Insertar(450, "juan");
                arbol2.Insertar(410, "juan");
                arbol2.Insertar(850, "juan");
                arbol2.Insertar(900, "juan");
                arbol2.Insertar(870, "juan");
                
                System.out.println("RECORRIDO A NIVELES arbol1: " + arbol.recorridoPorNiveles());
                System.out.println("RECORRIDO A NIVELES arbol2: " + arbol2.recorridoPorNiveles());
               
                System.out.println("SON IGUALES?: " + arbol.esSimilar(arbol2));
                break;
            case "18":
                System.out.println("18.- METODO DEVUELVE TRUE SI SON IGUALES DOS ARBOL BINARIO BUSQUEDA \n"
                        + "CASO CONTRARIO FALSE");
                arbol = new ArbolBinarioBusqueda<>();
                
                arbol.Insertar(100, "juan");
                arbol.Insertar(300, "juan");
                arbol.Insertar(500, "juan");
                arbol.Insertar(50, "juan");
                arbol.Insertar(400, "juan");
                arbol.Insertar(800, "juan");
                
                ArbolBinarioBusqueda<Integer,String> arbol3 = new ArbolBinarioBusqueda<>();
                
                arbol3.Insertar(420, "juan");
                arbol3.Insertar(450, "juan");
                arbol3.Insertar(410, "juan");
                arbol3.Insertar(850, "juan");
                arbol3.Insertar(900, "juan");
                arbol3.Insertar(870, "juan");
                
                System.out.println("RECORRIDO A NIVELES arbol1: " + arbol.recorridoPorNiveles());
                System.out.println("RECORRIDO A NIVELES arbol3: " + arbol3.recorridoPorNiveles());
                System.out.println("SON IGUALES?: " + arbol.esSimilar(arbol3));
                break;
            default:
                System.out.println("Tipo de Arbol invalido, eligiendo ArbolBinarioBusqueda\n");
                arbol=new ArbolBinarioBusqueda<>();
            
        }
    }
}
