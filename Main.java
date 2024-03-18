
/**
 * Universidad del Valle de Guatemala 
 * Algoritmos y estructuras de datos
 * @author Adrián López 21357
 * @version 1.0
 */

 
 //imports
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.stream.Stream;
 //http://www.cs.williams.edu/JavaStructures/doc/structure5/structure5/Association.html
 //https://stackoverflow.com/questions/52641366/how-to-make-an-object-of-association-comparable-by-only-one-generic-parameter-ty
 
 
 public class Main {
     public static void main(String[] args) {
         ArrayList<Association> dictionary = new ArrayList<>();
         ArrayList<String> words = readDictionary();
         ArrayList inOrder = new ArrayList();
         String[] divider;
         String tempWord;
         String key = "";
         String value = "";
         Scanner readLine = new Scanner(System.in);
 
 
         /*
          * Se itera sobre la lista de palabras obtenidas del diccionario. Cada palabra se procesa eliminando 
          * los paréntesis y dividiéndola en la clave y el valor. Luego se agregan a la lista dictionary como 
          * una nueva asociación, donde tanto la clave como el valor se convierten a mayúsculas.
          */
         for (int i = 0; i < words.size(); i++) {
             tempWord = words.get(i);
             tempWord = tempWord.replaceAll("\\(", "");
             tempWord = tempWord.replaceAll("\\)", "");
             divider = tempWord.split(",");
             key = divider[0];
             value = divider[1];
             dictionary.add(new Association(key.toUpperCase(), value.toUpperCase()));
         }
         
         /*
          * Se crea un árbol binario de búsqueda (BinarySearchTree) y se agregan todas las asociaciones 
          * del diccionario al árbol.
          */
         BinarySearchTree bTree = new BinarySearchTree<>();
         for (int i = 0; i < dictionary.size(); i++) {
             bTree.add(dictionary.get(i));
         }
 
         /*
          * Se realiza un recorrido en orden del árbol binario (bTree) y se almacenan las 
          * palabras en la lista inOrder. Luego, se imprime esta lista en la consola.
          */
         //=============In order traversing===============
         bTree.traverseInOrder(bTree.getRoot(),inOrder);
         bTree.printList(inOrder);
         //===============================================
          
         /*
          * Se llama al método readText() para leer el texto desde un archivo y se almacena en 
          * la variable text. Se inicializan algunas variables temporales para procesar el texto.
          */
         String text = readText();
         boolean isInDictionary = false;
         String[] splittedText = text.split(" ");
         String newText = "";
         
         /*
          * Se itera sobre las palabras del texto. Por cada palabra, se busca en el diccionario para 
          * encontrar su equivalente en español. Si se encuentra en el diccionario, se agrega su equivalente 
          * a newText, de lo contrario, se agrega la palabra original entre asteriscos.
          */
         for (int i = 0; i < splittedText.length; i++) {
             isInDictionary = false;
             for (int j = 0; j < dictionary.size(); j++) {
                 if (splittedText[i].equals(dictionary.get(j).key)) {
                     newText += dictionary.get(j).value + " ";
                     isInDictionary = true;
                 }
             }
             if (isInDictionary == false) {
                 newText += "*" + splittedText[i] + "*" + " ";
             }
         }
 
         System.out.println("\n" + newText);
 
         /*
         System.out.println("#### Inorder Traversal ####");
         List<Integer> inOrderList = new ArrayList<>();
         bTree.traverseInOrder(bTree.getRoot(), inOrderList);
         bTree.printList(inOrderList);*/
     }
 
     //Referencias:
     // https://www.mkyong.com/java8/java-8-stream-read-a-file-line-by-line/
     //https://www.mkyong.com/java8/java-8-foreach-examples/
     public static ArrayList<String> readDictionary() {
         String path = "./Dictionary.txt";
         ArrayList<String> words = new ArrayList<>();
 
         try (Stream<String> fileStream = Files.lines(Paths.get(path))) {
             fileStream.forEach(item -> {
                 words.add(item.toUpperCase());
             });
 
 
         } catch (IOException e) {
             e.printStackTrace();
         }
         return words;
     }
 
     public static String readText() {
         String path = "./Text.txt";
         String words =  "";
         ArrayList<String> text = new ArrayList<>();
 
         try (Stream<String> fileStream = Files.lines(Paths.get(path))) {
             fileStream.forEach(item -> {
                 text.add(item.toUpperCase());
             });
 
 
         } catch (IOException e) {
             e.printStackTrace();
         }
 
         for (int i = 0; i < text.size(); i++) {
             words += text.get(i) + " ";
         }
         System.out.println(words);
         return words;
         
     }
 
 }