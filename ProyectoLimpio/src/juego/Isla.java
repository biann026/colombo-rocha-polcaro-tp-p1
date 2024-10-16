package juego;

import java.awt.Image;

import entorno.Entorno;

public class Isla {
	double x,y;
	double bordeAbajo;
	double bordeArriba;
	double bordeDerecho;
	double bordeIzquierdo;
	double escala;
	double ancho;
	double alto;
	Image imagen;
	Entorno e;
	
	public Isla(double x, double y,Entorno e) {
		
		this.x = x;
		this.y = y;
		this.e=e;
		imagen=entorno.Herramientas.cargarImagen("Imagen3.png");
		
		this.bordeAbajo = this.y + (imagen.getHeight(null)/2);
		this.bordeArriba = this.y - (imagen.getHeight(null)/2);
		this.bordeDerecho= this.x + (imagen.getWidth(null)/2);
		this.bordeIzquierdo = this.x - (imagen.getWidth(null)/2);
		this.escala=0.1;
		this.ancho = imagen.getHeight(null)* escala;
		this.alto = imagen.getWidth(null)*escala;
		
	}
	public void mostrar() {
		this.e.dibujarImagen(imagen, x, y, 0, escala);
	}
}
