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

import JTrash.controller.audio.SimpleAudioManager;
import JTrash.model.card.MazzoDegliScarti;
import JTrash.model.card.MazzoDiCarte;
import JTrash.model.card.MazzoFactory;
import JTrash.model.player.GiocatoreCpu;
import JTrash.model.player.GiocatoreCpuFactory;
import JTrash.model.player.GiocatoreUmano;

/**
 * Questa classe e' responsabile della gestione del gioco. Si occupa
 * dell'inizializzazione del gioco, della creazione dei giocatori (sia umani che
 * CPU), della creazione del mazzo di carte e del mazzo degli scarti. Utilizza
 * il pattern Singleton per assicurarsi che ci sia solo una istanza di
 * GameManager in esecuzione.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class GameManager {

	/**
     * Istanza singleton di GameManager. Questo pattern garantisce che esista una sola 
     * istanza di GameManager nell'intera applicazione.
     */
	private static GameManager instance;

    /**
     * Lista dei giocatori CPU presenti nel gioco.
     */
	private List<GiocatoreCpu> giocatoriCpu;

    /**
     * Il mazzo principale di carte utilizzato nel gioco.
     */
	private MazzoDiCarte mazzo;

	/**
	 * Costruttore privato per prevenire la creazione diretta di istanze di
	 * GameManager.
	 */
	private GameManager() {
	}

	/**
	 * Questo metodo restituisce l'istanza esistente di GameManager o ne crea una
	 * nuova se non ne esiste una.
	 * 
	 * @return L'istanza corrente di GameManager.
	 */
	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}

	/**
	 * Inizializza il gioco. Crea il mazzo di carte, il mazzo degli scarti, e crea
	 * il giocatore umano e i giocatori CPU.
	 */
	public void inizializzaGioco() {
		SimpleAudioManager.getInstance().playGameMusic();

		int numeroCpu = NumeroGiocatoriManager.getInstance().getNumeroCpu();

		mazzo = MazzoFactory.creaMazzo(numeroCpu);
		MazzoDegliScarti.getInstance();
		GiocatoreUmano.getInstance();

		// Utilizza il metodo Factory per creare i giocatori CPU.
		giocatoriCpu = GiocatoreCpuFactory.creaGiocatoriCpu(numeroCpu);
	}

	/**
	 * Restituisce l'istanza del giocatore umano.
	 * 
	 * @return L'istanza di GiocatoreUmano.
	 */
	public GiocatoreUmano getGiocatoreUmano() {
		return GiocatoreUmano.getInstance();
	}

	/**
	 * Restituisce la lista dei giocatori CPU.
	 * 
	 * @return La lista dei giocatori CPU.
	 */
	public List<GiocatoreCpu> getGiocatoriCpu() {
		return giocatoriCpu;
	}

	/**
	 * Restituisce l'istanza del mazzo di carte.
	 * 
	 * @return L'istanza di MazzoDiCarte.
	 */
	public MazzoDiCarte getMazzo() {
		return mazzo;
	}
}
