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
	private int  contadorGnomoSalvados;
	

	private ControladorColisiones ControladorColisiones;
	
	// Variables y métodos propios de cada grupo
	// ...   
	
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
		inicializarTortugasRandom();
		
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
	        entorno.dibujarImagen(imagenFondo, entorno.ancho() / 2, entorno.alto() / 2, 0, 0.55);
	        
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
		                        System.out.println("Pep está apoyado.");
	                    	}
		    			}
		    		}
		    	}
	        	

	            if (pep != null && !pep.estaApoyado) {
	                pep.movVertical(); 
	               // System.out.println("Pep no está apoyado.");
	            }
	            if (pep != null) {
	            	pep.mostrar();
	            }
	            // MOVIMIENTO HORIZONTAL 
	            if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
	                pep.movHorizontal(-2);  // MOVER A LA DERECHA
	            }
	            if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
	                pep.movHorizontal(2);  // MOVER A LA IZQUIERDA
	            }
	            
		        //SALTO DE PEP 
		        if (pep!=null && entorno.sePresiono('w')) {
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
	        //SE CREA EL DISPARO SI HAY UNO EN PANTALLA
	        if(entorno.sePresiono('c') && disparoPep == null && pep != null) {
	    		this.disparoPep = new DisparoDePep(pep.getX(), pep.getY()+10, entorno);
	    		System.out.println("DISPARO");	
	        }
	        
	        
	        //SI DESAPARECE DEL ENTORNO 
	        if(disparoPep!=null && (disparoPep.getX()<0 ||disparoPep.getX()>entorno.ancho())) {
	        	disparoPep = null;
	        }
	        //VERIFICO LAS DIRECCIONES 
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
	        for (int i = 0; i < gnomos.length; i++) {
	        	if (pep != null && this.gnomos[i] != null) {
	        		if (ControladorColisiones.chocaronPepGnomo(pep, this.gnomos[i])) {
	        			contadorGnomoSalvados++;
	        			this.gnomos[i]= null;
	        		}
	        	}
	        }
	        
	        //CON BORDES
	        for (int i = 0; i < gnomos.length; i++) {
	        	if (this.gnomos[i] != null) {
	        		if(ControladorColisiones.seSalioDeLaPantallaGnomo(this.gnomos[i], this.entorno)) {
	        			this.gnomos[i] = null;
	        		}
	        	}
	        }
	        
	        

	        
//>>>>>>>>>>	        COSAS DE TORTUGAS           <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	       
	        mostrarTortugas();
	        
	        for(int i = 0; i < tortugas.length; i ++ ) {
	        	if (this.tortugas[i] != null) {
	        		if (!this.tortugas[i].estaApoyado) {
	        			this.tortugas[i].movVertical();
	        		}
	        		if (this.tortugas[i].estaApoyado) {
	        			this.tortugas[i].movHorizontalmente();
	        		}
	        	}
	        }
	        
	        //COLISIONES DE LAS TORTUGAS 
	        
	        //CON ISLAS 
	        for(int i = 0; i < tortugas.length; i ++ ) {
	        	for (int j =0;j < islas.length; j ++) {
	        		if (this.tortugas[i] != null) {
	        			if(ControladorColisiones.chocaronTortuIsla(this.tortugas[i], this.islas[j])) {
	        				this.tortugas[i].setY(this.islas[j].getBordeArriba() - (this.tortugas[i].getAlto() / 2));//ajustar a la tortuga arriba de la isla
		        			this.tortugas[i].estaApoyado = true;
		        		}
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
	               
	        //MOVIMIENTOS DE TORTUGAS EN LAS ISLAS 
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
	           
//>>>>>>>>>>	        TEXTO Y RELOJ          <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<	        
	        entorno.cambiarFont("Ebrima", 20, null);
	        entorno.escribirTexto("gnomos salvados: "+contadorGnomoSalvados, 25,25);
	        reloj.mostrar(entorno);     
	        
	    }//FIN DEL TICK
	

// >>>>>>> COSAS RELACIONADAS A COLISIONES <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	    
	    public boolean chocaConBordes(Tortuga t, Isla i) {
	    	
	    	boolean colisionHorizontal = t.getX() + t.getAncho()/2 > i.getX() -( i.getAncho()/2 )
                    && t.getX() - t.getAncho()/2 < i.getX() + i.getAncho()/2;
	    	    	
	    	return  colisionHorizontal; 
	    }
	    
	    
// >>>>>>> FIN DE COSAS RELACIONADAS A COLISIONES <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	    
	    
	    //SPAWN DE OBJETOS 
	    
	    // METODO PARA MOSTRAR ISLAS XD
	    private void mostrarIslas() {
	        for (Isla isla : islas) {
	            if (isla != null) {
	                isla.mostrar(); 
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
				   tortuguita.mostrar();
			   }
		   }
	   }
	   
	    
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

