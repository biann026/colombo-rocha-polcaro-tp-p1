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
	
	public boolean colisionCabezaPepConIslaAbajo(Pep p, Isla isla) {
		return p.getBordeArriba() < isla.getBordeAbajo();
	}
   
	// COLISION TORTUGA-ISLA
	public boolean chocaronTortuIsla(Tortuga t, Isla isla) {
	    // Verifica que los bordes horizontales se superpongan
	    boolean superposicionHorizontal = t.getBordeDerecho() - 9 > isla.getBordeIzquierdo() + 8 &&
	                                      t.getBordeIzquierdo() + 9 < isla.getBordeDerecho() - 8;
	    
	    // Verifica que la tortuga esté sobre la isla
	    boolean sobreIsla = t.getBordeAbajo() >= isla.getBordeArriba() &&
	                        t.getBordeArriba() < isla.getBordeAbajo();
	    
	    return superposicionHorizontal && sobreIsla;
	}
	      
	// COLISION TORTUGA-BORDES DE LA ISLA
	public boolean chocaConBordes(Tortuga t, Isla isla) {
	    // Verifica si la tortuga toca los bordes izquierdo o derecho de la isla
	    return t.getBordeIzquierdo() <= isla.getBordeIzquierdo() - 18 ||
	           t.getBordeDerecho() >= isla.getBordeDerecho() + 18;
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
        // Colisión horizontal (gnomo no pasa a través de la isla)
        boolean colisionHorizontal = g.getBordeDerecho() > isla.getBordeIzquierdo() && g.getBordeIzquierdo() < isla.getBordeDerecho();
        // Colisión vertical (gnomo está tocando la isla por arriba o por abajo)
        boolean colisionVertical = g.getBordeAbajo() >= isla.getBordeArriba() && g.getBordeArriba() < isla.getBordeAbajo();
        
        // Devolver si hay colisión en ambas direcciones
        return colisionHorizontal && colisionVertical;
    }
    
    public static boolean detectarColisionGnomoBorde(Gnomo g, int anchoPantalla, int altoPantalla) {
        // Verificar si el gnomo ha tocado el borde izquierdo
        if (g.getBordeIzquierdo() <= 0) {
            return true; // El gnomo tocó el borde izquierdo
        }
        // Verificar si el gnomo ha tocado el borde derecho
        if (g.getBordeDerecho() >= anchoPantalla) {
            return true; // El gnomo tocó el borde derecho
        }
        return false; // No hay colisión con los bordes
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


