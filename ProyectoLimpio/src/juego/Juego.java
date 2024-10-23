package juego;


import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Image imagenFondo;
	
	private Reloj reloj;
	
	private Isla[] islas;
	
	private CasaGnomos casaGnomos;
	
	//Pep y sus poderes
	private Pep pep;
	private DisparoDePep disparoPep;
	private boolean derechaDisparo=false;
	
	//Tortugas
	private Tortuga[] tortugas;
	
	//Gnomos
	private Gnomo[] gnomos;
	private int maxGnomos= 4;
	private int minGnomos = 2;
	private int  contadorGnomoSalvados;
	

	private ControladorColisiones ControladorColisiones;
	
	// Variables y métodos propios de cada grupo
	// ...

	//COSAS DE GNOMOS 
	
    // Contar cuantos gnomos hay actualmente en pantalla para despues spawnear mas si es necesario
    private int contarGnomos() {
        int contador = 0;
        for (Gnomo gnomo : gnomos) {
            if (gnomo != null) {
                contador++;
            }
        }
        return contador;
    }

    // Para que siempre haya entre 2 y 4 Gnomos
    private void verificarCantGnomos() {
        int cantidadActual = contarGnomos();
        
       //Para que nunca haya menos de 2 en pantalla 
        if (cantidadActual < minGnomos) {
            for (int i = 0; i < gnomos.length && cantidadActual < minGnomos; i++) {
                if (gnomos[i] == null) {
                    gnomos[i] = new Gnomo(400, 70, entorno);  // Crear un nuevo Gnomo
                    cantidadActual++;
                }
            }
        } 
        
        //Si hay mas de 2, spawnea hasta llegar a 4 gnomos en pantalla 
        if (cantidadActual < maxGnomos) {
            for (int i = 0; i < gnomos.length && cantidadActual < maxGnomos; i++) {
                if (gnomos[i] == null) {
                    gnomos[i] = new Gnomo(400, 70, entorno);  // Crear un nuevo gnomo
                    cantidadActual++;
                }
            }
        }
    }

    // Para mostrar en pantalla todos los gnomos 
    private void mostrarGnomos() {
        for (Gnomo gnomo : gnomos) {
            if (gnomo != null) {
                gnomo.mostrar();  // La muestra si no es null
            }
        }
    }
    
    //COSAS DE TORTUGAS
   private void mostrarTortugas() {
	   for (Tortuga tortuguita: tortugas) {
		   if (tortuguita != null) {
			   tortuguita.mostrar();
		   }
	   }
   }
    
   //COSAS DE PEP Y SUS DISPAROS 
    //poner aca metodos de pep si se necesitan <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
   
   
    
    
	
	Juego(){
		

		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		this.ControladorColisiones = new ControladorColisiones();
		// Inicializar lo que haga falta para el juego
		// ...	
		imagenFondo =  Herramientas.cargarImagen("cielo.png");
		
		this.reloj= new Reloj(entorno);
		
		this.pep = new Pep(370,300 , entorno);
		
		this.casaGnomos = new CasaGnomos (400, 70, entorno);
		
		tortugas = new Tortuga[2]; //DECIDIR CUANTAS TORTUGAS APARECEN ACA<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		inicializarTortugasRandom();//salen en posiciones random de la pantalla menos en el medio
		
		
		gnomos = new Gnomo[maxGnomos];
		inicializarGnomos();
		
		// Inicializar las islas
		this.islas = new Isla[15];
		int k = 0;
		int alturaInicial = 100;  // Altura de la primer fila
		int distanciaVertical = 100; // Entre filas

		for (int fila = 1; fila <= 5; fila++) {
		    // Calcular la posicion horizontal de la primera isla en cada fila
		    int cantidadIslas = fila;
		    int anchoPantalla = entorno.ancho();
		    int espacioEntreIslas = anchoPantalla / (cantidadIslas + 1);  

		    for (int j = 0; j < cantidadIslas; j++) {
		        // Posicionar las islas en la fila centradas
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
	        casaGnomos.mostrar();

	        // COSAS DE PEP
	        
	    
	        //Verifica que pep este vivo antes de hacer todo lo demas 
	        if (pep != null) { 
	        	//Colisiones de pep con cosas
		        verificarColisiones(); //Pep con islas 
		        //verificarColisionPepTortu(); //Pep con Tortugas
	        	//Movimiento vertical de pep
	            if (pep != null && !pep.estaApoyado) {
	                pep.movVertical(); 
	               // System.out.println("Pep no está apoyado.");
	            }
	            if (pep != null) {
	            	pep.mostrar();
	            }
	            // Movimiento horizontal 
	            if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
	                pep.movHorizontal(-2);  // Mover a la derecha
	            }
	            if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
	                pep.movHorizontal(2);  // Mover a la izquierda
	            }
	            
		        //salto de pep 
	            //verificarColisiones();
	            
		        if (pep!=null && entorno.sePresiono('w') && pep.estaApoyado==true ) {
		        	pep.saltar(120);	
		        		pep.estaApoyado=false;
		            	System.out.println("esta saltado");		          
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
	        
	        
	        //MovimientoDelDisparo
	        if(disparoPep != null) {
	        	if(derechaDisparo) {
	        		disparoPep.disparar(pep.getX(), pep.getY(), 3);
	        		disparoPep.mostrar(entorno);
		        } else {
			        disparoPep.disparar(pep.getX(), pep.getY(), -3);
			        disparoPep.mostrar(entorno);        	
		        } 
	        }
	        
	        //Disparo colisiona con tortuga 
	        for (Tortuga tortuguita : tortugas) {
	        	if (tortuguita !=null && disparoPep!=null && disparoColisionaConTortuga(tortuguita, disparoPep) == true) {
	        		//if(disparoPep!=null) {
	        			//disparoColisionaConTortuga(tortuguita, disparoPep);
	        			//no funciona 
	        			System.out.println("colisiono");
	        		//}
	        	}
	        }
	        
	        
//	        //COSAS DE GNOMOS
	        verificarCantGnomos();
	        mostrarGnomos();
	        
	        for (Gnomo gnomo : gnomos) {
	        	if (gnomo != null) {
	        		//COLISIONES ACA
	        		verificarColisionesGnomo(gnomo);//con islas 
	        		verificarColisionPepGnomo(gnomo);// con pep
	        		if (!gnomo.estaApoyado) {
	        			gnomo.movVertical();
	        		}
//	        		if (gnomo.estaApoyado) {
//	        			System.out.println("El gnomo esta sobre una isla");
//	        		}
	        		gnomo.movHorizontal(); // Mueve al gnomo en la direccion actual que tenga
	        	}
	        }
	        
	        //COSAS DE TORTUGAS
	        mostrarTortugas();
	        
	        for (Tortuga tortuguita : tortugas) {
	        	if (tortuguita !=null) {
	        		//COLISIONES 
	        		verificarColisionesTortu(tortuguita); // con islas
	        		verificarColisionPepTortu(tortuguita); // con pep
	        		if (!tortuguita.estaApoyado) {
	        			tortuguita.movVertical();
	        		}
	        		if (tortuguita.estaApoyado) {
	        			//tortuguita.movIzquierda();
	        			tortuguita.movHorizontalmente();
	        			//System.out.println("La tortuga esta sobre una isla.");
	        		}	
	        	}
	        }
	        
	        //delete 
	        for (Tortuga tortuguita: tortugas) {
	        	for (Isla islita : islas) {
	        		if(tortuguita.estaApoyado==true  ) {
	        			if(chocaConBordes(tortuguita, islita)==true) {
		        			//tortuguita.movHorizontalmente();
		        			tortuguita.rebote();
		        			//rebotar();
		        				//System.out.println("chocando");
	        			}
		        			
	        		}
	        	}
	        }
	        
	        //COSAS DE TORTUGAS Y GNOMOS
	        for (Tortuga tortuguita: tortugas) {
	        	for (Gnomo gnomo : gnomos) {
	        		verificarColisionesGnomoTortuga(gnomo,tortuguita);
	        	}
	        }

	        
			     
	        //texto
	        entorno.cambiarFont("Ebrima", 20, null);
	        entorno.escribirTexto("gnomos salvados: "+contadorGnomoSalvados, 25,25);
	        reloj.mostrar(entorno);             
	    }

	

// >>>>>>> COSAS RELACIONADAS A COLISIONES <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 
	
    
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
    
	    //COLISIONES GNOMO CON ISLAS 
	    private void verificarColisionesGnomo(Gnomo gnomo) {
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

	                        // Cambia de dirección si es necesario
	                        if (gnomo.yaCambioDireccion == false) {
	                            gnomo.cambiarDireccion(); 
	                            gnomo.yaCambioDireccion = true; // Pone que ya cambio direccion para que no repita
	                        }

	                        break;  
	                    }
	                }
	            }
	        }
	    }
    
	    
	    //COLISIONES DE TORTUGA CON ISLAS 
	    private void verificarColisionesTortu(Tortuga tortuguita) {
	    	tortuguita.estaApoyado = false; // Resetear el estado

	        for (Isla isla : islas) {
	            if (isla != null) {
	                // Mira si la tortuga esta en el rango horizontal de la isla
	                if (tortuguita.bordeDerecho > isla.bordeIzquierdo && tortuguita.bordeIzquierdo < isla.bordeDerecho) {

	                    // Mira si esta cayendo arriba de la isla 
	                    if (tortuguita.bordeAbajo >= isla.bordeArriba && tortuguita.bordeAbajo <= isla.bordeArriba + 5) {
	                        // Ajusta la tortuga para que quede arriba de la isla
	                    	tortuguita.y = isla.bordeArriba - (tortuguita.alto / 2);
	                    	tortuguita.actualizarBordes();
	                    	tortuguita.estaApoyado = true; 
	                        break;  // sale cuando encuentra una colision
	                    }
	                }
	            }
	        }	                   
	    }
	    

	    
	    

	    
	   //COLISIONES ENTRE DISPARO Y TORTUGA 
	    //PONER ACA EL METODO DE LAS COLISIONES DEL DISPARO Y L><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	    
		public boolean disparoColisionaConTortuga(Tortuga t, DisparoDePep d) {
			boolean colisionHorizontal = d.getX() - d.getAncho()/2 > t.getX()-t.getAncho()/2 && d.getX() + d.getAncho()/2 < t.getX()+t.getAncho()/2;
			boolean colisionVertical = d.getY() - d.getAlto()/2 > t.getY() - t.getAlto()/2 && d.getY() + t.getAlto()/2 < t.getY()+t.getAlto()/2;
			return colisionHorizontal && colisionVertical;
		}

	    
	    
	    
	    
	   //COLISIONES ENTRE PEP Y TORTUGAS
	    private void verificarColisionPepTortu(Tortuga tortuguita) {
	        if (pep != null && tortuguita != null) {
	            if (ControladorColisiones.chocaronPepTortu(pep, tortuguita)) {
	                // Si hay colision pep muere y se hace null
	                pep = null; 
	                System.out.println("Pep ha sido eliminado.");
	            }
	        }
	    }  
	    
	    
	    //COLISIONES ENTRE GNOMO Y TORTUGA
	    private void verificarColisionesGnomoTortuga(Gnomo gnomo,Tortuga tortuguita) {
	        if (gnomo != null && tortuguita != null) {
	            if (ControladorColisiones.chocaronGnomoTortu(gnomo, tortuguita)) {
	                for (int i = 0; i < gnomos.length; i ++) {
	                	if (gnomos [i]== gnomo) {
	                		gnomos[i] = null;
	                		break;
	                	}
	                }
	            }
	        }
	    }
	    
	    
	    //COLISIONES ENTRE PEP Y GNOMO
	    private void verificarColisionPepGnomo(Gnomo gnomo) {
	        if (pep != null && gnomo != null) {
	            if (ControladorColisiones.chocaronPepGnomo(pep, gnomo)) {
	                contadorGnomoSalvados++; // Aumenta el contador de gnomos salvados
	                // Busca el indice del gnomo que fue salvado y lo establece en null
	                for (int i = 0; i < gnomos.length; i++) {
	                    if (gnomos[i] == gnomo) {
	                        gnomos[i] = null; // Elimina el gnomo de la pantalla
	                        break; // Salir del bucle
	                    }
	                }
	                System.out.println("Pep ha salvado al gnomo.");
	            }
	        }
	    }
	    
	    public boolean chocaConBordes(Tortuga t, Isla i) {
	    	
	    	boolean colisionHorizontal = t.getX() + t.getAncho()/2 > i.getX() -( i.getAncho()/2 )
                    && t.getX() - t.getAncho()/2 < i.getX() + i.getAncho()/2;
	    	    	
	    	return  colisionHorizontal; 
	    }
	    
	    
// >>>>>>> FIN DE COSAS RELACIONADAS A COLISIONES <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	    
	    
	    //SPAWN DE OBJETOS 
	    
	    // Metodo para mostrar las islas 
	    private void mostrarIslas() {
	        for (Isla isla : islas) {
	            if (isla != null) {
	                isla.mostrar(); 
	            }
	        }
	    }
	    
	    //Crea los primeros Gnomos 
	    private void inicializarGnomos() {
	    	Random random = new Random();
	        for (int i = 0; i < minGnomos; i++) {
	        	// Solo spawnean dentro de los limites de la casita de gnomos
	            double minX = casaGnomos.getX(); 
	            double maxX = casaGnomos.getX() + casaGnomos.ancho;
	            
	            Double posX = random.nextDouble(maxX - minX) + minX; // pone una posicion aleatoria para que no salgan pegados
	            
	            // Crea un nuevo Gnomo en la posición generada
	            gnomos[i] = new Gnomo(posX, 70, entorno); 
	        }
	    }
	    
	    //Crear tortugas en lugares random
	    private void inicializarTortugasRandom() {
	    	Random random = new Random();
	    	int distanciaMinima = 50; // Para que aparezcan separadas
	    	 
	    	for (int i=0; i < tortugas.length; i++) {
	    		int posX = -1; //Para que tenga un valor inicial porque si no tira error xd
	    		boolean posicionValida = false;

	            // El ciclo va a correr mientras la posicion del random no sea valida
	            while (!posicionValida) {
	                // Decide que rango de numeros va a usar con un booleano aleatorio
	                boolean usarPrimerRango = random.nextBoolean();
	                if (usarPrimerRango) {
	                    posX = random.nextInt(350 - 75 + 1) + 75;
	                } else {
	                    posX = random.nextInt(700 - 480 + 1) + 480;
	                }

	                // Verifica que no esten muy cerca entre ellas
	                posicionValida = true; // inicia en valido
	                for (int j = 0; j < i; j++) {
	                    if (Math.abs(posX - tortugas[j].getX()) < distanciaMinima) {
	                        posicionValida = false; // Si estan muy cerca entonces no es valido
	                        break;
	                    }
	                }
	            }
	            // Cuando haya una posicion valida se va a crear la tortuga
	            tortugas[i] = new Tortuga(posX, 0, entorno);
	    	}    	
	    }


	    
	    
	    
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
