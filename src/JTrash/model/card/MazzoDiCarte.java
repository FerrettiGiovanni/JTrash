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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Rappresenta un mazzo di carte utilizzato nel gioco.
 * 
 * Questa classe e' implementata come un singleton, garantendo che ne esista una
 * e soltanto una istanza nel programma.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class MazzoDiCarte {
	/** Unica istanza della classe. */
	private static MazzoDiCarte instance = null;

	/** Lista delle carte nel mazzo. */
	private List<Carta> mazzo;

	/** Indice della prossima carta da estrarre. */
	private int cursore;

	/**
	 * Costruttore privato.
	 * <p>
	 * Essendo una classe singleton, non può essere istanziata direttamente.
	 * </p>
	 */
	private MazzoDiCarte() {
		mazzo = new ArrayList<>();
	}

	/**
	 * Metodo per ottenere l'istanza del mazzo di carte.
	 * 
	 * @return L'istanza singleton di MazzoDiCarte.
	 */
	public static MazzoDiCarte getInstance() {
		if (instance == null) {
			instance = new MazzoDiCarte();
		}
		return instance;
	}

	/**
	 * Inizializza il mazzo di carte utilizzando STREAM.
	 * 
	 * @param numeroDiGiocatoriCpu Il numero di giocatori CPU per decidere il numero
	 *                             di mazzi.
	 */
	public void inizializza(int numeroDiGiocatoriCpu) {
		// Ottiene l'elenco di tutte le carte possibili
		Carte[] carteValues = Carte.values();

		// Svuota il mazzo corrente
		mazzo.clear();

		// Determina il numero di mazzi da creare in base al numero di giocatori CPU
		int numeroDiMazzi = numeroDiGiocatoriCpu == 1 ? 1 : 2;

		// genero le carte e le aggiungo al mazzo
		mazzo.addAll(
				// Crea uno Stream di interi da 0 a numeroDiMazzi - 1
				IntStream.range(0, numeroDiMazzi)
						// Per ogni numero nel range, crea uno Stream di carte utilizzando .map
						.mapToObj(i -> Arrays.stream(carteValues).map(Carta::new))
						// Appiattisci gli Stream di carte in un singolo Stream
						.flatMap(cardStream -> cardStream)
						// Raccogli tutte le carte in una lista
						.collect(Collectors.toList()));
	}

	/**
	 * Raccoglie e rimescola le carte nel mazzo usando STREAM.
	 */
	public void raccogliERimescola() {
		cursore = 0;

		// Utilizza Stream API per impostare tutte le carte come non scoperte
		mazzo.stream().forEach(carta -> carta.setScoperta(false));

		// Rimescola il mazzo
		Collections.shuffle(mazzo);
	}

	/**
	 * Estrae la prossima carta dal mazzo.
	 * 
	 * @return La carta estratta o null se non ci sono altre carte.
	 */
	public Carta pesca() {
		if (cursore < mazzo.size()) {
			return mazzo.get(cursore++);
		} else {
			return null;
		}
	}

	/**
	 * Aggiunge un insieme di carte al mazzo.
	 * 
	 * @param carte L'elenco delle carte da aggiungere.
	 */
	public void aggiungiCarte(List<Carta> carte) {
		this.mazzo.addAll(carte);
	}

	/**
	 * Ottiene il numero di carte nel mazzo.
	 * 
	 * @return Il numero di carte nel mazzo.
	 */
	public int getNumeroDiCarte() {
		return mazzo.size();
	}
}
