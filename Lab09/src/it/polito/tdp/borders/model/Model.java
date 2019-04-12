package it.polito.tdp.borders.model;

public class Model {

	public Model() {
	
	}

	public boolean annoValido(String anno) {
		int annoValido= Integer.parseInt(anno);
		if(anno.matches("\\d{4}")) {
			return true;
		}
		return false;
	}

	public String calcolaConfini(String anno) {
		
		return null;
	}

}
