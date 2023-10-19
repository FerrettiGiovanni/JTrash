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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import JTrash.controller.audio.SimpleAudioManager;
import JTrash.model.player.AbstractGiocatore;
import JTrash.model.player.GiocatoreUmano;

/**
 * La classe Partita gestisce una singola partita di JTrash, occupandosi
 * dell'inizializzazione, dell'assegnazione dei profili, dello svolgimento dei
 * set e della determinazione del vincitore.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class Partita {

	/**
     * Gestisce il flusso e la logica dei singoli set all'interno della partita.
     */
	private SetManager setManager;

    /**
     * Rappresenta il giocatore che ha vinto la partita. 
     * Può essere sia un giocatore umano che una CPU.
     */
	private AbstractGiocatore vincitorePartita;

	/**
	 * Costruttore di Partita. Inizializza un nuovo SetManager.
	 */
	public Partita() {
		setManager = new SetManager();
	}

	/**
	 * Inizia una nuova partita. Si occupa dell'inizializzazione del gioco,
	 * dell'assegnazione dei profili, dello svolgimento dei set e della
	 * determinazione del vincitore.
	 */
	public void iniziaPartita() throws IOException, ClassNotFoundException {
		GameManager.getInstance().inizializzaGioco();

		assegnaProfili();
		

		List<AbstractGiocatore> vincitori = null;

		do {
			if (vincitori != null) {
				for (AbstractGiocatore vincitore : vincitori) {
					vincitore.setCarteIniziali(vincitore.getCarteIniziali() - 1);
					vincitore.getCarteSulTavolo().remove(0);
				}
			}

			setManager.raccogliERimescolaMazzi();
			setManager.iniziaSet();

			vincitori = setManager.eseguiSet();

			// I vincitori del set sono:
			for (AbstractGiocatore vincitore : vincitori) { 
				if (vincitore.getCarteIniziali() == 1 && haVintoPartita(vincitore)) {
					vincitorePartita = vincitore;
					break;
				}
			}
		} while (vincitorePartita == null);

		aggiornaProfiliPostPartita(vincitorePartita);

		// Il vincitore della partita e' vincitorePartita.getProfilo().getNickname());
		if (vincitorePartita instanceof GiocatoreUmano) {
			SimpleAudioManager.getInstance().playSound9();
		} else {
			SimpleAudioManager.getInstance().playSound8();
		}
	}

	/**
	 * Assegna i profili ai giocatori.
	 */
	private void assegnaProfili() {
		ProfiloAssigner.getInstance().assegnaProfili();
	}

	/**
	 * Determina se un giocatore ha vinto la partita.
	 * 
	 * @param vincitore Il giocatore da verificare.
	 * @return true se il giocatore ha vinto, altrimenti false.
	 */
	private boolean haVintoPartita(AbstractGiocatore vincitore) {
		return vincitore.getCarteIniziali() == 1;
	}

	/**
	 * Aggiorna i profili dei giocatori al termine della partita utilizzando uno
	 * stream.
	 * 
	 * @param vincitore Il giocatore che ha vinto la partita.
	 * @throws IOException            Se si verifica un errore di I/O durante il
	 *                                salvataggio del profilo.
	 * @throws ClassNotFoundException Se si verifica un errore di class not found
	 *                                durante il caricamento del profilo.
	 */
	private void aggiornaProfiliPostPartita(AbstractGiocatore vincitore) throws IOException, ClassNotFoundException {
		// Creazione di una lista contenente tutti i giocatori, inclusi il giocatore
		// umano e i giocatori CPU
		List<AbstractGiocatore> tuttiIGiocatori = new ArrayList<>();
		tuttiIGiocatori.add(GameManager.getInstance().getGiocatoreUmano());
		tuttiIGiocatori.addAll(GameManager.getInstance().getGiocatoriCpu());

		// Creazione di uno stream a partire dalla lista di giocatori e iterazione su
		// ciascun giocatore
		tuttiIGiocatori.stream().forEach(giocatore -> {
			// Aggiornamento del numero di partite giocate per il giocatore
			giocatore.getProfilo().setPartiteGiocate(giocatore.getProfilo().getPartiteGiocate() + 1);

			// Verifica se il giocatore e' il vincitore della partita
			if (giocatore.equals(vincitore)) {
				// Se si, incrementa il numero di partite vinte
				giocatore.getProfilo().setPartiteVinte(giocatore.getProfilo().getPartiteVinte() + 1);
			} else {
				// Altrimenti, incrementa il numero di partite perse
				giocatore.getProfilo().setPartitePerse(giocatore.getProfilo().getPartitePerse() + 1);
			}

			// Salva il profilo aggiornato del giocatore su file
			try {
				ProfiloManager.aggiornaESalvaProfilo(giocatore.getProfilo(), "profili.dat");
			} catch (ClassNotFoundException | IOException e) {
				// Gestione delle eccezioni in caso di errore durante il salvataggio del profilo
				e.printStackTrace();
			}
		});
	}

}
