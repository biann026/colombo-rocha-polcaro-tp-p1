package juego;

import java.awt.Image;

import entorno.Entorno;

public class Pep {
	double x,y;
	double bordeAbajo;
	double bordeArriba;
	double bordeDerecho;
	double bordeIzquierdo;
	double escala;	
	double ancho;
	double alto;
	boolean estaApoyado;
	boolean direccion = true; //izquierda => direccion=false
	double velocidadDisparo;

	Image imagen;
	Image imagenBolaDeFuego; // disparo
	Entorno e;
	
	public Pep(double x, double y,Entorno e){
		this.x = x;
		this.y = y;
		this.e=e;
		imagenBolaDeFuego = entorno.Herramientas.cargarImagen("bolaDeFuego.png");
		imagen=entorno.Herramientas.cargarImagen("pepDer.png");
		imagen=entorno.Herramientas.cargarImagen("pepIzq.png");		
		this.bordeAbajo = this.y + (imagen.getHeight(null)/2);
		this.bordeArriba = this.y - (imagen.getHeight(null)/2);
		this.bordeDerecho= this.x + (imagen.getWidth(null)/2);
		this.bordeIzquierdo = this.x - (imagen.getWidth(null)/2);
		this.escala=0.1;
		this.ancho = imagen.getHeight(null)* escala;
		this.alto = imagen.getWidth(null)*escala;
		this.estaApoyado = false;
		this.direccion= true;
		this.velocidadDisparo=2;//disparo
	
		
	}
		
	public void mostrar() {
		this.e.dibujarImagen(imagen, x, y, 0, escala);
	}
	
	public void movHorizontal(double n) {
		this.x = this.x-n;
	}
	public void movVertical(double y) {
		if(!estaApoyado) {
			this.y ++;
		}	
	}
	

	public double getX() {
		return x;
	}

//	public void setX(double x) {
//		this.x = x;
//	}

	public double getY() {
		return y;
	}

//	public void setY(double y) {
//		this.y = y;
//	}

	

}
