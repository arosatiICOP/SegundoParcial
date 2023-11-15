package logica;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws ExceptionEstado {
		
		Restaurante paucke = new Restaurante("Paucke ATENEO Inmaculada","General López 2545","Santa Fe");
		
		Scanner ingresoDatos = new Scanner(System.in);
		int eleccion;
		System.out.println("Ingrese una opcion.");
		paucke.mostrarMenu();
		eleccion=ingresoDatos.nextInt();
		while(eleccion!=0) {
		switch(eleccion) {
		case 1:paucke.mostrarReservas();break;
		case 2:paucke.mostrarMesas();break;
		case 3:paucke.mostrarMesaEsp();break;
		case 4:break;
		case 5:paucke.mostrarRestoFecha();break;
		case 6:paucke.altaReserva();break;
		case 7:paucke.altaMesa();break;
		case 8:paucke.cambiarEstadoMesa();break;
		case 9:paucke.bajaMesa();break;
		case 10:paucke.conteoMesas();break;
		case 0: System.exit(0);break;
		}
		paucke.mostrarMenu();
		eleccion=ingresoDatos.nextInt();
		}
		
		Restaurante losPrimos = new Restaurante("Los Primos","San Martín 1601","Santa Fe");
		int cM1 = 5, cM2 = 7, cM3 = 10;
        int capM1 = 4, capM2 = 6, capM3 = 8;
		losPrimos.crearMesas(new int[]{cM1, cM2, cM3}, new int[]{capM1, capM2, capM3});
		losPrimos.mostrarMenu();
		
		eleccion=ingresoDatos.nextInt();
		while(eleccion!=0) {
		switch(eleccion) {
		case 1:losPrimos.mostrarReservas();break;
		case 2:losPrimos.mostrarMesas();break;
		case 3:losPrimos.mostrarMesaEsp();break;
		case 4:break;
		case 5:losPrimos.mostrarRestoFecha();break;
		case 6:losPrimos.altaReserva();break;
		case 7:losPrimos.altaMesa();break;
		case 8:losPrimos.cambiarEstadoMesa();break;
		case 9:losPrimos.bajaMesa();break;
		case 10:losPrimos.conteoMesas();break;
		case 0: System.exit(0);break;
		}
		losPrimos.mostrarMenu();
		eleccion=ingresoDatos.nextInt();
		}
		
		ingresoDatos.close();
	}
}