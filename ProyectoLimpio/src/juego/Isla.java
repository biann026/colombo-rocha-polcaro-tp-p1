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
		imagen=entorno.Herramientas.cargarImagen("imagen3.png");
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
	
	public void actualizarBordes() {
	    this.bordeAbajo = this.y + (this.alto / 2);  // Borde inferior
	    this.bordeArriba = this.y - (this.alto / 2);  // Borde superior
	    this.bordeDerecho = this.x + (this.ancho / 2);  // Borde derecho
	    this.bordeIzquierdo = this.x - (this.ancho / 2);  // Borde izquierdo
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
	public double getAlto() {
		return this.ancho;
	}
	public double getBordeAbajo() {
        return this.y + (this.alto / 2);
    }

    public double getBordeArriba() {
        return this.y - (this.alto / 2);
    }

    public double getBordeDerecho() {
        return this.x + (this.ancho / 2);
    }

    public double getBordeIzquierdo() {
        return this.x - (this.ancho / 2);
    }
}
