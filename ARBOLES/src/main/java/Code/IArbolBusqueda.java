/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Code;

import java.util.List;

/**
 *
 * @author vivia
 */
public interface IArbolBusqueda<K extends Comparable <K>,V> {
    NodoBinario<K,V> getRaiz();
    void Insertar(K claveAInsertar,V valorAInsertar) throws NullPointerException;
    void InsertarR(K claveAInsertar,V valorAInsertar) throws NullPointerException;
    V eliminar(K claveAEliminar) throws ExceptionClaveNoExiste;
    V buscar(K claveABuscar);
    void vaciarArbol();
    boolean contiene(K claveAVuscar);
    int size();
    int altura();
    int cantHojas();
    boolean esArbolVacio();
    K minimo();
    K maximo();
    int cantHijosDerechosNivel(int nivel);
    int contarNodosConHijosDerecho();
    int contarNodosConHijosDerechoR();
    int contarNodosConHijosIzquierdoR();
    int contarNodosConHijosIzquierdo();
    int cantidadDeHijosVacios();
    int cantidadDeHijosVaciosDesdeNivel(int nivel);
    List<K> recorridoPorNiveles();
    List<K> recorridoEnPreOrden();
    List<K> recorridoEnInOrden();
    List<K> recorridoEnPostOrden();
    List<K> recorridoEnPostOrdenI();
    int cantidadNodosConUnHijoNoVacio();
    int numeroHijosVacioLogicaInOrden();
    K predecesorInOrden();
    boolean nodosCompletosEnElNivelN(int nivelN);
    boolean esSimilar(ArbolMViasBusqueda<K, V> arbol2);
    boolean esSimilar(ArbolBinarioBusqueda<K, V> arbol2);
}
