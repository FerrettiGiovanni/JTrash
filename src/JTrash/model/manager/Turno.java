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

package JTrash.model.manager;

import JTrash.model.card.Carta;
import JTrash.model.observable.StatoGioco;
import JTrash.model.player.AbstractGiocatore;

/**
 * Classe che rappresenta un singolo turno di gioco.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class Turno {

	/**
     * Rappresenta il giocatore che ha il turno attuale, ovvero 
     * il giocatore che deve svolgere le proprie azioni nel turno corrente.
     */
	private AbstractGiocatore giocatoreCorrente;

    /**
     * Indica se una carta e' stata scartata dal giocatore corrente durante 
     * questo turno. Questa variabile serve a tenere traccia delle azioni 
     * svolte nel corso del turno.
     */
	private boolean haScartato;

	/**
	 * Costruisce un nuovo turno.
	 * 
	 * @param giocatore   Il giocatore che ha il turno attuale.
	 * @param gameManager Il gestore del gioco.
	 */
	public Turno(AbstractGiocatore giocatore, GameManager gameManager) {
		this.giocatoreCorrente = giocatore; // Assegna il giocatore passato come parametro
		StatoGioco.getInstance();
		this.haScartato = false; // Inizializza la variabile haScartato a false
	}

	/**
	 * Esegue le azioni previste per il turno corrente.
	 */
	public void eseguiTurno() {
		haScartato = false;

		Carta cartaInCimaMazzoScarti = giocatoreCorrente.verificaMazzoScarti();

		gestisciPescaGiocatore(cartaInCimaMazzoScarti);

		processaManoGiocatore();
	}

	/**
	 * Decide se il giocatore corrente dovrebbe gestire una carta senza azione di
	 * scarto o pescare una carta dal mazzo.
	 * 
	 * @param cartaInCimaMazzoScarti La carta in cima al mazzo degli scarti.
	 */
	private void gestisciPescaGiocatore(Carta cartaInCimaMazzoScarti) {
		if (giocatoreCorrente.isCartaSenzaAzioneScarto(cartaInCimaMazzoScarti)) {
			giocatoreCorrente.decidiDaQualeMazzoPescare(cartaInCimaMazzoScarti);
		} else {
			giocatoreCorrente.pesca();
		}
	}

	/**
	 * Processa la mano del giocatore corrente, eseguendo le azioni delle carte in
	 * mano.
	 */
	private void processaManoGiocatore() {
		// Continua fino a quando il giocatore non ha scartato e ha carte in mano
		while (!haScartato && !giocatoreCorrente.getMano().isEmpty()) {
			// Ottieni l'ultima carta dalla mano del giocatore
			Carta cartaInMano = giocatoreCorrente.getMano().get(giocatoreCorrente.getMano().size() - 1);

			giocatoreCorrente.gestisciAzioneCarta(cartaInMano); // Gestisci l'azione della carta
		}
	}
}
