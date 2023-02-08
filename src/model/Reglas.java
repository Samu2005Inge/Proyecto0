package model;

import java.util.ArrayList;

public class Reglas {
	
	
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
			System.out.print(token + "\n");
			
			if (token.equals("robot_r"))
				esCorrecto = true;
			else
				esCorrecto = false;
		}
		
		return esCorrecto;
		
	}

}
