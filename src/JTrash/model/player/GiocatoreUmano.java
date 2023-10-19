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

import JTrash.controller.util.InputManager;
import JTrash.controller.util.TipoInput;
import JTrash.model.card.Carta;
import JTrash.model.card.MazzoDegliScarti;
import JTrash.model.card.MazzoDiCarte;
import JTrash.model.card.actions.AzioneJolly;
import JTrash.model.card.actions.AzioneMatch;
import JTrash.model.card.actions.AzioneScarto;
import JTrash.model.card.actions.InterfaceAction;
import JTrash.model.observable.StatoGioco;

/**
 * La classe GiocatoreUmano rappresenta un giocatore umano all'interno del
 * gioco.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class GiocatoreUmano extends AbstractGiocatore {

	/**
	 * Istanza singola (Singleton) del GiocatoreUmano. Garantisce che ci sia
	 * solo una singola istanza di GiocatoreUmano durante l'esecuzione del gioco.
	 */
	private static GiocatoreUmano instance = null;

	/**
	 * Costruttore privato per il Singleton.
	 */
	private GiocatoreUmano() {
		super();
	}

	/**
	 * Ottieni l'istanza Singleton del giocatore umano.
	 *
	 * @return L'istanza Singleton del giocatore umano.
	 */
	public static GiocatoreUmano getInstance() {
		if (instance == null) {
			instance = new GiocatoreUmano();
		}
		return instance;
	}

	/**
	 * Inizia il gioco per il giocatore umano.
	 */
	@Override
	public void iniziaGioco() {
		// Il giocatore " this.getProfilo().getNickname() ha pescato le carte dal mazzo per metterle sul tavolo...");
		this.getCarteSulTavolo().clear(); // Pulisce le carte sul tavolo prima di iniziare il gioco

		for (int i = 0; i < this.getCarteIniziali(); i++) {
			Carta cartaPescata = getMazzo().pesca();
			if (cartaPescata != null) {
				this.getCarteSulTavolo().add(cartaPescata);
			} else {
				// Devo gestire l'errore, il mazzo e' vuoto ma stiamo cercando di pescare una carta
			}
		}

		StatoGioco.getInstance().aggiungiCarteSulTavoloGiocatore(0, new ArrayList<>(this.getCarteSulTavolo()));
	}

	/**
	 * Esegue l'azione di pesca da parte del giocatore umano.
	 */
	public void pesca() {
		// Usiamo il metodo attendiInputPescata per attendere l'input dell'utente dalla
		// GUI
		boolean pescataDalMazzoPrincipale = InputManager.getInstance().attendiInputPescata();
		if (pescataDalMazzoPrincipale) {
			pesca(MazzoDiCarte.getInstance());
		} else {
			pesca(MazzoDegliScarti.getInstance());
		}
	}

	/**
	 * Decide da quale mazzo pescare una carta, in base all'input dell'utente.
	 *
	 * @param cartaInCimaMazzoScarti La carta in cima al mazzo degli scarti.
	 */
	public void decidiDaQualeMazzoPescare(Carta cartaInCimaMazzoScarti) {
		// Chiedi all'utente da quale mazzo vuole pescare usando InputManager
		boolean pescataDalMazzoPrincipale = InputManager.getInstance().attendiInputPescata();
		if (pescataDalMazzoPrincipale) {
			pesca(MazzoDiCarte.getInstance());
		} else {
			pesca(MazzoDegliScarti.getInstance());
		}
	}

	/**
	 * Gestisce l'azione associata alla carta in mano.
	 *
	 * @param cartaInMano La carta in mano al giocatore.
	 */
	public void gestisciAzioneCarta(Carta cartaInMano) {
		InterfaceAction azione = cartaInMano.getAzione();
		InputManager inputManager = InputManager.getInstance();

		if (azione instanceof AzioneMatch) {
			TipoInput tipoInput = inputManager.attendiInput();

			if (tipoInput == TipoInput.BUTTON_GIOCATORE
					&& inputManager.attendiInputButtonGiocatore() == cartaInMano.getValore().getNumero()) {
				azione.azionePosiziona(cartaInMano, this.getCarteSulTavolo(), MazzoDegliScarti.getInstance(),
						this.getMano(), this);
			} else if (tipoInput == TipoInput.BUTTON_SCARTI) {
				scartaCarta(cartaInMano);
			} else {
				inputManager.resetInput();
			}
		} else if (azione instanceof AzioneScarto) {
			boolean pressedButtonScarti = inputManager.attendiInputButtonScarti();
			if (pressedButtonScarti) {
				azione.azionePosiziona(cartaInMano, this.getCarteSulTavolo(), MazzoDegliScarti.getInstance(),
						this.getMano(), this);
			}
		} else if (azione instanceof AzioneJolly) {
			int indiceCartaSelezionata = inputManager.attendiInputButtonGiocatore() - 1;

			if (indiceCartaSelezionata >= 0 && indiceCartaSelezionata < this.getCarteSulTavolo().size()
					&& !this.getCarteSulTavolo().get(indiceCartaSelezionata).isScoperta()) {
				Carta cartaSostituita = this.getCarteSulTavolo().get(indiceCartaSelezionata);
				this.getMano().add(cartaSostituita);
				this.getMano().remove(cartaInMano);
				this.getCarteSulTavolo().set(indiceCartaSelezionata, cartaInMano);
				this.getCarteSulTavolo().get(indiceCartaSelezionata).setScoperta(true);
				StatoGioco.getInstance().aggiornaManoGiocatoreUmano(getInstance(), cartaSostituita);
				StatoGioco.getInstance().getCarteSulTavoloGiocatori();
				aggiornaStatoGioco(cartaSostituita);
			} else {
				scartaCarta(cartaInMano);
			}
		}

		inputManager.resetInput();
		aggiornaStatoGioco(cartaInMano);
	}

	/**
	 * Aggiorna lo stato del gioco con le informazioni attuali.
	 *
	 * @param cartaSostituita La carta sostituita durante l'azione.
	 */
	public void aggiornaStatoGioco(Carta cartaSostituita) {
		StatoGioco.getInstance().aggiungiCarteSulTavoloGiocatore(this.getIndice(), this.getCarteSulTavolo());
	}

	/**
	 * Scarta una carta dalla mano del giocatore e la aggiunge al mazzo degli
	 * scarti.
	 *
	 * @param cartaInMano La carta da scartare.
	 */
	public void scartaCarta(Carta cartaInMano) {
		// Rimuove la carta dalla mano del giocatore e la aggiunge al mazzo degli scarti
		this.getMano().remove(cartaInMano);
		MazzoDegliScarti.getInstance().aggiungiCarta(cartaInMano);
		StatoGioco.getInstance().setScartiCarta(MazzoDegliScarti.getInstance().getCartaInCima());
		StatoGioco.getInstance().aggiornaManoGiocatoreUmano(this, null); // Aggiorna la GUI se il giocatore e' umano

		this.haScartato = true;

		StatoGioco.getInstance().setManoGiocatoreUmano(null);
	}

	/**
	 * Sostituisce una carta nella mano del giocatore.
	 *
	 * @param cartaInMano     La carta da sostituire.
	 * @param cartaSostituita La nuova carta da aggiungere alla mano.
	 */
	public void sostituisciCartaInMano(Carta cartaInMano, Carta cartaSostituita) {
		this.getMano().remove(cartaInMano); // Rimuove la vecchia carta
		this.getMano().add(cartaSostituita); // Aggiunge la nuova carta

		StatoGioco.getInstance().aggiornaManoGiocatoreUmano(this, cartaSostituita);
	}
}
