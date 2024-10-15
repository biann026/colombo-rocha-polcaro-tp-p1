package juego;

import java.awt.Image;

import entorno.Entorno;

public class Plataforma {
	double x;
	double y;;
	double escala;
	Image imagen;
	
	public Plataforma(double x,double y) {
		this.x = x;
		this.y = y;
		this.escala = 0.07;
		this.imagen = entorno.Herramientas.cargarImagen("plataforma.png");
	}
	
	public void mostrarplataforma(Entorno e) {
		e.dibujarImagen(imagen, x, y, 0, escala);
	}
}
