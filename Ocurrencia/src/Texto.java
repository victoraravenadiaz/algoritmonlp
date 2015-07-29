import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;


public class Texto {

	private String texto;

	public Texto() {
	}

	public Texto(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	/**
	 * Método para remover los tildes (acentos gráficos)
	 * de un texto pasado por parámetro
	 * @param texto
	 * @return cadena de texto sin acentos
	 */
	
	public String removerAcentos(String texto) {
		
		String sinAcentos = "";
		// Descomposición canónica
	    String normalized = Normalizer.normalize(texto, Normalizer.Form.NFD);
	    // Nos quedamos únicamente con los caracteres ASCII
	    Pattern pattern = Pattern.compile("\\P{ASCII}+");
	    sinAcentos = pattern.matcher(normalized).replaceAll("");
	    
	    return sinAcentos;
	    
	}
	
	/**
	 * Método para remover los caracteres y símbolos
	 * especiales de un texto (si es que los hubiera)
	 * pasados por parámetro
	 * @param texto
	 * @return cadena de texto sin caracteres
	 */
	public String removerCaracteres(String texto) {
		String sinChars = texto;
		// Cadena de caracteres original a sustituir.
	    String caracteres = ",.:-'¡!¿?/()[]";
	    // Cadena de caracteres ASCII que reemplazarán los originales.
	    for (int i=0; i<caracteres.length(); i++) {
	        // Reemplazamos los caracteres especiales.
	        sinChars = sinChars.replaceAll("\\"+caracteres.charAt(i), "");
	    }
	    return sinChars;
	}
	
	/**
	 * Método para devolver una lista
	 * de palabras del texto ingresado
	 * por parámetro en el constructor
	 * @return arreglo de String
	 */
	public String[] palabras(){
			
			String cadena = this.getTexto(); //iniciar con cadena de texto introducida
			cadena = cadena.toLowerCase(); //convierte mayúsculas a minúsculas, sobre todo si hay dos o más palabras iguales, pero una o algunas de ellas con mayúsculas
			cadena = this.removerCaracteres(cadena); //remueve caracteres si es que los hubiera
	        cadena = this.removerAcentos(cadena); //remueve acentos si es que los hubiera
	        
	        String[] palabras = cadena.split(" "); //convierte el String en un arreglo de Strings separándolo por espacios
	        
	        Set<String> todos = new HashSet<String>(); //Se inicia un hashset que hará que no se repita ninguna palabra
			
			for (String s : palabras) {
			      todos.add(s); //se llena el hashset con cada palabra del arreglo de String
			}
			
			String[] nuevoPalabras = todos.toArray(new String[0]); //se pasa el hashset a un nuevo arreglo de palabras
			Arrays.sort(nuevoPalabras); //Se ordenan las palabras por orden alfabético
			
			return nuevoPalabras; //Se retorna el nuevo arreglo de String
			
		}
	
	/**
	 * Método que devuelve una lista de frecuencias
	 * de cada palabra del texto ingresado por parámetro
	 * en el constructor
	 * @param cdns
	 * @return arreglo de enteros
	 */
	public int[] frecuencias(String[] cdns){
		
		String cadena = this.getTexto(); //iniciar con cadena de texto introducida
		cadena = cadena.toLowerCase(); //convierte mayúsculas a minúsculas, sobre todo si hay dos o más palabras iguales, pero una o algunas de ellas con mayúsculas
		cadena = this.removerCaracteres(cadena); //remueve caracteres si es que los hubiera
        cadena = this.removerAcentos(cadena); //remueve acentos si es que los hubiera
        
        String[] palabras = cadena.split(" "); //convierte el String en un arreglo de Strings separándolo por espacios
		Arrays.sort(palabras); //el arreglo de String se ordena alfabéticamente
        
        int[] contadores = new int[cdns.length]; //se declara un arreglo de enteros
        int cont; //se declara un nuevo entero contador
        
        //se recorre el arreglo de String pasado por parámetro
        for(int i = 0; i < cdns.length; i++) {
        	cont = 0; //en cada inicio del ciclo, el contador se inicia a 0
        	
        	//se recorre la cadena de texto descompuesta en un arreglo
        	for(int j = 0; j < palabras.length; j++) {
        		/**
        		 * Si el elemento actual de la cadena ingresada
        		 * por parámetro es igual al de la cadena
        		 * descompuesta
        		 */
            	if(cdns[i].equals(palabras[j])) {
            		cont++; //el contador se aumenta a 1
            	}	
        	}
        	contadores[i] = cont; //en cada fin del ciclo,
        						//al elemento actual del arreglo de contadores
        						//se le asigna el valor final del contador
        }
		
		return contadores; //se retorna el arreglo de contadores
		
	}
		
	/**
	 * Método que devuelve en una cadena de texto 
	 * la descomposición del texto ingresado por parámetro en el constructor
	 * y la frecuencia de cada palabra.
	 * @return cadena de texto con palabras y sus frecuencias
	 */
	public String ocurrencia(){
		
		String[] palabras = this.palabras(); //se inicia un arreglo de String 
											//de acuerdo al método palabras() llamado
		
		int[] contadores = this.frecuencias(palabras); //se inicia un arreglo de int 
														//de acuerdo al método frecuencias 
														//llamado con el arreglo de String
														//pasado por parámetro
		/**
		 * Se inicia una cadena de texto
		 * con el texto ingresado por el constructor
		 * y separado por líneas
		 */
		String ocurrencia = this.getTexto() + "\n";
		ocurrencia += "Ocurrencia de palabras \n";
		
		//Se recorre ambos arreglos
		for(int i = 0; i < palabras.length; i++){
			//se concatena con los valores actuales de amobs arreglos 
			ocurrencia += palabras[i] + ": " + contadores[i] + "\n";
		}
		
		return ocurrencia; //se retorna la cadena ocurrencia
		
	}
}
