

import java.util.ArrayList;
import java.util.Scanner;

public class MainKassa {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        int toiminto = 0;
        String nimi;
        String tuotenimi = " ";
        double rahaMaara;
        int tuoteMaara;
        double tuoteHinta;
        double loppusumma = 0;
        
        
        ArrayList<Maksukortti> maksukortit = new ArrayList<>();
        maksukortit.add(new Maksukortti("Mikko", 120.60));
        maksukortit.add(new Maksukortti("Tapio", 200.00));
        maksukortit.add(new Maksukortti("Noora", 175.20));
        
        ArrayList<Tuote> tuotteet = new ArrayList<>();
        tuotteet.add(new Tuote("Tomaatti", 1.25));
        tuotteet.add(new Tuote("Omena", 0.95));
        
        ArrayList<String> ostetutTuotteet = new ArrayList<>();
        ArrayList<Kuitti> kuitit = new ArrayList<>();
        
        
        while (toiminto != 4) {
        	
        	/*
P��toiminto 1: Asiakkaan ostotapahtuma

P��toiminto 2: Osta tuote kaupalle / Varasto
		Toiminto 1: Varastosaldo
		Toiminto 2: Osta lis�� tuotetta varastoon


P��toiminto 3: Tuotehallinta
		Toiminto 1: Lis�� tuote
		Toiminto 2: Poista tuote
		Toiminto 3: Tulosta tuotteet
		
P��toiminto 4: Poistu ohjelmasta

P��toiminto 5: Kokeilut, Tulosta maksukortit
        	 
        	 */
        	
        	System.out.print("\nP��toiminto 1: Ostotapahtuma\nP��toiminto 2: Varaston hallinta\nP��toiminto 3: Tuotehallinta\n"
        			+ "P��toiminto 4: Poista ohjelmasta\nP��toiminto 5: Tulosta korttien tiedot\n");
        	toiminto = lukija.nextInt();
        	lukija.nextLine();
        	
        	switch(toiminto) {
    		case 1:
    			System.out.println("Kuka ostaa?");
    			nimi = lukija.nextLine();

    			for(Maksukortti omistaja : maksukortit) {

    				if(omistaja.palautaOmistaja().equals(nimi)) {

        				tuotenimi = " ";
    					while (!tuotenimi.equals("")) {
    					System.out.println("Valitse tuote sy�tt�m�ll� tuotteen nimi:");
    					for(Tuote tuote : tuotteet) {
    						tuote.tulostaTuote();
    					}
    					tuotenimi = lukija.nextLine();
    					
    					if(!tuotenimi.equals("")) {
    					for(Tuote tuote : tuotteet) {
    						if(tuote.haeNimi().equals(tuotenimi)) {
    							System.out.println("Paljonko ostetaan: ");
    							tuoteMaara = lukija.nextInt();
    							omistaja.veloita(tuote.haeHinta(), tuoteMaara);
    							lukija.nextLine();
    							ostetutTuotteet.add(tuotenimi+ " " +(tuoteMaara)+ "kpl");
    							loppusumma += (tuote.haeHinta()*tuoteMaara);
    						}
    					}
    					}
    					}
    					kuitit.add(new Kuitti(nimi, loppusumma));
    					
    					for(String ostettu : ostetutTuotteet) {
    						kuitit.get(kuitit.size() - 1).lisaaOstos(ostettu);
    					}
    					
    					kuitit.get(kuitit.size() - 1).tulostaKuitti();
    					ostetutTuotteet.clear();
    					loppusumma = 0;
    				}

    			}

    			
    			break;
    		case 2:
    			System.out.println("Varaston hallinta");
    			break;
    		case 3:
    			toiminto = 0;
				    			while (toiminto != 4) {
				    			System.out.println("Tuotteiden hallinta");
				
				    			System.out.print("\nToiminto 1: Lis�� tuote\nToiminto 2: Poista tuote\nToiminto 3: Tulosta tuotteet\n"
				    					+ "Toiminto 4: Poistu tuotehallinnasta\n");
				    			
				    			
				            		toiminto = lukija.nextInt();
				            		lukija.nextLine();
				            		
				            		switch(toiminto) {
				            		case 1:
				            			System.out.println("Tuotteen nimi:");
				            			nimi = lukija.nextLine();
				            			System.out.println("Tuotteen yksikk�hinta:");
				            			tuoteHinta = lukija.nextDouble();

				            			tuotteet.add(new Tuote(nimi, tuoteHinta));
				            		
				            		
				            		break;
				    				case 2:
				    	    			//Jos, lista tyhjenee nolliin, ohjelma rikkoutuu
				    	    			System.out.println("Mik� tuote poistetaan? sy�t� tyhj� kentt�, jos et halua poistaa mit��n");
				    	    			nimi = lukija.nextLine();
				    	    			for(Tuote tuote : tuotteet) {
				    	    				if(tuote.haeNimi().equals(nimi)) {
				    	    					tuotteet.remove(tuote);
				    	    				}
				    	    				else {
				    	    					System.out.println("Tuotetta ei l�ytynyt");
				    	    				}
				    	    			}

				    	    			break;
				            		
									case 3:
						    			System.out.println("Tulosta tuotteet");
						    			for(Tuote tuote : tuotteet) {
						    				tuote.tulostaTuote();
						    			}
						    			break;
				            		}
				    			}
				    		toiminto = 0;
    			break;
    		case 4:
    			System.out.println("Poistutaan ohjelmasta");
    			break;

    		case 5:
    			System.out.println("Tulosta korttien tiedot");
    			for(Maksukortti omistaja : maksukortit) {
    				omistaja.tulostaSaldo();
    			}
    			
    			System.out.println("\n\nTulosta kuitit");
    			for(Kuitti omistaja : kuitit) {
    				omistaja.tulostaKuitti();
    			}
    			

    			break;
    			
    		default:
    			System.out.println("Vain numerot 1-4");
    		}
        
        }
    }
}