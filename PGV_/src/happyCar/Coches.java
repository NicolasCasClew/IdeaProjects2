package happyCar;

public class Coches {

	boolean rented = false;
	String matricula;

	public Coches(int k) {
		this.matricula = "GC " + "50" + k;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public String getMatricula() {
		return matricula;
	}

}