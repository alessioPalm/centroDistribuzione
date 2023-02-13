package modello;

public class Ordine implements Comparable<Ordine>{
	private String codiceUnivoco;
    private int ore;
    private int minuti;
    private String nomeProdotto;
    private String nomeDestinatario;
    private String indirizzioDestinatario;

    public Ordine(String codiceUnivoco, int ore, int minuti, String nomeProdotto, String nomeDestinatario, String indirizzioDestinatario) {
        this.codiceUnivoco = codiceUnivoco;
        this.ore = ore;
        this.minuti = minuti;
        this.nomeProdotto = nomeProdotto;
        this.nomeDestinatario = nomeDestinatario;
        this.indirizzioDestinatario = indirizzioDestinatario;
    }
    
    public String getNomeProdotto() {
		return nomeProdotto;
	}
    
    public int contatore (String nomeProdotto) {
    	int contatore =0;
    	if(this.nomeProdotto.equals(nomeProdotto)) {
    		contatore++;
    	}
    	return contatore;
    	
    }

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public String getIndirizzioDestinatario() {
		return indirizzioDestinatario;
	}

	public int getMinuti() {
		return minuti;
	}

	public void setMinuti(int minuti) {
		this.minuti = minuti;
	}

	public int getOre() {
		return ore;
	}

	public String getCodiceUnivoco() {
		return codiceUnivoco;
	}


	@Override
	public String toString() {
		return "Ordine [codiceUnivoco=" + codiceUnivoco + ", orario=" + ore + ":" + minuti + ", nomeProdotto="
				+ nomeProdotto + ", nomeDestinatario=" + nomeDestinatario + ", indirizzioDestinatario="
				+ indirizzioDestinatario + "]";
	}
	public int calcoloOrario() {
		int calcoloOre= (this.ore*60) + minuti;
		return calcoloOre;
	}

	@Override
	public int compareTo(Ordine ordine) {
    	if(this.ore==ordine.getOre()) {
    		return this.minuti-ordine.getMinuti();
    	}else {
    		return this.ore - ordine.getOre();
    	}
	}
	@Override
	public boolean equals(Object o) {
		Ordine ordine = (Ordine)o;
		return ordine.getCodiceUnivoco().equals(this.codiceUnivoco);
	}
	@Override
	public int hashCode() {
		return this.codiceUnivoco.hashCode();
	}
}
