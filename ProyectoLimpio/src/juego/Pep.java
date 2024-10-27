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
	Image imagenDerecha;
	Image imagenIzquierda;
	Entorno e;
	boolean estaSaltando;
	double limite;
	double velocidadDeSalto;
	private boolean mirandoDerecha; 
	
	public Pep(double x, double y,Entorno e){
		this.x = x;
		this.y = y;
		this.e=e;
		imagenDerecha=entorno.Herramientas.cargarImagen("pepDer.png");
		imagenIzquierda=entorno.Herramientas.cargarImagen("pepIzq.png");
		this.escala=0.05;
		this.alto = imagenDerecha.getHeight(null)* escala;
		this.ancho = imagenDerecha.getWidth(null)*escala;
		this.limite =0; //limite inicial no c
		this.velocidadDeSalto = 3;
		this.mirandoDerecha = true;
	}
		
	
	
    public void mostrarDerechaPep() {
        this.mirandoDerecha = true;
    }
    
    public void mostrarIzquierdaPep() {
        this.mirandoDerecha = false;
    }
	
    public void mostrarAPep() {
        if (mirandoDerecha) {
        	this.e.dibujarImagen(imagenDerecha, this.x, this.y, 0, escala);
        	
        } else {
        	this.e.dibujarImagen(imagenIzquierda, this.x, this.y, 0, escala);	
        }
    }
    
    public void movHorizontalmenteAPep(int velocidad) {
        this.x += velocidad;   
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
	public void setLimite() {
    	this.limite = this.y - 100;
    }
	
	
	//METODOS DE PEP
	
	
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
            this.y+=(this.velocidadDeSalto+0.01); 
        }
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
	
}

