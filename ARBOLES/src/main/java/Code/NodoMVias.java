/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author vivia
 */
public class NodoMVias<K,V> {
    List<K> listaDeClaves;
    List<V> listaDeValores;
    List<NodoMVias<K,V>> listaDeHijos;
    
    public NodoMVias(int orden){
        listaDeClaves=new LinkedList<>();
        listaDeValores=new LinkedList<>();
        listaDeHijos=new LinkedList<>();
        for(int i=0;i<orden-1;i++){
            listaDeClaves.add(null);
            listaDeValores.add(null);
            listaDeHijos.add(null);
        }
        listaDeHijos.add(null);
    }
    
    public NodoMVias(int orden, K primerClave, V primerValor){
        this(orden);
        this.listaDeClaves.set(0,primerClave);
        this.listaDeValores.set(0,primerValor);
    }
    public static boolean esNodoVacio(NodoMVias unNodo){
        return unNodo==NodoMVias.nodoVacio();
    }
    public static NodoMVias nodoVacio(){
        return null;
    }
    public static Object datoVacio(){
        return null;
    }
    
    public K getClave(int posicion){
        return this.listaDeClaves.get(posicion);
    }
    
    public void setClave(int posicion, K clave){
        this.listaDeClaves.set(posicion,clave);
    }
    
    public V getValor(int posicion){
        return this.listaDeValores.get(posicion);
    }
    
    public void setValor(int posicion, V valor){
        this.listaDeValores.set(posicion,valor);
    }
    
    public NodoMVias<K,V> getHijo(int posicion){
        return this.listaDeHijos.get(posicion);
    }
    
    public void setHijo(int posicion, NodoMVias nodoHijo){
        this.listaDeHijos.set(posicion,nodoHijo);
    }
    
    public boolean esHijoVacio(int posicion){
        return NodoMVias.esNodoVacio(this.listaDeHijos.get(posicion));
    }
    
    public boolean esClaveVacia(int posicion){
        return this.listaDeClaves.get(posicion)==NodoMVias.datoVacio();
    }
    
    public boolean esHoja(){
        for(int i=0;i<this.listaDeHijos.size();i++){
            if(!this.esHijoVacio(i)){
                return false;
            }
        }
        return true;
    }
    
    public boolean estanClavesLlenas(){
        for(int i=0; i<this.listaDeClaves.size();i++){
            if(this.esClaveVacia(i)){
                return false;
            }
        }
        return true;
        //return !this.esClaveVacia(this.listaDeClaves.size()-1);
    }
    
    public int cantidadHijosNoVacios(){
        int cantidadDeHijosNoVacios=0;
        for(int i=0;i<this.listaDeHijos.size();i++){
            if(!this.esHijoVacio(i)){
                cantidadDeHijosNoVacios++;
            }
        }
        return cantidadDeHijosNoVacios;
    }
    
    public int cantidadClavesNoVacios(){
        int cantidadDeClavesNoVacios=0;
        for(int i=0;i<this.listaDeClaves.size();i++){
            if(!this.esClaveVacia(i)){
                cantidadDeClavesNoVacios++;
            }
        }
        return cantidadDeClavesNoVacios;
    }
    
    public int cantidadDeHijosVacios(){
        return this.listaDeHijos.size()-this.cantidadHijosNoVacios();
    }
}
