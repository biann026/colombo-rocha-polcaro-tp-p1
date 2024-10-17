package juego;

import java.awt.Image;

import entorno.Entorno;

public class DisparoDePep {
	double x,y;

	double bordeDerecho;
	double bordeIzquierdo;
	double velocidadDisparo;
	Image imagenBolaDeFuego; 
	Entorno e;
	
	public DisparoDePep(double x ,double y,Entorno e){
		this.x = x;
		this.y = y;
		this.e=e;
		imagenBolaDeFuego = entorno.Herramientas.cargarImagen("bolaDeFuego.png");
		this.bordeDerecho= this.x + (imagenBolaDeFuego.getWidth(null)/2);
		this.bordeIzquierdo = this.x - (imagenBolaDeFuego.getWidth(null)/2);
		this.velocidadDisparo = 2;	
	}
	
	public void mostrar() {
		this.e.dibujarImagen(imagenBolaDeFuego, x, y, 0, 0.1);
	}
	
	public void disparar(double x,double y) {
		
		x=+this.velocidadDisparo;
		
	}
	
}
