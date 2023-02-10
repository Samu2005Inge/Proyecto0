package model;

import java.security.Signature;
import java.util.ArrayList;

public class Reglas {
	
	String[] ltVars; // lista de variables
	final String initialChars = "abcdefghijklmnñopqrstuvwxyz";
	final String chars = "abcdefghijklmnñopqrstuvwxyz1234567890";
	final String nums = "1234567890";
	final String commands[] = {"assignTo","goto","move","turn","face","put",};
	
	/**
	 * Este metodo verifica que la linea enviada por parametro
	 * sea robot_r.
	 * @param signature
	 * @return En caso de que la linea sea robot_r retorna true
	 * en caso contrario false.
	 */
	public boolean signatureIsPresent(ArrayList<String> signature, String nomSignature) {
		boolean esCorrecto = true;
		
		for (String token: signature) {
			if (token.equals(nomSignature))
				esCorrecto = true;
			else
				esCorrecto = false;
		}
		
		return esCorrecto;
		
	}
	
	public boolean varsAreCorrect(ArrayList<String> vars) {
		boolean esCorrecto = true;
		int numToken = 1;
		int lenVars = vars.size();
		String lnVars = "";
		
		if (vars.get(0).equals("vars") && vars.get(lenVars - 1).contains(";")) {
			for (String token: vars) {
				if (numToken != 1)
					lnVars = lnVars + token;
				
				numToken++;
			}
		}
		else {
			esCorrecto = false;
		}
		
		
		if (esCorrecto) {
			this.ltVars = lnVars.substring(0, lnVars.length() - 1).split(","); 
			
			for (String var: ltVars) {
				if (esCorrecto)
					esCorrecto = varIsCorrect(var);
			}
		}
		
		return esCorrecto;
		
	}
	
	public boolean varIsCorrect(String var) {
		boolean esCorrecto = true;
		int pos = 1;
		
		for (char letra: var.toCharArray()) {
			if (esCorrecto) {
				if (pos == 1)
					esCorrecto = initialChars.contains(String.valueOf(letra)); 
				else
					esCorrecto = chars.contains(String.valueOf(letra)); 
			}
		}
		
		return esCorrecto;
	}
	
	public ArrayList<String> unirLineas(ArrayList<ArrayList<String>> informacion, int numLinea) {
		int cAbiertos = 0;
		int cCerrados = 0;
		ArrayList<String> bloque = new ArrayList<String>();
		
		for (int i=numLinea; i<informacion.size(); i++) {
			ArrayList<String> linea = informacion.get(i);
			for (String token: linea) {
				if (cAbiertos != cCerrados || cAbiertos == 0) {
					if (token.contains("[")) {
						cAbiertos++;
					}
					
					else if (token.contains("]")) {
						cCerrados++;
					}
					
					bloque.add(token);
				}
				
				if (cAbiertos == cCerrados && cAbiertos != 0) {
					bloque.add(String.valueOf(i));
					i = informacion.size() + 1;
					break;
				}
					
			}
		
		}
		return bloque;
			
	}
	
	public boolean comprobarInstruccion(ArrayList<String> instruccion, String primerToken, String[] type, ArrayList<String> parameters) {
		boolean signature = false, dosPuntos = false;
		String varsFuncion = "";
		boolean esCorrecto = false;
		String[] ltParameters = parameters.toString().split(",");
		
		for (String token: instruccion) {
			token = token.replace(" ", "");
			if (token.contains(primerToken))
				signature = true;
			if (signature && token.contains(":"))
				dosPuntos = true;
			else if (signature && dosPuntos) {
				varsFuncion = varsFuncion + token;
			}
		}
		
		if (signature && dosPuntos) {
			System.out.println("Llegue aqui");
			String[] varsSplit = varsFuncion.split(",");
			esCorrecto = comprobarVariables(varsSplit, type, ltParameters);
		}
		
		return esCorrecto;
	}
	
	private boolean comprobarVariables(String[] varsSplit, String[] type, String[] ltParamers) {
		boolean esCorrecto = true;
		
		if (varsSplit.length == type.length) {
			for (int i=0; i<type.length; i++) {
				String varActual = varsSplit[i].replaceAll("^\\s*","").replaceAll("\\s*$","").replace("]", "").replace(";", "");
				System.out.println(varActual);
				if (esCorrecto == true) {
					if (type[i].equals("name")) {
						esCorrecto = buscarEnArray(ltVars, varActual);
						if (!esCorrecto) {
							System.out.println("holaaaa");
							esCorrecto = buscarEnArray(ltParamers, varActual);
						}
								
					}
						
					
					
					else if (type[i].equals("n")){
						try {
				            Integer.parseInt(varActual);
				            esCorrecto = true;
				        } catch (NumberFormatException excepcion) {
				            esCorrecto = false;
				        }
					}
					
					else if (type[i].equals("nv")){
						try {
				            Integer.parseInt(varActual);
				            esCorrecto = true;
				        } catch (NumberFormatException excepcion) {
				        	esCorrecto = false;
				        }
						if (esCorrecto == false && (buscarEnArray(ltVars, varActual) || buscarEnArray(ltParamers, varActual)))
							esCorrecto = true;
					}
				}
			}
		}
		else {
			esCorrecto = false;
		}
		
		return esCorrecto;
	}
	
	private boolean buscarEnArray(String[] lt, String busqueda) {
		boolean isPresent = false;
		for (String palabra: lt) {
			palabra = palabra.replaceAll("^\\s*","").replaceAll("\\s*$","").replace("[", "").replace("]", "");
			if (palabra.equals(busqueda) && isPresent == false)
				isPresent = true;
		}
		return isPresent;
	}

}
