package it.polito.tdp.borders.model;

import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	public Model() {
	
	}
	
	private SimpleGraph<Country, DefaultEdge> grafo; //DeafultEdge arco non orientato e non pesato

	public boolean annoValido(String anno) {
		if(!anno.matches("\\d{4}")) {
			return false;
		}
		int annoInt = Integer.parseInt(anno);
		return annoInt >= 1816 && annoInt<=2016;
	}

	public String calcolaConfini(String anno) {
		grafo= new SimpleGraph<>(DefaultEdge.class); //faccio new nel metodo perch� il grafo cambia in base all'input che passo
		String confini="";
		BordersDAO dao = new BordersDAO();
		dao.popolaGrafo(grafo);
		for(Country c: grafo.vertexSet()) {  //vertexSet restituisce un set dei vertici del grafo
			confini+=c.toString()+" "+grafo.degreeOf(c)+"\n"; //degreeOf restituisce il grado del vertice(lo posso usare direttamente perch� il grafo non � orientato)
			//confini+=Graphs.neighborListOf(grafo, c).size(); (metodo alternativo per trovare il grado di un vertice facendomi ridare una lista dei suoi "vicini")
			
		}
		ConnectivityInspector<Country, DefaultEdge> inspector = new ConnectivityInspector<>(grafo); //Oggetto che controlla se tutto � apposto nella connettivit� del grafo
		confini+="Componenti connesse: "+inspector.connectedSets().size(); //connectedSets mi rid� una lista di tutte le componenti connesse
		return confini;
	}

}
