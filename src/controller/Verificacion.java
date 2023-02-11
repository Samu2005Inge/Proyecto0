package controller;

import java.util.ArrayList;

import model.Funcion;
import model.Reglas;

public class Verificacion {
	private ArrayList<ArrayList<String>> informacion;
	private Reglas reglas;
	private ArrayList<Funcion> funciones;
	private Funcion comandos;
	private String comandosbase = "assignto goto move turn face put pick movetothe moveindir jumptothe jumpindir nop ";
	private ArrayList<ArrayList<String>> funcPropias;
	
	
	/**
	 * Se definen los atributos de la clase Verificacion
	 * con las lineas de codigo.
	 * @param informacion
	 */
	public Verificacion(ArrayList<ArrayList<String>> informacion) {
		this.informacion = informacion;
		this.reglas = new Reglas();
		this.funciones = new ArrayList<Funcion>();
		this.funcPropias = new ArrayList<ArrayList<String>>();
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
		
		comandosbase=comandosbase.replace("[", "");
		
		int ultPos = this.funciones.size() -1;
		this.comandos = this.funciones.get(ultPos);
		this.funciones.remove(ultPos);
		
		
		if (estaCorrecto) {
			verificarComandos();
		}
		
		if (estaCorrecto) {
			estaCorrecto = verificarNombresFunciones();
		}
		
		if (estaCorrecto) {
			estaCorrecto = verificarInstrucciones();
		}
		
		
		return estaCorrecto; 
	}
	
	
	private void verificarComandos(){
		ArrayList<ArrayList<String>> instrucciones = comandos.getInstrucciones();
		int iterator = 0;
		System.out.println("hii "+ instrucciones);
			
			for (ArrayList<String> instruccion: instrucciones){
				for (String token: instruccion){
					String primerToken = token.replaceAll("^\\s*","").replaceAll("\\s*$","").replace("|", "");
					boolean contiene = " [".contains(primerToken);
					if (!comandosbase.contains(primerToken) && contiene){
						System.out.println(primerToken);
						funcPropias.add(instruccion);
						instrucciones.remove(iterator);
				}
				iterator++;
				}
				
			}
			System.out.println("inst "+ funcPropias);
				
		
	}
	
	
	private boolean verificarNombresFunciones() {
		ArrayList<Funcion> ltFunciones = getFunciones();
		boolean esCorrecto = true;
		
		for (Funcion funcion: ltFunciones) {
			String nombreFunc = funcion.getNombre();
			if (esCorrecto) {
				esCorrecto = reglas.varIsCorrect(nombreFunc);
			}
			this.comandosbase = this.comandosbase + " " + nombreFunc;
		}
		
		return esCorrecto;
	}
	
	
	private boolean verificarInstrucciones() {
		ArrayList<Funcion> ltFunciones = getFunciones();
		boolean esCorrecto = true;
		
		for (Funcion funcion: ltFunciones) {
			ArrayList<ArrayList<String>> instrucciones = funcion.getInstrucciones();
			
			for (ArrayList<String> instruccion: instrucciones) {
				String primerToken = instruccion.get(0).replaceAll("^\\s*","").replaceAll("\\s*$","").replace("|", "");
				if (esCorrecto) {
					if (primerToken.equals("assignto") || primerToken.equals("assignto:")) {
						String[] type = {"nv", "name"}; // nv representa un numero o variable o parametro, name es variable o parametro
						// n es un numero
						esCorrecto = reglas.comprobarInstruccion(instruccion, "assignto", type, funcion.getParametros());
					}
					else if (primerToken.equals("goto") || primerToken.equals("goto:")) {
						String[] type = {"nv", "nv"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "goto", type, funcion.getParametros());
					}
					else if (primerToken.equals("move") || primerToken.equals("move:")) {
						String[] type = {"nv"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "move", type, funcion.getParametros());
					}
					else if (primerToken.equals("turn") || primerToken.equals("turn:")) {
						String[] type = {"d"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "turn", type, funcion.getParametros());
					}
					else if (primerToken.equals("face") || primerToken.equals("face:")) {
						String[] type = {"f"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "face", type, funcion.getParametros());
					}
					else if (primerToken.equals("put") || primerToken.equals("put:")) {
						String[] type = {"nv","o"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "put", type, funcion.getParametros());
					}
					else if (primerToken.equals("pick") || primerToken.equals("pick:")) {
						String[] type = {"nv","o"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "pick", type, funcion.getParametros());
					}
					else if (primerToken.equals("movetothe") || primerToken.equals("movetothe:")) {
						String[] type = {"nv","de"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "movetothe", type, funcion.getParametros());
					}
					else if (primerToken.equals("moveindir") || primerToken.equals("moveindir:")) {
						String[] type = {"nv","f"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "moveindir", type, funcion.getParametros());
					}
					else if (primerToken.equals("jumptothe") || primerToken.equals("jumptothe:")) {
						String[] type = {"nv","de"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "jumptothe", type, funcion.getParametros());
					}
					else if (primerToken.equals("jumpindir") || primerToken.equals("jumpindir:")) {
						String[] type = {"nv","f"}; 
						esCorrecto = reglas.comprobarInstruccion(instruccion, "jumpindir", type, funcion.getParametros());
					}
					else if (primerToken.equals("nop") || primerToken.equals("nop:")) {
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
