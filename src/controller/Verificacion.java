package controller;

import java.util.ArrayList;

public class Verificacion {
	private ArrayList<ArrayList<String>> informacion;
	
	public Verificacion(ArrayList<ArrayList<String>> informacion) {
		this.informacion = informacion;
	}
	
	public boolean verificarArchivo() {
		boolean esCorrecto = true;
		return esCorrecto;
	}
	
	public boolean signatureIsPresent(ArrayList<String> signature) {
		for (String token: signature) {
			System.out.print(token);
		}
		
		boolean esCorrecto = true;
		return esCorrecto;
		
	}

}
