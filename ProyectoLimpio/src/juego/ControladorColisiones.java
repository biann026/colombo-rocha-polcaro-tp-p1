package juego;

import entorno.Entorno;

public class ControladorColisiones {
	
	//COLISION PEP ISLA
	public boolean detectarColisionPepIsla(Pep p, Isla isla) {
	    return p.getBordeDerecho() > isla.getBordeIzquierdo() && 
	           p.getBordeIzquierdo() < isla.getBordeDerecho() && 
	           p.getBordeAbajo() >= isla.getBordeArriba() && 
	           p.getBordeArriba() < isla.getBordeAbajo();
	}

    
    //COLISION TORTUGA ISLA  
    public boolean chocaronTortuIsla(Tortuga t, Isla isla) { //le quito bordes a ambos 
    	return t.getBordeDerecho()-8 > isla.getBordeIzquierdo()+7 &&  t.getBordeIzquierdo()+8 < isla.getBordeDerecho() -7 &&    			
 	           t.getBordeAbajo() >= isla.getBordeArriba() &&  t.getBordeArriba() < isla.getBordeAbajo();
    }
      
   //COLISION TORTUGA CON EL BORDE DE LA ISLA  
    public boolean chocaConBordes(Tortuga t, Isla i) {
    	return   t.getBordeIzquierdo() <= i.getBordeIzquierdo()- 18 || //si aumento un pixel no funciona       
    			 t.getBordeDerecho() >= i.getBordeDerecho() + 18;  //   fix******         
    }
    
    // COLISION PEP CON DISPARO TORTUGA 
    public boolean chocaronPepConDisparoTortuga(Pep p, DisparoTortuga disp) {
        return p.getBordeDerecho() > disp.getBordeIzquierdo() && 
               p.getBordeIzquierdo() < disp.getBordeDerecho() && 
               p.getBordeAbajo() > disp.getBordeArriba() && 
               p.getBordeArriba() < disp.getBordeAbajo();
    }
    
    
    // COLISION DISPARO DE PEP CON DISPARO TORTUGA  
    public boolean chocaronPepDisparoConTortugaDisparo(DisparoDePep p, DisparoTortuga disp) {
        return p.getBordeDerecho() > disp.getBordeIzquierdo() && 
               p.getBordeIzquierdo() < disp.getBordeDerecho() && 
               p.getBordeAbajo() > disp.getBordeArriba() && 
               p.getBordeArriba() < disp.getBordeAbajo();
    }
    
    //COLISION DISPARO CON ISLA 
    public boolean chocaronGnomoDisparoTortu(Gnomo g, DisparoTortuga disp) {
    	return g.getBordeDerecho() > disp.getBordeIzquierdo() && 
               g.getBordeIzquierdo() < disp.getBordeDerecho() && 
               g.getBordeAbajo() > disp.getBordeArriba() && 
               g.getBordeArriba() < disp.getBordeAbajo();
    }
    
    
     
    
    //COLISION GNOMO ISLA
    public boolean detectarColisionGnomoIsla(Gnomo g, Isla isla) {
    	return g.getBordeDerecho() > isla.getBordeIzquierdo() && 
  	           g.getBordeIzquierdo() < isla.getBordeDerecho() && 
  	           g.getBordeAbajo() >= isla.getBordeArriba() && 
  	           g.getBordeArriba() < isla.getBordeAbajo();             
    }
    
   // COLISION PEP TORTUGA
    public boolean chocaronPepTortu(Pep p, Tortuga t) {
        return p.getBordeDerecho() > t.getBordeIzquierdo() && 
               p.getBordeIzquierdo() < t.getBordeDerecho() && 
               p.getBordeAbajo() > t.getBordeArriba() && 
               p.getBordeArriba() < t.getBordeAbajo();
    }
    
    //COLISION GNOMO TORTU
    public boolean chocaronGnomoTortu(Gnomo g, Tortuga t) {
    	return g.getBordeDerecho() > t.getBordeIzquierdo() && 
               g.getBordeIzquierdo() < t.getBordeDerecho() && 
               g.getBordeAbajo() > t.getBordeArriba() && 
               g.getBordeArriba() < t.getBordeAbajo();
    }
    
    //COLISION PEP GNOMO
    public boolean chocaronPepGnomo(Pep p, Gnomo g) {
    	return p.getBordeDerecho() > g.getBordeIzquierdo() && 
               p.getBordeIzquierdo() < g.getBordeDerecho() && 
               p.getBordeAbajo() > g.getBordeArriba() && 
               p.getBordeArriba() < g.getBordeAbajo();
    }
    
    //COLISION TORTUGA DISPARO PEP
    public boolean disparoExitoso(DisparoDePep d, Tortuga t) {
    	return d.getBordeDerecho() > t.getBordeIzquierdo() && 
               d.getBordeIzquierdo() < t.getBordeDerecho() && 
               d.getBordeAbajo() > t.getBordeArriba() && 
               d.getBordeArriba() < t.getBordeAbajo();
    }    
    
    //GNOMO SE SALE DE LOS BORDES
    public boolean seSalioDeLaPantallaGnomo(Gnomo g, Entorno e) {
        return g.getBordeDerecho() < 0 || 
               g.getBordeIzquierdo() > e.ancho() || 
               g.getBordeAbajo() < 0 || 
               g.getBordeArriba() > e.alto(); 
    }
    
    //TORTUGA SE SALE DE LOS BORDES
    public boolean seSalioDeLaPantallaTortu(Tortuga t, Entorno e) {
    	return t.getBordeDerecho() < 0 || 
               t.getBordeIzquierdo() > e.ancho() || 
               t.getBordeAbajo() < 0 || 
               t.getBordeArriba() > e.alto(); 
    }
    
    //PEP SE SALE DE LOS BORDES
    public boolean seSalioDeLaPantallaPep(Pep p, Entorno e) {
    	return p.getBordeDerecho() < 0 || 
               p.getBordeIzquierdo() > e.ancho() || 
               p.getBordeAbajo() < 0 || 
               p.getBordeArriba() > e.alto();
    }
    
    //DISPARO DE PEP SE SALE DE PANTALLA 
    public boolean seSalioDeLaPantallaDisparo(DisparoDePep d, Entorno e) {
    	return d.getBordeDerecho() < 0 || 
               d.getBordeIzquierdo() > e.ancho() || 
               d.getBordeAbajo() < 0 || 
               d.getBordeArriba() > e.alto(); 
    }
    
    //ISLA SE SALE DE PANTALLA 
    public boolean seSalioDeLaPantallaIsla(Isla i, Entorno e) {
    	return i.getBordeDerecho() < 0 || 
               i.getBordeIzquierdo() > e.ancho() || 
               i.getBordeAbajo() < 0 || 
               i.getBordeArriba() > e.alto(); 
    }
    
    
    public boolean seSalioDeLaPantallaDisparoTortu(DisparoTortuga d, Entorno e) {
    	return d.getBordeDerecho() < 0 || 
               d.getBordeIzquierdo() > e.ancho() || 
               d.getBordeAbajo() < 0 || 
               d.getBordeArriba() > e.alto(); 
    }
}


