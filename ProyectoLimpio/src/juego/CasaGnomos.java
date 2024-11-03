package juego;

import java.awt.Image;

import entorno.Entorno;

public class CasaGnomos {
	private double x,y;
	private double bordeAbajo;
	private double bordeArriba;
	private double bordeDerecho;
	private double bordeIzquierdo;
	private double escala;
	private double ancho;
	private double alto;
	private Image imagen;
	private Entorno e;
	
	public CasaGnomos(double x, double y, Entorno e) {
		this.x = x;
		this.y = y;
		this.e=e;
		imagen=entorno.Herramientas.cargarImagen("CasaDeGnomos.png");
		this.escala=0.1;
		this.alto = imagen.getHeight(null)* escala;
		this.ancho = imagen.getWidth(null)*escala;
	}
	
	
	public void mostrar() {
		this.e.dibujarImagen(imagen, x, y, 0, escala);
	}
	
	//GETTERS SETTERS 
	public double getX() {
		return this.x;
	}
 
	public double getY() {
		return this.y;
	}
	public double getAncho() {
		return this.ancho;
	}
	
	public double getAlto() {
		return this.alto;
	}
	
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
}

