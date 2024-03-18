
/**
 * Universidad del Valle de Guatemala 
 * Algoritmos y estructuras de datos
 * @author Adrián López 21357
 * @version 1.0
 */

 import java.util.Comparator;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Stack;
 
 /**
  *Referencia: https://stackoverflow.com/questions/11263244/java-how-do-i-implement-a-generic-binary-search-tree
  */
 
  /**Clase que busca el arbol  */
 
 /*
  * Se define una clase interna TNode que representa un nodo en el árbol. Cada nodo tiene un dato (data) de tipo genérico T,
  *  y referencias a los nodos hijos izquierdo (left) y derecho (right).
  */
 public class BinarySearchTree< T extends Comparable<Association>>  {
     public class TNode<T extends Comparable<T>> {
         T data;
         public TNode<T> left;
         public TNode<T> right;
 
         public TNode(T data){
             this.data = data;
         }
     }
 
     /*
      * Se declara un nodo raíz (root) que marca el comienzo del árbol. 
      * Se proporciona un método getRoot() para obtener la raíz del árbol.
      */
     private TNode root;
 
     public TNode getRoot() {
         return this.root;
     }
 
     /*
      * El método add() permite agregar una nueva asociación al árbol. Toma como 
      * argumento una asociación (Association) y crea un nuevo nodo (TNode) con esta asociación.
      */
     public void add(Association data) {
         TNode newNode = new TNode(data);
         if (root == null) {
             root = newNode;
         } else {
             TNode tempNode = root;
             TNode prev = null;
             while (tempNode != null) {
                 prev = tempNode;
                 if (data.compareTo((Association) tempNode.data) > 0) {
                     tempNode = tempNode.right;
                 } else {
                     tempNode = tempNode.left;
                 }
             }
             if (data.compareTo((Association) prev.data) < 0) {
                 prev.left = (TNode) newNode;
             } else {
                 prev.right = (TNode) newNode;
             }
 
         }
     }
     /*
      * El método traverseInOrder() realiza un recorrido en orden del árbol, 
      * lo que significa que visita los nodos en el orden izquierdo, raíz, derecho. 
      * Durante el recorrido, agrega las asociaciones visitadas a una lista de almacenamiento (storageList).
      */
     public void traverseInOrder(TNode root, List<Association> storageList) {
         if (root != null) {
             traverseInOrder(root.left, storageList);
             storageList.add((Association) root.data);
             traverseInOrder(root.right, storageList);
         }
 
     }
 
     /*
      * El método printList() imprime las asociaciones almacenadas en una lista, 
      * mostrando la clave y el valor de cada asociación.
      */
     public void printList(List<Association> list) {
         for (Association item : list) {
             System.out.println(item.key + " - " + item.value);
         }
     }
 
 }
