package juego;

import java.awt.Color;

import entorno.Entorno;

public class Reloj {
	private int horas;
	private int minutos;
	private int segundos;
	Entorno e;
    private boolean tiempoCongelado = false;
    private int horasCongeladas;
    private int minutosCongelados;
    private int segundosCongelados;
	
	public Reloj(Entorno e) {
		    this.e = e;
	        this.horas = 0;    // Inicializamos en 0
	        this.minutos = 0;
	        this.segundos = 0;
	        
	}
	public void mostrar(Entorno e) {
		 if (!tiempoCongelado) {
	       int sec = e.tiempo() / 1000; //  e.tiempo() devuelve el tiempo en milisegundos
	        this.segundos = sec % 60;
	        int min = sec / 60;
	        this.minutos = min % 60;
	        this.horas = min / 60;
	        
		e.cambiarFont("Press Start 2P", 10, Color.white, e.NORMAL);
        e.escribirTexto(String.format("tiempo: "+"%02d:%02d:%02d", horas, minutos, segundos), 574, 575);
        
		e.cambiarFont("Press Start 2P", 10, Color.BLACK, e.NORMAL);
        e.escribirTexto(String.format("tiempo: "+"%02d:%02d:%02d", horas, minutos, segundos), 575, 574);
	}	
	
	}	
	
	
    public void congelarTiempo() {
        // Guardamos el tiempo actual y congelamos la actualización del reloj
        this.horasCongeladas = this.horas;
        this.minutosCongelados = this.minutos;
        this.segundosCongelados = this.segundos;
        this.tiempoCongelado = true;
    }
  
    public void mostrarTiempoCongelado(Pep p) {
        if (tiempoCongelado&&p!=null) {
        	
        	e.cambiarFont("Press Start 2P", 20, Color.white, e.NORMAL);
            e.escribirTexto(String.format("Tiempo total: "+"%02d:%02d:%02d", horasCongeladas, minutosCongelados, segundosCongelados), 172, 574);
       
        	e.cambiarFont("Press Start 2P", 20, Color.black, e.NORMAL);
            e.escribirTexto(String.format("Tiempo total: "+"%02d:%02d:%02d", horasCongeladas, minutosCongelados, segundosCongelados), 174, 572);
       
        }else if(tiempoCongelado&& p==null) {
        	e.cambiarFont("Press Start 2P", 20, Color.black, e.NORMAL);
            e.escribirTexto(String.format("Tiempo total: "+"%02d:%02d:%02d", horasCongeladas, minutosCongelados, segundosCongelados), 172, 574);
       
        	e.cambiarFont("Press Start 2P", 20, Color.white, e.NORMAL);
            e.escribirTexto(String.format("Tiempo total: "+"%02d:%02d:%02d", horasCongeladas, minutosCongelados, segundosCongelados), 174, 572);
       
        }
        
    }
    

	

}

