package controller;

import java.util.ArrayList;

import model.Funcion;
import model.Reglas;

public class Verificacion {
	private ArrayList<ArrayList<String>> informacion;
	private Reglas reglas;
	private ArrayList<Funcion> funciones;
	
	
	/**
	 * Se definen los atributos de la clase Verificacion
	 * con las lineas de codigo.
	 * @param informacion
	 */
	public Verificacion(ArrayList<ArrayList<String>> informacion) {
		this.informacion = informacion;
		this.reglas = new Reglas();
		this.funciones = new ArrayList<Funcion>();
	}
	
	
	/**
	 * Este metodo verifica linea por linea el programa
	 * e indica si es correcto o incorrecto.
	 * @return true si el programa esta correcto y
	 * false en caso contrario
	 */
	public boolean verificarArchivo() {
		int numLinea = 0;
		boolean estaCorrecto = true;
		
		while (numLinea < informacion.size()) {
			if (estaCorrecto) {
				if (numLinea == 0)
					estaCorrecto = reglas.signatureIsPresent(informacion.get(numLinea), "robot_r");
					
				else if (numLinea == 1)
					estaCorrecto = reglas.varsAreCorrect(informacion.get(numLinea));
				
				else if (numLinea == 2)
					estaCorrecto = reglas.signatureIsPresent(informacion.get(numLinea), "procs");
				
				else {
					ArrayList<String> bloque = reglas.unirLineas(informacion, numLinea);
					numLinea = Integer.parseInt(bloque.get(bloque.size()-1));
					bloque.remove(bloque.get(bloque.size()-1));
					this.funciones.add(new Funcion(bloque));
					
				}
				
			}
			numLinea++;
		}
		
		if (estaCorrecto) {
			estaCorrecto = verificarInstrucciones();
		}
		
		return estaCorrecto;
	}
	
	private boolean verificarInstrucciones() {
		ArrayList<Funcion> ltFunciones = getFunciones();
		boolean esCorrecto = true;
		
		for (Funcion funcion: ltFunciones) {
			ArrayList<ArrayList<String>> instrucciones = funcion.getInstrucciones();
			
			for (ArrayList<String> instruccion: instrucciones) {
				String primerToken = instruccion.get(0);
				if (esCorrecto) {
					if (primerToken.contains("assignto")) {
						String[] type = {"nv", "name"}; // nv representa un numero o variable o parametro, name es variable o parametro
						// n es un numero
						esCorrecto = reglas.comprobarInstruccion(instruccion, "assignto", type, funcion.getParametros());
					}
					else if (primerToken.contains("goto")) {
						String[] type = {"nv", "nv"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "goto", type, funcion.getParametros());
					}
					else if (primerToken.contains("move")) {
						String[] type = {"nv"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "move", type, funcion.getParametros());
					}
					else if (primerToken.contains("turn")) {
						String[] type = {"d"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "turn", type, funcion.getParametros());
					}
					else if (primerToken.contains("face")) {
						String[] type = {"f"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "face", type, funcion.getParametros());
					}
					else if (primerToken.contains("put")) {
						String[] type = {"nv","o"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "put", type, funcion.getParametros());
					}
					else if (primerToken.contains("pick")) {
						String[] type = {"nv","o"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "pick", type, funcion.getParametros());
					}
					else if (primerToken.contains("movetothe")) {
						String[] type = {"nv","de"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "movetothe", type, funcion.getParametros());
					}
					else if (primerToken.contains("moveindir")) {
						String[] type = {"nv","f"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "moveindir", type, funcion.getParametros());
					}
					else if (primerToken.contains("jumptothe")) {
						String[] type = {"nv","de"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "jumptothe", type, funcion.getParametros());
					}
					else if (primerToken.contains("jumpindir")) {
						String[] type = {"nv","f"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "jumpindir", type, funcion.getParametros());
					}
					else if (primerToken.contains("nop")) {
						esCorrecto =true; 	
					}
					
							
				}
			}
		}
		return esCorrecto;
	}


	public ArrayList<Funcion> getFunciones() {
		return funciones;
	}


	public void setFunciones(ArrayList<Funcion> funciones) {
		this.funciones = funciones;
	}
	

}
