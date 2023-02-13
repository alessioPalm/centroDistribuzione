package modello;

import java.util.ArrayList;
import java.util.HashMap;

public class ProdottoTemporaneo implements Comparable<ProdottoTemporaneo> {
	private Integer contatore;
	private String prodotto;
	
	
	public ProdottoTemporaneo(Integer contatore, String prodotto) {
		this.contatore = contatore;
		this.prodotto = prodotto;
	}
	


	public Integer getContatore() {
		return contatore;
	}



	public String getProdotto() {
		return prodotto;
	}



	@Override
	public int compareTo(ProdottoTemporaneo pt) {
		
    	return pt.getContatore() - this.contatore;
	}
	
	
}
