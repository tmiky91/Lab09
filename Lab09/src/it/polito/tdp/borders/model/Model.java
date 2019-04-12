package it.polito.tdp.borders.model;

import java.util.List;

import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private SimpleGraph<Country, DefaultEdge> grafo;

	public Model() {
	
	}

	public boolean annoValido(String anno) {
		if(!anno.matches("\\d{4}")) {
			return false;
		}
		int annoValido= Integer.parseInt(anno);
		return annoValido>=1816 && annoValido<=2016;
	}

	public String calcolaConfini(String anno) {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		String risultato="";
		BordersDAO dao = new BordersDAO();
		dao.popolaGrafo(grafo);
		for(Country c: grafo.vertexSet()) {
			risultato+=c.toString()+ " "+grafo.degreeOf(c)+"\n";
		}
		ConnectivityInspector<Country, DefaultEdge> inspector = new ConnectivityInspector<>(grafo);
		risultato+=inspector.connectedSets().size();
		
		
		return risultato;
	}

	public static List<Country> getAllStates() {
		BordersDAO dao = new BordersDAO();
		return dao.loadAllCountries();
	}

	public String calcolaConnessioniDelloStato(String anno, Country c) {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		String risultato="";
		BordersDAO dao = new BordersDAO();
		dao.popolaGrafo(grafo);
		ConnectivityInspector<Country, DefaultEdge> inspector = new ConnectivityInspector<>(grafo);
		risultato+=inspector.connectedSetOf(c);
		return risultato;
	}

}
