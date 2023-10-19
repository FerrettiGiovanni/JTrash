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

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import JTrash.model.data.Profilo;
import JTrash.model.observable.StatoGioco;
import JTrash.model.player.GiocatoreUmano;

/**
 * La classe ProfiloAssigner si occupa dell'assegnazione dei profili ai vari
 * giocatori, sia umani che CPU, durante una partita.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class ProfiloAssigner {

	/**
     * Istanza singleton di ProfiloAssigner. Questo pattern garantisce che esista 
     * una sola istanza di ProfiloAssigner nell'intera applicazione.
     */
	private static ProfiloAssigner instance;

    /**
     * Lista di tutti i profili disponibili per l'assegnazione ai giocatori.
     */
	private List<Profilo> profili;

    /**
     * Oggetto utilizzato per generare numeri casuali, ad esempio per scegliere 
     * casualmente i profili da assegnare.
     */
	private Random rand;

	/**
	 * Costruttore privato per il pattern Singleton. Inizializza l'elenco dei
	 * profili da un file e genera un oggetto Random.
	 */
	private ProfiloAssigner() {
		rand = new Random();

		try {
			profili = ProfiloManager.caricaTuttiIProfili("profili.dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ottiene l'istanza corrente di ProfiloAssigner. Se non esiste, ne crea una
	 * nuova.
	 * 
	 * @return l'istanza corrente di ProfiloAssigner
	 */
	public static ProfiloAssigner getInstance() {
		if (instance == null) {
			instance = new ProfiloAssigner();
		}
		return instance;
	}

	/**
	 * Assegna un profilo al giocatore umano e aggiorna l'elenco dei profili
	 * disponibili utilizzando Stream. Questo metodo assegna un profilo al giocatore
	 * umano, aggiunge il profilo alla lista degli elenchi dei profili disponibili e
	 * rimuove il profilo assegnato dalla lista degli elenchi dei profili
	 * disponibili utilizzando le stream.
	 * 
	 * @param profilo Il profilo da assegnare al giocatore umano.
	 */
	public void setProfiloGiocatoreUmano(Profilo profilo) {
		// Assegna il profilo al giocatore umano tramite GameManager e GiocatoreUmano
		GameManager.getInstance().getGiocatoreUmano().setProfilo(profilo);
		GiocatoreUmano.getInstance().setProfilo(profilo);

		// Aggiunge il profilo alla lista degli elenchi dei profili disponibili
		StatoGioco.getInstance().aggiungiProfiloGiocatore(profilo);

		// Utilizza uno stream per rimuovere il profilo assegnato dalla lista degli
		// elenchi dei profili disponibili
		profili = profili.stream().filter(p -> !p.equals(profilo)) // Filtra i profili diversi dal profilo assegnato
				.collect(Collectors.toList()); // Raccoglie i profili filtrati in una nuova lista
	}

	/**
	 * Assegna profili casuali ai giocatori CPU.
	 */
	public void assegnaProfili() {
		Profilo profiloUmano = GameManager.getInstance().getGiocatoreUmano().getProfilo();

		GameManager.getInstance().getGiocatoriCpu().forEach(giocatoreCpu -> {
			if (profili.isEmpty()) {
				throw new IllegalStateException("Non ci sono profili disponibili da assegnare.");
			}

			Profilo profiloCasuale = prendiProfiloCasuale();
			while (profiloCasuale.equals(profiloUmano) || profiloEAssegnato(profiloCasuale)) {
				profiloCasuale = prendiProfiloCasuale();
			}

			giocatoreCpu.setProfilo(profiloCasuale);
			StatoGioco.getInstance().aggiungiProfiloGiocatore(profiloCasuale);
			profili.remove(profiloCasuale);
		});
	}

	/**
	 * Seleziona un profilo in modo casuale dall'elenco dei profili disponibili.
	 * 
	 * @return un profilo selezionato casualmente
	 */
	private Profilo prendiProfiloCasuale() {
		return profili.get(rand.nextInt(profili.size()));
	}

	/**
	 * Verifica se un profilo e' stato assegnato a uno dei giocatori (umano o CPU)
	 * nel gioco.
	 *
	 * @param profilo Il profilo da verificare.
	 * @return true se il profilo e' stato assegnato a uno dei giocatori, altrimenti
	 *         false.
	 */
	private boolean profiloEAssegnato(Profilo profilo) {
		// Verifica se il profilo e' stato assegnato al giocatore umano
		if (GameManager.getInstance().getGiocatoreUmano().getProfilo().equals(profilo)) {
			return true;
		}

		// Utilizza uno stream per verificare se il profilo e' stato assegnato a uno dei
		// giocatori CPU
		return GameManager.getInstance().getGiocatoriCpu().stream()
				// Filtra i giocatori CPU che hanno un profilo diverso da null e che corrisponde
				// al profilo dato
				.anyMatch(cpu -> cpu.getProfilo() != null && cpu.getProfilo().equals(profilo));
	}

}
