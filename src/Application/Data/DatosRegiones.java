package Application.Data;

import Application.Modelos.ModeloRegiones;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DatosRegiones {
	private final String rutaDatosRegiones = "C:/DatosControlBuses/regiones.txt";
	
	public DatosRegiones() throws IOException{
		File miArchivo = new File(rutaDatosRegiones);
		if (!miArchivo.exists()){//DETERMINA SI EL ARCHIVO ESPECIFICADO NO EXISTE
				miArchivo.createNewFile();//CREA EL ARCHIVO EN CASO DE NO EXISTIR
		}
	}
	
	private List<ModeloRegiones> ObtenerDatos() throws Exception{
		List<ModeloRegiones> lst = new ArrayList();
		FileReader archivo = new FileReader(rutaDatosRegiones);
		BufferedReader entrada = new BufferedReader(archivo);
		String datos = entrada.readLine();

		while (datos != null) {//RECORRE EL ARCHIVO MIENTRAS SE ENCUENTREN DATOS DENTRO DE ESTE
			String[] region = datos.split("\\|");//CREA UN ARREGLO CON LA LINEA ACTUAL DE LA LECTURA DEL ARCHIVO
			ModeloRegiones modelo = new ModeloRegiones();
				
			//ASIGNAMOS LOS DATOS CORRESPONDIENTES AL MODELO DE LAS REGIONES
			modelo.setIdRegion(Integer.parseInt(region[0]));
			modelo.setNombreRegion(region[1]);
			modelo.setRecorrido(region[2]);
			
			lst.add(modelo);//AGREGAMOS EL MODELO AL LIST
			
			datos = entrada.readLine();
		}

		archivo.close();//SE CIERRA LA LECTURA DEL ARCHIVO
			
		return lst;
	}
	
	private void Registrar(ModeloRegiones modelo){
		
                    try (FileWriter fw = new FileWriter(rutaDatosRegiones, true); PrintWriter salida = new PrintWriter(fw)) {
			FileReader fr = new FileReader(rutaDatosRegiones);
			String cadena = "";
			
			cadena = modelo.getNombreRegion().trim() + "|" + modelo.getRecorrido().trim();//CONCATENA TODOS LOS DATOS REQUERIDOS PARA EL REGISTRO EN UNA LINEA DE TEXTO SEPARANDO CADA CAMPO CON "|"
			
			salida.println(cadena);
			
			salida.close();
			fw.close();
                    } catch (IOException ex) {
                              System.out.println(ex.getMessage());                                                                  
                    }
	}
}
