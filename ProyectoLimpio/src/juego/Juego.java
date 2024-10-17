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
		this.pep = new Pep(400,0 , entorno);
		this.tortuga = new Tortuga(480, 480, entorno);
		this.gnomo = new Gnomo(300, 300, entorno);
		
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
	        
	        mostrarIslas();
	        
	        verificarColisiones();

	        // Movimiento vertical
	        if (!pep.estaApoyado) {
	            pep.movVertical(); 
	            System.out.println("no esta apoyado");
	        }

	        // Mostrar a Pep 
	        pep.mostrar();

	        // Movimiento horizontal
	        if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
	            pep.movHorizontal(-2);  // Mover a la derecha
	        }
	        if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
	            pep.movHorizontal(2);  // Mover a la izquierda
	        }

	        
	        tortuga.mostrar();
	        gnomo.mostrar();
	    }

	    // Metodo para mostrar las islas
	    private void mostrarIslas() {
	        for (Isla isla : islas) {
	            if (isla != null) {
	                isla.mostrar(); 
	            }
	        }
	    }

	    private void verificarColisiones() {
	        pep.estaApoyado = false; // Resetear el estado

	        for (Isla isla : islas) {
	            if (isla != null) {
	                // Verificar si Pep está dentro del rango horizontal de la isla
	                if (pep.bordeDerecho > isla.bordeIzquierdo && pep.bordeIzquierdo < isla.bordeDerecho) {

	                    // Verificar si Pep está cayendo justo sobre la parte superior de la isla
	                    if (pep.bordeAbajo >= isla.bordeArriba && pep.bordeAbajo <= isla.bordeArriba + 5) {
	                        // Ajustar la posición de Pep para que quede exactamente sobre la isla
	                        pep.y = isla.bordeArriba - (pep.alto / 2);
	                        pep.actualizarBordes();
	                        pep.estaApoyado = true; // Establecer que Pep está apoyado
	                        break;  // Salir del bucle al encontrar una colisión
	                    }
	                }
	            }
	        }
	    }
	    
	    public boolean detectarColision(Pep p, Isla isla) {
	        return p.bordeDerecho > isla.bordeIzquierdo && 
	               p.bordeIzquierdo < isla.bordeDerecho && 
	               p.bordeAbajo > isla.bordeArriba && 
	               p.bordeArriba < isla.bordeAbajo;
	    }

	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
