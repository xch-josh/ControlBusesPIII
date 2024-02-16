package Application.Componentes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class FuncionesEspeciales {
	public static int ContarLineasArchivo(String filePath) {
		int count = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			while (reader.readLine() != null) {
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return count;
    }
}
