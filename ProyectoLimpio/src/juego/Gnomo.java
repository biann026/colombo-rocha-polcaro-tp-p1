package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;

public class Gnomo {
	private double x,y;
	private double bordeAbajo;
	private double bordeArriba;
	private double bordeDerecho;
	private double bordeIzquierdo;
	private double ancho;
	private double alto;
	private double escala;
	private Image imagenDerecha;
	private Image imagenIzquierda;
	Entorno e;
	boolean estaApoyado;
	private int direccion; // 1 para der, -1 para izq
	private double velocidad;
	private boolean mirandoDerecha;
	boolean estabaApoyado;
	
	public Gnomo(double x, double y,Entorno e) {
		this.x = x;
		this.y = y;
		this.e=e;
		imagenDerecha=entorno.Herramientas.cargarImagen("GallinitaDer.gif");
		imagenIzquierda=entorno.Herramientas.cargarImagen("GallinitaIzq.gif");
		this.escala=0.25;
		this.alto = imagenDerecha.getHeight(null)* escala;
		this.ancho = imagenDerecha.getWidth(null)*escala;
        this.direccion = (Math.random() < 0.5) ? 1 : -1; // REVISAR Para que tengan una direccion inicial aleatoria 
        this.velocidad = 0.45;
        this.mirandoDerecha = true;
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

	
	//METODOS DE GNOMO
	public void mostrar() {
		if (mirandoDerecha) {
			this.e.dibujarImagen(imagenDerecha, x, y, 0, escala);
		}
		if (!mirandoDerecha) {
			this.e.dibujarImagen(imagenIzquierda, x, y, 0, escala);
		}
	}
	
	//Movimiento
    public void movVertical() {
        if (!estaApoyado) {
            this.y+=velocidad; 
        }
    }
    
    public void cambiarDireccion() {
            Random random = new Random();
            int nAleatorio= random.nextInt(51);
            if (nAleatorio >25){
             	this.direccion = -1;
             }
             else {
             	this.direccion = 1;
             }
            
    }
    
 
    public void movHorizontal() {
    	if (direccion == -1) {
    		this.setX(this.x-velocidad);
    		mostrarIzquierdaGnomo();
    	}
    	else {
    		this.setX(this.x +=velocidad);
    		mostrarDerechaGnomo();
    	}
    }
    
    public void mostrarDerechaGnomo() {
        this.mirandoDerecha = true;
    }
    
    public void mostrarIzquierdaGnomo() {
        this.mirandoDerecha = false;
    }
}
