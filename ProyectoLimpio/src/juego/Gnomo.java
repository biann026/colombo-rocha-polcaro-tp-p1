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
	boolean estaApoyado;
	
	public Gnomo(double x, double y,Entorno e) {
		this.x = x;
		this.y = y;
		this.e=e;
		imagen=entorno.Herramientas.cargarImagen("gnomoDer.png");
		imagen=entorno.Herramientas.cargarImagen("gnomoIzq.png");		
		this.escala=0.05;
		this.alto = imagen.getHeight(null)* escala;
		this.ancho = imagen.getWidth(null)*escala;
		actualizarBordes();
	}
	
	public void mostrar() {
		this.e.dibujarImagen(imagen, x, y, 0, escala);
	}
	
	//Movimiento
    public void movVertical() {
        if (!estaApoyado) {
            this.y++; 
        }

        actualizarBordes(); 
    }
	
    public void actualizarBordes() {
        // Segun escala
        this.bordeAbajo = this.y + (this.alto / 2);
        this.bordeArriba = this.y - (this.alto / 2);
        this.bordeDerecho = this.x + (this.ancho / 2);
        this.bordeIzquierdo = this.x - (this.ancho / 2);
    }

}
