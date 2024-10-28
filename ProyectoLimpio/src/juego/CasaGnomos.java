package juego;

import java.awt.Image;

import entorno.Entorno;

public class CasaGnomos {
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
	
	public CasaGnomos(double x, double y, Entorno e) {
		this.x = x;
		this.y = y;
		this.e=e;
		imagen=entorno.Herramientas.cargarImagen("CasaDeGnomos.png");
		this.escala=0.1;
		this.alto = imagen.getHeight(null)* escala;
		this.ancho = imagen.getWidth(null)*escala;
        this.bordeAbajo = this.y + (this.alto / 2);
        this.bordeArriba = this.y - (this.alto / 2);
        this.bordeDerecho = this.x + (this.ancho / 2);
        this.bordeIzquierdo = this.x - (this.ancho / 2);
	}
	
	
	public void mostrar() {
		this.e.dibujarImagen(imagen, x, y, 0, escala);
	}
	
	public double getX() {
		return this.x;
	}
 
	public double getY() {
		return this.y;
	}
	public double getAncho() {
		return this.ancho;
	}
}

