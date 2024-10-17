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
	Image imagen;
	Entorno e;
	
	public Pep(double x, double y,Entorno e){
		this.x = x;
		this.y = y;
		this.e=e;
		imagen=entorno.Herramientas.cargarImagen("pepDer.png");
		this.escala=0.05;
		this.alto = imagen.getHeight(null)* escala;
		this.ancho = imagen.getWidth(null)*escala;
		actualizarBordes();
		
	}
		
	public void mostrar() {
		this.e.dibujarImagen(imagen, x, y, 0, escala);
	}
	
	public void movHorizontal(double n) {
		this.x = this.x-n;
		if (x<0) {
			x=0 + this.ancho/2;
		}
		if(x>e.ancho()) {
			x = e.ancho() - this.ancho/2;
		}
		
	}
	
    public void movVertical() {
        if (!estaApoyado) {
            this.y++; 
        }

        actualizarBordes(); 
    }
	
    public void actualizarBordes() {
        // segun escala
        this.bordeAbajo = this.y + (this.alto / 2);
        this.bordeArriba = this.y - (this.alto / 2);
        this.bordeDerecho = this.x + (this.ancho / 2);
        this.bordeIzquierdo = this.x - (this.ancho / 2);
    }
}
