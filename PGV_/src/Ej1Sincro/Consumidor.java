package Ej1Sincro;
public class Consumidor extends Thread {

	private MiBuffer buffer;

	public Consumidor(MiBuffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		
		//Intenta leer del vector
		
		char valor;
		try {
			for (int i = 1; i < 100; i++) {
				buffer.consumir();
				sleep((int)(Math.random()*500));
			}
		} catch (InterruptedException e) {
		}
	}
}
