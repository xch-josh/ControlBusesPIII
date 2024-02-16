package Application.Data;

import Application.Modelos.ModeloRegiones;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DatosRegiones {
	private final String rutaDatosRegiones = "C:/DatosControlBuses/regiones.txt";
	
	
	private void Registrar(ModeloRegiones modelo){
		File miArchivo = new File(rutaDatosRegiones);
		Scanner teclado = new Scanner(System.in);
                    String cadena, codigo, nombre, categoria;
		float precioUnitario;
		

                    System.out.println("\n<----REGISTRAR PRODUCTO");
                    
                    try (FileWriter fw = new FileWriter(rutaDatosRegiones, true); PrintWriter salida = new PrintWriter(fw)) {
			if (!miArchivo.exists()){//DETERMINA SI EL ARCHIVO ESPECIFICADO NO EXISTE
				miArchivo.createNewFile();//CREA EL ARCHIVO EN CASO DE NO EXISTIR
			}
			FileReader fr = new FileReader(rutaDatosRegiones);
			BufferedReader entrada = new BufferedReader(fr);
			String cadenaCategoria = entrada.readLine();
			boolean hayCategoria = false;
			
			System.out.println("Ingrese el código del producto:");
			codigo = teclado.nextLine();
			System.out.println("Ingrese el nombre del producto:");
			nombre = teclado.nextLine();
			System.out.println("Ingrese la categoría del producto:");
			categoria = teclado.nextLine();
			System.out.println("Ingrese el precio unitario del producto:");
			precioUnitario = teclado.nextFloat();
			
			while (cadenaCategoria != null) {//RECORRE EL ARCHIVO MIENTRAS SE ENCUENTREN DATOS DENTRO DE ESTE
				if (cadenaCategoria.contains(categoria))
					hayCategoria = true;
			}
			
			if (!hayCategoria) {
				int ingrCat;
				System.out.println("La categoría no existe desea registrarla? (1=Si / 0=No)");
				ingrCat = teclado.nextInt();
				if (ingrCat == 1) {
					RegistrarCategoria(categoria);
				}else{
					return;
				}
			}
			
			cadena = codigo.trim() + "|" + nombre.trim() + "|" + precioUnitario + "|0|" + categoria;//CONCATENA TODOS LOS DATOS REQUERIDOS PARA EL REGISTRO EN UNA LINEA DE TEXTO SEPARANDO CADA CAMPO CON "|"
			
			salida.println(cadena);//INSERTA LA LINEA DE TEXTO AL ARCHIVO
			System.out.println("\nProducto guardado exitosamente\n\n");
							  
			salida.close();
			fw.close();
                    } catch (IOException ex) {
                              System.out.println(ex.getMessage());                                                                  
                    }
	}
}
