public class Main {

	public static void main(String[] args) {
		
		
		Texto t = new Texto("Hola hola, chao chao, adi�s a todos.");
		String[] p = t.ocurrencia();
		
		for(int i = 0; i < p.length; i++){
			System.out.println(p[i]);
		}
	}

}
