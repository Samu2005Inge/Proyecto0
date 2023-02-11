package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.CargaDatos;
import controller.Verificacion;

public class Consola {
	
	private Verificacion verificacion;
	
	/**
	 * Este metodo imprime el menu y llama al metodo 
	 * necesario para cumplir con la opcion selecciona
	 * por el usuario
	 */
	public void ejecutarAplicacion() {
		System.out.println("Comenzando parser");
		
		boolean seguir = true;
		
		while (seguir) {
			try {
				mostarApp();
				int opcionSeleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
				
				if (opcionSeleccionada == 1)
					ejecutarCargaArchivo();
				else if (opcionSeleccionada == 2)
					ejecutarVerificacion();
				else if (opcionSeleccionada == 3)
					seguir=false;
			} 
			catch (NumberFormatException | IOException e) {
				System.out.println("Debe seleccionar una de las opciones.");
			}
		}
		
	}
	
	/**
	 * Imprime el menu
	 */
	public void mostarApp() {
		System.out.println("\nOpciones: \n");
		System.out.println("1. Cargar un archivo");
		System.out.println("2. Ejecutar comprobacion");
		System.out.println("3. Salir");
		
	}
	
	
	/**
	 * Este metodo imprime un mensaje enviado como argumento
	 * y recibe una respuesta por el usuario
	 * @param mensaje
	 * @return Cadena de caracteres que el ususario ingresa
	 * @throws IOException
	 */
	public String input(String mensaje) throws IOException
	{
		System.out.print(mensaje + ": ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}
	
	
	/**
	 * Con el nombre del archivo recibido por parametro
	 * carga el archivo en la variable verificacion
	 * @throws IOException
	 */
	private void ejecutarCargaArchivo() throws IOException {
		System.out.println("\nCarga de archivo\n");
		String archivo  = input("Ingrese el nombre del archivo .txt");
		
		verificacion = CargaDatos.cargarArchivo("textos/" + archivo);
		System.out.println("Se cargo el archivo correctamente.");
		
	}
	
	
	/**
	 * Este metodo verifica todo el codigo del txt e imprime
	 * si es correcto o incorrecto
	 */
	private void ejecutarVerificacion() {
		System.out.println("\nEjecutando verificacion\n");
		boolean esCorrecto = verificacion.verificarArchivo();
		
		if (esCorrecto)
			System.out.println("El archivo esta escrito correctamente.");
		else {
			System.out.println("El archivo es incorrecto");
		}
	}
		
		
	/**
	 * Este metodo ejecuta la aplicacion
	 * @param args
	 */
	public static void main(String[] args) {
		Consola consola = new Consola();
		consola.ejecutarAplicacion();
	}
}
