package model;

import java.util.ArrayList;

public class Reglas {
	
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
