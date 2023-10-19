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

package JTrash.controller.util;

/**
 * Classe per gestire gli input utente all'interno del gioco.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class InputManager {

	/**
     * Istanza singleton di InputManager. Questo pattern garantisce che esista una sola 
     * istanza di InputManager nell'intera applicazione.
     */
	private static InputManager instance;

    /**
     * Flag che indica se l'utente ha pescato dal mazzo principale.
     */
	private boolean haPescatoDalMazzoPrincipale;

    /**
     * Oggetto utilizzato come lock per la sincronizzazione.
     * Può essere utilizzato per garantire che solo un thread per volta 
     * acceda a risorse o sezioni di codice specifiche.
     */
	private final Object lock = new Object();

    /**
     * Numero associato al pulsante del giocatore premuto.
     * Un valore di -1 indica che nessun pulsante e' stato premuto.
     */
	private int buttonGiocatoreNumero = -1;

    /**
     * Flag che indica se il pulsante degli scarti e' stato premuto.
     */
	private boolean buttonScartiPremuto;

    /**
     * Memorizza l'ultimo input rilevato dal giocatore.
     */
	private TipoInput ultimoInput = TipoInput.NESSUN_INPUT;

    /**
     * Posizione della carta selezionata dal giocatore.
     * Un valore di -1 indica che nessuna carta e' stata selezionata.
     */
	@SuppressWarnings("unused")
	private int posizioneCartaSelezionata = -1;

    /**
     * Flag che indica se l'utente ha scartato una carta.
     */
	@SuppressWarnings("unused")
	private boolean haScartato;

	/**
	 * Costruttore privato per impedire l'istanziazione dall'esterno.
	 */
	private InputManager() {
		haPescatoDalMazzoPrincipale = true; // Valore di default
	}

	/**
	 * Ottiene l'istanza singleton di InputManager.
	 * 
	 * @return L'istanza singleton di InputManager.
	 */
	public static InputManager getInstance() {
		if (instance == null) {
			synchronized (InputManager.class) {
				if (instance == null) {
					instance = new InputManager();
				}
			}
		}
		return instance;
	}

	/**
	 * Imposta la scelta dell'utente riguardo al pescaggio dal mazzo principale.
	 * 
	 * @param value Valore booleano della scelta dell'utente.
	 */
	public void setHaPescatoDalMazzoPrincipale(boolean value) {
		synchronized (lock) {
			haPescatoDalMazzoPrincipale = value;
			lock.notifyAll(); // Notifica i thread in attesa dell'input
		}
	}

	/**
	 * Attende l'input dell'utente relativo al pescaggio.
	 * 
	 * @return Il valore booleano della scelta dell'utente.
	 */
	public boolean attendiInputPescata() {
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return haPescatoDalMazzoPrincipale;
		}
	}

	/**
	 * Imposta il valore dell'azione di scarto dell'utente.
	 * 
	 * @param value Valore booleano dell'azione di scarto.
	 */
	public void setHaScartato(boolean value) {
		synchronized (lock) {
			haScartato = value;
			lock.notify();
		}
	}

	/**
	 * Imposta la posizione della carta selezionata dall'utente.
	 * 
	 * @param posizione Posizione della carta selezionata.
	 */
	public void setPosizioneCartaSelezionata(int posizione) {
		synchronized (lock) {
			posizioneCartaSelezionata = posizione;
			lock.notifyAll();
		}
	}

	/**
	 * Attende l'input dell'utente relativo al pulsante del giocatore.
	 * 
	 * @return Il numero associato al pulsante del giocatore premuto.
	 */
	public int attendiInputButtonGiocatore() {
		synchronized (lock) {
			try {
				while (buttonGiocatoreNumero == -1) {
					lock.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int numero = buttonGiocatoreNumero;
			buttonGiocatoreNumero = -1;
			return numero;
		}
	}

	/**
	 * Attende l'input dell'utente relativo al pulsante degli scarti.
	 * 
	 * @return Il valore booleano indicante se il pulsante degli scarti e' stato
	 *         premuto.
	 */
	public boolean attendiInputButtonScarti() {
		synchronized (lock) {
			try {
				while (!buttonScartiPremuto) {
					lock.wait();
				}
				buttonScartiPremuto = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	/**
	 * Imposta il pulsante del giocatore come premuto.
	 * 
	 * @param numero Il numero associato al pulsante del giocatore.
	 */
	public void setButtonGiocatorePremuto(int numero) {
		synchronized (lock) {
			buttonGiocatoreNumero = numero;
			ultimoInput = TipoInput.BUTTON_GIOCATORE;
			lock.notifyAll();
		}
	}

	/**
	 * Imposta il pulsante degli scarti come premuto.
	 * 
	 * @param value Valore booleano indicante se il pulsante degli scarti e' stato
	 *              premuto.
	 */
	public void setButtonScartiPremuto(boolean value) {
		synchronized (lock) {
			buttonScartiPremuto = value;
			if (value) {
				ultimoInput = TipoInput.BUTTON_SCARTI;
			}
			lock.notifyAll();
		}
	}

	/**
	 * Attende un input generico dall'utente.
	 * 
	 * @return Il tipo di input ricevuto.
	 */
	public TipoInput attendiInput() {
		synchronized (lock) {
			try {
				while (ultimoInput == TipoInput.NESSUN_INPUT) {
					lock.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			TipoInput risultato = ultimoInput;
			ultimoInput = TipoInput.NESSUN_INPUT;
			return risultato;
		}
	}

	/**
	 * Reimposta tutti gli input.
	 */
	public void resetInput() {
		synchronized (lock) {
			ultimoInput = TipoInput.NESSUN_INPUT;
			buttonGiocatoreNumero = -1;
			buttonScartiPremuto = false;
		}
	}
}
