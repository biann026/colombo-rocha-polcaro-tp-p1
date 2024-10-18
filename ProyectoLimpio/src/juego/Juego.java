package juego;


import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Image imagenFondo;
	private Isla[] islas;
	private Pep pep;
	private DisparoDePep disparoPep;
	private Tortuga tortuga;
	private Tortuga[] tortuguita;
	private Gnomo gnomo;
	private boolean derechaDisparo=false;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...	
		imagenFondo =  Herramientas.cargarImagen("cielo.png");
		this.pep = new Pep(380,0 , entorno);
		this.tortuga = new Tortuga(200, 0, entorno); 
		
		// ARREGLO DE TORTUGAS 
		this.tortuguita = new Tortuga[4];
		for(int i=0; i<tortuguita.length; i++) {
			this.tortuguita[i] = new Tortuga(50*(i+7), 150, entorno);
			
		}
		
		
		
		this.gnomo = new Gnomo(410, 40, entorno);
		
		//prueba spawn piramidal de islas ?¿
		// Inicializar las islas
		this.islas = new Isla[15]; // 15 islas en total
		int k = 0;
		int alturaInicial = 100;  // Altura inicial para la primera fila
		int distanciaVertical = 100; // Entre filas

		for (int fila = 1; fila <= 5; fila++) {
		    // Calcular la posicion horizontal de la primera isla en cada fila
		    int cantidadIslas = fila;
		    int anchoPantalla = entorno.ancho();
		    int espacioEntreIslas = anchoPantalla / (cantidadIslas + 1);  

		    for (int j = 0; j < cantidadIslas; j++) {
		        // Posicionar las islas en la fila de manera centrada
		        int posicionX = espacioEntreIslas * (j + 1);
		        int posicionY = alturaInicial + distanciaVertical * (fila - 1);
		        
		        // Crear la isla 
		        this.islas[k] = new Isla(posicionX, posicionY, entorno);
		        k++;
		    }
		}
		
		
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
	        // Cambiar fondo
	        entorno.dibujarImagen(imagenFondo, entorno.ancho() / 2, entorno.alto() / 2, 0, 0.55);
	        
	        mostrarIslas();
	        
	        verificarColisionesTortu();
	        verificarColisionesTortu2();
	        

	        // COSAS DE PEP
	    
	        //Verifica que pep este vivo antes de hacer todo lo demas 
	        if (pep != null) { 
	        	//Colisiones de pep con cosas
		        verificarColisiones(); //Pep con islas 
		        verificarColisionPepTortu(); //Pep con Tortugas
	        	//Movimiento vertical de pep
	            if (!pep.estaApoyado) {
	                pep.movVertical(); 
	                System.out.println("Pep no está apoyado.");
	            }
	            
	            pep.mostrar();
	            
	            // Movimiento horizontal 
	            if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
	                pep.movHorizontal(-2);  // Mover a la derecha
	            }
	            if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
	                pep.movHorizontal(2);  // Mover a la izquierda
	            }
	            
	        } else {
	            System.out.println("Pep ha sido eliminado.");
	        }
	        	        
	        
	        //DISPARO DE PEP 
	        //se crea el disparo si hay uno en pantalla
	        if(entorno.sePresiono('c') && disparoPep == null && pep != null) {
	    		this.disparoPep = new DisparoDePep(pep.getX(), pep.getY()+10, entorno);
	    		System.out.println("DISPARO");
	        	
	        }
	        //Si desaparece el disparo del entorno
	        if(disparoPep!=null && (disparoPep.getX()<0 ||disparoPep.getX()>entorno.ancho())) {
	        	disparoPep = null;
	        }
	        //Verifico las direcciones 
	        if(disparoPep == null) {
	        	if(entorno.sePresiono(entorno.TECLA_DERECHA)) {
	        		this.derechaDisparo = true;
	        	}        
	        	else if(entorno.sePresiono(entorno.TECLA_IZQUIERDA)) {
		        	this.derechaDisparo = false;
	        	} 	
	        }
	        // no me convence modificar luego
	        
	        //MovimientoDelDisparo
	        if(disparoPep != null) {
	        	if(derechaDisparo) {
	        		disparoPep.disparar(pep.getX(), pep.getY(), 5);
	        		disparoPep.mostrar(entorno);
		        } else {
			        disparoPep.disparar(pep.getX(), pep.getY(), -5);
			        disparoPep.mostrar(entorno);        	
		        } 
	        }
	        
	        ////
	        
	        
	        //COSAS DE GNOMOS
	        //verifica que el gnomo no sea null
	        if (gnomo != null) {
	        	//Colisiones
	        	verificarColisionesGnomo(); //Con islas
	        	verificarColisionesGnomoTortuga(); //Con tortugas
	        	 verificarColisionPepGnomo(); //con pep
	            if (!gnomo.estaApoyado) {
	                gnomo.movVertical(); 
	                System.out.println("El gnomo no está apoyado.");
	            }
	            gnomo.mostrar();
	        }
	        
	        //>>>>>>ARREGLO DE TORTUGAS 
	      //Mostrar las tortugas
			for(int i=0; i<tortuguita.length; i++) {
				this.tortuguita[i].mostrar();				
			}
			for(int i=0; i<tortuguita.length; i++) {
				//this.tortuguita[i].mostrar();	
		        if (!this.tortuguita[i].estaApoyado) {
		        	this.tortuguita[i].movVertical(); 
		            System.out.println("no esta apoyada la tortuga"+this.tortuguita[i]);
		        }
			}
			    
	        
	        //COSAS DE TORTUGAS
	        //Movimiento vertical de tortuga
	        if (!tortuga.estaApoyado) {
	        	tortuga.movVertical(); 
	            System.out.println("no esta apoyada la tortuga");
	        }
	        
	        //Mostrar a la tortuga
	        tortuga.mostrar();
	    }

	    // Metodo para mostrar las islas
	    private void mostrarIslas() {
	        for (Isla isla : islas) {
	            if (isla != null) {
	                isla.mostrar(); 
	            }
	        }
	    }

	    //COLISIONES PEP CON ISLAS 
	    private void verificarColisiones() {
	        pep.estaApoyado = false; // Resetear el estado

	        for (Isla isla : islas) {
	            if (isla != null) {
	                // Mira si pep esta en el rango horizontal de la isla
	                if (pep.bordeDerecho > isla.bordeIzquierdo && pep.bordeIzquierdo < isla.bordeDerecho) {

	                    // Mira si esta cayendo arriba de la isla 
	                    if (pep.bordeAbajo >= isla.bordeArriba && pep.bordeAbajo <= isla.bordeArriba + 5) {
	                        // Ajusta a pep para que quede arriba de la isla
	                        pep.y = isla.bordeArriba - (pep.alto / 2);
	                        pep.actualizarBordes();
	                        pep.estaApoyado = true; 
	                        break;  // sale cuando encuentra una colision
	                    }
	                }
	            }
	        }
	    }
	    public boolean detectarColision(Pep p, Isla isla) {
	        return p.bordeDerecho > isla.bordeIzquierdo && 
	               p.bordeIzquierdo < isla.bordeDerecho && 
	               p.bordeAbajo > isla.bordeArriba && 
	               p.bordeArriba < isla.bordeAbajo;
	    }
	    
	    //COLISIONES GNOMO CON ISLAS 
	    private void verificarColisionesGnomo() {
	        gnomo.estaApoyado = false; // Resetear el estado

	        for (Isla isla : islas) {
	            if (isla != null) {
	                // Mira si el gnomo esta en el rango horizontal de la isla
	                if (gnomo.bordeDerecho > isla.bordeIzquierdo && gnomo.bordeIzquierdo < isla.bordeDerecho) {

	                    // Mira si esta cayendo arriba de la isla 
	                    if (gnomo.bordeAbajo >= isla.bordeArriba && gnomo.bordeAbajo <= isla.bordeArriba + 5) {
	                        // Ajusta al gnomo para que quede arriba de la isla
	                    	gnomo.y = isla.bordeArriba - (gnomo.alto / 2);
	                    	gnomo.actualizarBordes();
	                    	gnomo.estaApoyado = true; 
	                        break;  // sale cuando encuentra una colision
	                    }
	                }
	            }
	        }
	    }
	    public boolean detectarColisionGnomo(Gnomo g, Isla isla) {
	        return g.bordeDerecho > isla.bordeIzquierdo && 
	               g.bordeIzquierdo < isla.bordeDerecho && 
	               g.bordeAbajo > isla.bordeArriba && 
	               g.bordeArriba < isla.bordeAbajo;
	    }
	
	  //>>>>>>ARREGLO DE TORTUGAS 	    //COLISIONES DE TORTUGA CON ISLAS
	    private void verificarColisionesTortu2() {
	    	tortuga.estaApoyado = false; 
		    for(int i = 0 ; i<this.islas.length ; i++) {
			    for(int j = 0 ; j<this.tortuguita.length ; j++) {
			    	 if (islas[i] != null) {
			                if (tortuguita[j].bordeDerecho > islas[i].bordeIzquierdo && tortuguita[j].bordeIzquierdo < islas[i].bordeDerecho) {  
			                	if (tortuguita[j].bordeAbajo >= islas[i].bordeArriba && tortuguita[j].bordeAbajo <= islas[i].bordeArriba + 5) {
			                		System.out.println("tortuguita "+j+" esta en la isla "+i); 
			                		tortuguita[j].y = islas[i].bordeArriba - (tortuguita[j].alto / 2);
			                		tortuguita[j].actualizarBordes();
			                		tortuguita[j].estaApoyado = true; 
			                        break;  // sale cuando encuentra una colision
			                	
			                	}

			                }
		    	 }
		       }
	    	}
	    }
	    
	    
	    //COLISIONES DE TORTUGA CON ISLAS 
	    private void verificarColisionesTortu() {
	        tortuga.estaApoyado = false; // Resetear el estado

	        for (Isla isla : islas) {
	            if (isla != null) {
	                // Mira si la tortuga esta en el rango horizontal de la isla
	                if (tortuga.bordeDerecho > isla.bordeIzquierdo && tortuga.bordeIzquierdo < isla.bordeDerecho) {

	                    // Mira si esta cayendo arriba de la isla 
	                    if (tortuga.bordeAbajo >= isla.bordeArriba && tortuga.bordeAbajo <= isla.bordeArriba + 5) {
	                        // Ajusta la tortuga para que quede arriba de la isla
	                    	tortuga.y = isla.bordeArriba - (tortuga.alto / 2);
	                    	tortuga.actualizarBordes();
	                    	tortuga.estaApoyado = true; 
	                        break;  // sale cuando encuentra una colision
	                    }
	                }
	            }
	        }
	    }
	    public boolean detectarColisionGnomo(Tortuga t, Isla isla) {
	        return t.bordeDerecho > isla.bordeIzquierdo && 
	               t.bordeIzquierdo < isla.bordeDerecho && 
	               t.bordeAbajo > isla.bordeArriba && 
	               t.bordeArriba < isla.bordeAbajo;
	    }
	    
	    //COLISIONES ENTRE PEP Y TORTUGAS
	    private void verificarColisionPepTortu() {
	        if (pep != null && tortuga != null) {
	            if (chocaronPepTortu(pep, tortuga)) {
	                // Si hay colision pep muere y se hace null
	                pep = null; 
	                System.out.println("Pep ha sido eliminado.");
	            }
	        }
	    }

	    // Método para detectar colisión entre Pep y Tortuga
	    public boolean chocaronPepTortu(Pep p, Tortuga t) {
	        return p.bordeDerecho > t.bordeIzquierdo && 
	               p.bordeIzquierdo < t.bordeDerecho && 
	               p.bordeAbajo > t.bordeArriba && 
	               p.bordeArriba < t.bordeAbajo;
	    }
	    
	    //COLISIONES ENTRE GNOMO Y TORTUGA
	 // 
	    private void verificarColisionesGnomoTortuga() {
	        if (gnomo != null && tortuga != null) {
	            if (chocaronGnomoTortu(gnomo, tortuga)) {
	                // Si chocan, el gnomo muere
	                gnomo = null; 
	                System.out.println("La tortuga ha sido eliminada");
	            }
	        }
	    }

	    // Saber si chocaron 
	    public boolean chocaronGnomoTortu(Gnomo g, Tortuga t) {
	        return g.bordeDerecho > t.bordeIzquierdo && 
	               g.bordeIzquierdo < t.bordeDerecho && 
	               g.bordeAbajo > t.bordeArriba && 
	               g.bordeArriba < t.bordeAbajo;
	    }
	    
	    //COLISIONES ENTRE PEP Y GNOMO
	    
	    private void verificarColisionPepGnomo() {
	        if (pep != null && gnomo != null) {
	            // Verifica si hay colisión
	            if (chocaronPepGnomo(pep, gnomo)) {
	            	// Gnomo salvado de hace null REVISAR DIFERENCIA ENTRE SALVADO Y MUERTO!!
	                gnomo = null; 
	                System.out.println("Pep ha salvado al gnomo.");
	            }
	        }
	    }

	    // Saber si chocaron
	    public boolean chocaronPepGnomo(Pep p, Gnomo g) {
	        return p.bordeDerecho > g.bordeIzquierdo && 
	               p.bordeIzquierdo < g.bordeDerecho && 
	               p.bordeAbajo > g.bordeArriba && 
	               p.bordeArriba < g.bordeAbajo;
	    }
	    
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
