package juego;

import java.awt.Image;

import entorno.Entorno;

public class Gnomo {
	double x,y;
	double bordeAbajo;
	double bordeArriba;
	double bordeDerecho;
	double bordeIzquierdo;
	double ancho;
	double alto;
	double escala;
	Image imagen;
	Entorno e;
	
	public Gnomo(double x, double y,Entorno e) {
		this.x = x;
		this.y = y;
		this.e=e;
		imagen=entorno.Herramientas.cargarImagen("gnomoDer.png");
		imagen=entorno.Herramientas.cargarImagen("gnomoIzq.png");		
		this.bordeAbajo = this.y + (imagen.getHeight(null)/2);
		this.bordeArriba = this.y - (imagen.getHeight(null)/2);
		this.bordeDerecho= this.x + (imagen.getWidth(null)/2);
		this.bordeIzquierdo = this.x - (imagen.getWidth(null)/2);
		this.escala=0.08;
		this.ancho = imagen.getHeight(null)* escala;
		this.alto = imagen.getWidth(null)*escala;
	}
	
	public void mostrar() {
		this.e.dibujarImagen(imagen, x, y, 0, escala);
	}

}
