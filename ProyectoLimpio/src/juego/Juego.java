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
		
		// Inicializar lo que haga falta para el juego
		// ...	
		imagenFondo =  Herramientas.cargarImagen("cielo.png");
		this.pep = new Pep(400,350 , entorno);
		this.tortuga = new Tortuga(480, 480, entorno);
		this.gnomo = new Gnomo(300, 300, entorno);
		
		//no funciona 
		//this.islas = new Isla[15];
		//int k=0;
		//for(int i=1; i<=5;i++) {
		//	for(int j=1; j<=1; j++) {
		//		this.islas[k] = new Isla((j*entorno.ancho()/(i+1))-65+(25*j),100*i,entorno);
		//	k=k+1;
		//	}
		//}
		
//		for( int i=0; i<islas.length; i++) {
//			this.islas[i]=new Isla(i*100, i, entorno);
//		}
		
//		for( int i=0; i<islas.length; i++) {
//			this.islas[i]=new Isla(entorno.ancho()/2, entorno.alto()/5, entorno);	
//		}
		
		//prueba spawn piramidal de islas ?¿
		// Inicializar las islas
		this.islas = new Isla[15]; // 15 islas en total
		int k = 0;
		int alturaInicial = 100;  // Altura inicial para la primera fila
		int distanciaVertical = 100; // Entre filas

		for (int fila = 1; fila <= 5; fila++) {
		    // Calcular la posicion horizontal de la primera isla en cada fila
		    int cantidadIslas = fila;
		    int anchoPantalla = entorno.ancho();
		    int espacioEntreIslas = anchoPantalla / (cantidadIslas + 1);  

		    for (int j = 0; j < cantidadIslas; j++) {
		        // Posicionar las islas en la fila de manera centrada
		        int posicionX = espacioEntreIslas * (j + 1);
		        int posicionY = alturaInicial + distanciaVertical * (fila - 1);
		        
		        // Crear la isla 
		        this.islas[k] = new Isla(posicionX, posicionY, entorno);
		        k++;
		    }
		}
		
		
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
	    // Cambiar fondo
	    entorno.dibujarImagen(imagenFondo, entorno.ancho() / 2, entorno.alto() / 2, 0, 0.55);
	    
	    // Control de movimientos
	    if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
	        pep.movHorizontal(-2);
	    }
	    if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
	        pep.movHorizontal(2);
	    }
	    
	    //COSAS DE COLISION, NO ESTA TERMINADO
	    
	    // Mover Pep verticalmente
//	    if (!pep.estaApoyado) {
//	        pep.movVertical(2);  // Movimiento hacia abajo si no esta apoyado
//	    } else {
//	        pep.estaApoyado = false;  // Reinicia la variable para despues
//	    }

//	    // Verificar colisiones con islas
//	    for (Isla isla : islas) {
//	        if (isla != null && hayColision(pep, isla)) {
//	            pep.estaApoyado = true;
//	            // Ajustar la posicion de pep si esta en una isla
//	            pep.y = isla.bordeArriba - (pep.alto / 2); // Colocar Pep justo encima de la isla
//	        }
//	    }

	    // NO FUNCIONA 
	    // Hacer que pep no se salga 
//	    if (pep.bordeIzquierdo < 0) {
//	        pep.x = pep.ancho / 2; 
//	    }
//	    if (pep.bordeDerecho > entorno.ancho()) {
//	        pep.x = entorno.ancho() - pep.ancho / 2; 
//	    }
//	    if (pep.bordeArriba < 0) {
//	        pep.y = pep.alto / 2;
//	    }
//	    if (pep.bordeAbajo > entorno.alto()) {
//	        pep.y = entorno.alto() - pep.alto / 2; 
//	    }

	    // Mostrar elementos en la pantalla
	    for (Isla isla : islas) {
	        if (isla != null) {
	            isla.mostrar();
	        }
	    }
	    
	    pep.mostrar(); 
	    tortuga.mostrar(); 
	    gnomo.mostrar(); 
	}

	// Metodo para detectar colisiones
	public boolean hayColision(Pep p, Isla isla) {
	    boolean colisionHorizontal = p.bordeDerecho > isla.bordeIzquierdo && p.bordeIzquierdo < isla.bordeDerecho;
	    boolean colisionVertical = p.bordeAbajo > isla.bordeArriba && p.bordeArriba < isla.bordeAbajo;
	    
	    return colisionHorizontal && colisionVertical;
	}

	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
