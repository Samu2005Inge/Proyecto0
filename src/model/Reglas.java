package model;

import java.util.ArrayList;

public class Reglas {
	
	final String initialChars = "abcdefghijklmnñopqrstuvwxyz";
	final String chars = "abcdefghijklmnñopqrstuvwxyz1234567890";
	
	/**
	 * Este metodo verifica que la linea enviada por parametro
	 * sea robot_r.
	 * @param signature
	 * @return En caso de que la linea sea robot_r retorna true
	 * en caso contrario false.
	 */
	public boolean signatureIsPresent(ArrayList<String> signature) {
		boolean esCorrecto = true;
		
		for (String token: signature) {
			if (token.equals("robot_r"))
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
			String[] ltVars = lnVars.substring(0, lnVars.length() - 1).split(","); 
			
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

}
