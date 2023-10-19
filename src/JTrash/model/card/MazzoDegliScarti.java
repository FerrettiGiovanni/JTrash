/* 
Copyright (c) [2023] [Giovanni Ferretti]

Con il presente si concede, gratuitamente, a chiunque ottenga una copia
di questo software e dei file di documentazione associati (il "Software"), di trattare
nel Software senza restrizione, inclusi, senza limitazione, i diritti
di utilizzo, copia, modifica, unione, pubblica distribuzione, sottolicenza e/o vendita
copie del Software, e di consentire alle persone a cui il Software e'
fornito di farlo, alle seguenti condizioni:

L'indicazione di copyright sopra indicata e questa indicazione di permesso devono essere incluse in
tutte le copie o parti sostanziali del Software.

IL SOFTWARE e' FORNITO "COSI' COM'E'", SENZA GARANZIA DI ALCUN TIPO, ESPRESSA O
IMPLICITA, INCLUSO MA NON LIMITATO ALLE GARANZIE DI COMMERCIABILITÀ,
IDONEITÀ PER UN PARTICOLARE SCOPO E NON VIOLAZIONE. IN NESSUN CASO GLI
AUTORI O I TITOLARI DEL COPYRIGHT POTRANNO ESSERE RITENUTI RESPONSABILI PER QUALSIASI RECLAMO,
DANNO O ALTRA RESPONSABILITÀ, SIA IN UN'AZIONE DI CONTRATTO, TORTO O ALTRIMENTI, DERIVANTE DA,
FUORI O IN RELAZIONE CON IL SOFTWARE O L'USO O ALTRE OPERAZIONI NEL
SOFTWARE.
 */

package JTrash.model.card;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe rappresenta il mazzo degli scarti in un gioco di carte.
 * 
 * Utilizza il pattern Singleton per garantire che esista un'unica istanza di
 * MazzoDegliScarti nell'intera applicazione. Questo significa che tutte le
 * operazioni eseguite sul mazzo degli scarti saranno effettuate su questo
 * singolo oggetto.
 * 
 * Contiene metodi per aggiungere una carta al mazzo, prelevare una carta dal
 * mazzo, verificare se il mazzo e' vuoto, rimescolare le carte nel mazzo
 * principale, ottenere l'ultima carta aggiunta al mazzo senza rimuoverla e
 * ottenere tutte le carte scartate.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class MazzoDegliScarti {

	/** Lista che contiene le carte scartate. */
	private List<Carta> scarti;

	/**
	 * L'unico oggetto MazzoDegliScarti che esistera' nell'applicazione, come
	 * definito dal pattern Singleton.
	 */
	private static MazzoDegliScarti instance = null;

	/**
	 * Costruttore privato.
	 * 
	 * Requisito chiave del pattern Singleton che rende impossibile l'istanziazione
	 * di questa classe dall'esterno, garantendo che solo un'istanza esista.
	 * 
	 */
	private MazzoDegliScarti() {
		this.scarti = new ArrayList<>();
	}

	/**
	 * Metodo per accedere all'istanza Singleton del mazzo degli scarti.
	 * 
	 * Se l'istanza non esiste, la crea. Se l'istanza esiste gia', la restituisce.
	 * Questo garantisce che ci sia sempre solo un'istanza di MazzoDegliScarti.
	 * 
	 * 
	 * @return L'istanza singleton di MazzoDegliScarti.
	 */
	public static MazzoDegliScarti getInstance() {
		if (instance == null) {
			instance = new MazzoDegliScarti();
		}
		return instance;
	}

	/**
	 * Aggiunge una carta al mazzo degli scarti.
	 * 
	 * @param cartaDaScartare La carta da aggiungere al mazzo degli scarti.
	 */
	public void aggiungiCarta(Carta cartaDaScartare) {
		this.scarti.add(cartaDaScartare);
	}

	/**
	 * Pesca l'ultima carta dal mazzo degli scarti.
	 * 
	 * @return La carta pescata.
	 * @throws IllegalStateException Se il mazzo degli scarti e' vuoto.
	 */
	public Carta pesca() {
		if (scarti.isEmpty()) {
			throw new IllegalStateException("Il mazzo degli scarti e' vuoto.");
		}
		return scarti.remove(scarti.size() - 1);
	}

	/**
	 * Controlla se il mazzo degli scarti e' vuoto.
	 * 
	 * @return true se il mazzo e' vuoto, false altrimenti.
	 */
	public boolean vuoto() {
		return scarti.isEmpty();
	}

	/**
	 * Restituisce l'ultima carta aggiunta al mazzo degli scarti, senza rimuoverla.
	 * 
	 * @return La carta in cima al mazzo degli scarti.
	 * @throws IllegalStateException Se il mazzo degli scarti e' vuoto.
	 */
	public Carta getCartaInCima() {
		if (scarti.isEmpty()) {
			throw new IllegalStateException("Il mazzo degli scarti e' vuoto.");
		}
		return scarti.get(scarti.size() - 1);
	}
}
