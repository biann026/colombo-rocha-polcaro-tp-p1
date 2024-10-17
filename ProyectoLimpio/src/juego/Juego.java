package juego;


import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Image imagenFondo;
	private Isla[] islas;
	private Pep pep;
	private Tortuga tortuga;
	private Gnomo gnomo;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		imagenFondo =  Herramientas.cargarImagen("cielo.png");
		this.pep = new Pep(400, 400, entorno);
		this.tortuga = new Tortuga(480, 480, entorno);
		this.gnomo = new Gnomo(300, 300, entorno);
		//no funciona 
		this.islas = new Isla[15];
		int k=0;
		for(int i=1; i<=5;i++) {
			for(int j=1; j<=1; j++) {
				this.islas[k] = new Isla((j*entorno.ancho()/(i+1))-65+(25*j),100*i,entorno);
			k=k+1;
			}
		}
		
//		for( int i=0; i<islas.length; i++) {
//			this.islas[i]=new Isla(i*100, i, entorno);
//		}
		
//		for( int i=0; i<islas.length; i++) {
//			this.islas[i]=new Isla(entorno.ancho()/2, entorno.alto()/5, entorno);
//			
//			
//		}
		
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		
		// Procesamiento de un instante de tiempo
		// ...
		entorno.dibujarImagen(imagenFondo, entorno.ancho()/2, entorno.alto()/2, 0,0.55);
		pep.mostrar();
		tortuga.mostrar();
		gnomo.mostrar();
		for(int i=0; i<islas.length;i++) {
			if(this.islas[i]!=null) {
			islas[i].mostrar();
			}
		}
		//pep.movVertical(2);
		
		
		if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			pep.movHorizontal(-2);
		}
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			pep.movHorizontal(2);
		}
		
		//chequeo si esta en la isla
		
//		for( int i=0; i<islas.length; i++) {
//			if(pep.bordeAbajo>islas[i].bordeArriba) {
//				
//			}
//		}
		

	}
	
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
