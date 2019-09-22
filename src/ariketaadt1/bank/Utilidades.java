
package ariketaadt1.bank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Utilidades {
  
    //Función para la captura de palabras por teclado
    static String introducirCadena() throws IOException{
	String cadena="";
	InputStreamReader entrada = new InputStreamReader(System.in);
	BufferedReader teclado = new BufferedReader(entrada);
				
	try {
            cadena = teclado.readLine();
	} catch (IOException er){
            System.out.println("Error al introducir datos");
            System.exit(0);				
	}
	return cadena;
    }
			
    //Función para leer un Long
    public static Long leerLong() throws IOException {
	Long n= null;
				
        try {
            n = Long.parseLong(introducirCadena());
	} catch (NumberFormatException e) {
            System.out.println("No has introducido un número");
            System.exit(0);
	}
	return n;
	}
    
    //Función para leer un double
    public static double leerDouble() throws IOException {
	double n= 0;
				
        try {
            n = Double.parseDouble(introducirCadena());
	} catch (NumberFormatException e) {
            System.out.println("No has introducido un número");
            System.exit(0);
	}
	return n;
	}
    
    //Función para leer un double
    public static int leerInt() throws IOException {
	int n= 0;
				
        try {
            n = Integer.parseInt(introducirCadena());
	} catch (NumberFormatException e) {
            System.out.println("No has introducido un número");
            System.exit(0);
	}
	return n;
	}
}
 