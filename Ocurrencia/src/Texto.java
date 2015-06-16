
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
	
	public String[] ocurrencia(){
		
		String cadena = this.getTexto();
		int cp = 0; // Cantidad de palabras
        
        // Recorremos en busca de espacios
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == ' ') { // Si es un espacio
                cp++; // Aumentamos en uno la cantidad de palabras
            }
        }
         
        // Por ej. "Hola a  todos" tiene 2 espacios y 2 + 1 palabras
        String[] palabras = new String[cp + 1];
        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = ""; // Se inicializa en "" en lugar de null (defecto)
        }
         
        int ind = 0; // Creamos un índice para las palabras
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == ' ') { // Si hay un espacio
                ind++; // Pasamos a la siguiente palabra
                continue; // Próximo i
            }
            palabras[ind] += cadena.charAt(i); // Sino, agregamos el carácter a la palabra actual
        }
		
		String[] ocurrencias = new String[palabras.length];
		int[] contadores = new int[palabras.length];
		int contPalabrasRep = 0;
		int contador = 1;
		
		for(int i = 0; i < palabras.length; i++){
			for(int j = i+1; j < palabras.length; j++){
				if(palabras[j].equals(palabras[i])){ //si hay una palabra repetida
					contador++; //se aumenta a 1 el contador
					palabras[j] = ""; //se anula la palabra repetida
				}
			}
			
			if(palabras[i].equals("")){ //si la palabra está anulada por haber sido repetida
				contadores[i] = 0; //se anula a 0 los contadores
				contPalabrasRep++; //se aumenta a 1 el contador de palabras repetidas
			}else{
				contadores[i] = contador; //se agrega el valor actual del contador 
			}
			
			contador = 1;
			ocurrencias[i] = palabras[i] + ": " + contadores[i];
		}
		System.out.println("Contador palabras repetidas: " + contPalabrasRep);
		
		return ocurrencias;
	}
}
