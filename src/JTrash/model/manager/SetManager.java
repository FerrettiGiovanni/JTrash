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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import JTrash.controller.audio.SimpleAudioManager;
import JTrash.model.card.Carta;
import JTrash.model.card.MazzoDiCarte;
import JTrash.model.observable.StatoGioco;
import JTrash.model.player.AbstractGiocatore;
import JTrash.model.player.GiocatoreCpu;

/**
 * Classe responsabile della gestione dei set di gioco.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class SetManager {

	/**
	 * Raccoglie e rimescola le carte nel mazzo.
	 */
	public void raccogliERimescolaMazzi() {
		GameManager.getInstance().getMazzo().raccogliERimescola();
		SimpleAudioManager.getInstance().playSound2();
		// Il mazzo e' stato mescolato
		
		StatoGioco.getInstance().pulisciCarteSulTavoloGiocatori();
		StatoGioco.getInstance().getUltimaCartaScoperta();
		StatoGioco.getInstance().setScartiCarta(null);
	}

	/**
	 * Avvia un nuovo set di gioco.
	 */
	public void iniziaSet() {
		StatoGioco.getInstance().pulisciCarteSulTavoloGiocatori();
		// Ho resettato le carte sul tavolo prima di iniziare il nuovo set
		
		GameManager.getInstance().getGiocatoreUmano().iniziaGioco();
		StatoGioco.getInstance().setUltimaCartaScoperta(MazzoDiCarte.getInstance().pesca());

		for (GiocatoreCpu giocatoreCpu : GameManager.getInstance().getGiocatoriCpu()) {
			giocatoreCpu.iniziaGioco();
		
		}
	}

	/**
	 * Recupera una lista di tutti i giocatori.
	 * 
	 * @return Una lista contenente tutti i giocatori.
	 */
	private List<AbstractGiocatore> getGiocatori() {
		List<AbstractGiocatore> giocatori = new ArrayList<>();
		giocatori.add(GameManager.getInstance().getGiocatoreUmano());
		giocatori.addAll(GameManager.getInstance().getGiocatoriCpu());
		return giocatori;
	}

	/**
	 * Esegue un set di gioco e determina i vincitori.
	 * 
	 * @return Una lista dei giocatori vincitori.
	 */
	public List<AbstractGiocatore> eseguiSet() {
		List<AbstractGiocatore> vincitori = new ArrayList<>();
		List<AbstractGiocatore> giocatori = getGiocatori();
		List<AbstractGiocatore> giocatoriFinalTurn = new ArrayList<>(giocatori);
		boolean vincitoreTrovato = false;

		while (vincitori.isEmpty() || !giocatoriFinalTurn.isEmpty()) {
			for (AbstractGiocatore giocatore : giocatori) {
				if (!vincitoreTrovato || giocatoriFinalTurn.contains(giocatore)) {
					StatoGioco.getInstance().setManoGiocatoreUmano(null);
					StatoGioco.getInstance().getManoGiocatoreUmano();
					Turno turno = new Turno(giocatore, GameManager.getInstance());
					turno.eseguiTurno();

					if (!vincitoreTrovato && giocatoreVincitore(giocatore)) {
						SimpleAudioManager.getInstance().playSound7();
						vincitoreTrovato = true;
					}

					if (vincitoreTrovato) {
						giocatoriFinalTurn.remove(giocatore);
					}

					if (vincitoreTrovato && giocatoriFinalTurn.isEmpty()) {
						vincitori = determinaVincitoriSet();
						break;
					}
				}
			}
		}

		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return vincitori;
	}

	/**
	 * Controlla se un giocatore ha vinto.
	 * 
	 * @param giocatore Il giocatore da verificare.
	 * @return `true` se il giocatore ha vinto, altrimenti `false`.
	 */
	private boolean giocatoreVincitore(AbstractGiocatore giocatore) {
		return giocatore.getCarteSulTavolo().stream().allMatch(Carta::isScoperta);
	}

	/**
	 * Determina i vincitori del set attuale.
	 * 
	 * @return Una lista di giocatori che hanno vinto il set.
	 */
	private List<AbstractGiocatore> determinaVincitoriSet() {
		
		/*
		 * Ottieni la lista di tutti i giocatori, inclusi il giocatore umano e i 
		 * giocatori CPU.
		 */
		List<AbstractGiocatore> giocatori = getGiocatori();

		/*
		 * Utilizza una stream per filtrare i giocatori in base a una condizione.
		 * La condizione e' che tutte le carte sul tavolo del giocatore siano scoperte.
		 * (isScoperta() restituisce true per tutte le carte).
		 */
		List<AbstractGiocatore> vincitori = giocatori.stream() 
				/*
				 * giocatori.stream() inizia una stream sulla lista giocatori.
				 */

				.filter(giocatore -> giocatore.getCarteSulTavolo().stream().allMatch(Carta::isScoperta))
				/*
				 * E' un'operazione di filtro che applica una condizione a ciascun elemento
				 * della stream. La condizione e' definita tramite la lambda giocatore ->
				 * giocatore.getCarteSulTavolo().stream().allMatch(Carta::isScoperta). Questa
				 * lambda prende ogni giocatore nella stream, quindi giocatore e' una variabile
				 * che rappresenta un elemento della lista giocatori.
				 */
				.collect(Collectors.toList());

		/* Restituisce la lista dei vincitori del set.*/
		return vincitori;
	}

}
