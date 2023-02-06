package happyCar;

public class Cliente extends Thread {

	RentACar rentacar;

	public Cliente(RentACar rentacar) {
		super();
		this.rentacar = rentacar;
	}

	public void run() {
		Coches coche;
		
		// Para que salga algo, le dejo alquilar 2 veces
		for (int i = 0; i < 2; i++) {

			try {
				Thread.sleep((int) (Math.random() * 10000));
				coche = rentacar.Alquilar();
				Thread.sleep((int) (Math.random() * 2000));
				rentacar.Devolver(coche);
				
			} catch (NullPointerException nu) {
			} catch (InterruptedException e) {
			}
		
		}
	}
}
