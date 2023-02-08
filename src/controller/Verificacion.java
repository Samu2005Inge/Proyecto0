package controller;

import java.util.ArrayList;

import model.Reglas;

public class Verificacion {
	private ArrayList<ArrayList<String>> informacion;
	private Reglas reglas;
	
	public Verificacion(ArrayList<ArrayList<String>> informacion) {
		this.informacion = informacion;
		this.reglas = new Reglas();
	}
	
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
