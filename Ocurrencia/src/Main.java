public class Main {

	public static void main(String[] args) {
		
		
		Texto t = new Texto("hola a los que están aquí y a los que están allá y acuyá");

        String[] p = t.ocurrencia(); 
         
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]);
        }
	}

}
