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
	boolean cayendo;
	
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
		this.cayendo=true;
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
    
    public void movVerticalmente() {
       
            this.y++; 
        }

       
    
    public void movHorizontal(int signo) {
    	this.x-=signo;
    }
    
	
    public void actualizarBordes() {
        // Segun escala
        this.bordeAbajo = this.y + (this.alto / 2);
        this.bordeArriba = this.y - (this.alto / 2);
        this.bordeDerecho = this.x + (this.ancho / 2);
        this.bordeIzquierdo = this.x - (this.ancho / 2);
    }
    
    //luego pasar a Clase Colisiones
    
    public boolean GnomoColisionIsla(Isla i ) {
    	boolean colisionHorizontal = this.x + this.ancho/2 > i.getX()-i.getAncho()/2 && this.x -this.ancho/2 < i.getX()+i.getAncho()/2;
    	
        double epsilon = 1.0; // Tolerancia pequeña para la colisión vertical
        boolean colisionVertical = Math.abs((this.y + this.alto / 2) - (i.getY() - i.getAlto() / 2)) < epsilon;
    	
    	return colisionVertical && colisionHorizontal;
    }

	public double getY() {
		return this.y;
	}
    
    
 

}
