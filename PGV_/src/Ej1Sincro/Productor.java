package Ej1Sincro;

public class Productor extends Thread {
	private MiBuffer buffer;
	
	public Productor(MiBuffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		try {
			
			//Mientras num generados menor que tama�o vector, produce ;-)
			//y almacena en vector
			
			int i=1;
			while(buffer.producir(i)) {
				i+=2;
				sleep((int)(Math.random()*200));
			}
		}catch(	InterruptedException e)
	{
	}
}}
