package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {

		System.out.println("TODO -- BordersDAO -- getCountryPairs(int anno)");
		return new ArrayList<Border>();
	}

	public void popolaGrafo(SimpleGraph<Country, DefaultEdge> grafo) {
		String sql = "select c.CCode as id1, c.StateAbb as cod, c.StateNme as name, c2.CCode as id2, c2.StateAbb as cod2, c2.StateNme as name2 " + 
					 "from contiguity as cg, country as c, country as c2 " + 
					 "where conttype=1 " + 
					 "and c.CCode=cg.state1no " + 
					 "and c2.CCode=cg.state2no";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id1=rs.getInt("id1");
				String cod=rs.getString("cod");
				String name=rs.getString("name");
				int id2=rs.getInt("id2");
				String cod2=rs.getString("cod2");
				String name2=rs.getString("name2");
				
				Country c1= new Country(id1, cod, name);
				Country c2= new Country(id2, cod2, name2);
				
				if(!grafo.containsVertex(c1)) {
					grafo.addVertex(c1);
				}
				if(!grafo.containsVertex(c2)) {
					grafo.addVertex(c2);
				}
				if(!grafo.containsEdge(c1, c2) && !grafo.containsEdge(c2, c1)) {
					grafo.addEdge(c1, c2);
				}
			}
			
			conn.close();

		} catch (SQLException e) {
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
}
