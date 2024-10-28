package juego;

import java.awt.Color;

import entorno.Entorno;

public class Reloj {
	int horas;
	int minutos;
	int segundos;
	Entorno e;
	public Reloj(Entorno e) {
		    this.e = e;
	        this.horas = 0;    // Inicializamos en 0
	        this.minutos = 0;
	        this.segundos = 0;
	}
	public void mostrar(Entorno e) {
	       int sec = e.tiempo() / 1000; // Suponiendo que e.tiempo() devuelve el tiempo en milisegundos
	        this.segundos = sec % 60;
	        int min = sec / 60;
	        this.minutos = min % 60;
	        this.horas = min / 60;
	        
		e.cambiarFont("Ebrima", 20, Color.BLACK, e.NORMAL);
        e.escribirTexto(String.format("tiempo: "+"%02d:%02d:%02d", horas, minutos, segundos), 620, 25);
	}	
	 //metodo para temporizar la caida de gnomos
	public boolean temporizador(int segundos) {
		if(this.segundos%segundos==0) {
			return true;
		}
		return false;
	}
}

