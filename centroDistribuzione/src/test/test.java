package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllo.CodiceEsistenteException;
import controllo.Controllo;
import junit.framework.Assert;
import modello.Archivio;
import modello.Centro;
import modello.Ordine;

class test {
	
	Controllo controllo;
	Archivio archivio;
	
	@BeforeEach
	void setUp () {
		archivio = new Archivio ();
		controllo = new Controllo (archivio);
		
		Centro c1 = new Centro ("amazon1", "piacenza");
		Centro c2 = new Centro ("amazon2", "roma");
		
		archivio.aggiungiCentro(c1);
		archivio.aggiungiCentro(c2);
		
		Ordine o1 = new Ordine ("001", 10, 00, "nexsus 6P", "mario rossi", "via ateneo Lucano, PZ");
		Ordine o2 = new Ordine ("002", 8, 00, "custodia nexsus 6P", "mario rossi", "via ateneo Lucano, PZ");
		Ordine o3 = new Ordine ("003", 10, 00, "set coltelli", "carlo cracco", "via dei mille, milano");
		
		try {
			c1.aggiungiOrdine(o1);
			c1.aggiungiOrdine(o2);
			c1.aggiungiOrdine(o3);
		} catch (CodiceEsistenteException e) {
			fail();
		}
		
		Ordine o4 = new Ordine ("005", 18, 00, "nexsus 6P", "mario rossi", "via ateneo Lucano, PZ");
		Ordine o5 = new Ordine ("006", 12, 00, "shampo", "attilio lombardo", "via ateneo Lucano, PZ");
		
		try {
			c2.aggiungiOrdine(o4);
			c2.aggiungiOrdine(o5);
		} catch (CodiceEsistenteException e) {
			fail();
		}
	}

	@Test
	void testaggiungiOrdine() {
		Centro c1 = new Centro ("amazon1", "piacenza");
		
		Ordine o1 = new Ordine ("001", 10, 00, "nexsus 6P", "mario rossi", "via ateneo Lucano, PZ");
		Ordine o2 = new Ordine ("001", 10, 00, "custodia nexsus 6P", "mario rossi", "via ateneo Lucano, PZ");
		Ordine o3 = new Ordine ("003", 10, 00, "set coltelli", "carlo cracco", "via dei mille, milano");
		
		
		try {
			c1.aggiungiOrdine(o1);
			Assert.assertEquals(1, c1.getInsieme().size());
		} catch (CodiceEsistenteException e) {
			fail();
		}
		
		try {
			c1.aggiungiOrdine(o2);
			fail();
		} catch (CodiceEsistenteException e) {
			Assert.assertEquals(1, c1.getInsieme().size());
		}
		
		try {
			c1.aggiungiOrdine(o3);
			Assert.assertEquals(2, c1.getInsieme().size());
		} catch (CodiceEsistenteException e) {
			fail();
		}
	}
	
	@Test
	void testgetListaOrdiniOrdinatiPerOrario() {
		
		Assert.assertEquals("002", controllo.getListaOrdiniOrdinatiPerOrario().get(0).getCodiceUnivoco());
		Assert.assertEquals("001", controllo.getListaOrdiniOrdinatiPerOrario().get(1).getCodiceUnivoco());
		Assert.assertEquals("003", controllo.getListaOrdiniOrdinatiPerOrario().get(2).getCodiceUnivoco());
		Assert.assertEquals("006", controllo.getListaOrdiniOrdinatiPerOrario().get(3).getCodiceUnivoco());
		Assert.assertEquals("005", controllo.getListaOrdiniOrdinatiPerOrario().get(4).getCodiceUnivoco());
	}
	
	
	@Test
	void testgetInsiemeIndirizziConNominativiDiversi() {
		
		Assert.assertEquals(true, controllo.verificaEsistenzaOrdineStessoProdottoEDestinatario());
	}
	
	@Test
	void testgetMappaIndirizziConNominativiDiversi() {
		
		Assert.assertEquals(1, controllo.getInsiemeIndirizziConNominativiDiversi().size());
	}
	
	@Test
	void testgetProdottoPiuOrdinato() {
		Assert.assertEquals("nexsus 6P", controllo.getProdottoPiuOrdinato());
	}

}
