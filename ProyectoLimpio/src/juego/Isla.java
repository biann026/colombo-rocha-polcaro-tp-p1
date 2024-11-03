package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

public class Isla {
	private double x,y;
	private double bordeAbajo;
	private double bordeArriba;
	private double bordeDerecho;
	private double bordeIzquierdo;
	private double escala;
	private double ancho;
	private double alto;
	private Image imagen;
	private double velocidad;  // Velocidad de movimiento de la isla
	private double anchoPantalla;
	Entorno e;
	
	
	public Isla(double x, double y,Entorno e) {
		
		this.x = x;
		this.y = y;
		this.e=e;
		imagen=entorno.Herramientas.cargarImagen("plataforma.png");
		this.escala=0.05;
		this.alto = imagen.getHeight(null)* escala;
		this.ancho = imagen.getWidth(null)*escala;
		this.anchoPantalla = e.ancho(); 
		this.velocidad = 0.5; //velocidad de islas
		this.anchoPantalla = e.ancho(); // Inicializa el ancho de pantalla
		
		
		
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
    
    public double getAnchoPantalla() { //Getter para anchoPantalla
        return this.anchoPantalla;
    }
    public double getVelocidad() {
        return this.velocidad;
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
	public void setVelocidad(int velocidad) {
	    this.velocidad = velocidad;
	}

	
	//METODOS DE ISLA 
	public void mover(Isla[] islas) {
	    this.x += this.velocidad;

	    // Verifica si toca los bordes y rebota
	    if (this.getBordeDerecho() >= anchoPantalla) {
	        this.x = anchoPantalla - (this.ancho / 2);
	        this.cambiarDireccion(); // Rebote
	        notificarCambioDireccion(islas); // Notifica a las demás islas
	    } else if (this.getBordeIzquierdo() <= 0) {
	        this.x = this.ancho / 2;
	        this.cambiarDireccion(); // Rebote
	        notificarCambioDireccion(islas); // Notifica a las demás islas
	    }

	    System.out.println("Isla movida a posición: " + this.x);
	}
	

	// Método para notificar a otras islas que cambien de dirección
	private void notificarCambioDireccion(Isla[] islas) {
	    // Cambia la dirección de todas las islas desde 3 a 9
	    for (int i = 3; i <= 9; i++) {
	        if (islas[i] != null && islas[i] != this) { 
	            islas[i].cambiarDireccion(); // Cambia la dirección de las otras islas
	        }
	    }
	}
	
	
	
	public void reaparicionDeIslas() {
		// Actualiza la posición de la isla
	    this.x += this.velocidad;

	    // Verifica si toca los bordes de la pantalla y reaparece en el lado opuesto
	    if (this.getBordeDerecho() >= anchoPantalla) {
	        this.x = -this.ancho / 2; // Reaparece por el borde izquierdo
	        System.out.println("Isla reapareció por el borde izquierdo en posición: " + this.x);
	    }
	}
	public void cambiarDireccion() {
	    this.velocidad = -this.velocidad; // Cambia la dirección
	}

	
	public void mostrar() {
		this.e.dibujarImagen(imagen, x, y, 0, escala);
	}


	
}
	
   