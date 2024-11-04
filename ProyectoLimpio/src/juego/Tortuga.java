package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;

public class Tortuga {
	private double x,y;
	private double bordeAbajo;
	private double bordeArriba;
	private double bordeDerecho;
	private double bordeIzquierdo;
	private double ancho;
	private double alto;
	private double escala;
	private double desplazamiento;
	private Image imagenDer;
	private Image imagenIzq;
	Entorno e;
	boolean estaApoyado;
	boolean mirandoDerecha = true;
	
	public Tortuga(double x, double y,Entorno e) {
		this.x = x;
		this.y = y;
		this.e=e;
		imagenDer=entorno.Herramientas.cargarImagen("MonstruitoDer.gif");
		imagenIzq=entorno.Herramientas.cargarImagen("MonstruitoIzq.gif");
		this.escala=0.25;
		this.alto = imagenDer.getHeight(null)* escala;
		this.ancho = imagenDer.getWidth(null)*escala;
	//	this.estaApoyado = true;//si lo pongo en true no funciona
		this.desplazamiento = 0.2;
		this.mirandoDerecha = false;
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
	
	//METODOS DE TORTUGA
   
	
    public void movHorizontalmente() {
    	this.x-=desplazamiento;  	
    }
    
    
    public void rebote() {
    	this.desplazamiento = this.desplazamiento *(-1);
    }
    
    
    //Movimiento
    public void movVertical() {
        if (!estaApoyado) {
            this.y++; 
        }       
    }
     
 	public void mostrarDerechaTortuga() {
 	    this.mirandoDerecha = true;
 	}

 	public void mostrarIzquierdaTortuga() {
 	    this.mirandoDerecha = false;
 	}

 	public void mostrarTortugas() {
 	    if (mirandoDerecha) {
 	        this.e.dibujarImagen(imagenDer, this.x, this.y, 0, escala); 
 	    } else {
 	        this.e.dibujarImagen(imagenIzq, this.x, this.y, 0, escala); 
 	    }
 	}

 	
    
}