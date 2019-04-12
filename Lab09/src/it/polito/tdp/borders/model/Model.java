package it.polito.tdp.borders.model;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	public Model() {
	
	}

	public boolean annoValido(String anno) {
		if(!anno.matches("\\d{4}")) {
			return false;
		}
		int annoValido= Integer.parseInt(anno);
		return annoValido<=1816 && annoValido>=2016;
	}

	public String calcolaConfini(String anno) {
		String risultato="";
		BordersDAO dao = new BordersDAO();
		
		
		return risultato;
	}

}
