package juego;

import java.awt.Image;

import entorno.Entorno;

public class DisparoTortuga {
	private double x,y;
	private double escala;
	private double alto;
	private double ancho;
	private Image disparoTortuga; 
	Entorno e;
	boolean mirandoDerecha;
	private int velocidad;
	
	public DisparoTortuga(double x ,double y,Entorno e,boolean mirandoDerecha){
		this.x = x;
		this.y = y;
		this.e=e;
		disparoTortuga = entorno.Herramientas.cargarImagen("disparoTortuga.png");
		this.escala=0.04;
		this.alto = disparoTortuga.getHeight(null)* escala;
		this.ancho = disparoTortuga.getWidth(null)*escala;
		this.mirandoDerecha = mirandoDerecha;
		this.velocidad = 1;
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
		this.e.dibujarImagen(disparoTortuga, x, y, 0, escala);
	}	
	
	
	public void dispararDerecha() {
        this.x += velocidad; // Mueve a la derecha independientemente de la tortuga
    }

    public void dispararIzquierda() {
        this.x -= velocidad; // Mueve a la izquierda independientemente de la tortuga
    }

	
}

