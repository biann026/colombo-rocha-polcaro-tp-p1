package juego;

public class ControladorColisiones {
	
    public boolean detectarColision(Pep p, Isla isla) {
        return p.bordeDerecho > isla.bordeIzquierdo && 
               p.bordeIzquierdo < isla.bordeDerecho && 
               p.bordeAbajo > isla.bordeArriba && 
               p.bordeArriba < isla.bordeAbajo;
    }
    
    public boolean detectarColisionGnomo(Gnomo g, Isla isla) {
        return g.bordeDerecho > isla.bordeIzquierdo && 
               g.bordeIzquierdo < isla.bordeDerecho && 
               g.bordeAbajo > isla.bordeArriba && 
               g.bordeArriba < isla.bordeAbajo;
    }
    
    public boolean detectarColisionGnomo(Tortuga t, Isla isla) {
        return t.bordeDerecho > isla.bordeIzquierdo && 
               t.bordeIzquierdo < isla.bordeDerecho && 
               t.bordeAbajo > isla.bordeArriba && 
               t.bordeArriba < isla.bordeAbajo;
    }
   
   
    public boolean chocaronPepTortu(Pep p, Tortuga t) {
        return p.bordeDerecho > t.bordeIzquierdo && 
               p.bordeIzquierdo < t.bordeDerecho && 
               p.bordeAbajo > t.bordeArriba && 
               p.bordeArriba < t.bordeAbajo;
    }
    
    public boolean chocaronGnomoTortu(Gnomo g, Tortuga t) {
        return g.bordeDerecho > t.bordeIzquierdo && 
               g.bordeIzquierdo < t.bordeDerecho && 
               g.bordeAbajo > t.bordeArriba && 
               g.bordeArriba < t.bordeAbajo;
    }
    
    public boolean chocaronPepGnomo(Pep p, Gnomo g) {
        return p.bordeDerecho > g.bordeIzquierdo && 
               p.bordeIzquierdo < g.bordeDerecho && 
               p.bordeAbajo > g.bordeArriba && 
               p.bordeArriba < g.bordeAbajo;
    }
    
    public boolean disparoExitoso(DisparoDePep d, Tortuga t) {
    	return d.bordeDerecho > t.bordeIzquierdo && 
                d.bordeIzquierdo < t.bordeDerecho && 
                d.bordeAbajo > t.bordeArriba && 
                d.bordeArriba < t.bordeAbajo;
    }
    
}
