package happyCar;

import java.util.ArrayList;

public class RentACar{

	ArrayList<Coches> cars=new ArrayList<>();
	
	public RentACar(int numcars) {
		//cargar la flota ;-)
		for(int i=0; i<numcars; i++) {
		Coches coche=new Coches(i);
		cars.add(coche);	
		}
	}

	public synchronized Coches Alquilar() {
		
		int i=0;
		try {
		while (cars.get(i).isRented()) {
			i++;
		}
		cars.get(i).setRented(true);
		System.out.println(Thread.currentThread().getName()+" alquiló el coche "+cars.get(i).getMatricula());
		return cars.get(i);
		}catch(IndexOutOfBoundsException e) {
			System.err.println("CONTROL-borrar: Llegó al final array");
		}
		return null;
	}

	public synchronized void Devolver(Coches coche) {
		coche.setRented(false);
		System.out.println(Thread.currentThread().getName()+" devuelve el coche "+coche.getMatricula());
		
	}
	}
