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
	Image imagenFondoDia;
	Image imagenFondoAtardecer;
	Image imagenFondoNoche;
	
	
	private Reloj reloj;
	
	private Isla[] islas;
	
	private CasaGnomos casaGnomos;
	
	//Pep y sus poderes
	private Pep pep;
	//private boolean miraAlaIzquierda=true;
	private DisparoDePep disparoPep;
	private boolean derechaDisparo=false;
	private int enemigosEliminados;
	
	//Tortugas
	private Tortuga[] tortugas;
	private int mismaCantTortugasYDisparos =6;
	private DisparoTortuga[] disparoTortugas;
	
	//Gnomos
	private Gnomo[] gnomos;
	private int maxGnomos= 4;
	private int contadorGnomoSalvados;
	private int contadorGnomosPerdidos;
	

	private ControladorColisiones ControladorColisiones;

	
	// Variables y métodos propios de cada grupo
	// ...   
	
	Juego(){
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		this.ControladorColisiones = new ControladorColisiones();
		// Inicializar lo que haga falta para el juego
		// ...	
		imagenFondoDia =  Herramientas.cargarImagen("imagenDia.jpg");
		imagenFondoAtardecer=  Herramientas.cargarImagen("imagenAtardecer.jpg");
		imagenFondoNoche=  Herramientas.cargarImagen("imagenNoche.jpg");
		
		this.reloj= new Reloj(entorno);
		
		this.pep = new Pep(380,480 , entorno);
		
		this.casaGnomos = new CasaGnomos (400, 70, entorno);
		
		tortugas = new Tortuga[mismaCantTortugasYDisparos]; //DECIDIR CUANTAS TORTUGAS APARECEN ACA<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		inicializarTortugasRandom();
		
		this.disparoTortugas = new DisparoTortuga[mismaCantTortugasYDisparos];
		
		gnomos = new Gnomo[maxGnomos];
		spawnGnomos();
		
		// INICIALIZAR LAS ISLAS 
		this.islas = new Isla[15];
		int k = 0;
		int alturaInicial = 100; 
		int distanciaVertical = 100; // ENTRE FILAS

		for (int fila = 1; fila <= 5; fila++) {
		    // PONERLAS ORDENADITAS
		    int cantidadIslas = fila;
		    int anchoPantalla = entorno.ancho();
		    int espacioEntreIslas = anchoPantalla / (cantidadIslas + 1);  

		    for (int j = 0; j < cantidadIslas; j++) {
		        // POSICIONARLAS EN FILAS CNENTRADAS
		        int posicionX = espacioEntreIslas * (j + 1);
		        int posicionY = alturaInicial + distanciaVertical * (fila - 1);
		        
		        
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
	        // CAMBIAR FONDO
	        entorno.dibujarImagen(imagenFondoDia, entorno.ancho() / 2, entorno.alto() / 2, 0, 0.555);
	        
	        mostrarIslas();
	        casaGnomos.mostrar();

	        
//>>>>>>>>>>	        COSAS DE PEP           <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<        
	        
	        if (pep != null) { 
	        	//COLISION DE PEP CON ISLAS
	        	pep.estaApoyado=false;
		    	for (int i = 0; i < islas.length;i++) {
		    		if (this.islas[i]!=null) {
		    			if (ControladorColisiones.detectarColisionPepIsla(pep, this.islas[i])) {
		    				if (!pep.estaSaltando) {
		    					pep.estaApoyado = true;
		    					pep.setY(this.islas[i].getBordeArriba() - (pep.alto / 2));
		                        //System.out.println("Pep está apoyado.");
	                    	}
		    			}
		    		}
		    	}
	        	

	            if (pep != null && !pep.estaApoyado) {
	                pep.movVertical(); 
	            }
	            if (pep != null) {
	                pep.mostrarAPep();  
	            }

	            // Movimiento horizontal
	            if (pep != null && entorno.estaPresionada(entorno.TECLA_DERECHA)) {
	                pep.mostrarDerechaPep();
	                pep.movHorizontalmenteAPep(2);  // Mover a la derecha
	            } else if (pep != null && entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
	                pep.mostrarIzquierdaPep();
	                pep.movHorizontalmenteAPep(-2);  // Mover a la izquierda
	            }           
	            
		        //SALTO DE PEP 
		        if (pep!=null && (entorno.sePresiono('w') || entorno.sePresiono(entorno.TECLA_ARRIBA))) {
		        	pep.iniciarSalto();	
		            	System.out.println("Se inicio el salto");		          
		        }
		        
		        if (pep!=null && pep.estaSaltando) {
		        	if (pep.getY()>= pep.limite) {
		        		pep.saltar();
		        		System.out.println("esta saltando");
		        	}
		        	if (pep.getY() < pep.limite) {
		        		pep.estaSaltando = false;
		        	}
		        }
	           
		        if (pep!=null && ControladorColisiones.seSalioDeLaPantallaPep(pep, entorno)) {
		        	pep=null;
		        }
	        } else {
	            System.out.println("Pep ha sido eliminado.");
	        }
	        
  
	   
	        //DISPARO DE PEP 
	        //SE CREA EL DISPARO SI  NO HAY UNO EN PANTALLA                     			  ****fix
	        if(entorno.sePresiono('c') && disparoPep == null && pep != null) {
	    		this.disparoPep = new DisparoDePep(pep.getX(), pep.getY()+10, entorno);
	    		System.out.println("DISPARO");	
	        }	        
	        
	        //VERIFICO LAS DIRECCIONES 
	        if(disparoPep == null) {
	        	if(entorno.sePresiono(entorno.TECLA_DERECHA)) {
	        		this.derechaDisparo = true;
	        	}        
	        	 if(entorno.sePresiono(entorno.TECLA_IZQUIERDA)) {
		        	this.derechaDisparo = false;
	        	} 	
	        }
	        
	        //MovimientoDelDisparo
	        if(disparoPep != null && pep!=null) {
	        	if(derechaDisparo ) {
	        		disparoPep.disparar(pep.getX(), pep.getY(), 3);
	        		disparoPep.mostrar(entorno);
		        } else {
			        disparoPep.disparar(pep.getX(), pep.getY(), -3);
			        disparoPep.mostrar(entorno);        	
		        } 
	        }
	        
	        
	               
	        //SI DESAPARECE DEL ENTORNO 
	        if(disparoPep!=null && (disparoPep.getX()<0 ||disparoPep.getX()>entorno.ancho())) {
	        	disparoPep = null;
	        }
	        

	        
//>>>>>>>>>>	        COSAS DE GNOMOS           <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	        
	        mostrarGnomos();
	        spawnGnomos();
	        
	        for (int i = 0; i < gnomos.length ; i++) {
	        	if(this.gnomos[i] != null) {
	        		this.gnomos[i].estaApoyado = false; //DE ENTRADA NO ESTA APOYADO PORQUE SI NO NO CAE XDX
	        			this.gnomos[i].movHorizontal();
	        		if (!this.gnomos[i].estaApoyado) {
	        			this.gnomos[i].movVertical();
	        		}
	        	}
	        }
	               
	        //COLISIONES
	        
	        //CON ISLAS
	        for (int i = 0; i < gnomos.length; i++) {
	        	for (int j = 0; j< islas.length; j++) {
	        		if (this.gnomos[i] != null) {
		        		if (ControladorColisiones.detectarColisionGnomoIsla(this.gnomos[i], this.islas[j])) {
		        			this.gnomos[i].setY(this.islas[j].getBordeArriba() - (this.gnomos[i].alto / 2));//AJUSTAR ARRIBA DE LA ISLA 
		        			this.gnomos[i].estaApoyado = true;
		        		}
		        	}
	        	}
	        }
	        
	        //CON PEP 
	        for(int j=0; j<islas.length;j++) {
		        for (int i = 0; i < gnomos.length; i++) {
		        	if (pep != null && this.gnomos[i] != null) {
		        		//Pep tiene que colisionar y tiene que estar debajo de la tercera fila de islas
		        		if (ControladorColisiones.chocaronPepGnomo(pep, this.gnomos[i]) && this.islas[3].getY() < pep.getY()) {
		        			
		        			contadorGnomoSalvados++;
		        			this.gnomos[i]= null;
		        		}
		        	}
		        }
	        }
	        //CON BORDES
	        for (int i = 0; i < gnomos.length; i++) {
	        	if (this.gnomos[i] != null) {
	        		if(ControladorColisiones.seSalioDeLaPantallaGnomo(this.gnomos[i], this.entorno)) {
	        			contadorGnomosPerdidos++;
	        			this.gnomos[i] = null;
	        		}
	        	}
	        }
	        

	        
//>>>>>>>>>>	        COSAS DE TORTUGAS           <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	       
	        mostrarTortugas();        
	        
	        //CON ISLAS 
        
	        
	        //COLISION TORTUGAS CON ISLA, MOVIMIENTOS DE TORTUGAS EN LAS ISLAS  Y  REBOTE   

	        
	        for(int i = 0; i < tortugas.length; i++) {
	            for (int j = 0; j < islas.length; j++) {
	                if (this.tortugas[i] != null && ControladorColisiones.chocaronTortuIsla(this.tortugas[i], this.islas[j])) {
	                   
	                	// Ajustar la posición en la isla
	                    this.tortugas[i].setY(this.islas[j].getBordeArriba() - (this.tortugas[i].getAlto() / 2));
	                    this.tortugas[i].estaApoyado = true;
	                    
	                    //si esta apoyado se mueve horizontalmente 
	                    if (tortugas[i].estaApoyado) {
	                        tortugas[i].movHorizontalmente();
	                        
	                        //si colisiona con el borde va a rebotar 
	                        if (ControladorColisiones.chocaConBordes(tortugas[i], islas[j])) {
	                            tortugas[i].rebote();
	                            
	                            // Cambiar la dirección del dibujito
	                            tortugas[i].mirandoDerecha = !tortugas[i].mirandoDerecha; 
	                        }
	                    }
	                }
	            }
	            if (this.tortugas[i] != null ) {
	            	tortugas[i].mostrarTortugas();
	            }
	        }

	        
	        //DISPARO DE TORTUGA    
	        
	        for (int i = 0; i < tortugas.length; i++) {
	            if (tortugas[i] != null && tortugas[i].estaApoyado) {
	                // Crear el disparo para la tortuga si no existe 
	                if (disparoTortugas[i] == null) {
	                	// asigno el booleano de la tortuga a la del disparo
	                    boolean direccionInicial = tortugas[i].mirandoDerecha;
	                    disparoTortugas[i] = new DisparoTortuga(tortugas[i].getX(), tortugas[i].getY()+10, entorno, direccionInicial);
	                }

	                // Muestra y mueve el disparo segun la dirección 
	                disparoTortugas[i].mostrar(entorno);
	                if (disparoTortugas[i].mirandoDerecha) {
	                    disparoTortugas[i].dispararDerecha();
	                } else {
	                    disparoTortugas[i].dispararIzquierda();
	                }

	                // Elimina el disparo si sale del entorno
	                if (disparoTortugas[i].getX() > (entorno.ancho() + 20) || disparoTortugas[i].getX() < -20) {
	                    disparoTortugas[i] = null;
	                }
	            }
	        }
        
	        
	        //COLISION DE DISPARO DE PEP CON DISPARO TORTUGA
	        for (int i = 0; i < disparoTortugas.length; i++) {
	            if (disparoTortugas[i] != null && disparoPep != null) {
	                if (ControladorColisiones.chocaronPepDisparoConTortugaDisparo(disparoPep, disparoTortugas[i])) {
	                    disparoTortugas[i] = null;
	                    disparoPep = null;
	                    System.out.println("disparo anulado");
	                }
	            }
	        }


	        
	        //COLISION DE DISPAROS DE TORTUGAS CON PEP
	        for(int i=0; i<disparoTortugas.length; i++) {
	        	if(disparoTortugas[i] != null && pep !=null) {
	        		if(ControladorColisiones.chocaronPepConDisparoTortuga(pep,disparoTortugas[i] )) {
	        			pep =null;
	        		}
	        	}
	        }
	        
	        
	        // si la torttuga no esta apoyada en la isla va a caer 
	        
	        for(int i = 0; i < tortugas.length; i ++ ) {
	        	if (this.tortugas[i] != null) {
	        		if (!this.tortugas[i].estaApoyado) {
	        			this.tortugas[i].movVertical();
	        			//System.out.println("no esta apoyada la tortuga");
	        		}
	        	}
	        }
	        
	        
	        //CON PEP 
	        for (int i = 0; i < tortugas.length; i ++) {
	        	if (this.tortugas[i] != null && pep !=null) {
	        		if(ControladorColisiones.chocaronPepTortu(pep, this.tortugas[i])) {
	        			pep = null;
	        		}
	        	}
	        }
	        
	        
	        //CON GNOMOS 
	        for(int i = 0; i < tortugas.length; i ++ ) {
	        	for (int j =0;j < gnomos.length; j ++) {
	        		if(this.tortugas[i] != null && this.gnomos[j] != null) {
	        			if(ControladorColisiones.chocaronGnomoTortu(this.gnomos[j], this.tortugas[i])) {
	        				contadorGnomosPerdidos++;
		        			this.gnomos[j]=null;
		        		}
	        		}
	        	}
	        }
	        
	        //CON DISPAROS 
	        for(int i = 0; i < tortugas.length; i ++ ) {
	        	if (this.tortugas[i] != null&& disparoPep != null) {
	        		if(ControladorColisiones.disparoExitoso(disparoPep, this.tortugas[i])) {
		        		this.tortugas[i]=null;
		        		disparoPep =null;
		        		enemigosEliminados++;
		        	}
	        	}	
	        }
	        
	        //CON BORDES
	        for (int i = 0; i < tortugas.length; i++) {
	        	if (this.tortugas[i] != null) {
	        		if(ControladorColisiones.seSalioDeLaPantallaTortu(this.tortugas[i], this.entorno)) {
	        			this.tortugas[i] = null;
	        		}
	        	}
	        }
	                       


	           
//>>>>>>>>>>	        TEXTO Y RELOJ          <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<	        
	        entorno.cambiarFont("Ebrima", 18, null);
	        entorno.escribirTexto("gnomos salvados: "+contadorGnomoSalvados+" gnomos perdidos: "+contadorGnomosPerdidos+" Enemigos eliminados: "+enemigosEliminados, 25,25);
	        reloj.mostrar(entorno); 
	        
	        
	        
	        
//>>>>>>>>>>>>>>>>   SI SE PIERDE O SI SE GANA EL JUEGO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	       
	        //*****hacer mas lindo 
	        if(contadorGnomoSalvados>=15) { 
	        	entorno.dibujarImagen(imagenFondoAtardecer, entorno.ancho() / 2, entorno.alto() / 2, 0, 0.555);
	        	entorno.cambiarFont("Ebrima", 50, null);
	        	entorno.escribirTexto("Ganaste el juego ", (entorno.ancho() /3-50),entorno.alto() / 2);
	        }
	       
	        
	        if(pep==null) {
	        	entorno.dibujarImagen(imagenFondoNoche, (entorno.ancho() / 2)-80, entorno.alto() / 2, 0, 0.555);
	        	entorno.cambiarFont("Ebrima", 50, null);
	        	entorno.escribirTexto("Perdiste el juego ", (entorno.ancho() /3-50),entorno.alto() / 2);
	        }
	        
	        
	    }//FIN DEL TICK
	

// >>>>>>> COSAS RELACIONADAS A COLISIONES <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	    

	    
// >>>>>>> FIN DE COSAS RELACIONADAS A COLISIONES <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	    
	    
	    //SPAWN DE OBJETOS 
	    
	    // METODO PARA MOSTRAR ISLAS XD
	    private void mostrarIslas() {
	        for (Isla isla : islas) {
	            if (isla != null) {
	                isla.mostrar(); 
	               // isla.dibujar();
	            }
	        }
	    }
	    
	    //GNOMOS
	    
	    // PARA MOSTRAR EN PANTALLA TODOS LOS GNOMOS
	    
	    
	    private void mostrarGnomos() {
	    	 for (int i = 0; i < maxGnomos; i++) {
	    		 if (gnomos[i] != null) {
	    			 gnomos[i].mostrar();
	    		 }
	    	 }
	    }
	    
	    //CREA LOS GNOMOS 
	    private void spawnGnomos() {
	    	Random random = new Random();
	        for (int i = 0; i < maxGnomos; i++) {
	        	if (gnomos[i] == null) {
	        		// SOLO SPAWNEAN DENTRO DE LOS LIMITES DE LA CASITA DE GNOMOS
		            double minX = casaGnomos.getX(); 
		            double maxX = casaGnomos.getX() + casaGnomos.ancho;
		            Double posX = random.nextDouble(maxX - minX) + minX; // PONE UNA POSICION ALEATORIA DENTRO DE LOS LIMITES PARA QUE NO SALGAN PEGADOS
		            
		            gnomos[i] = new Gnomo(posX, 70, entorno); 
	        	}
	        }
	    }
	    
	    //TORTUGAS
	    
	    
		   private void mostrarTortugas() {
		   for (Tortuga tortuguita: tortugas) {
			   if (tortuguita != null) {
				   tortuguita.mostrarTortugas();
			   }
		   }
	   }  
	    
	    
	    
//	   
	    
	    //CREAR TORTUGAS EN LUGARES RANDOM
	    private void inicializarTortugasRandom() {
	    	Random random = new Random();
	    	int distanciaMinima = 50; // PARA QUE APAREZCAN SEPARADAS
	    	 
	    	for (int i=0; i < tortugas.length; i++) {
	    		int posX = -1; // PARA QUE TENGA UN VALOR INICIAL SI NO DA ERROR XDXD
	    		boolean posicionValida = false;

	            
	            while (!posicionValida) {
	                // DECIDE EL RANGO DE NUM CON BOOLEANO ALEATORIO
	                boolean usarPrimerRango = random.nextBoolean();
	                if (usarPrimerRango) {
	                    posX = random.nextInt(350 - 75 + 1) + 75;
	                } else {
	                    posX = random.nextInt(700 - 480 + 1) + 480;
	                }

	                // VERIFICA QUE NO ESTEN MUY PEGADAS
	                posicionValida = true; // INICIA EN VALIDO 
	                for (int j = 0; j < i; j++) {
	                    if (Math.abs(posX - tortugas[j].getX()) < distanciaMinima) {
	                        posicionValida = false; //
	                        break;
	                    }
	                }
	            }
	            // CUANDO HAYA POSICION VALIDA SE CREA LA TORTUGA
	            tortugas[i] = new Tortuga(posX, 0, entorno);
	    	}    	
	    }
	    
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}

