package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class ControladorPantalla {
	private double x,y;
	Image imagenFondoJuego;
	Image imagenFondoGanar;
	Image imagenFondoPerder;
	Entorno e;
	

	public ControladorPantalla() {                       
		imagenFondoJuego= Herramientas.cargarImagen("imagenDia.jpg");
		imagenFondoGanar= Herramientas.cargarImagen("imagenAtardecer.jpg");
		imagenFondoPerder= Herramientas.cargarImagen("imagenNoche.jpg");
	}
	
	
	public void mostarPantallaInicial(Entorno e, int gnomosSalvados,int gnomosPerdidos,int enemigosEliminados) {
		e.dibujarImagen(imagenFondoJuego, e.ancho() / 2, e.alto() / 2, 0, 0.555);
		
		e.cambiarFont("Press Start 2P", 10, Color.white);
		e.escribirTexto("Gallinas salvadas: "+gnomosSalvados+"     Gallinas perdidas: "+gnomosPerdidos+"     Enemigos eliminados: "+enemigosEliminados, 24,25);
		
		e.cambiarFont("Press Start 2P", 10, Color.black);
		e.escribirTexto("Gallinas salvadas: "+gnomosSalvados+"     Gallinas perdidas: "+gnomosPerdidos+"     Enemigos eliminados: "+enemigosEliminados, 25,24);
	}
	
	public void mostrarPartidaGanada(Entorno e, int gnomosSalvados,int gnomosPerdidos,int enemigosEliminados) {
		
		e.dibujarImagen(imagenFondoGanar, e.ancho() / 2, e.alto() / 2, 0, 0.555);
    	e.cambiarFont("Press Start 2P", 39, Color.white);
    	e.escribirTexto("¡Ganaste el juego! ", 40, 170);  
    	//sombreado
    	e.cambiarFont("Press Start 2P", 39, Color.black);
    	e.escribirTexto("¡Ganaste el juego! ", 45, 165);
    	
    	
    	//muestra cuantos gnomos se Perdieron 
    	e.cambiarFont("Press Start 2P", 25, Color.white);
    	e.escribirTexto("Gallinas perdidas: "+ gnomosPerdidos, 170,312);   //sombreado
    	
    	e.cambiarFont("Press Start 2P", 25, Color.black);
    	e.escribirTexto("Gallinas perdidas: "+ gnomosPerdidos, 173,310);
    	
    	//muestra cuantos gnomos se Salvaron 
    	e.cambiarFont("Press Start 2P", 25, Color.white);//sombreado
    	e.escribirTexto("Gallinas salvadas: "+ gnomosSalvados, 170,382);   
    	
    	e.cambiarFont("Press Start 2P", 25, Color.black);
    	e.escribirTexto("Gallinas salvadas: "+ gnomosSalvados, 173,380);
    	
    	
    	//muestra cuantos enemigos eliminados
    	e.cambiarFont("Press Start 2P", 25, Color.white);	//sombreado
    	e.escribirTexto("Enemigos eliminados: "+ enemigosEliminados, 170,442);   
    	
    	e.cambiarFont("Press Start 2P", 25, Color.black);
    	e.escribirTexto("Enemigos eliminados: "+ enemigosEliminados, 173,440);
	}
	
	public void mostrarPartidaPerdida(Entorno e, int gnomosPerdidos,int gnomosSalvados,int enemigosEliminados) {
    	e.dibujarImagen(imagenFondoPerder, (e.ancho()/2), e.alto()/2, 0, 0.555);
    	e.cambiarFont("Press Start 2P", 39, Color.black);
    	e.escribirTexto("¡Perdiste el juego! ", 40, 170);  
    	//sombreado
    	e.cambiarFont("Press Start 2P", 39, Color.white);
    	e.escribirTexto("¡Perdiste el juego! ", 43, 165);
    	
    	
    	//muestra cuantos gnomos se Perdieron 
    	e.cambiarFont("Press Start 2P", 25, Color.black);
    	e.escribirTexto("Gallinas perdidas: "+ gnomosPerdidos, 170,312);   
    	
    	e.cambiarFont("Press Start 2P", 25, Color.white);
    	e.escribirTexto("Gallinas perdidas: "+ gnomosPerdidos, 172,310);
    	
    	//muestra cuantos gnomos se Salvaron 
    	e.cambiarFont("Press Start 2P", 25, Color.black);
    	e.escribirTexto("Gallinas salvadas: "+ gnomosSalvados, 170,382);   
    	
    	e.cambiarFont("Press Start 2P", 25, Color.white);
    	e.escribirTexto("Gallinas salvadas: "+ gnomosSalvados, 172,380);
    	
    	
    	//muestra cuantos enemigos eliminados
    	e.cambiarFont("Press Start 2P", 25, Color.black);
    	e.escribirTexto("Enemigos eliminados: "+ enemigosEliminados, 170,442);   
    	
    	e.cambiarFont("Press Start 2P", 25, Color.white);
    	e.escribirTexto("Enemigos eliminados: "+ enemigosEliminados, 172,440);
	}
	
	
}
