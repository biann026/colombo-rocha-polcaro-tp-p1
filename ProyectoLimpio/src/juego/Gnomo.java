package juego;

import java.awt.Image;
import java.util.Random;

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
	int direccion; // 1 para der, -1 para izq
	double velocidad;
	boolean yaCambioDireccion; //Para que no cambie a cada rato
	
	public Gnomo(double x, double y,Entorno e) {
		this.x = x;
		this.y = y;
		this.e=e;
		imagen=entorno.Herramientas.cargarImagen("gnomoIzq.png");		
		this.escala=0.05;
		this.alto = imagen.getHeight(null)* escala;
		this.ancho = imagen.getWidth(null)*escala;
        this.bordeAbajo = this.y + (this.alto / 2);
        this.bordeArriba = this.y - (this.alto / 2);
        this.bordeDerecho = this.x + (this.ancho / 2);
        this.bordeIzquierdo = this.x - (this.ancho / 2);
        this.direccion = (Math.random() < 0.5) ? 1 : -1; // REVISAR Para que tengan una direccion inicial aleatoria 
        this.velocidad = 0.5;
        this.yaCambioDireccion = false;
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
		this.e.dibujarImagen(imagen, x, y, 0, escala);
	}
	
	//Movimiento
    public void movVertical() {
        if (!estaApoyado) {
            this.y++; 
        }
        actualizarBordes();
    }
    
    public void cambiarDireccion() {
        if (estaApoyado && !yaCambioDireccion) {
            // Cambia la direccion
        	 if (this.direccion == 1) {
             	this.direccion = -1;
             }
             else {
             	this.direccion = 1;
             }
            yaCambioDireccion = true; // Para que no cambie de direccion constantemente 
            System.out.println("Gnomo cambió de dirección: " + this.direccion);
        } else if (!estaApoyado) {
            yaCambioDireccion = false; // Resetea
        }
    }
    
 
    public void movHorizontal() {
    	if (direccion == -1) {
    		this.x -= velocidad;
    	}
    	else {
    		this.x +=velocidad;
    	}
    }
	
    public void actualizarBordes() {
        // Segun escala
        this.bordeAbajo = this.y + (this.alto / 2);
        this.bordeArriba = this.y - (this.alto / 2);
        this.bordeDerecho = this.x + (this.ancho / 2);
        this.bordeIzquierdo = this.x - (this.ancho / 2);
    }
    

}
