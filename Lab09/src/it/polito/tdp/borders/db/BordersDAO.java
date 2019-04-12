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

		String sql = "SELECT ccode as code, StateAbb as abb, StateNme as name FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				String abb = rs.getString("abb");
				int code = rs.getInt("code");
				String name = rs.getString("name");
				
				Country c1 = new Country(abb, code, name);
				result.add(c1);
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
		String sql = "select c1.CCode as code1, c1.StateAbb as abb1, c1.StateNme as name1, c2.StateAbb as abb2, c2.CCode as cod2, c2.StateNme as name2 " + 
					 "from country as c1, contiguity as cy, country as c2 " + 
					 "where c1.CCode=cy.state1no " + 
					 "and c2.CCode=cy.state2no " + 
					 "and conttype='1'";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int code1 = rs.getInt("code1");
				String sigla1 = rs.getString("abb1");
				String name1 = rs.getString("name1");
				int code2 = rs.getInt("cod2");
				String sigla2 = rs.getString("abb2");
				String name2 = rs.getString("name2");
				
				Country c1 = new Country(sigla1, code1, name1);
				Country c2 = new Country(sigla2, code2, name2);
				
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
