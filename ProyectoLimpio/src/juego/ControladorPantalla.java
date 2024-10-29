package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class ControladorPantalla {
	private double x,y;
	Image imagenFondoJuego;
	Image imagenFondoGanar;
	Image imagenFondoPerder;
	Entorno e;
	
	public ControladorPantalla(double x, double y, Entorno e) {
		imagenFondoJuego= Herramientas.cargarImagen("imagenDia.jpg");
		imagenFondoGanar= Herramientas.cargarImagen("imagenAtardecer.jpg");
		imagenFondoPerder= Herramientas.cargarImagen("imagenNoche.jpg");
	}
	
	public void mostrarPartidaGanada() {
		e.dibujarImagen(imagenFondoGanar, x, y, y,x);
	}
}
