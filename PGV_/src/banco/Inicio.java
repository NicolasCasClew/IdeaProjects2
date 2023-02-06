package banco;

import java.util.ArrayList;

//import Concesionario.ClientesCoche;

public class Inicio {

	public static void main(String[] args) {
		
		/*Simulaci�n
		 * con UNA sola cuenta familiar, los miembros de la familia sacan dinero mientras puedan ;-)
		 * Inicialmente una cantidad fija hasta comprender funcionamiento
		 * SOLO sacan dinero
		 * Luego se propone cantidades aleatorias
		 * y el padre y la madre,adem�s de poder sacar, son los que hacen ingresos cada cierto tiempo
		 * 
		 * PRUEBA las diferentes situaciones
		 */
		
		String[] listClientes={"Padre Pepe","Madre Mar�a", "Jes�s","Juan","Mateo","Magdalena" };

		Cuenta cu = new Cuenta();
		cu.setSaldo(1000);
		
		for(int i=0; i<listClientes.length; i++){
			Clientes cli=new Clientes(listClientes[i],cu);
			cli.start();
		}
		
		
	}

}
