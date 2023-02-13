package modello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import controllo.CodiceEsistenteException;

public class Centro {
	private String nome;
    private String citta;
    Set <Ordine> insiemeOrdini = new HashSet <Ordine>();
    
    
	public Centro(String nome, String citta) {
        this.nome = nome;
        this.citta = citta;
    }
	
	public Set<Ordine> getInsieme() {
		return insiemeOrdini;
	}
	public ArrayList <Ordine> ordinaListaOrdini () {
		ArrayList <Ordine> lista = new ArrayList <Ordine>(insiemeOrdini);
		Collections.sort(lista);
		return lista;
	}
	public String StampainsiemeOrdini() {
		ArrayList <Ordine> lista = ordinaListaOrdini();
		String sommaOrdini = "";
		for(Ordine ordine : lista) {
			sommaOrdini += ordine.toString() + "\n"; 
		}
		return sommaOrdini;
	}

	public boolean verificaOrdineGiaEsistente (String codiceUnivoco) {
		for (Ordine ordine : insiemeOrdini) {
			if(ordine.getCodiceUnivoco().equals(codiceUnivoco)){    // attenzione
				return true;
			}
		}
		return false;
	}
	public void aggiungiOrdine (Ordine ordine)throws CodiceEsistenteException {
		boolean ordineVerificato = verificaOrdineGiaEsistente(ordine.getCodiceUnivoco());
		if(!ordineVerificato) {
			insiemeOrdini.add(ordine);
		}else {
			throw new CodiceEsistenteException();
		}
		
	}

    public String getNome() {
        return nome;
    }

    public String getCitta() {
        return citta;
    }

	@Override
	public String toString() {
		return "Ordine [nome=" + nome + ", citta=" + citta + "]";
	}
}
