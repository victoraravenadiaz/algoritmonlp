import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		Texto t = new Texto("¿Cuánto frío hace hoy, cuánto frío hará mañana y cuánto calor hará pasado?");
		System.out.println(t.ocurrencia());
	}

}
