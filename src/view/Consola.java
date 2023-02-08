package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.CargaDatos;
import controller.Verificacion;

public class Consola {
	
	private Verificacion verificacion;
	
	public void ejecutarAplicacion() {
		System.out.println("Comenzando parser");
		
		boolean seguir = true;
		
		while (seguir) {
			try {
				mostarApp();
				int opcionSeleccionada = Integer.parseInt(input("Por favor seleccione una opciÃ³n"));
				
				if (opcionSeleccionada == 1)
					ejecutarCargaArchivo();
				else if (opcionSeleccionada == 2)
					ejecutarVerificacion();
			} 
			catch (NumberFormatException | IOException e) {
				System.out.println("Debe seleccionar una de las opciones.");
			}
		}
		
	}
	
	public void mostarApp() {
		System.out.println("\nOpciones: \n");
		System.out.println("1. Cargar un archivo");
		System.out.println("2. Ejecutar comprobacion");
		System.out.println("4. Salir");
		
	}
	
	public String input(String mensaje) throws IOException
	{
		System.out.print(mensaje + ": ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}
	
	private void ejecutarCargaArchivo() throws IOException {
		System.out.println("\nCarga de archivo\n");
		String archivo  = input("Ingrese el nombre del archivo .txt");
		
		verificacion = CargaDatos.cargarArchivo(archivo);
		System.out.println("Se cargo el archivo correctamente.");
		
	}
	
	private void ejecutarVerificacion() {
		System.out.println("\nEjecutando verificacion\n");
		boolean esCorrecto = verificacion.verificarArchivo();
		
		if (esCorrecto)
			System.out.println("El archivo esta escrito correctamente.");
		else {
			System.out.println("El archivo es incorrecto");
		}
	}
		
		
	
	public static void main(String[] args) {
		Consola consola = new Consola();
		consola.ejecutarAplicacion();
	}
}
