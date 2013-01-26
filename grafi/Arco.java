package grafi;

public class Arco {

	protected int in;
	protected int fin;
	
	public Arco(int in, int fin) {
		this.in = in;
		this.fin = fin;
	}

	public Arco(Arco a) {
		in = a.in;
		fin = a.fin;
	}
	
	public int getIn() { return in; }

	public void setIn(int in) { this.in = in; }

	public int getFin() { return fin; }

	public void setFin(int fin) { this.fin = fin; }

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fin;
		result = prime * result + in;
		return result;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Arco)) return false;
		if (o == this) return true;
		Arco a = (Arco)o;
		return in == a.in && fin == a.fin;
	}

	public String toString() {
		return "[Arco (" + in + "," + fin + ")]";
	}
}
