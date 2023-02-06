package Ej1Sincro;

/*El objetivo es exponer el modelo PRODUCTOR-CONSUMIDOR
 * 1) Clase Main, inicializa y lanza hilos
 * 2) Clase Buffer donde ir� la estructura de datos y m�todos que al ser accesibles por todos
 * requieren sinronizaci�n (secciones cr�ticas)
 * 3) Clase Productor: produce o genera recursos. Thread
 * 4) Clase Consumidor: lee o consume recursos. Thread
 */

public class MainHilos {

  public static void main(String[] args) {
	  
	final int num_p=1, num_c=5, num_items=30;
	
    MiBuffer buff=new MiBuffer(num_items);
    
    for(int i=0; i<num_p; i++) {
    	Productor p=new Productor(buff);
    	p.setName("HiloP_"+i);
    	p.start();
    }
    for(int i=0; i<num_c; i++) {
    	Consumidor c=new Consumidor(buff);
    	c.setName("HiloC_"+i);
    	c.start();
    }
        
  }
}
