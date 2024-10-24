package juego;

import java.awt.Image;

import entorno.Entorno;

public class DisparoDePep {
	double x,y;
	double bordeDerecho;
	double bordeIzquierdo;
	double bordeArriba;
	double bordeAbajo;
	double escala;
	double alto;
	double ancho;
	Image imagenBolaDeFuego; 
	Entorno e;
	
	public DisparoDePep(double x ,double y,Entorno e){
		this.x = x;
		this.y = y;
		this.e=e;
		imagenBolaDeFuego = entorno.Herramientas.cargarImagen("bolaDeFuego.png");
		this.escala=0.015;
		this.alto = imagenBolaDeFuego.getHeight(null)* escala;
		this.ancho = imagenBolaDeFuego.getWidth(null)*escala;
	}
	
//GETTERS Y SETTERS 
    
    public double getBordeArriba(){
    	return this.y - (this.alto / 2);
    }
    
    public double getBordeAbajo(){
    	return this.y + (this.alto / 2);
    }
    
    public double getBordeDerecho() {
    	return this.x + (this.ancho / 2);
    }
    
    public double getBordeIzquierdo() {
    	return this.x - (this.ancho / 2);
    }
    
    public void setBordeArriba(double b) {
    	this.bordeArriba = b;
    }
    public void setBordeAbajo(double a) {
    	this.bordeAbajo = a;
    }
    public void setBordeDerecho(double d) {
    	this.bordeArriba = d;
    }
    public void setBordeIzquierdo(double i) {
    	this.bordeArriba = i;
    }
    
	public double getX() {
		return this.x;
	}
 
	public double getY() {
		return this.y;
	}
	
	public void setX(double x) {
	    this.x = x;
	}
	
	public void setY(double y) {
	    this.y = y;
	}
	 
	public double getAncho() {		
		return this.ancho;
	}

	public double getAlto() {
		return this.alto;
	}
	
	
	//METODOS DEL DISPARO 
	
	public void mostrar(Entorno e) {
		this.e.dibujarImagen(imagenBolaDeFuego, x, y, 0, escala);
	}
	
	public void disparar(double x,double y,double velocidadDisparo) {
		this.x+=velocidadDisparo;	
	}
	
	//ESTO VA EN JUEGO
//	public boolean disparoColisionaConTortuga(Tortuga t) {
//		boolean colisionHorizontal = this.x - this.ancho/2 > t.getX()-t.getAncho()/2 && this.x + this.ancho/2 < t.getX()+t.getAncho()/2;
//		boolean colisionVertical = this.y - this.alto/2 > t.getY() - t.getAlto()/2 && this.y + this.alto/2 < t.getY()+t.getAlto()/2;
//		return colisionHorizontal && colisionVertical;
//	}

	

	
}

