package Application.Data;

import static Application.Componentes.FuncionesEspeciales.ContarLineasArchivo;
import Application.Modelos.ModeloRegiones;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DatosRegiones {
	private final String rutaDatosRegiones = "D:/DatosControlBuses/regiones.txt";
	private final String temp = "D:/DatosControlBuses/temp.txt";
	
	public DatosRegiones() throws Exception{
		File miArchivo = new File(rutaDatosRegiones);
		if (!miArchivo.exists()){//DETERMINA SI EL ARCHIVO ESPECIFICADO NO EXISTE
			if (miArchivo.createNewFile()){//CREA EL ARCHIVO EN CASO DE NO EXISTIR
				System.out.println("Se ha creado el archivo");
			}
			else
				System.out.println("No se ha creado el archivo");
		}
	}
	
	public List<ModeloRegiones> ObtenerDatos() throws Exception{
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
	
	public void Registrar(ModeloRegiones modelo){
		try (FileWriter fw = new FileWriter(rutaDatosRegiones, true); PrintWriter salida = new PrintWriter(fw)) {
			FileReader fr = new FileReader(rutaDatosRegiones);
			String cadena = "";
			
			int lineas = ContarLineasArchivo(rutaDatosRegiones);//FUNCION QUE CUENTA LAS LINEAS DE NUESTRO ARCHIVO
			int id = lineas + 1;
			
			cadena = String.valueOf(id) + "|" + modelo.getNombreRegion().trim() + "|" + modelo.getRecorrido().trim();//CONCATENA TODOS LOS DATOS REQUERIDOS PARA EL REGISTRO EN UNA LINEA DE TEXTO SEPARANDO CADA CAMPO CON "|"
			
			salida.println(cadena);
			
			fr.close();
			fw.close();
			salida.close();
		} catch (Exception ex) {
                                 
		}
	}
	
	public void Editar(ModeloRegiones modelo){
		try (FileWriter fw = new FileWriter(temp, true); PrintWriter salida = new PrintWriter(fw))  {
			FileReader fr = new FileReader(rutaDatosRegiones);
			BufferedReader entrada = new BufferedReader(fr);
			String cadena = entrada.readLine(), nuevaCadena;
				
			while (cadena != null) {//RECORRE EL ARCHIVO MIENTRAS SE ENCUENTREN DATOS DENTRO DE ESTE
				String[] region = cadena.split("\\|");//CREA UN ARREGLO CON LA LINEA ACTUAL DE LA LECTURA DEL ARCHIVO
					
				if (region[0] == String.valueOf(modelo.getIdRegion())){//VALIDA SI EL ID DE LA LINEA ACTUAL DEL ARCHIVO COINCIDE CON EL DEL MODELO
					nuevaCadena = String.valueOf(modelo.getIdRegion()) + "|" + modelo.getNombreRegion() + "|" + modelo.getRecorrido(); //CREA UNA NUEVA CADENA QUE REEMPLAZARA EL REGISTRO QUE DESEAMOS EDITAR
					salida.println(nuevaCadena);//IMPRIMIMOS EN EL ARCHIVO LA NUEVA CADENA
				}
				else
					salida.println(cadena);//EN CASO EL ID NO COINCIDA, SIMPLEMENTE IMPRIMIMOS EN EL ARCHIVO LA LINEA ACTUAL
				
				cadena = entrada.readLine();
			}
			
			fr.close();
			fw.close();
			entrada.close();
			salida.close();
			
			File regionTemp = new File(temp);
			File regionFile = new File(rutaDatosRegiones);
		
			regionFile.delete();
			regionTemp.renameTo(regionFile);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void Eliminar(int id){
		try (FileWriter fw = new FileWriter(temp, true); PrintWriter salida = new PrintWriter(fw))  {
			FileReader fr = new FileReader(rutaDatosRegiones);
			BufferedReader entrada = new BufferedReader(fr);
			String cadena = entrada.readLine(), nuevaCadena;
				
			while (cadena != null) {//RECORRE EL ARCHIVO MIENTRAS SE ENCUENTREN DATOS DENTRO DE ESTE
				String[] region = cadena.split("\\|");//CREA UN ARREGLO CON LA LINEA ACTUAL DE LA LECTURA DEL ARCHIVO
					
				if (!region[0].contains(String.valueOf(id))){//VALIDA SI LA VARIBLE
					salida.println(cadena);
				}
				
				cadena = entrada.readLine();
			}
			
			fr.close();
			fw.close();
			entrada.close();
			salida.close();
			
			File regionTemp = new File(temp);
			File regionFile = new File(rutaDatosRegiones);
		
			regionFile.delete();
			regionTemp.renameTo(regionFile);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
