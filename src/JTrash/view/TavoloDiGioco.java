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

package JTrash.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import JTrash.model.manager.NumeroGiocatoriManager;
import JTrash.model.observable.StatoGioco;

/**
 * Questa classe rappresenta il pannello iniziale del gioco JTrash. Gestisce
 * l'inizializzazione del numero di giocatori e la disposizione dei pannelli di
 * gioco.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
@SuppressWarnings("deprecation")
public class TavoloDiGioco extends JPanel implements Observer {

	/**
	 * ID seriale per la serializzazione, usato per garantire la consistenza durante la serializzazione e deserializzazione.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Rappresenta il numero di giocatori che parteciperanno al gioco.
	 */
	private int numGiocatori;


	/**
	 * Costruttore della classe ViewJTrashInitialize. Registra questa classe come
	 * osservatore dello stato di gioco. Inizializza il numero di giocatori e
	 * inizializza il layout del pannello.
	 */
	public TavoloDiGioco() {
		StatoGioco.getInstance().addObserver(this);
		inizializzaNumGiocatori();
		inizializza();
	}

	/**
	 * Ottiene il numero di giocatori attualmente impostato.
	 *
	 * @return Il numero di giocatori.
	 */
	public int getNumGiocatori() {
		return numGiocatori;
	}

	/**
	 * Inizializza il layout e la disposizione dei pannelli all'interno di questo
	 * pannello.
	 */
	private void inizializza() {
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(600, 450));

		// Imposta il numero di giocatori in base alle impostazioni correnti
		this.inizializzaNumGiocatori();

		JPanel contentPanel = new JPanel(new BorderLayout());

		// Aggiunge il pannello laterale
		SideBar sideBar = new SideBar(numGiocatori);
		this.add(sideBar, BorderLayout.EAST);

		// Aggiunge il pannello centrale
		CentralePanel centralePanel = new CentralePanel();
		this.add(centralePanel, BorderLayout.CENTER);

		contentPanel.add(centralePanel, BorderLayout.CENTER);

		// Aggiunge il pannello di sfondo
		SfondoPanel sfondoPanel = new SfondoPanel();
		centralePanel.add(sfondoPanel, BorderLayout.CENTER);

		this.add(contentPanel, BorderLayout.CENTER);

		// Aggiunge i pannelli vuoti e dei giocatori
		PannelliVuoti pannelliVuoti = new PannelliVuoti();
		JPanel[] panels = pannelliVuoti.creaPannelliVuoti();

		GiocatoriPanels giocatoriPanels = new GiocatoriPanels();
		JPanel[] panelGiocatori = giocatoriPanels.createGiocatoriPanels(numGiocatori);

		sfondoPanel.add(panels[0]);
		sfondoPanel.add(panelGiocatori[1]);
		sfondoPanel.add(panels[1]);
		sfondoPanel.add(panelGiocatori[2]);

		// Aggiunge il pannello del mazzo e degli scarti
		MazzoEScartiPanel mazzoEScartiPanel = new MazzoEScartiPanel();
		JPanel panelMazzoEScarti = mazzoEScartiPanel.createMazzoEScartiPanel();
		sfondoPanel.add(panelMazzoEScarti);

		sfondoPanel.add(panelGiocatori[3]);
		sfondoPanel.add(panels[2]);
		sfondoPanel.add(panelGiocatori[0]);

		// Aggiunge il pannello della mano del giocatore
		ManoGiocatoreUmanoPanel manoGiocatoreUmanoPanel = new ManoGiocatoreUmanoPanel();
		JPanel panelManoGiocatore1 = manoGiocatoreUmanoPanel.creaPannelloManoGiocatore();
		sfondoPanel.add(panelManoGiocatore1);
	}

	/**
	 * Inizializza il numero di giocatori in base alle impostazioni correnti.
	 */
	public void inizializzaNumGiocatori() {
		setNumGiocatori(NumeroGiocatoriManager.getInstance().getNumeroCpu() + 1);
	}

	/**
	 * Imposta il numero di giocatori.
	 *
	 * @param numGiocatori Il numero di giocatori da impostare.
	 */
	public void setNumGiocatori(int numGiocatori) {
		this.numGiocatori = numGiocatori;
	}

	/**
	 * Gestisce gli aggiornamenti dello stato di gioco. Aggiorna il numero di
	 * giocatori in base allo stato di gioco.
	 *
	 * @param o   L'oggetto osservato (StatoGioco).
	 * @param arg L'argomento passato dall'oggetto osservato.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof StatoGioco) {
			StatoGioco statoGioco = (StatoGioco) o;
			this.setNumGiocatori(statoGioco.getNumeroGiocatori());
			// Aggiungi qui ulteriori operazioni se necessario...
		}
	}
}