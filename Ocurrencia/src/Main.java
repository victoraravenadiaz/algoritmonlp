public class Main {

	public static void main(String[] args) {
		
		
		Texto t = new Texto("hola a los que est�n aqu� y a los que est�n all� y acuy�");

        String[] p = t.ocurrencia(); 
         
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]);
        }
	}

}
