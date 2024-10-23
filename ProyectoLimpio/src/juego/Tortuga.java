package juego;

import java.awt.Image;

import entorno.Entorno;

public class Tortuga {
	double x,y;
	double bordeAbajo;
	double bordeArriba;
	double bordeDerecho;
	double bordeIzquierdo;
	double ancho;
	double alto;
	double escala;
	double desplazamiento;
	Image imagen;
	Entorno e;
	boolean estaApoyado;
	
	public Tortuga(double x, double y,Entorno e) {
		this.x = x;
		this.y = y;
		this.e=e;
		imagen=entorno.Herramientas.cargarImagen("tortugaDer.png");
		imagen=entorno.Herramientas.cargarImagen("tortugaIzq.png");
		this.escala=0.05;
		this.alto = imagen.getHeight(null)* escala;
		this.ancho = imagen.getWidth(null)*escala;
        this.bordeAbajo = this.y + (this.alto / 2);
        this.bordeArriba = this.y - (this.alto / 2);
        this.bordeDerecho = this.x + (this.ancho / 2);
        this.bordeIzquierdo = this.x - (this.ancho / 2);
	//	this.estaApoyado = true;//si lo pongo en true no funciona
		this.desplazamiento = 0.5;
	}
	
	public void mostrar() {
		this.e.dibujarImagen(imagen, x, y, 0, escala);
	}
	
    public void actualizarBordes() {
        // Segun escala
        this.bordeAbajo = this.y + (this.alto / 2);
        this.bordeArriba = this.y - (this.alto / 2);
        this.bordeDerecho = this.x + (this.ancho / 2);
        this.bordeIzquierdo = this.x - (this.ancho / 2);
    }
    
    public void movDerecha(){
    	
    		this.x+=desplazamiento;
    	
    }
    public void movIzquierda() {
    	
    		this.x-=desplazamiento;
    	
    }
    public void movHorizontalmente() {
    	this.x-=desplazamiento;
    }
    public void rebote() {
    	this.desplazamiento = this.desplazamiento *(-1);
    }
    
    public boolean colisionaBordeIslaDerecha(Isla i){
    	if(this.x + this.ancho/2 >= i.getX() + i.getAncho()/2) {
    		return true;
    	}
		return false;
    	
    }
    
    
    //Movimiento
    public void movVertical() {
        if (!estaApoyado) {
            this.y++; 
        }
        actualizarBordes(); 
    }
//    public void rebote() {
//    	this.desplazamiento= this.desplazamiento*(-1);
//    }

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



    
    
    
//    public void rebotar(double factorRebote, int bordeDerecho, int bordeIzquierdo) {
//        // Si está en el borde derecho, invertimos la velocidad
//        if (this.bordeDerecho >= bordeDerecho) {
//            this.velocidadX = -this.velocidadX * factorRebote;  // Invertimos y aplicamos el factor de rebote
//        }
//
//        // También puedes hacer algo similar si quieres manejar el borde izquierdo
//        if (this.bordeIzquierdo <= bordeIzquierdo) {
//            this.velocidadX = -this.velocidadX * factorRebote;
//        }
//    }
    
    
    
    
    
    
   
//    
//    public void rebotar(double deslizamiento,double bordeDeIslaDerecha,double bordeDeIslaIzquierda) {
//
//    	if(estaApoyado) {  
//    		this.x += deslizamiento; 
//    		System.out.println("derecha");
//    		if((bordeDeIslaDerecha)-5<this.bordeDerecho) {
//    			this.x-=deslizamiento;
//    			System.out.println("izquierda");
//    			}
//
//    	}
    	
    	
    	
    	
//    	test
//    	if(estaApoyado) {  
//    	    System.out.println("tiene que rebotar");
//
//    	    // Si el random es 1, mueve el objeto
//    	    if(random == 1) {
//    	        // Desplaza el objeto hacia la derecha
//    	        this.x += deslizamiento;
//    	        
//    	        // Verifica si ha llegado al borde derecho de la isla
//    	        if(this.bordeDerecho >= (bordeDeIslaDerecha - 5)) {
//    	            // Invertimos la dirección al tocar el borde derecho
//    	            deslizamiento = -deslizamiento; 
//    	            this.x += deslizamiento; // Ahora el objeto se moverá hacia la izquierda
//    	            System.out.println("Rebota en el borde derecho");
//    	        }
//    	    }
//
//    	    // Verifica si ha llegado al borde izquierdo de la isla
//    	    if(this.bordeIzquierdo <= (bordeDeIslaIzquierda + 5)) {
//    	        // Invertimos la dirección al tocar el borde izquierdo
//    	        deslizamiento = -deslizamiento;
//    	        this.x += deslizamiento; // Ahora el objeto se moverá hacia la derecha
//    	        System.out.println("Rebota en el borde izquierdo");
//    	    }
//    	}

    
    
    
}
