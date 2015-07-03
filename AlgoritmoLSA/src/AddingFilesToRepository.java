import tml.storage.*;

public class AddingFilesToRepository {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

        Repository repository = new Repository("C:\\BerryDumais");
        // ACA SE AGREGA EL ARCHIVO DE TEXTO PUNTUAL 
        // TOMA EL DIRECTORIO COMPLETO EL ARCHIVO PUNTUAL NO
       repository.addDocumentsInFolder("C:\\handbookOfLSA/");
        
        System.out.println("Documents added to repository successfully!");

		
	}

}
