package controller;

import java.util.ArrayList;

import model.Reglas;

public class Verificacion {
	private ArrayList<ArrayList<String>> informacion;
	private Reglas reglas;
	
	
	/**
	 * Se definen los atributos de la clase Verificacion
	 * con las lineas de codigo.
	 * @param informacion
	 */
	public Verificacion(ArrayList<ArrayList<String>> informacion) {
		this.informacion = informacion;
		this.reglas = new Reglas();
	}
	
	
	/**
	 * Este metodo verifica linea por linea el programa
	 * e indica si es correcto o incorrecto.
	 * @return true si el programa esta correcto y
	 * false en caso contrario
	 */
	public boolean verificarArchivo() {
		int numLinea = 1;
		boolean estaCorrecto = false;
		
		for (ArrayList<String> linea: informacion) {
			if (numLinea == 1)
				estaCorrecto = reglas.signatureIsPresent(linea);
			
			numLinea++;
		}
		
		return estaCorrecto;
	}
	

}
