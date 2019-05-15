package it.polito.tdp.borders.model;

public class Country {
	
	private int ccode;
	private String stateAbb;
	private String name;
	public Country(int ccode, String stateAbb, String name) {
		super();
		this.ccode = ccode;
		this.stateAbb = stateAbb;
		this.name = name;
	}
	public int getCcode() {
		return ccode;
	}
	public void setCcode(int ccode) {
		this.ccode = ccode;
	}
	public String getStateAbb() {
		return stateAbb;
	}
	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ccode;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (ccode != other.ccode)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Country: "+this.getCcode()+" "+this.getStateAbb()+" "+this.getName();
	}
	

}
