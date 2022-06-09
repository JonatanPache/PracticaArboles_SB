/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;

import java.util.*;
import java.util.List;

/**
 *
 * @author vivia
 * @param <K>
 * @param <V>
 */
public class ArbolBinarioBusqueda<K extends Comparable<K>,V> implements IArbolBusqueda<K,V> {
    
    NodoBinario<K,V> raiz;
    
    public ArbolBinarioBusqueda(){
        
    }
    
    /**
     *  Instancia un arbol reconstruyendo en base a sus recorridos inOrden y (PreOrden o PostOrden)
     * Si el parametro usandoPreOrden es verdadero, los parametros clavesNoInOrden y valorNoInOrden tendran
     * el recorrido en preOrden del arbol , caso contrario seran el postOrden
     * @param clavesInOrden
     * @param valoresInOrden
     * @param clavesNoInOrden
     * @param valoresNoInOrden
     * @param usandoPreOrden
     */
    public ArbolBinarioBusqueda(List<K> clavesInOrden,List<V> valoresInOrden,
                                List<K> clavesNoInOrden,List<V> valoresNoInOrden,
                                boolean usandoPreOrden){
        if(clavesInOrden==null || valoresInOrden==null || clavesNoInOrden==null ||
             valoresNoInOrden==null){
            throw new IllegalArgumentException("los parametros no pueden ser nulos");
        }
        
        if(clavesInOrden.isEmpty() || valoresInOrden.isEmpty() || clavesNoInOrden.isEmpty() ||
             valoresNoInOrden.isEmpty()){
            throw new IllegalArgumentException("los parametros no pueden ser vacios");
        }
        
        if(clavesInOrden.size()!=clavesNoInOrden.size() || valoresInOrden.size()!=valoresInOrden.size()
                || clavesInOrden.size()!=valoresNoInOrden.size()){
            throw new IllegalArgumentException("los parametros no pueden ser de diferentes tamanios");
        }
        
        if(usandoPreOrden){
            this.raiz=reconstruirConPreOrden(clavesInOrden,valoresInOrden,clavesNoInOrden,valoresNoInOrden);
        }else{
            this.raiz=reconstruirConPostOrden(clavesInOrden,valoresInOrden,clavesNoInOrden,valoresNoInOrden);
        }
    }
    
    private NodoBinario<K,V> reconstruirConPreOrden(List<K> clavesInOrden,List<V> valoresInOrden,
                                List<K> clavesPreOrden,List<V> valoresPreOrden){
        if(clavesInOrden.isEmpty()){
            return NodoBinario.nodoVacio();
        }
        int posicionDeClavePadreEnPreOrden=0;
        K clavePadre=clavesPreOrden.get(posicionDeClavePadreEnPreOrden);
        V valorPadre=valoresPreOrden.get(posicionDeClavePadreEnPreOrden);
        int posicionDeClavePadreEnInorden=this.posicionDeClave(clavePadre, clavesInOrden);
        
        // para armar la rama izquierda
        
        List<K> clavesInOrdenPorIzquierda=clavesInOrden.subList(posicionDeClavePadreEnPreOrden,
                posicionDeClavePadreEnInorden);
        List<V> valoresInOrdenPorIzquierda=valoresInOrden.subList(posicionDeClavePadreEnPreOrden,
                posicionDeClavePadreEnInorden);
        
        List<K> clavesPreOrdenPorIzquierda=clavesPreOrden.subList(posicionDeClavePadreEnPreOrden+1,
                posicionDeClavePadreEnInorden+1);
        List<V> valoresPreOrdenPorIzquierda=valoresPreOrden.subList(posicionDeClavePadreEnPreOrden+1,
                posicionDeClavePadreEnInorden+1);
        
        NodoBinario<K,V> hijoIzquierdo=reconstruirConPreOrden(clavesInOrdenPorIzquierda,valoresInOrdenPorIzquierda,
                clavesPreOrdenPorIzquierda,valoresPreOrdenPorIzquierda);
        
        // para armar la rama derecha
        
        List<K> clavesInOrdenPorDerecho=clavesInOrden.subList(posicionDeClavePadreEnInorden+1,
                clavesInOrden.size());
        List<V> valoresInOrdenPorDerecho=valoresInOrden.subList(posicionDeClavePadreEnInorden+1,
                clavesInOrden.size());
        
        List<K> clavesPreOrdenPorDerecho=clavesPreOrden.subList(posicionDeClavePadreEnInorden+1,
                clavesInOrden.size());
        List<V> valoresPreOrdenPorDerecho=valoresPreOrden.subList(posicionDeClavePadreEnPreOrden+1,
                clavesInOrden.size());
        
        NodoBinario<K,V> hijoDerecho=reconstruirConPreOrden(clavesInOrdenPorDerecho,valoresInOrdenPorDerecho,
                clavesPreOrdenPorDerecho,valoresPreOrdenPorDerecho);
        
        // armando el nodoActual
        NodoBinario<K,V> nodoActual=new NodoBinario<>(clavePadre,valorPadre);
        nodoActual.setHijoIzquierdo(hijoIzquierdo);
        nodoActual.setHijoDerecho(hijoDerecho);
        return nodoActual;
    }
    
    private NodoBinario<K,V> reconstruirConPostOrden(List<K> clavesInOrden,List<V> valoresInOrden,
                                List<K> clavesPostOrden,List<V> valoresPostOrden){
        if(clavesInOrden.isEmpty()){
            return (NodoBinario<K,V>)NodoBinario.nodoVacio();
        }
        int posicionDeClavePadreEnPostOrden=clavesPostOrden.size()-1;
        K clavePadre=clavesPostOrden.get(posicionDeClavePadreEnPostOrden);
        V valorPadre=valoresPostOrden.get(posicionDeClavePadreEnPostOrden);
        int posicionDeClavePadreEnInorden=this.posicionDeClave(clavePadre, clavesInOrden);
        
        // para armar la rama izquierda
        
        List<K> clavesInOrdenPorIzquierda=clavesInOrden.subList(0,
                posicionDeClavePadreEnInorden);
        List<V> valoresInOrdenPorIzquierda=valoresInOrden.subList(0,
                posicionDeClavePadreEnInorden);
        
        List<K> clavesPostOrdenPorIzquierda=clavesPostOrden.subList(0,
                posicionDeClavePadreEnInorden);
        List<V> valoresPostOrdenPorIzquierda=valoresPostOrden.subList(0,
                posicionDeClavePadreEnInorden);
        
        NodoBinario<K,V> hijoIzquierdo=reconstruirConPostOrden(clavesInOrdenPorIzquierda,valoresInOrdenPorIzquierda,
                clavesPostOrdenPorIzquierda,valoresPostOrdenPorIzquierda);
        
        // para armar la rama derecha
        
        List<K> clavesInOrdenPorDerecho=clavesInOrden.subList(posicionDeClavePadreEnInorden+1,
                clavesInOrden.size());
        List<V> valoresInOrdenPorDerecho=valoresInOrden.subList(posicionDeClavePadreEnInorden+1,
                valoresInOrden.size());
        
        List<K> clavesPostOrdenPorDerecho=clavesPostOrden.subList(posicionDeClavePadreEnInorden,
                clavesPostOrden.size()-1);
        List<V> valoresPostOrdenPorDerecho=valoresPostOrden.subList(posicionDeClavePadreEnInorden,
                clavesPostOrden.size()-1);
        
        NodoBinario<K,V> hijoDerecho=reconstruirConPostOrden(clavesInOrdenPorDerecho,valoresInOrdenPorDerecho,
                clavesPostOrdenPorDerecho,valoresPostOrdenPorDerecho);
        
        // armando el nodoActual
        NodoBinario<K,V> nodoActual=new NodoBinario<>(clavePadre,valorPadre);
        nodoActual.setHijoDerecho(hijoDerecho);
        nodoActual.setHijoIzquierdo(hijoIzquierdo);
        
        return nodoActual;
    }
    
    private int posicionDeClave(K claveABuscar,List<K> listaDeClaves){
        for(int i=0;i<listaDeClaves.size();i++){
            K claveActual=listaDeClaves.get(i);
            if(claveABuscar.compareTo(claveActual)==0){
                return i;
            }
        }
        return -1;
    }
    @Override
    public void Insertar(K claveAInsertar, V valorAInsertar) throws NullPointerException {
        if(claveAInsertar==null){
            throw new NullPointerException("No se permite clave nulo");
        }
        if(valorAInsertar==null){
            throw new NullPointerException("No se permite valor nulo");
        }
        if(this.esArbolVacio()){
            this.raiz=new NodoBinario<>(claveAInsertar,valorAInsertar);
        }
        NodoBinario<K,V> nodoAnterior=NodoBinario.nodoVacio();
        NodoBinario<K,V> nodoActual=this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual=nodoActual.getClave();
            nodoAnterior=nodoActual;
            if(claveAInsertar.compareTo(claveActual)<0){
                nodoActual=nodoActual.getHijoIzquierdo();
            }else if(claveAInsertar.compareTo(claveActual)>0){
                nodoActual=nodoActual.getHijoDerecho();
            }else{
                nodoActual.setValor(valorAInsertar);
                return;
            }
        }
        K claveAnterior=nodoAnterior.getClave();
        NodoBinario<K,V> nuevoNodo=new NodoBinario<>(claveAInsertar,valorAInsertar);
        if(claveAInsertar.compareTo(claveAnterior)<0){
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        }else{
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }
    
    @Override
    public void InsertarR(K claveAInsertar,V valorAInsertar) throws NullPointerException{
        if(claveAInsertar==null){
            throw new NullPointerException("clave nula");
        }
        if(valorAInsertar==null){
            throw new NullPointerException("valor nula");
        }
        //methd_1.0:recursivo
        this.raiz=insertar(this.raiz,claveAInsertar,valorAInsertar);
    }
    
    /**
     * methd_1.0:recursivo_insertar
     */
    private NodoBinario<K,V> insertar(NodoBinario<K,V> nodoActual,K claveAInsertar,V valorAInsertar){
        //tambien sirve si en caso el nodo es nulo 
        //entonces lo creara 
        if(NodoBinario.esNodoVacio(nodoActual)){    //es vacio el nodo
            NodoBinario<K,V> nuevoNodo=new NodoBinario<>(claveAInsertar,valorAInsertar);
            return nuevoNodo;
        }
        K claveActual=nodoActual.getClave();
        if(claveAInsertar.compareTo(claveActual)>0){    //clave a insertar es mayor 
            NodoBinario<K,V> supuestoHijoDerecha=insertar(nodoActual.getHijoDerecho(),claveAInsertar,valorAInsertar);
            nodoActual.setHijoDerecho(supuestoHijoDerecha);
            return nodoActual;
        }
        if(claveAInsertar.compareTo(claveActual)<0){    //clave a insertar es menor
            NodoBinario<K,V> supuestoHijoIzquierdo=insertar(nodoActual.getHijoIzquierdo(),claveAInsertar,valorAInsertar);
            nodoActual.setHijoIzquierdo(supuestoHijoIzquierdo);
            return nodoActual;
        }
        //encontro el nodo 
        //solo debemos reemplazar su valor
        nodoActual.setValor(valorAInsertar);
        return nodoActual;
    }

    @Override
    public V eliminar(K claveAEliminar) throws ExceptionClaveNoExiste {
        if(claveAEliminar==null){
            throw new IllegalArgumentException("Clave a eliminar no pude ser nula");
        }
        V valorARetornar=this.buscar(claveAEliminar);
        if(valorARetornar==null){
            throw new IllegalArgumentException("Clave no encontrada");
        }
        this.raiz=eliminar(this.raiz,claveAEliminar);
        return valorARetornar;
    }
    
    private NodoBinario<K,V> eliminar(NodoBinario<K,V> nodoActual,K claveAEliminar){
        K claveActual=nodoActual.getClave();
        if(claveAEliminar.compareTo(claveActual)>0){
            //puede que me devuelva el mismo que era antes
            NodoBinario<K,V> posibleNuevoHijoDerecho=eliminar(nodoActual.getHijoDerecho(),claveAEliminar);
            nodoActual.setHijoDerecho(posibleNuevoHijoDerecho);
            return nodoActual;
        }
        if(claveAEliminar.compareTo(claveActual)<0){
            //puede que me devuelva el mismo que era antes
            NodoBinario<K,V> posibleNuevoHijoIzquierdo=eliminar(nodoActual.getHijoIzquierdo(),claveAEliminar);
            nodoActual.setHijoIzquierdo(posibleNuevoHijoIzquierdo);
            return nodoActual;
        }
        //llego a encontrarlo
        //caso1; esHoja
        if(nodoActual.esHoja()){
            return NodoBinario.nodoVacio();
        }
        //caso2 tenga un hijo
        //caso2.1 tiene hijo izq
        if(!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoIzquierdo();
        }
        //caso2.2 tiene hijo der
        if(nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoDerecho();
        }
        //caso 3: tiene dos hijos
        //buscamos su nodo izquierdo para reemplazar
        NodoBinario<K,V> nodoReemplazo=this.buscarNodoSucesor(nodoActual.getHijoDerecho());
        // este nodo puede ser caso 1 hoja o caso 2 tiene solo hijo derecho
        NodoBinario<K,V> posibleNuevoHijoDerecho=eliminar(nodoActual.getHijoDerecho(),nodoReemplazo.getClave());
        //exite dos formas : eliminar el nodo o reemplazar los datos;
        //reeplazar dato
        nodoActual.setHijoDerecho(posibleNuevoHijoDerecho);
        nodoActual.setClave(nodoReemplazo.getClave());
        nodoActual.setValor(nodoReemplazo.getValor());
        return nodoActual;
        
    }
    private NodoBinario<K,V> buscarNodoSucesor(NodoBinario<K,V> nodoActual){
        NodoBinario<K,V> nodoAnterior=null;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior=nodoActual;
            nodoActual=nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior;
    }
    @Override
    public K minimo(){
        if(this.esArbolVacio()){
            return null;
        }
        NodoBinario<K,V> nodoActual=this.raiz;
        NodoBinario<K,V> nodoAnterior=NodoBinario.nodoVacio();
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior=nodoActual;
            nodoActual=nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior.getClave();
    }
    
    @Override
    public K maximo(){
        if(this.esArbolVacio()){
            return null;
        }
        NodoBinario<K,V> nodoActual=this.raiz;
        NodoBinario<K,V> nodoAnterior=NodoBinario.nodoVacio();
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior=nodoActual;
            nodoActual=nodoActual.getHijoDerecho();
        }
        return nodoAnterior.getClave();
    }
    @Override
    public int contarNodosConHijosIzquierdo(){
        int cant=0;
        if(!this.esArbolVacio()){
            Queue <NodoBinario<K,V>> colaDeNodos=new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while(!colaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoActual=colaDeNodos.poll();
                if(!nodoActual.esVacioHijoIzquierdo()){
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                    cant++;
                }
                if(!nodoActual.esVacioHijoDerecho()){
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
            }
        }
        return cant;
    }
    @Override
    public int contarNodosConHijosIzquierdoR(){
        return contarNodosConHijosIzquierdo(this.raiz);
    }
    
    private int contarNodosConHijosIzquierdo(NodoBinario<K,V> nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantNodoSubIz=contarNodosConHijosIzquierdo(nodoActual.getHijoIzquierdo());
        int cantNodoSubDer=contarNodosConHijosIzquierdo(nodoActual.getHijoDerecho());
        if(!nodoActual.esVacioHijoIzquierdo()){
            return cantNodoSubIz + cantNodoSubDer+1;
        }
        return cantNodoSubIz + cantNodoSubDer;
    }
    
    @Override
    public int contarNodosConHijosDerecho(){
        int cant=0;
        if(!this.esArbolVacio()){
            Queue <NodoBinario<K,V>> colaDeNodos=new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while(!colaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoActual=colaDeNodos.poll();
                if(!nodoActual.esVacioHijoIzquierdo()){
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if(!nodoActual.esVacioHijoDerecho()){
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                    cant++;
                }
            }
        }
        return cant;
    }
    @Override
    public int contarNodosConHijosDerechoR(){
        return contarNodosConHijosDerecho(this.raiz);
    }
    private int contarNodosConHijosDerecho(NodoBinario<K,V> nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantNodoSubIz=contarNodosConHijosDerecho(nodoActual.getHijoIzquierdo());
        int cantNodoSubDer=contarNodosConHijosDerecho(nodoActual.getHijoDerecho());
        if(!nodoActual.esVacioHijoDerecho()){
            return cantNodoSubIz + cantNodoSubDer+1;
        }
        return cantNodoSubIz + cantNodoSubDer;
    }
    @Override
    public V buscar(K claveABuscar) {
        if(claveABuscar==null){
            throw new IllegalArgumentException("Clave a eliminar no pude ser nula");
        }
        if(this.esArbolVacio()){
            return null;
        }
        NodoBinario<K,V> nodoActual=this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual=nodoActual.getClave();
            
            if(claveABuscar.compareTo(claveActual)==0){
                return nodoActual.getValor();
            }else if(claveABuscar.compareTo(claveActual)<0){
                nodoActual=nodoActual.getHijoIzquierdo();
            }else{
                nodoActual=nodoActual.getHijoDerecho();
            }
        }
        return null; //no lo encontro
    }

    @Override
    public boolean contiene(K claveAVuscar) {
        return this.buscar(claveAVuscar)!=null;
    }

    @Override
    public int size() {
        int cantidad=0;
        Queue<NodoBinario<K,V>> colaDeNodos=new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while(!colaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual=colaDeNodos.poll();
            cantidad++;
            if(!nodoActual.esVacioHijoDerecho()){
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
            if(!nodoActual.esVacioHijoIzquierdo()){
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
        }
        return cantidad;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }
    
    public int altura(NodoBinario<K,V> nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int alturaPorDerecha=altura(nodoActual.getHijoDerecho());
        int alturaPorIzquierda=altura(nodoActual.getHijoIzquierdo());
        if(alturaPorDerecha>alturaPorIzquierda){
            return alturaPorDerecha+1;
        }
        return alturaPorIzquierda+1;
    }

    @Override
    public int cantHojas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }
    /**
    * nivel: se cuenta desde 0
    * altura: desde 1
    */
    
    @Override
    public int cantHijosDerechosNivel(int nivel){
        /*if(nivel>this.altura()-1){
            return 0;
        }
        return cantHijosDerechosNivel(this.raiz,nivel);*/
        int cant=0;
        int cont=0;
        if(!this.esArbolVacio()){
            Queue <NodoBinario<K,V>> colaDeNodos=new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while(!colaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoActual=colaDeNodos.poll();
                
                if(!nodoActual.esVacioHijoIzquierdo()){
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if(!nodoActual.esVacioHijoDerecho()){
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                    //cuenta desde ya lo que bajo el hijo derecho y lo acumula
                    if(cont>nivel){
                        cant++;
                    }
                }
                cont++;
            }
        }
        return cant;
    }
    
    private int cantHijosDerechosNivel(NodoBinario<K,V> nodoActual,int nivel){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        //encontre el nivel de donde debo buscar
        if(nivel==0){
            int cantPorIzquierda=cantHijosDerechosNivel(nodoActual.getHijoIzquierdo(),0);
            int cantPorDerecha=cantHijosDerechosNivel(nodoActual.getHijoDerecho(),0);
            //hago el trabajo donde elnodo es 0
            if(!nodoActual.esVacioHijoDerecho()){
                return cantPorIzquierda+cantPorDerecha+1;
            }
            return cantPorIzquierda+cantPorDerecha;
            
        }
        int cantPorIzquierda=cantHijosDerechosNivel(nodoActual.getHijoIzquierdo(),nivel-1);
        int cantPorDerecha=cantHijosDerechosNivel(nodoActual.getHijoDerecho(),nivel-1);
        return cantPorIzquierda+cantPorDerecha;
    }  
    
    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido=new ArrayList<>();
        if(!this.esArbolVacio()){
            Queue <NodoBinario<K,V>> colaDeNodos=new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while(!colaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoActual=colaDeNodos.poll();
                recorrido.add(nodoActual.getClave());
                if(!nodoActual.esVacioHijoIzquierdo()){
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if(!nodoActual.esVacioHijoDerecho()){
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
            }
        }
        return recorrido;
    }
    
    @Override
    public List<K> recorridoEnPreOrden(){
        List<K> recorrido=new ArrayList<>();
        if(!this.esArbolVacio()){
            Stack <NodoBinario<K,V>> pilaDeNodos=new Stack<>();
            pilaDeNodos.push(this.raiz);
            while(!pilaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoActual=pilaDeNodos.pop();
                recorrido.add(nodoActual.getClave());
                if(!nodoActual.esVacioHijoDerecho()){
                    pilaDeNodos.push(nodoActual.getHijoDerecho());
                }
                if(!nodoActual.esVacioHijoIzquierdo()){
                    pilaDeNodos.push(nodoActual.getHijoIzquierdo());
                }
            }
        }
        return recorrido;
    };
    /*
    public List<K> recorridoEnInOrdenI(){
        List<K> recorrido=new ArrayList<>();
        if(this.esArbolVacio()){
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodos=new Stack<>();
        NodoBinario<K,V> nodoActual=this.raiz;
        pilaDeNodos.push(nodoActual);
        // apila todos los nodos padres hasta el ultimo nodo izquierdo hoja
        meterPilaParaInOrden(pilaDeNodos,nodoActual);
        // empezamos a iteramos sobre la pila
        while(!pilaDeNodos.isEmpty()){
            // sacamos el nodo tope
            // el nodo tope es el ultimo nodo izquierdo del arbol
            nodoActual=pilaDeNodos.pop();
            // y agregamos al array la clave del nodoActual
            recorrido.add(nodoActual.getClave());
            // preguntamos si todavia hay mas nodo en la pila
            if(!pilaDeNodos.isEmpty()){
                // referenciamos al nodoTope
                NodoBinario<K,V> nodoDelTope=pilaDeNodos.peek();
                // preguntamos si tiene hijo derecho y si es diferente del nodoActual
                // caso1: si tiene hijo debemos apilar todos sus nodos izquierdos 
                //      si en caso es un subarbol
                if(!nodoDelTope.esVacioHijoDerecho() && 
                        nodoDelTope.getHijoDerecho()!=nodoActual){
                    // le decimos que apile desde el hijo derecho del nodotope 
                    meterPilaParaInOrden(pilaDeNodos,nodoDelTope.getHijoDerecho());
                }
            }
        }
        return recorrido;
    }
    
    private void meterPilaParaInOrden(Stack<NodoBinario<K,V>> pilaDeNodos,NodoBinario<K,V> nodoActual){
        // recorremos los nodos para apilar
        while(!NodoBinario.esNodoVacio(nodoActual)){
            
            if(!nodoActual.esVacioHijoIzquierdo()){
                nodoActual=nodoActual.getHijoIzquierdo();
            
            if(!nodoActual.esVacioHijoIzquierdo()){    
                nodoActual=nodoActual.getHijoDerecho();
            }
        }
    }
    */
    @Override
    public List<K> recorridoEnInOrden(){
        List<K> recorrido=new ArrayList<>();
        recorridoEnInOrden(this.raiz,recorrido);
        return recorrido;
    }
    
    private void recorridoEnInOrden(NodoBinario<K,V> nodoActual,List<K> recorrido){
        if(NodoBinario.esNodoVacio(nodoActual)){   //caso base
           return;
        }
        recorridoEnInOrden(nodoActual.getHijoIzquierdo(),recorrido);
        recorrido.add(nodoActual.getClave());
        recorridoEnInOrden(nodoActual.getHijoDerecho(),recorrido);
    };
    
    /**
     * Recorrido en PostOrden 
     * De Forma Iterativa
     * @return 
     */
    @Override
    public List<K> recorridoEnPostOrdenI() {
        List<K> recorrido=new ArrayList<>();
        if(this.esArbolVacio()){
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodos=new Stack<>();
        NodoBinario<K,V> nodoActual=this.raiz;
        // apila todos los nodos padres hasta el ultimo nodo izquierdo hoja
        meterPilaParaPostOrden(pilaDeNodos,nodoActual);
        // empezamos a iteramos sobre la pila
        while(!pilaDeNodos.isEmpty()){
            // sacamos el nodo tope
            // el nodo tope es el ultimo nodo izquierdo del arbol
            nodoActual=pilaDeNodos.pop();
            // y agregamos al array la clave del nodoActual
            recorrido.add(nodoActual.getClave());
            // preguntamos si todavia hay mas nodo en la pila
            if(!pilaDeNodos.isEmpty()){
                // referenciamos al nodoTope
                NodoBinario<K,V> nodoDelTope=pilaDeNodos.peek();
                // preguntamos si tiene hijo derecho y si es diferente del nodoActual
                // caso1: si tiene hijo debemos apilar todos sus nodos izquierdos 
                //      si en caso es un subarbol
                if(!nodoDelTope.esVacioHijoDerecho() && 
                        nodoDelTope.getHijoDerecho()!=nodoActual){
                    // le decimos que apile desde el hijo derecho del nodotope 
                    meterPilaParaPostOrden(pilaDeNodos,nodoDelTope.getHijoDerecho());
                }
            }
        }
        return recorrido;
        
    }
    
    /**
     * Recorrido En PostOrden
     * De Forma Recursiva
     * @return 
     */
    @Override
    public List<K> recorridoEnPostOrden(){
        List<K> recorrido=new ArrayList<>();
        recorridoEnInOrden(this.raiz,recorrido);
        return recorrido;
    }
    
    private void recorridoEnPostOrden(NodoBinario<K,V> nodoActual,List<K> recorrido){
        if(NodoBinario.esNodoVacio(nodoActual)){   //caso base
           return;
        }
        recorridoEnPostOrden(nodoActual.getHijoIzquierdo(),recorrido);
        recorridoEnPostOrden(nodoActual.getHijoDerecho(),recorrido);
        recorrido.add(nodoActual.getClave());
    };
    
    private void meterPilaParaPostOrden(Stack<NodoBinario<K,V>> pilaDeNodos,NodoBinario<K,V> nodoActual){
        // recorremos los nodos para apilar
        while(!NodoBinario.esNodoVacio(nodoActual)){
            pilaDeNodos.push(nodoActual);
            if(!nodoActual.esVacioHijoIzquierdo()){
                nodoActual=nodoActual.getHijoIzquierdo();
            }else{
                nodoActual=nodoActual.getHijoDerecho();
            }
        }
    }
    
    @Override
    public void vaciarArbol() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NodoBinario<K,V> getRaiz() {
        return this.raiz;
    }



    @Override
    public int cantidadDeHijosVacios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int cantidadDeHijosVaciosDesdeNivel(int nivel) {
        int cantidadNodos=cantidadNodosConUnHijoNoVacio(this.raiz);
        return cantidadNodos;
    }

    @Override
    public int cantidadNodosConUnHijoNoVacio() {
        int cantidadNodos=cantidadNodosConUnHijoNoVacio(this.raiz);
        return cantidadNodos;
    }
    

    public int cantidadNodosConUnHijoNoVacio(NodoBinario<K,V> nodoActual) {
        if(NodoBinario.esNodoVacio(nodoActual)){   //caso base
           return 0;
        }
        
        
        int cantidadDeLosHijosIzquierdo=cantidadNodosConUnHijoNoVacio(nodoActual.getHijoIzquierdo());
        int cantidadDeLosHijosDerecho=cantidadNodosConUnHijoNoVacio(nodoActual.getHijoDerecho());
        // nodoactual
        if((nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()) || 
                (!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo())){
            return cantidadDeLosHijosIzquierdo+cantidadDeLosHijosDerecho+1;
        }/*else if(!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()){
            return cantidadDeLosHijosIzquierdo+cantidadDeLosHijosDerecho+1;
        }*/
        return cantidadDeLosHijosIzquierdo+cantidadDeLosHijosDerecho;
    }
    
    // 13
    @Override
    public int numeroHijosVacioLogicaInOrden() {
        if (this.esArbolVacio()) {
            return 0;
        }
        
        int cant = 0;
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        NodoBinario<K, V> nodoActual = this.raiz;
        this.insertarEnPilaParaInOrden(nodoActual, pilaDeNodos);
        
        while (!pilaDeNodos.isEmpty()) {            
            nodoActual = pilaDeNodos.pop();    
            if (nodoActual.esVacioHijoIzquierdo()) {
                cant++;
            }
            if (!nodoActual.esVacioHijoDerecho()) {
                this.insertarEnPilaParaInOrden(nodoActual.getHijoDerecho(), pilaDeNodos);
            } else {
                cant++;
            }
        }
        
        return cant;
    }
    
    private void insertarEnPilaParaInOrden(NodoBinario<K, V> nodoActual, Stack<NodoBinario<K, V>> pilaDeNodos) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {           
            pilaDeNodos.push(nodoActual);
            nodoActual = nodoActual.getHijoIzquierdo();
        }  
    }
    
    //14
    @Override
    public K predecesorInOrden() {
        return predecesorInOrden(this.raiz.getHijoDerecho().getHijoDerecho());
    }
    
    private K predecesorInOrden(NodoBinario<K, V> nodoActual) {
        K predecesor = (K)NodoBinario.nodoVacio();
        if(NodoBinario.esNodoVacio(nodoActual)) {
            return predecesor;
        }

        K claveActual = nodoActual.getClave();
        NodoBinario<K, V> nodoPredecesor = this.raiz;
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();

        if (claveActual.compareTo(nodoPredecesor.getClave()) > 0) {
            this.insertarEnPilaParaInOrden(nodoPredecesor.getHijoDerecho(), pilaDeNodos);
        } else if (claveActual.compareTo(nodoPredecesor.getClave()) < 0) {
            this.insertarEnPilaParaInOrden(nodoPredecesor.getHijoIzquierdo(), pilaDeNodos);
        } else {
            return predecesor;
        }

        NodoBinario<K, V> nodoAux;
        while (!pilaDeNodos.isEmpty()) {
            nodoAux = nodoPredecesor;
            nodoPredecesor = pilaDeNodos.pop();

            if (nodoPredecesor.getClave().compareTo(claveActual) == 0) {

                if (pilaDeNodos.isEmpty()) {
                    predecesor = nodoAux.getClave();
                } else {
                    nodoPredecesor = pilaDeNodos.pop();
                    predecesor = nodoPredecesor.getClave();
                    return predecesor;
                }
            }
            if (!nodoPredecesor.esVacioHijoDerecho()) {
                this.insertarEnPilaParaInOrden(nodoPredecesor.getHijoDerecho(), pilaDeNodos);
            }
        }
        return predecesor;
    }
    
    @Override
    public boolean esSimilar(ArbolBinarioBusqueda<K, V> arbol2) {
        if (this.esArbolVacio() && arbol2.esArbolVacio()) {
            return true;
        }
        if ((!this.esArbolVacio() && arbol2.esArbolVacio())
            || (this.esArbolVacio() && !arbol2.esArbolVacio())) {
            return false;
        }

        Queue<NodoBinario<K, V>> colaDeNodos1 = new LinkedList<>();
        Queue<NodoBinario<K, V>> colaDeNodos2 = new LinkedList<>();
        colaDeNodos1.offer(this.raiz);
        colaDeNodos2.offer(arbol2.raiz);

        while (!colaDeNodos1.isEmpty() && !colaDeNodos2.isEmpty()) {
            NodoBinario<K, V> nodoActual1 = colaDeNodos1.poll();
            NodoBinario<K, V> nodoActual2 = colaDeNodos2.poll();

            if (!nodoActual1.esVacioHijoIzquierdo()&& !nodoActual2.esVacioHijoIzquierdo()) {
                colaDeNodos1.offer(nodoActual1.getHijoIzquierdo());
                colaDeNodos2.offer(nodoActual2.getHijoIzquierdo());
            } else {
                if ((!nodoActual1.esVacioHijoIzquierdo() && nodoActual2.esVacioHijoIzquierdo()) || 
                        (nodoActual1.esVacioHijoIzquierdo() && !nodoActual2.esVacioHijoIzquierdo())) {
                    return false;
                }
            }

            if (!nodoActual1.esVacioHijoDerecho()&& !nodoActual2.esVacioHijoDerecho()) {
                colaDeNodos1.offer(nodoActual1.getHijoDerecho());
                colaDeNodos2.offer(nodoActual2.getHijoDerecho());
            } else {
                if ((!nodoActual1.esVacioHijoDerecho() && nodoActual2.esVacioHijoDerecho()) || 
                        (nodoActual1.esVacioHijoDerecho() && !nodoActual2.esVacioHijoDerecho())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean nodosCompletosEnElNivelN(int nivelN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean esSimilar(ArbolMViasBusqueda<K, V> arbol2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
