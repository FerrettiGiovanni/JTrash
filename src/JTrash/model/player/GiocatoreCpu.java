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

import JTrash.model.card.Carta;
import JTrash.model.card.MazzoDegliScarti;
import JTrash.model.card.MazzoDiCarte;
import JTrash.model.card.actions.AzioneScarto;
import JTrash.model.card.actions.InterfaceAction;
import JTrash.model.observable.StatoGioco;

/**
 * Rappresenta il giocatore gestito dalla CPU.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class GiocatoreCpu extends AbstractGiocatore {

	/**
	 * Indice del giocatore CPU, utilizzato per identificare o ordinare i giocatori CPU.
	 */
	private final int indice;

	/**
	 * Indica se il giocatore CPU ha scartato una carta durante il turno corrente.
	 */
	private boolean haScartato;

	/**
	 * Costruttore del giocatore CPU.
	 * 
	 * @param indice L'indice del giocatore CPU.
	 */
	public GiocatoreCpu(int indice) {
		super();
		this.indice = indice;
	}

	/**
	 * Ottieni l'indice del giocatore.
	 * 
	 * @return L'indice del giocatore.
	 */
	public int getIndice() {
		return this.indice;
	}

	@Override
	/**
	 * Inizia il gioco per il giocatore CPU.
	 */
	public void iniziaGioco() {
		// Il giocatore this.getProfilo().getNickname() ha pescato le carte dal mazzo per metterle sul tavolo...");
		this.getCarteSulTavolo().clear(); // Pulisce le carte sul tavolo prima di iniziare il gioco

		for (int i = 0; i < this.getCarteIniziali(); i++) {

			Carta cartaPescata = getMazzo().pesca();
			if (cartaPescata != null) {
				this.getCarteSulTavolo().add(cartaPescata);
			} else {
			}
		}

		StatoGioco.getInstance().aggiungiCarteSulTavoloGiocatore(this.getIndice(),
				new ArrayList<>(this.getCarteSulTavolo()));
	}

	/**
	 * Il giocatore CPU pesca una carta.
	 */
	public void pesca() {
		StatoGioco.getInstance().setManoGiocatoreUmano(null);
		StatoGioco.getInstance().getManoGiocatoreUmano();

		pesca(MazzoDiCarte.getInstance());
	}

	/**
	 * Decide da quale mazzo il giocatore CPU deve pescare in base alla carta in
	 * cima al mazzo degli scarti.
	 * 
	 * @param cartaInCimaMazzoScarti La carta in cima al mazzo degli scarti.
	 */
	public void decidiDaQualeMazzoPescare(Carta cartaInCimaMazzoScarti) {
		boolean cartaTrovataNelTavolo = cercaCartaSulTavolo(cartaInCimaMazzoScarti);
		if (cartaTrovataNelTavolo) {
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			pesca(MazzoDegliScarti.getInstance());
			
		} else {
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			pesca(MazzoDiCarte.getInstance());
		}
	}

	/**
	 * Cerca la carta sul tavolo e restituisce true se trovata.
	 * 
	 * @param cartaInCimaMazzoScarti La carta in cima al mazzo degli scarti.
	 * @return True se la carta viene trovata sul tavolo, altrimenti false.
	 */
	private boolean cercaCartaSulTavolo(Carta cartaInCimaMazzoScarti) {
		return getCarteSulTavolo().stream().filter(cartaSulTavolo -> !cartaSulTavolo.isScoperta())
				.anyMatch(cartaSulTavolo -> cartaInCimaMazzoScarti.getValore()
						.getNumero() == getCarteSulTavolo().indexOf(cartaSulTavolo) + 1);
	}

	/**
	 * Gestisce l'azione associata alla carta in mano.
	 *
	 * @param cartaInMano La carta da gestire.
	 */
	public void gestisciAzioneCarta(Carta cartaInMano) {
		InterfaceAction azione = cartaInMano.getAzione();

		// Esegui l'azione e ottieni la carta sostituita (se esiste)
		Carta cartaSostituita = azione.azionePosiziona(cartaInMano, this.getCarteSulTavolo(),
				MazzoDegliScarti.getInstance(), this.getMano(), this);

		// Se non c'e' carta sostituita o l'azione e' AzioneScarto
		if (cartaSostituita == null || azione instanceof AzioneScarto) {
			if (!this.haScartato) {
				scartaCarta(cartaInMano);

				// Scarta la carta se non e' già stato fatto
			}
		} else {
			sostituisciCartaInMano(cartaInMano, cartaSostituita);
			StatoGioco.getInstance().setManoGiocatoreUmano(null);
			StatoGioco.getInstance().getManoGiocatoreUmano();

			// Altrimenti sostituisci la carta in mano
		}

		aggiornaStatoGioco(cartaSostituita); // Aggiorna lo stato del gioco con le informazioni attuali
	}

	/**
	 * Aggiorna lo stato del gioco con le informazioni attuali.
	 *
	 * @param cartaSostituita La carta sostituita.
	 */
	public void aggiornaStatoGioco(Carta cartaSostituita) {
		StatoGioco.getInstance().aggiungiCarteSulTavoloGiocatore(this.getIndice(), this.getCarteSulTavolo());
	}

	/**
	 * Scarta una carta dalla mano del giocatore.
	 *
	 * @param cartaInMano La carta da scartare.
	 */
	public void scartaCarta(Carta cartaInMano) {
		// Rimuove la carta dalla mano del giocatore e la aggiunge al mazzo degli scarti
		this.getMano().remove(cartaInMano);
		MazzoDegliScarti.getInstance().aggiungiCarta(cartaInMano);
		StatoGioco.getInstance().setScartiCarta(MazzoDegliScarti.getInstance().getCartaInCima());
		this.haScartato = true; // Setta il flag haScartato a true
	}

	/**
	 * Sostituisci una carta in mano del giocatore con un'altra.
	 *
	 * @param cartaInMano     La carta originale.
	 * @param cartaSostituita La carta sostitutiva.
	 */
	public void sostituisciCartaInMano(Carta cartaInMano, Carta cartaSostituita) {
		this.getMano().remove(cartaInMano); // Rimuove la vecchia carta
		this.getMano().add(cartaSostituita); // Aggiunge la nuova carta
	}

}
