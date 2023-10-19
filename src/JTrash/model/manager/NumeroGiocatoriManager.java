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

import JTrash.model.observable.StatoGioco;

/**
 * Questa classe gestisce il numero dei giocatori CPU nel gioco. Fornisce metodi
 * per ottenere e impostare il numero di giocatori CPU, e utilizza il pattern
 * Singleton per assicurarsi che ci sia solo una istanza di
 * NumeroGiocatoriManager in esecuzione.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class NumeroGiocatoriManager {

	/**
     * Istanza singleton di NumeroGiocatoriManager. Questo pattern garantisce che esista 
     * una sola istanza di NumeroGiocatoriManager nell'intera applicazione.
     */
	private static NumeroGiocatoriManager instance;

    /**
     * Numero di giocatori CPU presenti nel gioco.
     */
	private int numeroCpu;

	/**
	 * Costruttore privato per prevenire la creazione diretta di istanze di
	 * NumeroGiocatoriManager.
	 */
	private NumeroGiocatoriManager() {
	}

	/**
	 * Restituisce l'istanza esistente di NumeroGiocatoriManager o ne crea una nuova
	 * se non ne esiste una.
	 *
	 * @return L'istanza corrente di NumeroGiocatoriManager.
	 */
	public static NumeroGiocatoriManager getInstance() {
		if (instance == null) {
			instance = new NumeroGiocatoriManager();
		}
		return instance;
	}

	/**
	 * Ottiene il numero di giocatori CPU.
	 *
	 * @return Il numero di giocatori CPU.
	 */
	public int getNumeroCpu() {
		return numeroCpu;
	}

	/**
	 * Imposta il numero di giocatori CPU e aggiorna lo stato del gioco.
	 *
	 * @param numeroCpu Il numero di giocatori CPU da impostare.
	 * @throws IllegalArgumentException Se il numero di giocatori CPU non e' valido
	 *                                  (meno di 1 o più di 3).
	 */
	public void setNumeroCpu(int numeroCpu) {
		if (numeroCpu < 1 || numeroCpu > 3) {
			throw new IllegalArgumentException("Numero di CPU non valido");
		}
		this.numeroCpu = numeroCpu; // il numero di giocatori totali e' numero cpu + 1
		StatoGioco.getInstance().setNumeroGiocatori(this.numeroCpu + 1); // Aggiorna lo stato di gioco.
	}
}
