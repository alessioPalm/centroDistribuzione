package controllo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import modello.Archivio;
import modello.Ordine;
import modello.ProdottoTemporaneo;

public class Controllo {
	Archivio archivio = new Archivio();
		
	public Controllo (Archivio archivio) {
		this.archivio = archivio;
	}
	
	public ArrayList <Ordine> getListaOrdiniOrdinatiPerOrario (){
		ArrayList <Ordine> listaOrdini = archivio.getInsiemeTotaliOrdini();
		Collections.sort(listaOrdini);
		
		return listaOrdini;
	}
	
	
	
	public ArrayList <Ordine> getListaOrdiniStessoProdottoEDestinatario (){
    	ArrayList <Ordine> listaTotaleOrdini = archivio.getInsiemeTotaliOrdini();
    	ArrayList <Ordine> listaOrdiniVerificati = new ArrayList<Ordine>();
		for(Ordine o1 : listaTotaleOrdini) {
			for(Ordine o2 : listaTotaleOrdini) {
				if(o1!=o2){
					if(o1.getNomeProdotto().equals(o2.getNomeProdotto())) {
						if(o1.getNomeDestinatario().equals(o2.getNomeDestinatario())) {
							if(!listaOrdiniVerificati.contains(o2)) {
								listaOrdiniVerificati.add(o2);
							}
						}
					}
				}
			}
		}
		return listaOrdiniVerificati;
    }
	public boolean verificaEsistenzaOrdineStessoProdottoEDestinatario() {
		return getListaOrdiniStessoProdottoEDestinatario().size()>0;
	}
	
	
	
	
	
	public HashMap <String , Integer> contaggioOccorrenzaProdotti () {
		HashMap <String , Integer> mappaOrdini = new HashMap <String , Integer>();
		ArrayList <Ordine> listaOrdini = archivio.getInsiemeTotaliOrdini();
			for(Ordine ordine : listaOrdini) {
				Integer contatore = mappaOrdini.get(ordine.getNomeProdotto());
				if(contatore == null) {
					contatore = 0;
				}
				contatore++;
				mappaOrdini.put(ordine.getNomeProdotto() , contatore);
			}
		return mappaOrdini;
	}
    public String getProdottoPiuOrdinato () {
    	HashMap <String , Integer> mappaOrdini = contaggioOccorrenzaProdotti();
    	Integer massimoTemporaneo = 0;
    	String prodottoMassimo = null;
    	for(String prodotto : mappaOrdini.keySet()) {
    		Integer i = mappaOrdini.get(prodotto);
    		if(i > massimoTemporaneo) {
    			massimoTemporaneo = i;
    			prodottoMassimo=prodotto;
    		}
    	}
    	return prodottoMassimo;
    }
    
    
    
    public ArrayList <ProdottoTemporaneo> getInsiemeProdottoTemporaneo (){
    	ArrayList <ProdottoTemporaneo> listaProdottoTemporaneo = new ArrayList <ProdottoTemporaneo> ();
    	HashMap <String , Integer> mappa = contaggioOccorrenzaProdotti();
    	for(String prodotto : mappa.keySet()) {
    		listaProdottoTemporaneo.add(new ProdottoTemporaneo(mappa.get(prodotto), prodotto));
    	}
    	return listaProdottoTemporaneo;
    }
    public ArrayList <ProdottoTemporaneo> getInsiemeProdottiOrdinata() {
    	ArrayList <ProdottoTemporaneo> listaPt = getInsiemeProdottoTemporaneo ();
		Collections.sort(listaPt);
		return listaPt;
	}
    
    
    
    
    public Set <String> getInsiemeIndirizziConNominativiDiversi () {
    	Set <String> insiemeOrdini = new HashSet <String> ();
		ArrayList <Ordine> listaOrdini = archivio.getInsiemeTotaliOrdini();
			for(Ordine o1 : listaOrdini) {
				for(Ordine o2 : listaOrdini) {
					if(o1.getIndirizzioDestinatario().equals(o2.getIndirizzioDestinatario())) {
						if(!o1.getNomeDestinatario().equals(o2.getNomeDestinatario())) {
							insiemeOrdini.add(o2.getIndirizzioDestinatario());
						}
					}
				}
			}
		return insiemeOrdini;
    }
}
