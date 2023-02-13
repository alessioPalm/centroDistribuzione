package persistenza;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;

import controllo.CodiceEsistenteException;
import controllo.ProblemaScritturaFileException;
import modello.Centro;
import modello.Ordine;

public class CentroToFile {
private final static String nomeFile = "C:\\Users\\Palmese\\Desktop\\corso java\\centro .txt";
	
	public void scriviListaCentro (Centro centro) throws ProblemaScritturaFileException{
		PrintWriter flusso=null;
		try {
		java.io.FileWriter fileWriter = new java.io.FileWriter(nomeFile);
		flusso= new java.io.PrintWriter(fileWriter);
		flusso.println("centro | " + "nome = " + centro.getNome() + " | " + "citta = " + centro.getCitta());
		flusso.println("_________________");
		Set <Ordine> listaOrdini = centro.getInsieme();
		for(Ordine ordine : listaOrdini) {
			flusso.println(ordine.getCodiceUnivoco() + "|" + ordine.getOre() + "|" + ordine.getMinuti() + " | " 
			+ ordine.getNomeProdotto() + "|" + ordine.getNomeDestinatario() + "|" + ordine.getIndirizzioDestinatario());
		}
		
		}catch (java.io.IOException e) {
			throw new ProblemaScritturaFileException();
		}finally {
			if(flusso!=null) {
				flusso.close();
			}
		}
	}
	public Centro leggiCentro () {
		java.io.BufferedReader flusso=null;
		Centro centro = null;
		try {
			java.io.FileReader fileReader = new java.io.FileReader(nomeFile);
			flusso=new java.io.BufferedReader(fileReader);
			String linea;
			linea = flusso.readLine();
			centro = estraiNomeCostoCocktail (linea);
			flusso.readLine();
			while((linea=flusso.readLine()) != null) {
				try {
						centro.aggiungiOrdine(estraiOrdine(linea));
					} catch (NumberFormatException | CodiceEsistenteException nfe) {
						System.out.println("Errore: " + nfe);
					}
				} 
			}catch(java.io.FileNotFoundException fnfe) { 
				System.out.println("ERRORE: " + fnfe);
			} catch (java.io.IOException ioe) { 
					System.out.println("ERRORE: " + ioe); 
			}
			finally {
				try { if (flusso != null) { flusso.close(); } } catch (java.io.IOException ioe) {}
			}
		return centro;
	}
	
	
	public Centro estraiNomeCostoCocktail (String lineaCentro) {
		java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(lineaCentro, "|");

		tokenizer.nextToken().trim();  
		String nomeCentro= tokenizer.nextToken().trim();
		String nomeCitta= tokenizer.nextToken().trim();
		Centro centro = new Centro(nomeCentro,nomeCitta);
		return centro;

	}
	public Ordine estraiOrdine (String lineaOrdine) {
		java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(lineaOrdine, "|");
		
		String codiceUnivoco = tokenizer.nextToken().trim();
	    int ore = Integer.parseInt(tokenizer.nextToken().trim());
	    int minuti = Integer.parseInt(tokenizer.nextToken().trim());
	    String nomeProdotto = tokenizer.nextToken().trim();
	    String nomeDestinatario = tokenizer.nextToken().trim();
	    String indirizzoDestinatario = tokenizer.nextToken().trim();
		
		Ordine ordine = new Ordine (codiceUnivoco,ore,minuti,nomeProdotto,nomeDestinatario,indirizzoDestinatario);
		return ordine;
	}
}
