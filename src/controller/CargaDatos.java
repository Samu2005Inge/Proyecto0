package controller;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

public class CargaDatos {
	public static Verificacion cargarArchivo(String narchivo) throws IOException{
		ArrayList<ArrayList<String>> retorno = new ArrayList<ArrayList<String>>();
		BufferedReader br = new BufferedReader(new FileReader(narchivo));
		String linea = br.readLine(); // La primera línea del archivo se ignora porque únicamente tiene los títulos de
										// las columnas
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(" ");
			ArrayList<String> palabras= new ArrayList<String>();
			for (int i=0;i<partes.length;i++) {
				palabras.add(partes[i]);
			}
			retorno.add(palabras);
			
			linea = br.readLine(); // Leer la siguiente línea
		}
		br.close();
		
		Verificacion datosVer = new Verificacion(retorno);
		
		return datosVer;
	}
}
