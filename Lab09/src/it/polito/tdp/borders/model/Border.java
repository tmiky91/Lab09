package it.polito.tdp.borders.model;

public class Border {
	
	private Country id1;
	private Country id2;
	public Border(Country id1, Country id2) {
		super();
		this.id1 = id1;
		this.id2 = id2;
	}
	public Country getId1() {
		return id1;
	}
	public void setId1(Country id1) {
		this.id1 = id1;
	}
	public Country getId2() {
		return id2;
	}
	public void setId2(Country id2) {
		this.id2 = id2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id1 == null) ? 0 : id1.hashCode());
		result = prime * result + ((id2 == null) ? 0 : id2.hashCode());
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
		Border other = (Border) obj;
		if (id1 == null) {
			if (other.id1 != null)
				return false;
		} else if (!id1.equals(other.id1))
			return false;
		if (id2 == null) {
			if (other.id2 != null)
				return false;
		} else if (!id2.equals(other.id2))
			return false;
		return true;
	}
	
}