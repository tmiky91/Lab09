package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private SimpleGraph<Country, DefaultEdge> grafo = new SimpleGraph<>(DefaultEdge.class);
	private Map<Integer, Country> idMap = new HashMap<>();

	public Model() {
	
	}

	public boolean isDigit(String anno) {
		
		if(!anno.matches("\\d{4}")) {
			return false;
		}
		int annoValido = Integer.parseInt(anno);
		return annoValido>=1816 && annoValido<=2016;
	}

	public String calcolaConfini(String anno) {
		String risultato="";
		
		BordersDAO dao = new BordersDAO();
		dao.loadAllCountries(idMap);
		List<Border> confini = dao.getArchi(anno, idMap);
		for(Border b: confini) {
//			grafo.addVertex(b.getId1());
//			grafo.addVertex(b.getId2());
//			grafo.addEdge(b.getId1(), b.getId2());
			
			Graphs.addEdgeWithVertices(grafo, b.getId1(), b.getId2());
			
		}
		for(Country c: grafo.vertexSet()) {
			risultato+=c.getCcode()+" "+c.getStateAbb()+" "+c.getName()+" Grado: "+grafo.degreeOf(c)+"\n";
		}
		ConnectivityInspector<Country,DefaultEdge> inspector= new ConnectivityInspector<>(grafo);
		risultato+="Componenti Connesse: "+inspector.connectedSets().size()+"\n";
		return risultato;
	}
	
	public int getNumeroVertici() {
		int vertici = grafo.vertexSet().size();
		return vertici;
	}
	
	public int getNumeroArchi() {
		int archi = grafo.edgeSet().size();
		return archi;
	}

	public List<Country> getAllCountry() {
		BordersDAO dao = new BordersDAO();
		return dao.loadAllCountries(idMap);
	}

	public String trovaVicini(Country c) {
		String risultato="";
		List<Country> vicini = new LinkedList<Country>();
		BreadthFirstIterator<Country, DefaultEdge> iterator = new BreadthFirstIterator<>(grafo, c);
		while(iterator.hasNext()) {
			vicini.add(iterator.next());
		}
		for(Country c1: vicini) {
			risultato+=c1.getName()+"\n";
		}
		return risultato;
	}
	
	

}
