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

package JTrash.model.observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import JTrash.model.card.Carta;
import JTrash.model.data.Profilo;
import JTrash.model.player.AbstractGiocatore;
import JTrash.model.player.GiocatoreUmano;

/**
 * Classe rappresentante lo stato del gioco. Utilizza il pattern Singleton per
 * garantire una sola istanza durante l'esecuzione.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
@SuppressWarnings("deprecation")
public class StatoGioco extends Observable {

    /**
     * Istanza singleton di StatoGioco. Questo pattern garantisce che esista 
     * una sola istanza di StatoGioco nell'intera applicazione.
     */
	private static StatoGioco instance;

    /**
     * Rappresenta la carta attualmente in mano al giocatore umano.
     */
	private Carta manoGiocatoreUmano;

    /**
     * Lista delle carte presenti sul tavolo per ogni giocatore. 
     * Ogni lista interna rappresenta le carte di un singolo giocatore.
     */
	private List<ArrayList<Carta>> carteSulTavoloGiocatori;

    /**
     * Rappresenta l'ultima carta che e' stata scoperta e messa sul tavolo.
     */
	private Carta ultimaCartaScoperta;

    /**
     * Rappresenta la carta attualmente sulla cima del mazzo degli scarti.
     */
	private Carta scartiCarta;

    /**
     * Lista dei nomi dei giocatori presenti nella partita.
     */
	private List<String> nomiGiocatori;

    /**
     * Indica il numero totale di giocatori nella partita.
     */
	private int numeroGiocatori;

    /**
     * Lista dei profili associati ai giocatori presenti nella partita.
     */
	private List<Profilo> profiliGiocatori;

	/**
	 * Costruttore privato per prevenire l'istanziazione dall'esterno.
	 */
	private StatoGioco() {
		this.setManoGiocatoreUmano(null);
		this.carteSulTavoloGiocatori = new ArrayList<>();
		this.nomiGiocatori = new ArrayList<>();
		this.ultimaCartaScoperta = null;
		this.setScartiCarta(null);
		this.numeroGiocatori = 0;
		this.profiliGiocatori = new ArrayList<>();
	}

	/**
	 * Ritorna l'istanza singleton della classe.
	 *
	 * @return L'istanza singleton di StatoGioco.
	 */
	public static StatoGioco getInstance() {
		if (instance == null) {
			instance = new StatoGioco();
		}
		return instance;
	}

	/**
	 * Restituisce una lista delle carte presenti sul tavolo dei giocatori.
	 * 
	 * @return Una lista delle carte sul tavolo dei giocatori.
	 */
	public List<ArrayList<Carta>> getCarteSulTavoloGiocatori() {
	    return carteSulTavoloGiocatori;
	}

	/**
	 * Restituisce la mano del giocatore umano.
	 * 
	 * @return La mano del giocatore umano.
	 */
	public Carta getManoGiocatoreUmano() {
	    return manoGiocatoreUmano;
	}

	/**
	 * Imposta la mano del giocatore umano e notifica gli osservatori.
	 *
	 * @param manoGiocatoreUmano La nuova mano del giocatore umano.
	 */
	public void setManoGiocatoreUmano(Carta manoGiocatoreUmano) {
		this.manoGiocatoreUmano = manoGiocatoreUmano;
		setChanged();
		notifyObservers(this.manoGiocatoreUmano);
	}

	/**
	 * Aggiorna la mano del giocatore umano.
	 *
	 * @param giocatore  Il giocatore da confrontare.
	 * @param nuovaCarta La nuova carta da impostare.
	 */
	public void aggiornaManoGiocatoreUmano(AbstractGiocatore giocatore, Carta nuovaCarta) {
		if (giocatore.equals(GiocatoreUmano.getInstance())) {
			this.setManoGiocatoreUmano(nuovaCarta);
			setChanged();
			notifyObservers(this.manoGiocatoreUmano);
		}
	}

	/**
	 * Aggiunge le carte sul tavolo del giocatore indicato.
	 *
	 * @param indiceGiocatore Indice del giocatore a cui aggiungere le carte.
	 * @param carteSulTavolo  Lista delle carte da aggiungere.
	 */
	public void aggiungiCarteSulTavoloGiocatore(int indiceGiocatore, ArrayList<Carta> carteSulTavolo) {
		if (indiceGiocatore < 0) {
			throw new IllegalArgumentException("L'indice del giocatore non può essere negativo.");
		}

		// Assicurati che ci siano abbastanza slot nella lista
		while (indiceGiocatore >= this.carteSulTavoloGiocatori.size()) {
			this.carteSulTavoloGiocatori.add(new ArrayList<>());
		}

		// Aggiungi le carte usando .set() e .addAll() in una singola operazione
		this.carteSulTavoloGiocatori.set(indiceGiocatore, carteSulTavolo.stream() 
				/*
				 * Questa parte crea uno stream delle 
				 * carte presenti nella lista 
				 * carteSulTavolo.
				 */

				/*
				 * Questa e' un'operazione di raccolta che trasforma lo stream delle carte in
				 * una nuova ArrayList. In altre parole, sto prendendo ciascuna carta nello
				 * stream e raccogliendola in una nuova lista ArrayList. Il
				 * Collectors.toCollection(ArrayList::new) specifica che vogliamo raccogliere
				 * gli elementi in una ArrayList.
				 */
				.collect(Collectors.toCollection(ArrayList::new)));

		/* Notifica gli osservatori */
		setChanged();
		notifyObservers(this.carteSulTavoloGiocatori);
	}

	/**
	 * Restituisce l'ultima carta che e' stata scoperta.
	 * 
	 * @return L'ultima carta scoperta.
	 */
	public Carta getUltimaCartaScoperta() {
	    return this.ultimaCartaScoperta;
	}


	/**
	 * Imposta l'ultima carta che e' stata scoperta e notifica gli osservatori dell'aggiornamento.
	 * 
	 * @param carta La carta da impostare come ultima carta scoperta.
	 */
	public void setUltimaCartaScoperta(Carta carta) {
	    this.ultimaCartaScoperta = carta;
	    setChanged();
	    notifyObservers(this.ultimaCartaScoperta);
	}

	/**
	 * @return La lista dei nomi dei giocatori.
	 */
	public List<String> getNomiGiocatori() {
		return nomiGiocatori;
	}

	/**
	 * @return Il numero dei giocatori.
	 */
	public int getNumeroGiocatori() {
		return numeroGiocatori;
	}

	/**
	 * Imposta il numero dei giocatori.
	 *
	 * @param numeroGiocatori Il nuovo numero dei giocatori.
	 */
	public void setNumeroGiocatori(int numeroGiocatori) {
		if (numeroGiocatori < 0) {
			throw new IllegalArgumentException("numeroGiocatori non può essere negativo");
		}
		this.numeroGiocatori = numeroGiocatori;
		setChanged();
		notifyObservers();
	}

	/**
	 * Rimuove tutte le carte presenti sul tavolo dei giocatori e notifica gli osservatori del cambiamento.
	 */
	public void pulisciCarteSulTavoloGiocatori() {
	    this.carteSulTavoloGiocatori.clear();
	    setChanged();
	    notifyObservers(this.carteSulTavoloGiocatori);
	}


	/**
	 * @return La carta degli scarti.
	 */
	public Carta getScartiCarta() {
		return scartiCarta;
	}

	/**
	 * Imposta la carta nello stack degli scarti e notifica gli osservatori del cambiamento.
	 * 
	 * @param scartiCarta La carta da aggiungere allo stack degli scarti.
	 */
	public void setScartiCarta(Carta scartiCarta) {
	    this.scartiCarta = scartiCarta;
	    setChanged();
	    notifyObservers(this.scartiCarta);
	}


	/**
	 * Restituisce la lista dei profili dei giocatori.
	 * 
	 * @return Una lista contenente i profili di tutti i giocatori.
	 */
	public List<Profilo> getProfiliGiocatori() {
	    return profiliGiocatori;
	}


	/**
	 * Aggiunge un nuovo profilo alla lista dei profili dei giocatori.
	 *
	 * @param profilo Il profilo da aggiungere.
	 */
	public void aggiungiProfiloGiocatore(Profilo profilo) {
		this.profiliGiocatori.add(profilo);
		setChanged();
		notifyObservers(this.profiliGiocatori);
	}
}
