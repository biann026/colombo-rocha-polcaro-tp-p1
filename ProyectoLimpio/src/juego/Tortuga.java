package juego;


import java.awt.Image;


import entorno.Entorno;

public class Tortuga {
	private double x,y;
	private double ancho;
	private double alto;
	private double escala;
	private double desplazamiento;
	private Image imagenDer;
	private Image imagenIzq;
	Entorno e;
	boolean estaApoyado;
	boolean mirandoDerecha = true;
	private double velocidadCaida;
	private double velocidad;
	private int direccion;//1 derecha -1 izq

	
	
	public Tortuga(double x, double y,Entorno e) {
		this.x = x;
		this.y = y;
		this.e=e;
		imagenDer=entorno.Herramientas.cargarImagen("MonstruitoDer.gif");
		imagenIzq=entorno.Herramientas.cargarImagen("MonstruitoIzq.gif");
		this.escala=0.25;
		this.alto = imagenDer.getHeight(null)* escala;
		this.ancho = imagenDer.getWidth(null)*escala;
		this.estaApoyado = false;//si lo pongo en true no funciona
		this.desplazamiento = 0.1;
		this.mirandoDerecha = false;
		this.velocidadCaida = 0.8;
		this.velocidad=0.7;
		this.direccion=1;
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
	
	//METODOS DE TORTUGA
   
	
    public void movHorizontalmente() {
    	this.x-=desplazamiento;  	
    }
    
    
    public void rebote() {
    	this.desplazamiento = this.desplazamiento *(-1);
    	
    }
    
    
    //Movimiento
    public void movVertical() {
        if (!estaApoyado) {
            this.y += this.velocidadCaida; 
        }       
    }
     
 	public void mostrarDerechaTortuga() {
 	    this.mirandoDerecha = true;
 	}

 	public void mostrarIzquierdaTortuga() {
 	    this.mirandoDerecha = false;
 	}

 	public void mostrarTortugas() {
 	    if (mirandoDerecha) {
 	        this.e.dibujarImagen(imagenDer, this.x, this.y, 0, escala); 
 	    } else {
 	        this.e.dibujarImagen(imagenIzq, this.x, this.y, 0, escala); 
 	    }
 	}
 	
 	
 	//movimieto es las islas 
 	
 	public void moverEnIsla(Isla isla) {
 	    // Ajusta la dirección inicial de la imagen según el movimiento
 	    if (this.direccion == 1) {
 	        this.mostrarDerechaTortuga(); // La imagen muestra movimiento hacia la derecha
 	    } else if (this.direccion == -1) {
 	        this.mostrarIzquierdaTortuga(); // La imagen muestra movimiento hacia la izquierda
 	    }


 	 	    // Movimiento horizontal
 	 	    this.x += this.velocidad * this.direccion;

 	 	    // Cambia dirección si llega a un borde de la isla
 	 	    if (this.getBordeDerecho() >= isla.getBordeDerecho()) {
 	 	        this.x = isla.getBordeDerecho() - this.ancho / 2; // Ajusta para que no salga
 	 	        this.direccion = -1; // Cambia a la izquierda
 	 	        this.mostrarIzquierdaTortuga(); // Cambia la imagen a la que mira a la izquierda
 	 	    } else if (this.getBordeIzquierdo() <= isla.getBordeIzquierdo()) {
 	 	        this.x = isla.getBordeIzquierdo() + this.ancho / 2; // Ajusta para que no salga
 	 	        this.direccion = 1; // Cambia a la derecha
 	 	        this.mostrarDerechaTortuga(); // Cambia la imagen a la que mira a la derecha
 	 	    }
 	 	}
 	}

 	   

