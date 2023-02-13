package modello;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import modello.ProdottoTemporaneo;

public class Archivio {
ArrayList <Centro> lista = new ArrayList<Centro>();
	
    public ArrayList<Centro> getInsieme() {
		return lista;
	}
    public void aggiungiCentro (Centro centro)  {
        lista.add(centro);
    }
    
    public ArrayList <Ordine> getInsiemeTotaliOrdini (){
    	ArrayList <Ordine> listaTotaleOrdini = new ArrayList <Ordine> ();
    	for(Centro centro : lista) {
    		listaTotaleOrdini.addAll(centro.getInsieme());
    	}
    	return listaTotaleOrdini;
    }
    
    public Centro centroByNome (String nome) {
    	Centro centroVerificato = null;
    	for(Centro centro : this.lista) {
    		if(centro.getNome().equals(nome)) {
    			centroVerificato = centro;
    		}
    	}
    	return centroVerificato;
    }
}
//ordina
//lista con nuovo oggetto con proprieta contatore 
