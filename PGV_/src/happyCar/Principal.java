package happyCar;
public class Principal {

	public static void main(String[] args) {
		
		final int numcars=5;
		int numclients=20;//simulamos X usuarios 
		
		RentACar rentacar = new RentACar(numcars);
			
		for (int i=0; i<numclients; i++){
			Cliente cli = new Cliente(rentacar);
			cli.setName("Cliente_"+i);
			cli.start();
			System.out.println("Se inicio el cliente = "+Thread.currentThread().getName());
		}
	}
}
