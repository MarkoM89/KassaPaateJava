

/**
 *
 * @author Marko
 */
public class Maksukortti {
    
	private String omistaja;
    private double saldo;
    
    public Maksukortti(String omistaja, double alkusaldo){
    	this.omistaja = omistaja;
        this.saldo = alkusaldo;
    }
    
    public String palautaOmistaja() {
    	return this.omistaja;
    }
    
    public void tulostaSaldo(){
        System.out.println(this.omistaja+ ": Kortilla on rahaa " + this.saldo + " euroa");
    }

    
    public void lataaRahaa(double rahamaara){
        if(rahamaara > 0) {
            this.saldo += rahamaara;
        }
        else{
        	System.out.println("Raham��r�n t�ytyy olla 0� suurempi");
        }
    }
    
    public void veloita(double hinta, int maara) {
    	double kokonaishinta = hinta * maara;
        if(this.saldo >= kokonaishinta){
            this.saldo -= kokonaishinta;
        }
        else {
        	System.out.println("Kortilla ei ole tarpeeksi katetta");
        }
    }
      
}
