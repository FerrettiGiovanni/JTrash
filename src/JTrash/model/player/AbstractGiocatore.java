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

package JTrash.model.player;

import java.util.ArrayList;
import java.util.Collections;

import JTrash.controller.audio.SimpleAudioManager;
import JTrash.controller.util.InputManager;
import JTrash.model.card.Carta;
import JTrash.model.card.MazzoDegliScarti;
import JTrash.model.card.MazzoDiCarte;
import JTrash.model.card.actions.AzioneScarto;
import JTrash.model.data.Profilo;
import JTrash.model.observable.StatoGioco;

/**
 * Classe astratta che rappresenta un giocatore nel gioco JTrash.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public abstract class AbstractGiocatore implements AzioniGiocatore {

    /**
     * Profilo associato al giocatore, che contiene informazioni come 
     * nickname, avatar e statistiche di gioco.
     */
	private Profilo profilo;

    /**
     * Lista di carte che il giocatore ha attualmente in mano.
     */
	private ArrayList<Carta> mano;

    /**
     * Lista delle carte che il giocatore ha posizionato sul tavolo.
     */
	private ArrayList<Carta> carteSulTavolo;

    /**
     * Numero di carte che il giocatore ha in mano all'inizio del turno.
     */
	private int carteIniziali;

    /**
     * Mazzo di carte che rappresenta il mazzo principale da cui i giocatori
     * pescano le carte durante il gioco.
     */
	private MazzoDiCarte mazzo;

    /**
     * Indica se il giocatore ha già scartato una carta in questo turno.
     */
	protected boolean haScartato = false;

    /**
     * Indice del giocatore, utilizzato per determinare l'ordine di gioco e
     * altre operazioni.
     */
	private int indice;

	/**
	 * Costruttore per l'AbstractGiocatore.
	 */
	public AbstractGiocatore() {
		this.mano = new ArrayList<>();
		carteIniziali = 10;
		carteSulTavolo = new ArrayList<>(Collections.nCopies(carteIniziali, null));
		this.mazzo = MazzoDiCarte.getInstance();
	}

	/**
	 * Restituisce il mazzo.
	 *
	 * @return mazzo del giocatore.
	 */
	public MazzoDiCarte getMazzo() {
		return this.mazzo;
	}

	/**
	 * Restituisce le carte sul tavolo.
	 *
	 * @return una lista delle carte sul tavolo.
	 */
	public ArrayList<Carta> getCarteSulTavolo() {
		return this.carteSulTavolo;
	}

	/**
	 * Restituisce la mano del giocatore.
	 *
	 * @return una lista delle carte in mano.
	 */
	public ArrayList<Carta> getMano() {
		return this.mano;
	}

	/**
	 * Imposta il numero di carte iniziali.
	 *
	 * @param carteIniziali numero di carte.
	 */
	public void setCarteIniziali(int carteIniziali) {
		this.carteIniziali = carteIniziali;
	}

	/**
	 * Restituisce il numero di carte iniziali.
	 *
	 * @return il numero di carte iniziali.
	 */
	public int getCarteIniziali() {
		return this.carteIniziali;
	}

	/**
	 * Restituisce l'indice.
	 *
	 * @return indice del giocatore.
	 */
	public int getIndice() {
		return indice;
	}

	/**
     * Permette al giocatore di pescare una carta dal mazzo degli scarti. Se la pesca avviene con successo, 
     * la carta viene aggiunta alla mano del giocatore e viene riprodotta un'audio corrispondente all'azione di pesca.
     * Se il giocatore e' di tipo GiocatoreUmano, vengono aggiornate alcune variabili di stato relative al gioco.
     * In caso di errori (es. mazzo degli scarti vuoto), viene gestita un'eccezione e mostrato un messaggio di errore.
     *
     * @param mazzo Il mazzo degli scarti da cui il giocatore pescherà una carta.
     */
	public void pesca(MazzoDegliScarti mazzo) {

		StatoGioco.getInstance().setManoGiocatoreUmano(null);
		StatoGioco.getInstance().getManoGiocatoreUmano();
		// this.getProfilo().getNickname() + " sta pescando una carta dal mazzo degli scarti...
		
		SimpleAudioManager.getInstance().playSound1();

		try {
			Carta cartaPescata = mazzo.pesca(); 

			if (cartaPescata != null) {
				cartaPescata.scopriCarta();
				this.getMano().add(cartaPescata);
				InputManager.getInstance().resetInput();

				// Ultima carta scoperta dal mazzo degli scarti: StatoGioco.getInstance().getUltimaCartaScoperta());
				StatoGioco.getInstance().setScartiCarta(null);
				StatoGioco.getInstance().getScartiCarta();

				if (this instanceof GiocatoreUmano) {
					StatoGioco.getInstance().setManoGiocatoreUmano(cartaPescata);
					StatoGioco.getInstance().getManoGiocatoreUmano();
					InputManager.getInstance().resetInput();

				}

			}
		} catch (IllegalStateException e) {
			System.out.println("Errore: " + e.getMessage());
		}
	}

	/**
	 * Metodo che permette al giocatore di pescare una carta dal mazzo principale.
	 * 
	 * @param mazzo Il mazzo principale da cui il giocatore pesca.
	 */
	public void pesca(MazzoDiCarte mazzo) {
		// this.getProfilo().getNickname() + " sta pescando una carta dal mazzo...
		SimpleAudioManager.getInstance().playSound1();
		
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Carta cartaPescata = mazzo.pesca();

		if (cartaPescata != null) {
			cartaPescata.scopriCarta();
			this.getMano().add(cartaPescata);
			InputManager.getInstance().resetInput();

			StatoGioco.getInstance().setUltimaCartaScoperta(mazzo.pesca());
			StatoGioco.getInstance().getUltimaCartaScoperta();

			if (this instanceof GiocatoreUmano) {
				StatoGioco.getInstance().setManoGiocatoreUmano(cartaPescata);
				StatoGioco.getInstance().getManoGiocatoreUmano();
				InputManager.getInstance().resetInput();
			}

			// Ultima carta scoperta : " StatoGioco.getInstance().getUltimaCartaScoperta()
		}
	}

	/**
	 * Metodo per verificare la carta in cima al mazzo degli scarti.
	 * 
	 * @return La carta in cima al mazzo degli scarti, o null se il mazzo e' vuoto.
	 */
	public Carta verificaMazzoScarti() {
		return MazzoDegliScarti.getInstance().vuoto() ? null : MazzoDegliScarti.getInstance().getCartaInCima();
	}

	/**
	 * Controlla se la carta passata come parametro non ha un'azione di scarto
	 * associata.
	 * 
	 * @param cartaInCimaMazzoScarti La carta da verificare.
	 * @return true se la carta non ha un'azione di scarto, altrimenti false.
	 */
	public boolean isCartaSenzaAzioneScarto(Carta cartaInCimaMazzoScarti) {
		return cartaInCimaMazzoScarti != null && !(cartaInCimaMazzoScarti.getAzione() instanceof AzioneScarto);
	}

	/**
	 * Restituisce il profilo del giocatore.
	 *
	 * @return il profilo del giocatore.
	 */
	public Profilo getProfilo() {
		return this.profilo;
	}

	/**
	 * Imposta il profilo del giocatore.
	 *
	 * @param profilo profilo del giocatore.
	 */
	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}
}
