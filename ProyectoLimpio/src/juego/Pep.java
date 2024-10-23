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
	boolean estaSaltando;
	double limite;
	double velocidadDeSalto;
	
	public Pep(double x, double y,Entorno e){
		this.x = x;
		this.y = y;
		this.e=e;
		imagen=entorno.Herramientas.cargarImagen("pepDer.png");
		this.escala=0.05;
		this.alto = imagen.getHeight(null)* escala;
		this.ancho = imagen.getWidth(null)*escala;
        this.bordeAbajo = this.y + (this.alto / 2);
        this.bordeArriba = this.y - (this.alto / 2);
        this.bordeDerecho = this.x + (this.ancho / 2);
        this.bordeIzquierdo = this.x - (this.ancho / 2);
		this.limite =0; //limite inicial no c
		this.velocidadDeSalto = 3;
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
        if (!estaApoyado && !estaSaltando) {
            this.y++; 
        }

        actualizarBordes(); //funcion
    }
	
    public void saltar() {
    	this.y -= this.velocidadDeSalto;
    	}

    
    
    public void iniciarSalto() {
    	if (this.estaApoyado) {
    		this.estaSaltando = true;
    		setLimite();
    	}
    }
     
    
    
    public void actualizarBordes() {
        // segun escala
        this.bordeAbajo = this.y + (this.alto / 2);
        this.bordeArriba = this.y - (this.alto / 2);
        this.bordeDerecho = this.x + (this.ancho / 2);
        this.bordeIzquierdo = this.x - (this.ancho / 2);
    }

    
    //GETTERS Y SETTERS 
    
    public void setLimite() {
    	this.limite = this.y - 100;
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
    
}

