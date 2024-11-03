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
	boolean mirandoDerecha;
	Image imagenBolaDeFuego;
	double velocidadDisparo;
	boolean yaDisparo;
	Entorno e;
	
	public DisparoDePep(double x ,double y,Entorno e){
		this.x = x;
		this.y = y;
		this.e=e;
		imagenBolaDeFuego = entorno.Herramientas.cargarImagen("bolaDeFuego.png");
		this.escala=0.018;
		this.alto = imagenBolaDeFuego.getHeight(null)* escala;
		this.ancho = imagenBolaDeFuego.getWidth(null)*escala;
		this.velocidadDisparo=4;
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
	
	public void movimientoDisparo() {
		if(mirandoDerecha){
        this.x += velocidadDisparo; // Mueve a la derecha la tortuga
		}
	 else {
		 this.x -= velocidadDisparo;
	}
}
   
	

	

	
}

