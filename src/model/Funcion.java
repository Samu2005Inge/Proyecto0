package model;

import java.util.ArrayList;

public class Funcion {
	private String nombre;
	private ArrayList<String> parametros;
	private ArrayList<ArrayList<String>> instrucciones;
	
	public Funcion(ArrayList<String> bloque) {
		this.nombre = bloque.get(0);
		this.parametros = new ArrayList<String>();
		this.instrucciones = new ArrayList<ArrayList<String>>();
		
		int cont = 0,corchetes=0;
		ArrayList<String> tempInstruccion = new ArrayList<String>();
		
		// recorre cada token y extrae las variables para almacenarlas
		while (cont!=bloque.size()) {
			String token = bloque.get(cont);
			String[] ltToken = token.split(",");
			
			for (String cad: ltToken) {
				if (cad.contains("|"))
					corchetes++;
				
				if (corchetes == 1) 
					this.parametros.add(cad.replace("|", "").replace(",", "").replace(" ", ""));
			}
		
			if (!token.contains("|") && corchetes == 2) {
				tempInstruccion.add(token);
				
				if (token.contains(";") || cont == bloque.size() -1) {
					this.instrucciones.add((ArrayList<String>) tempInstruccion.clone());
					tempInstruccion.clear();;
				}
					
			}
				
			cont++;
		}
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getParametros() {
		return parametros;
	}


	public ArrayList<ArrayList<String>> getInstrucciones() {
		return instrucciones;
	}

	
	

}
