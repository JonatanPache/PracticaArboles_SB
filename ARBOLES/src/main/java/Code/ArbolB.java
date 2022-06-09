/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;

import java.util.Stack;

/**
 *
 * @author jonatan
 * @param <K>
 * @param <V>
 */
public class ArbolB<K extends Comparable<K>,V> extends ArbolMViasBusqueda<K,V>{
    private final int nroMaximoDeDatos;
    private final int nroMinimoDeDatos;
    private final int nroMinimoDeHijos;
    
    public ArbolB(){
        super();
        this.nroMaximoDeDatos=2;
        this.nroMinimoDeHijos=2;
        this.nroMinimoDeDatos=1;
    }
    
    public ArbolB(int orden) throws ExceptionOrdenInvalido{
        super(orden);
        this.nroMaximoDeDatos=orden-1;
        this.nroMinimoDeDatos=this.nroMaximoDeDatos/2;
        this.nroMinimoDeHijos=this.nroMinimoDeDatos+1;
    }
    
    @Override
    public void Insertar(K claveAInsertar, V valorAInsertar) throws NullPointerException {
        if (claveAInsertar==null){
            throw new NullPointerException("No se permite datos nulos");
        }
        if (valorAInsertar==null){
            throw new NullPointerException("No se permite datos nulos");
        }
        //si el arbol es vacio
        // creamos un nuevo nodo y la raiz lo apuntamos
        // al nuevo nodo con los datos a insertar
    
        if(this.esArbolVacio()){
            this.raiz=new NodoMVias<>(this.orden+1,claveAInsertar,valorAInsertar);
            return;
        }
        
        Stack<NodoMVias<K,V>> pilaDeAncestros=new Stack<>();
        NodoMVias<K,V> nodoActual=this.raiz;
        while(!NodoMVias.esNodoVacio(nodoActual)){
            int posicionDeClaveAInsertar=this.obtenerPosicionDeClave(nodoActual,claveAInsertar);
            if(posicionDeClaveAInsertar!=ArbolMViasBusqueda.POSICION_NO_VALIDA){
                 nodoActual.setValor(posicionDeClaveAInsertar, valorAInsertar);
                 //return
                 nodoActual=NodoMVias.nodoVacio();
            }else if(nodoActual.esHoja()){
                // es hoja todavia hay espacio para insertar
                insertarClaveValorHijo(nodoActual, claveAInsertar, valorAInsertar);
                // el maximo de datos puede ser pasarse
                // ajustar si en caso pasa
                if(nodoActual.cantidadClavesNoVacios()>this.nroMaximoDeDatos){
                    // crea dos nodos nuevos
                    this.dividir(nodoActual,pilaDeAncestros);
                }
                //return
                nodoActual=NodoMVias.nodoVacio();
            }else{
                // si no es hoja, busco la posicion por donde bajar
                // es decir el nodo hijo que debo bajar
                int posicionPorDondeBajar=this.getPosicionPorDondeBajar(nodoActual, claveAInsertar);
                pilaDeAncestros.push(nodoActual);
                nodoActual=nodoActual.getHijo(posicionPorDondeBajar);
            }
            
        }
        
    }
    
    protected void dividir(NodoMVias<K,V> nodoActual,Stack <NodoMVias<K,V>> pilaDeAncestros){
        // calculamos el valor medio del nodoActual 
        //tendremos la posicion de la clave
        int posicionClaveNuevoPadre=(this.orden/2)-1;
        NodoMVias<K,V> nodoPadre;
        // preguntamos si tiene ancestros o padres
        if(pilaDeAncestros.isEmpty()){
            // si no tiene padre y hay que dividir
            //entonces debemos crear un nuevo padre con la clave media respectiva 
            nodoPadre=new NodoMVias<>(this.orden+1,nodoActual.getClave(posicionClaveNuevoPadre),nodoActual.getValor(posicionClaveNuevoPadre));
        }else{
            nodoPadre=pilaDeAncestros.pop();
            insertarClaveValorHijo(nodoPadre,nodoActual.getClave(posicionClaveNuevoPadre),nodoActual.getValor(posicionClaveNuevoPadre));
            

        }
        NodoMVias<K,V> nodoAnt=new NodoMVias<>(this.orden+1);
        NodoMVias<K,V> nodoSuc=new NodoMVias<>(this.orden+1);
        // insertamos claves antes de la clave padre en el nodoActual en un 
        // nuevo nodo anterior
        for(int i=posicionClaveNuevoPadre-1;i>=0;i--){
            insertarClaveValorHijo(nodoAnt,nodoActual.getClave(i),nodoActual.getValor(i));
            // arreglar porque la posicion 0 coordina con el hijo pero
            //que pasaria si el arbol es de orden 6
            nodoAnt.setHijo(nodoAnt.cantidadClavesNoVacios()-1, nodoActual.getHijo(i));
        }
        if(!nodoActual.esHoja()){
            nodoAnt.setHijo(nodoAnt.cantidadClavesNoVacios(), nodoActual.getHijo(posicionClaveNuevoPadre));
        }
        // insertamos claves despues de la clave padre en el nodoActual en un 
        // nuevo nodo anterior
        for(int j=posicionClaveNuevoPadre+1;j<nodoActual.cantidadClavesNoVacios();j++){
            insertarClaveValorHijo(nodoSuc,nodoActual.getClave(j),nodoActual.getValor(j));
            nodoSuc.setHijo(nodoSuc.cantidadClavesNoVacios()-1, nodoActual.getHijo(j));
        }
        if(!nodoActual.esHoja()){
            nodoSuc.setHijo(nodoSuc.cantidadClavesNoVacios(), nodoActual.getHijo(nodoActual.cantidadClavesNoVacios()));
        }
        //int posicionHijoAnterior=this.getPosicionPorDondeBajar(nodoPadre, nodoAnt.getClave(0));
        nodoPadre.setHijo(getPosicionPorDondeBajar(nodoPadre, nodoAnt.getClave(0)), nodoAnt);
        nodoPadre.setHijo(getPosicionPorDondeBajar(nodoPadre, nodoSuc.getClave(0)), nodoSuc);
        
        this.raiz=nodoPadre;
        if(nodoPadre.cantidadClavesNoVacios()>this.nroMaximoDeDatos){
            dividir(nodoPadre,pilaDeAncestros);
        }
    }
    
    
    protected void insertarClaveValorHijo(NodoMVias<K,V> nodoActual,K claveaInsertar,V valoraInsertar){
        int j=0;
        boolean claveaInsertadoEnNodoActual=false;
        while(j<nodoActual.cantidadClavesNoVacios() && claveaInsertadoEnNodoActual==false){
            K claveActual=nodoActual.getClave(j);
            if(claveaInsertar.compareTo(claveActual)<0){
                // recoremos las claves del nodo actual, para insertar una nueva clave 
                for(int i=nodoActual.cantidadClavesNoVacios();i>0 && i>j;i--){
                    nodoActual.setClave(i, nodoActual.getClave(i-1));
                    nodoActual.setValor(i, nodoActual.getValor(i-1));
                    if(!nodoActual.esHoja()){
                        nodoActual.setHijo(i+1, nodoActual.getHijo(i));
                    }
                }
                //insertar la nueva clave en la posicion correspondiente
                nodoActual.setClave(j, claveaInsertar);
                nodoActual.setValor(j, valoraInsertar);
                // cambiamos de estado si la clave se inserto
                claveaInsertadoEnNodoActual=true;
            }
            // si no es menor entonces incrementa
            j++;
        }
        // si todavia no se inserto entonces debemos insertar en la ultima posicion del nodoActual
        // o tambien el nodo esta vacia entonces insertar en la posicion 0 la primera clave
        if(claveaInsertadoEnNodoActual==false){
            nodoActual.setClave(j, claveaInsertar);
            nodoActual.setValor(j, valoraInsertar);
        }
    }
    
    @Override
    public V eliminar(K claveAEliminar) throws NullPointerException,ExceptionClaveNoExiste {
        if(claveAEliminar==null){
            throw new NullPointerException("No se puede eliminar clave nula");
        }

        Stack<NodoMVias<K,V>> pilaDeAncestros=new Stack<>();
        NodoMVias<K,V> nodoActual=this.buscarNodoClaveAEliminar(claveAEliminar,pilaDeAncestros);
        if(NodoMVias.esNodoVacio(nodoActual)){
            throw new ExceptionClaveNoExiste();
        }
        int posicionDeLaClaveAEliminar=super.obtenerPosicionDeClave(nodoActual, claveAEliminar);
        V valorARetornar=nodoActual.getValor(posicionDeLaClaveAEliminar);
        
        // nodo actual es hoja
        if(nodoActual.esHoja()){
            super.eliminarClaveValorDePosicion(nodoActual, posicionDeLaClaveAEliminar);
            if(nodoActual.cantidadClavesNoVacios()<this.nroMinimoDeDatos){
                // si el nodo actual es la raiz
                // no tiene padre es decir la pila de ancestros esta vacia
                if(pilaDeAncestros.isEmpty()){
                    // si no tiene claves entonces vaciamos el arbol
                    // si tiene claves no hacemos nada lo dejamos asi
                    if(nodoActual.cantidadClavesNoVacios()==0){
                        super.vaciarArbol();
                    }
                }else{
                    // el nodo actual no es la raiz
                    // y tiene minimo de datos o menor
                    this.prestarseOFusionarse(nodoActual,pilaDeAncestros);
                }
                
            }
        }else{
            // en caso de que el nodo actual no es hoja
            // buscamos su predecesor
            pilaDeAncestros.push(nodoActual);
            NodoMVias<K,V> nodoDelPredecesor=this.obtenerNodoDeClavePredecesora(pilaDeAncestros,
                    nodoActual.getHijo(posicionDeLaClaveAEliminar));
            int posicionDeClavePredecesora=nodoDelPredecesor.cantidadClavesNoVacios()-1;
            K clavePredecesora=nodoDelPredecesor.getClave(posicionDeClavePredecesora);
            V valorPredecesora=nodoDelPredecesor.getValor(posicionDeClavePredecesora);
            super.eliminarClaveValorDePosicion(nodoDelPredecesor, posicionDeClavePredecesora);
            nodoActual.setClave(posicionDeLaClaveAEliminar,clavePredecesora);
            nodoActual.setValor(posicionDeLaClaveAEliminar,valorPredecesora);
            if(nodoDelPredecesor.cantidadClavesNoVacios()<this.nroMinimoDeDatos){
                this.prestarseOFusionarse(nodoDelPredecesor,pilaDeAncestros);
            }
        }
        return valorARetornar;
    }
    
    protected NodoMVias<K,V> buscarNodoClaveAEliminar(K claveAEliminar,Stack<NodoMVias<K,V>> pilaDeAncestros){
        NodoMVias<K,V> nodoActual=this.raiz;
        while(!NodoMVias.esNodoVacio(nodoActual)){
            
            boolean huboCambioDeNodoActual=false;
            for(int i=0;i<nodoActual.cantidadClavesNoVacios()&&
                    !huboCambioDeNodoActual;i++){
                //K claveNodoActual=
                if(nodoActual.getClave(i).compareTo(claveAEliminar)==0){
                    return nodoActual;
                }
                if(claveAEliminar.compareTo(nodoActual.getClave(i))<0){
                    pilaDeAncestros.push(nodoActual);
                    nodoActual=nodoActual.getHijo(i);
                    huboCambioDeNodoActual=true;
                }
            }
            if(!huboCambioDeNodoActual){
                pilaDeAncestros.push(nodoActual);
                nodoActual=nodoActual.getHijo(nodoActual.cantidadClavesNoVacios());
            }
        }
        return nodoActual;
    }
    
    protected void prestarseOFusionarse(NodoMVias<K,V> nodoActual,Stack<NodoMVias<K,V>> pilaDeAncestros){
        NodoMVias<K,V> nodoPadre=pilaDeAncestros.pop();
        int posicionDelNodoActualEliminado=this.obtenerPosicionNodoEliminado(nodoPadre,nodoActual);
        //Verificamos si podemos PRESTARSNOS DE SU HERMANO
        // Caso contrario seria mejor fusionar
        if(!nodoPadre.esHijoVacio(posicionDelNodoActualEliminado+1)&& 
                nodoPadre.getHijo(posicionDelNodoActualEliminado+1).cantidadClavesNoVacios()>=this.nroMinimoDeDatos){
            // inserto la clave de su hermano del nodo vacio
            insertarClaveValorHijo(nodoActual,nodoPadre.getClave(posicionDelNodoActualEliminado),
                    nodoPadre.getValor(posicionDelNodoActualEliminado));
            
            nodoPadre.setClave(posicionDelNodoActualEliminado,
                    nodoPadre.getHijo(posicionDelNodoActualEliminado+1).getClave(0));
            nodoPadre.setValor(posicionDelNodoActualEliminado,
                    nodoPadre.getHijo(posicionDelNodoActualEliminado+1).getValor(0));
            super.eliminarClaveValorDePosicion(nodoPadre.getHijo(posicionDelNodoActualEliminado+1),
                    0);
            pilaDeAncestros.push(nodoPadre);
        }else{
            
        }
    }
    
    protected int obtenerPosicionNodoEliminado(NodoMVias<K,V> nodoPadre,NodoMVias<K,V> nodoActual){
        for(int i=0;i<nodoPadre.cantidadClavesNoVacios();i++){
            if(nodoPadre.getHijo(i)==nodoActual){
                return i;
            }
        }
        return -1;
    }
    
    protected NodoMVias<K,V> obtenerNodoDeClavePredecesora(Stack<NodoMVias<K,V>> pilaDeAncestros,NodoMVias<K,V> nodoActual){
        while(!nodoActual.esHoja()){
            pilaDeAncestros.push(nodoActual);
            nodoActual=nodoActual.getHijo(nodoActual.cantidadClavesNoVacios());
        }
        return nodoActual;
    }
}
