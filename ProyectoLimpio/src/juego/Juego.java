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
	private ControladorPantalla controladorPantalla;
	
	private Reloj reloj;
	
	private Isla[] islas;
	
	private CasaGnomos casaGnomos;
	
	//Pep y sus poderes
	private Pep pep;
	private DisparoDePep disparoPep;
	
	private int enemigosEliminados;
	
	//Tortugas
	private Tortuga[] tortugas;
	private int maxTortugas = 4; //PARA QUE APAREZCAN MAS SI SE MUERE ALGUNA
	//private int mismaCantTortugasYDisparos =6;
	private DisparoTortuga[] disparoTortugas;
	
	//Gnomos
	private Gnomo[] gnomos;
	private int maxGnomos= 4;
	private int contadorGnomoSalvados;
	private int contadorGnomosPerdidos;
	

	private ControladorColisiones ControladorColisiones;
	
	private boolean pararJuego = false;

	
	// Variables y métodos propios de cada grupo
	// ...   
	
	Juego(){
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		this.controladorPantalla = new ControladorPantalla();
		this.ControladorColisiones = new ControladorColisiones();
		// Inicializar lo que haga falta para el juego
		// ...	

		
		this.reloj= new Reloj(entorno);
		
		this.pep = new Pep(380,480 , entorno);	
		
		this.casaGnomos = new CasaGnomos (400, 65, entorno);
		
		tortugas = new Tortuga[4]; //DECIDIR CUANTAS TORTUGAS APARECEN ACA<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		this.disparoTortugas = new DisparoTortuga[4];
		
		gnomos = new Gnomo[maxGnomos];
		// INICIALIZAR LAS ISLAS 
		this.islas = new Isla[15]; // CANT TOTAL

		// PRIMER FILA
		this.islas[0] = new Isla(entorno.ancho() / 2, 100, entorno);

		// FILA 2
		int espacioFila2 = 160; //ESPACIO ENTRE ISLAS
		this.islas[1] = new Isla((entorno.ancho() / 2) - espacioFila2 / 2, 200, entorno);
		this.islas[2] = new Isla((entorno.ancho() / 2) + espacioFila2 / 2, 200, entorno);

		// FILA 3
		int espacioFila3 = 160;
		this.islas[3] = new Isla((entorno.ancho() / 2) - espacioFila3, 300, entorno);
		this.islas[4] = new Isla(entorno.ancho() / 2, 300, entorno);
		this.islas[5] = new Isla((entorno.ancho() / 2) + espacioFila3, 300, entorno);

		// FILA 4
		int espacioFila4 = 160;
		this.islas[6] = new Isla((entorno.ancho() / 2) - 1.5 * espacioFila4, 400, entorno);
		this.islas[7] = new Isla((entorno.ancho() / 2) - 0.5 * espacioFila4, 400, entorno);
		this.islas[8] = new Isla((entorno.ancho() / 2) + 0.5 * espacioFila4, 400, entorno);
		this.islas[9] = new Isla((entorno.ancho() / 2) + 1.5 * espacioFila4, 400, entorno);

		// FILA 5
		int espacioFila5 = 160;
		this.islas[10] = new Isla((entorno.ancho() / 2) - 2 * espacioFila5, 500, entorno);
		this.islas[11] = new Isla((entorno.ancho() / 2) - espacioFila5, 500, entorno);
		this.islas[12] = new Isla(entorno.ancho() / 2, 500, entorno);
		this.islas[13] = new Isla((entorno.ancho() / 2) + espacioFila5, 500, entorno);
		this.islas[14] = new Isla((entorno.ancho() / 2) + 2 * espacioFila5, 500, entorno);
		

					
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
			mostrarPantallaInicial();        
	    	chequearColisiones();
            chequearTeclas();
            verificarMovimiento();		        
            mostrarObjetos();
            spawnObjetos();//PARA QUE SIGAN APARECIENDO EN PANTALLA NUEVOS DESPUES DE HACERSE NULL 
	        verificarSiGanaOPierde();
	   

	        }//FIN DEL TICK

	
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void mostrarPantallaInicial() {
		controladorPantalla. mostarPantallaInicial(entorno , contadorGnomoSalvados, contadorGnomosPerdidos,enemigosEliminados);
        reloj.mostrar(entorno); 
	}
	

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	private void chequearColisiones() {
        //COLISIONES DE PEP
        if (pep != null) { 
        	if (pep.estaApoyado) {
        		pep.estaCayendo = false;// Para que solo muestre la imagen cayendo cuando lo haga realmente
        	}
        	//COLISION DE PEP CON ISLAS
        	pep.estaApoyado=false;
	    	for (int i = 0; i < islas.length;i++) {
	    		if (this.islas[i]!=null) {
	    			if (ControladorColisiones.detectarColisionPepIsla(pep, this.islas[i])) {
	    				if (!pep.estaSaltando) {
	    					pep.estaApoyado = true;
	    					pep.setY(this.islas[i].getBordeArriba() - (pep.getAlto() / 2));
	                        //System.out.println("Pep está apoyado.");
                    	}
	    				if (pep.estaSaltando) {
		    				if (ControladorColisiones.colisionCabezaPepConIslaAbajo(pep, islas[i])) {
		    					pep.estaSaltando=false;
		    				}
		    			}
	    			}
	    			
	    		}
	    	}
	        if (pep!=null && ControladorColisiones.seSalioDeLaPantallaPep(pep, entorno)) {
	        	pep=null;
	        	pararJuego=true;																		
	        	}
	        } 
        else {
            System.out.println("Pep ha sido eliminado.");
        }
        //COLISIONES DE GNOMOS 
        
        // COLISION CON ISLAS Y CAMBIO DE DIRECCION 
        for (int i = 0; i < gnomos.length; i++) {
            if (this.gnomos[i] != null) {
                this.gnomos[i].estabaApoyado = this.gnomos[i].estaApoyado;
                this.gnomos[i].estaApoyado = false; // EMPIEZA EN FALSE PORQUE SI NO NO CAEN

                for (int j = 0; j < islas.length; j++) {
                    if (ControladorColisiones.detectarColisionGnomoIsla(this.gnomos[i], this.islas[j])) {
                        this.gnomos[i].setY(this.islas[j].getBordeArriba() - (this.gnomos[i].getAlto() / 2)); //AJUSTAR PARA QUE SE QUEDE ARRIBA DE LA ISLA SI CHOCA
                        this.gnomos[i].estaApoyado = true;

                        
                    }
                }
            }
        }
        
        //CON PEP 
	    for (int i = 0; i < gnomos.length; i++) {
	    	if (pep != null && this.gnomos[i] != null) {
        		//PEP SOLO PUEDE SALVAR GNOMOS A PARTIR DE LA TERCER FILA								
        		if (ControladorColisiones.chocaronPepGnomo(pep, this.gnomos[i]) && this.islas[3].getY() < pep.getY() && !pararJuego) {
        			
        			contadorGnomoSalvados++;
        			this.gnomos[i]= null;
        		}
        	}
	    }
        //CON BORDES
        for (int i = 0; i < gnomos.length; i++) {
        	if (this.gnomos[i] != null) {
        		if(ControladorColisiones.seSalioDeLaPantallaGnomo(this.gnomos[i], this.entorno )&& !pararJuego) {
        			contadorGnomosPerdidos++;
        			this.gnomos[i] = null;
        		}
        	}
        }
       
        //CON ISLAS + REBOTE 
        for(int i = 0; i < tortugas.length; i++) {
            for (int j = 0; j < islas.length; j++) {
                if (this.tortugas[i] != null && ControladorColisiones.chocaronTortuIsla(this.tortugas[i], this.islas[j])) {
                   
                    this.tortugas[i].setY(this.islas[j].getBordeArriba() - (this.tortugas[i].getAlto() / 2)); //LA DEJA SOBRE LA ISLA
                    this.tortugas[i].estaApoyado = true;
                        
                        if (ControladorColisiones.chocaConBordes(tortugas[i], islas[j])) {//SI CHOCA CON UN BORDE REBOTA
                            tortugas[i].rebote();
                            
                            // Cambiar la dirección del dibujito
                            tortugas[i].mirandoDerecha = !tortugas[i].mirandoDerecha; 
                        }
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
        		if(ControladorColisiones.chocaronPepConDisparoTortuga(pep,disparoTortugas[i] )&&!pararJuego) {
        			pep =null;
        		}
        	}
        }
        //COLISION DISPARO CON GNOMOS 
        for(int i=0; i<disparoTortugas.length; i++) {
        	for (int j =0;j < gnomos.length; j ++) {
        		if(disparoTortugas[i] != null && gnomos[j] !=null) {  						
        			if(ControladorColisiones.chocaronGnomoDisparoTortu(gnomos[j], disparoTortugas[i])&&!pararJuego) {
        				contadorGnomosPerdidos++;
        				gnomos[j]=null;
        				 disparoTortugas[i]=null;       				 
        			}
        		}
        	}
        }
        
    
        
        
        //CON PEP 
        for (int i = 0; i < tortugas.length; i ++) {
        	if (this.tortugas[i] != null && pep !=null) {
        		if(ControladorColisiones.chocaronPepTortu(pep, this.tortugas[i])) {
        			pep = null;
        			pararJuego=true; 
        		}
        	}
        }
        //CON GNOMOS 
        for(int i = 0; i < tortugas.length; i ++ ) {
        	for (int j =0;j < gnomos.length; j ++) {
        		if(this.tortugas[i] != null && this.gnomos[j] != null) {
        			if(ControladorColisiones.chocaronGnomoTortu(this.gnomos[j], this.tortugas[i]) && !pararJuego) {
        				contadorGnomosPerdidos++;
	        			this.gnomos[j]=null;
	        		}
        		}
        	}
        }
        
        //CON DISPAROS 
        for (int i = 0; i < tortugas.length; i++) {
            if (this.tortugas[i] != null && disparoPep != null) {
                if (ControladorColisiones.disparoExitoso(disparoPep, this.tortugas[i]) && !pararJuego) {
                    this.tortugas[i] = null; 
                    this.disparoTortugas[i]=null;
                    disparoPep = null; 
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
        
        //CON ISLAS + REBOTE   

        for (int i = 0; i < tortugas.length; i++ ) {
        	if(disparoTortugas[i]!=null){
        	if (ControladorColisiones.seSalioDeLaPantallaDisparoTortu( disparoTortugas[i], entorno)) {
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
        
        //SI DESAPARECE DEL ENTORNO EL DISPARO PEP
        if(disparoPep!=null && ControladorColisiones.seSalioDeLaPantallaDisparo(disparoPep, entorno)) {
        	disparoPep.yaDisparo=false;
        	disparoPep = null;
        }   
    }	
	
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	private void verificarMovimiento() {
		//MOVIMIENTOS DE PEP
        if (pep != null && !pep.estaApoyado) {
            pep.movVertical(); 
        }
        if (pep!=null && pep.estaSaltando) {
        	if (pep.getY()>= pep.getLimite()) {
        		pep.saltar();
        		System.out.println("esta saltando");
        	}
        	if (pep.getY() < pep.getLimite()) {
        		pep.estaSaltando = false;
        	}
        }
        
        //MOVIMIENTO DISPARO PEP
        if(disparoPep!=null) {
        	disparoPep.movimientoDisparo();
        }
        
       
        // MOVIMIENTOS DE GNOMOS
        //VOVIMIENTO VERTICAL
        for (int i = 0; i < gnomos.length; i++) {
            if (this.gnomos[i] != null && this.gnomos[i].estaApoyado) {
                this.gnomos[i].movHorizontal();
            }
        }
        
        //MOVIMIENTO HORIZONTAL
        for (int i = 0; i < gnomos.length; i++) {
            if (this.gnomos[i] != null && !this.gnomos[i].estaApoyado) {
                this.gnomos[i].movVertical(); 
            }
        }
        
        for (int i = 0; i < gnomos.length; i++) {
        	if (this.gnomos[i]!=null &&!this.gnomos[i].estabaApoyado) { //CAMBIA DE DIRECCION SI CAE REVISARR PARA PONERLO EN OTRO LADO
                this.gnomos[i].cambiarDireccion();
            }
        }
        
      //MOVIMIENTOS DE TORTUGAS 
        // si la torttuga no esta apoyada en la isla va a caer 
        
        for(int i = 0; i < tortugas.length; i ++ ) {
        	if (this.tortugas[i] != null) {
        		if (!this.tortugas[i].estaApoyado) {
        			this.tortugas[i].movVertical();
        			//System.out.println("no esta apoyada la tortuga");
        		}
        		//si esta apoyado se mueve horizontalmente 
                if (this.tortugas[i].estaApoyado) {
                    this.tortugas[i].movHorizontalmente();
                    }	
                } 
        	//DISPARO TORTUGA
        	if (disparoTortugas[i] != null &&disparoTortugas[i].mirandoDerecha) {
                disparoTortugas[i].dispararDerecha();
            } else if (disparoTortugas[i] != null) {
                disparoTortugas[i].dispararIzquierda();
                }
        	} 
        	 
        
        
        //MOVIMIENTO DISPARO 
        if(disparoPep!=null) {
        	disparoPep.movimientoDisparo();
        }
        

  } 

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	 private void chequearTeclas()	{
         // CONTROL DE MOVIMIENTO HORIZONTAL 
         if (pep != null && entorno.estaPresionada(entorno.TECLA_DERECHA)) {   //MOVER DERECHA 
             pep.mostrarDerechaPep();
             pep.movHorizontalmenteAPep(2);//sacar la velocidad **********************************************
             pep.estaEnEspera = false;//Esto es para la animacion de espera
         } else if (pep != null && entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {  //MOVER IZQUIERDA
             pep.mostrarIzquierdaPep();
             pep.movHorizontalmenteAPep(-2); 
             pep.estaEnEspera = false;//Esto es para la animacion de espera
         }  
         
         //CONTROL DE SALTO 
	        if (pep!=null && (entorno.sePresiono('w') || entorno.sePresiono(entorno.TECLA_ARRIBA))) {  //tecla arriba
	        	pep.iniciarSalto();	
	            	System.out.println("Se inicio el salto");		          
	        }
	        
	        //DISPARO DE PEP 
	        //SE CREA EL DISPARO SI  NO HAY UNO EN PANTALLA                     			 
	        if(entorno.sePresiono('c') && disparoPep == null && pep != null ) {              //tecla disparo

	    		this.disparoPep = new DisparoDePep(pep.getX(), pep.getY()+10, entorno);
	    		disparoPep.mirandoDerecha=pep.mirandoDerecha;
	        	disparoPep.yaDisparo=true;
	    		System.out.println("DISPARO");	
	        }
	        
	        
	 }
	 
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 
	 
	    //MOSTRAR OBJETOS
	    
	 private void mostrarObjetos() {
		 //PEP
	        if (pep != null) {
	            pep.mostrarAPep();  
	        }
	        
	     // DISPARO DE PEP 
	        if(disparoPep != null && pep!=null) {	        	
       		disparoPep.mostrar(entorno);		          
	        }
	        
	        
		 //ISLAS
		 for (Isla isla : islas) {
	            if (isla != null) {
	                isla.mostrar(); 
	               // isla.dibujar();
	            }
	        }
		 //GNOMOS
		 for (int i = 0; i < maxGnomos; i++) {
    		 if (gnomos[i] != null) {
    			 gnomos[i].mostrar();
    		 }
    	 }
		 //TORTUGAS
		 for (int i =0; i < maxTortugas ; i++) {
			   if (tortugas[i]!=null) {
				   tortugas[i].mostrarTortugas();
				   //DISPARO TORTUGA
				   if (disparoTortugas[i]!=null) {
					   disparoTortugas[i].mostrar(entorno);
				   }
			   }
		   }
		 //CASA GNOMOS
		 casaGnomos.mostrar();

	 }
    
	 
//----------------------------------------------------------------------------------------------------------------------------
	 //CREACION DE LOS OBJETOS, PARA QUE SIGAN APARECIENDO A LO LARGO DEL JUEGO
	 
	 private void spawnObjetos() {
		 Random random = new Random();
		 //GNOMOS
	        for (int i = 0; i < maxGnomos; i++) {
	        	if (gnomos[i] == null) {
	        		// SOLO SPAWNEAN DENTRO DE LOS LIMITES DE LA CASITA DE GNOMOS
		            double minX = casaGnomos.getX(); 
		            double maxX = casaGnomos.getX() + casaGnomos.getAncho();
		            Double posX = random.nextDouble(maxX - minX) + minX; // PONE UNA POSICION ALEATORIA DENTRO DE LOS LIMITES PARA QUE NO SALGAN PEGADOS
		            
		            gnomos[i] = new Gnomo(posX, 65, entorno); 
	        	}
	        }
	    //TORTUGAS
	        int distanciaMinima = 60; // PARA QUE APAREZCAN SEPARADAS
	    	 
	    	for (int i=0; i < tortugas.length; i++) {
	    		int posX = -1; // PARA QUE TENGA UN VALOR INICIAL SI NO DA ERROR XDXD
	    		boolean posicionValida = false;

	            
	            while (!posicionValida) {
	                // DECIDE EL RANGO DE NUM CON BOOLEANO ALEATORIO
	                boolean usarPrimerRango = random.nextBoolean();
	                if (usarPrimerRango) {
	                    posX = random.nextInt(300 - 75 + 1) + 75;
	                } else {
	                    posX = random.nextInt(700 - 500 + 1) + 480;
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
	            
	            if (tortugas[i]==null) {
	            	// CUANDO HAYA POSICION VALIDA SE CREA LA TORTUGA
		            tortugas[i] = new Tortuga(posX, 0, entorno);
	            }
	            //CREAR LOS DISPAROS DE TORTUGAS
	            if (tortugas[i] != null && tortugas[i].estaApoyado) {
	                // Crear el disparo para la tortuga si no existe 
	            	if (tortugas[i] != null && tortugas[i].estaApoyado && disparoTortugas[i] == null) {
	            	    boolean direccionInicial = tortugas[i].mirandoDerecha;
	            	    disparoTortugas[i] = new DisparoTortuga(tortugas[i].getX(), tortugas[i].getY() + 10, entorno, direccionInicial);
	            	}
	            	if (tortugas[i] == null) {
	                    disparoTortugas[i] = null;
	                    }
	            	}
	    	}        	
	    	
	 }	  
	//-----------------------------------------------------------------------------------------------------------------------------
		private void verificarSiGanaOPierde(){ 
				
	        if(contadorGnomoSalvados>=15 ) {     
	        	pararJuego=true ;
	        	controladorPantalla.mostrarPartidaGanada(entorno,contadorGnomoSalvados, contadorGnomosPerdidos, enemigosEliminados);
	        	if (pararJuego) {
	        	    reloj.congelarTiempo();
	        	    reloj.mostrarTiempoCongelado(pep);
	        	}     	
	        }     
	        if(pep==null ) {
	        	pararJuego=true;
	        	controladorPantalla.mostrarPartidaPerdida(entorno , contadorGnomosPerdidos,contadorGnomoSalvados, enemigosEliminados);
	        	if (pararJuego) {
	        	    reloj.congelarTiempo();
	        	    reloj.mostrarTiempoCongelado(pep);
	        	}
	        }  
		}
		


	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
