package Ej1Sincro;

import java.util.concurrent.Semaphore;

public class MiBuffer {
	private int num_items;
	private int[] listasalida;  //En ese ejemplo vector que contiene impares, producidos
								//por hilos productores
	
	private int p,c = 0;//puntero-contador para saber cuantos elementos se han a�adido/le�do
	
	//Activar para sincronizar con sem�foros
	//o con monitores, poniendo synchronized
	
	//Semaphore mutex=new Semaphore(1);
	//No implementado aqui, prueba.
		
	public MiBuffer(int num_items) {
		this.num_items=num_items;
		this.listasalida = new int[num_items];
	}

	public boolean producir (int valor) {
		
		if(p<listasalida.length) {
				listasalida[p] = valor;
				System.out.println("Productor: " +Thread.currentThread().getName()+" valor: " + listasalida[p]);
				p++;
				return true;
		}
		return false;
	}

	public void consumir() {
		
		if (p>c) {
				System.out.println("Consumidor: "+Thread.currentThread().getName() +" valor: "+ listasalida[c]);
				c++;
		}
		
			
	}
}