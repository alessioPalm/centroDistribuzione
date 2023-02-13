package controllo;

import java.util.Scanner;

import interfaccia.InterfacciaConsoleUtente;
import modello.Archivio;
import modello.Centro;
import persistenza.CentroToFile;

public class Main {
	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);
	    Archivio archivio = new Archivio();
	    Controllo controllo = new Controllo(archivio);
	    InterfacciaConsoleUtente interfaccia = new InterfacciaConsoleUtente();
	    CentroToFile dao = new CentroToFile();
	    
	    int i = interfaccia.menu();
	    while(i>0){
	        if(i==1){
	        	archivio.aggiungiCentro(interfaccia.leggiCentro());
	        }else if(i==2) {
	        	System.out.println("inserisci centro distribuzionde dove si vuole aggiungere l'ordine");
	        	String nomeCentro = scanner.next();
	        	Centro centroVerificato = archivio.centroByNome(nomeCentro);
	        	if(centroVerificato != null) {
	        		System.out.println(centroVerificato);
	        		try {
						centroVerificato.aggiungiOrdine(interfaccia.leggiOrdine());
					} catch (CodiceEsistenteException e) {
						System.out.println("codice univoco già esistente riprovare");
					}
	        	}if(centroVerificato == null) {
	        		System.out.println("centro distribuzione inesistente");
	        	}
	        	
	        }else if(i==3) {
	        	System.out.println(interfaccia.stampaListaOrdinataPerOrario(archivio));
	        }else if(i==4) {
	        	if(controllo.verificaEsistenzaOrdineStessoProdottoEDestinatario()) {
	        		System.out.println("esistono due ordini effettuati dalla stessa persona per lo stesso prodotto");
	        	}else if(!controllo.verificaEsistenzaOrdineStessoProdottoEDestinatario()) {
	        		System.out.println("non esistono due ordini effettuati dalla stessa persona per lo stesso prodotto");
	        	}
	        }else if(i==5) {
	        	System.out.println(interfaccia.stampaIndirizziNominativiDiversi(archivio));
	        }else if(i==6) {
	        	System.out.println(controllo.getProdottoPiuOrdinato());
	        }else if(i==7) {
	        	Centro centro = archivio.centroByNome(interfaccia.leggiNomeCentro());
				if(centro != null) {
					try {
						dao.scriviListaCentro(centro);
					} catch (ProblemaScritturaFileException e) {
						System.out.println("si è verificato un errore durante un salvataggio nel file");
					}
				}else {
					System.out.println("nessun cocktail con que nome");
				}
	        }
	        
            i = interfaccia.menu();

	        
	    }
	    
	    
	}
}
