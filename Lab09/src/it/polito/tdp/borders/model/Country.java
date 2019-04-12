package it.polito.tdp.borders.model;

public class Country {
	
	private String statAbb;
	private int cCode;
	private String name;
	
	public Country(String statAbb, int cCode, String name) {
		this.statAbb = statAbb;
		this.cCode = cCode;
		this.name = name;
	}

	public String getStatAbb() {
		return statAbb;
	}

	public void setStatAbb(String statAbb) {
		this.statAbb = statAbb;
	}

	public int getcCode() {
		return cCode;
	}

	public void setcCode(int cCode) {
		this.cCode = cCode;
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
		result = prime * result + cCode;
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
		if (cCode != other.cCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [statAbb=" + statAbb + ", cCode=" + cCode + ", name=" + name + "]";
	}
	
	

}
