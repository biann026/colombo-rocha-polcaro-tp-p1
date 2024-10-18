package juego;

import java.awt.Image;

import entorno.Entorno;

public class DisparoDePep {
	double x,y;
	double bordeDerecho;
	double bordeIzquierdo;
//	double velocidadDisparo; //sin usar
	boolean direccionDerecha;
	Image imagenBolaDeFuego; 
	Entorno e;
	
	public DisparoDePep(double x ,double y,Entorno e){
		this.x = x;
		this.y = y;
		this.e=e;
		imagenBolaDeFuego = entorno.Herramientas.cargarImagen("bolaDeFuego.png");
		this.bordeDerecho= this.x + (imagenBolaDeFuego.getWidth(null)/2);
		this.bordeIzquierdo = this.x - (imagenBolaDeFuego.getWidth(null)/2);
//		this.velocidadDisparo = 2; // sin usar
		this.direccionDerecha =true;
	}
	
	public void mostrar(Entorno e) {
		this.e.dibujarImagen(imagenBolaDeFuego, x, y, 0, 0.01);
	}
	
	public void disparar(double x,double y,double velocidadDisparo) {
		this.x+=velocidadDisparo;	
	}
	
	//SIN USAR
//	public void setDireccion(boolean direccionDerecha) {
//		 this.direccionDerecha=direccionDerecha;
//	}
//	public boolean getDireccion() {
//		return direccionDerecha;
//	}
	
	public double getX() {
		return this.x;
	}
	
}
