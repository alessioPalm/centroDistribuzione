package interfaccia;

import java.util.Scanner;

import controllo.Controllo;
import modello.Archivio;
import modello.Centro;
import modello.Ordine;

public class InterfacciaConsoleUtente {
	public int menu () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserisci 1 se si vuole aggiungere un centroDistribuzioni");
        System.out.println("inserisci 2 se si vuole aggiungere un ordine");
        System.out.println("inserisci 3 sistema gli ordini in ordine crescente");
        System.out.println("inserisci 4 per verificare se ci sono piu prodotti comprati dalla stessa persona");
        System.out.println("inserisci 5 stampa indirizzi a cui corrispondono piu nominativi diversi");
        System.out.println("inserisci 6 stampa prodotto piu venduto");
        System.out.println("inserisci 7 per salvare centro distribuzione su file");
        System.out.println();
        System.out.println("inserisci 0 per uscire dal programma");
        int i=scanner.nextInt();
        while (i<0 && i>7) {
            System.out.println("inserisci numero 1 o numero 2");
            i=scanner.nextInt();
        }
        return i;
    }
    public Centro leggiCentro () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserisci nome centro distribuzione");
        String nome = scanner.next();
        System.out.println("inserisci citta centro distribuzione");
        String citta = scanner.next();
        return new Centro(nome,citta);
    }
    public Ordine leggiOrdine(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserisci codice univoco del ordine");
        String codice = scanner.next();
        System.out.println("inserisci orario del ordine");
        System.out.println("ore");
        int ore = scanner.nextInt();
        while(ore<0 || ore>25) {
        	System.out.println("errore : orario inesistente");
        	ore = scanner.nextInt();
        }
        System.out.println("minuti");
        int minuti = scanner.nextInt();
        System.out.println("inserisci nome prodotto");
        String nomeProdotto = scanner.next();
        System.out.println("inserisci nome destinatario");
        String nomeDestinatario = scanner.next();
        System.out.println("inserisci indirizzo destinatario");
        String indirizzo= scanner.next();

        return new Ordine ( codice, ore , minuti, nomeProdotto, nomeDestinatario, indirizzo);

    }
    public String leggiNomeCentro() {
		Scanner scanner = new Scanner (System.in);
		System.out.println("inserisci nome centro distribuzione che si vuole scrivere nel file");
		String nome = scanner.nextLine();
		return nome;
	}
    
    public String stampaListaOrdinataPerOrario (Archivio archivio) {
    	Controllo controllo = new Controllo (archivio);
    	String somma = "";
    	for(Ordine ordine : controllo.getListaOrdiniOrdinatiPerOrario()) {
    		somma += ordine + "\n";
    	}
    	return somma;
    }
    public String stampaIndirizziNominativiDiversi (Archivio archivio) {
    	Controllo controllo = new Controllo (archivio);
    	String somma = "";
    	for(String indirizzo : controllo.getInsiemeIndirizziConNominativiDiversi()) {
    		somma += indirizzo + "\n";
    	}
    	return somma;
    }
}
