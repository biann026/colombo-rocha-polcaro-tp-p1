package juego;

import java.awt.Image;

import entorno.Entorno;

public class Personaje {
	double x;
	double y;
	boolean direccion;
	double escala;
	double velocidad;
	Image imagenDer;
	Image imagenIzq;
	
	public Personaje(double x,double y) {
		this.x = x;
		this.y = y;
		this.direccion = false;
		this.escala = 0.2;
		this.velocidad = 1;
		this.imagenDer = entorno.Herramientas.cargarImagen("imgDer.png");
		this.imagenIzq = entorno.Herramientas.cargarImagen("imgIzq.png");
	}
	
	public void mostrarpj(Entorno e) {
		e.dibujarImagen(imagenDer, x, y, 0, escala);
	}

}
