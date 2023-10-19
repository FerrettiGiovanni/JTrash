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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import JTrash.model.card.actions.AzioneJolly;
import JTrash.model.card.actions.AzioneMatch;
import JTrash.model.card.actions.AzioneScarto;
import JTrash.model.card.actions.InterfaceAction;
import JTrash.model.player.AbstractGiocatore;

/*
 * Fra Carte e Carta c'e' una relazione di COMPOSIZIONE in quanto Carta ha un campo di tipo Carte.
 * E' un esempio di composizione dato che un oggetto e' formato da altri
 * Ho utilizzato lo Strategy Pattern per gestire le funzioni della carta , Questo modello permette di cambiare
 *  il comportamento di un oggetto (in questo caso, una Carta) a runtime,
 *  in base al valore della carta. Ogni volta che crei una nuova Carta, imposterai l'azione appropriata
 *  basandoti sul valore della carta.
 *
 * Pattern Strategy, dove l'azione che una Carta deve eseguire viene determinata a runtime in base al suo valore. 
 * Inoltre, si utilizza la composizione per associare un'istanza di Carte ad ogni Carta. Le azioni vengono definite 
 * attraverso l'interfaccia InterfaceAction, e la specifica implementazione viene selezionata a runtime nel metodo 
 * setInterfaceAction()
 */

/**
 * Rappresenta una singola carta del gioco JTrash. Questa classe utilizza il
 * pattern Strategy per determinare l'azione che una Carta deve eseguire in base
 * al suo valore. Inoltre, si utilizza una relazione di composizione con la
 * classe Carte.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class Carta implements InterfaceAction {
	/**
	 * Rappresenta una composizione con la classe Carte
	 */
	private final Carte carte;
	
	/**
	 * Campo che implementa l'interfaccia InterfaceAction
	 */
	private InterfaceAction azione; 
	
	/**
	 * Flag per tenere traccia se la carta e' scoperta o no
	 */
	private boolean scoperta;

	/**
	 * Costruisce una nuova carta.
	 * 
	 * @param carte Il tipo di carta.
	 */
	public Carta(Carte carte) {
		this.carte = carte;
		this.scoperta = false; // Di default, la carta e' coperta
		setInterfaceAction(); // Imposta l'azione basandosi sul valore della carta
	}

	/**
	 * Imposta l'azione della carta in base al suo valore.
	 */
	public void setInterfaceAction() {
		// Creazione di set di valori per le diverse azioni
		Set<Valori> matchValori = new HashSet<>(Arrays.asList(Valori.ASSO, Valori.DUE, Valori.TRE, Valori.QUATTRO,
				Valori.CINQUE, Valori.SEI, Valori.SETTE, Valori.OTTO, Valori.NOVE, Valori.DIECI));
		Set<Valori> scartoValori = new HashSet<>(Arrays.asList(Valori.JACK, Valori.QUEEN));

		// Recupero del valore della carta
		Valori cartaValori = carte.getValore();

		// Impostazione dell'azione basandosi sul valore della carta
		if (matchValori.contains(cartaValori)) {
			azione = new AzioneMatch();
		} else if (scartoValori.contains(cartaValori)) {
			azione = new AzioneScarto();
		} else {
			azione = new AzioneJolly();
		}
	}

	/**
	 * @return True se la carta e' scoperta, false altrimenti.
	 */
	public boolean isScoperta() {
		return scoperta;
	}

	/**
	 * Imposta lo stato della carta come scoperta o coperta.
	 * 
	 * @param scoperta True se la carta deve essere impostata come scoperta, false
	 *                 altrimenti.
	 */
	public void setScoperta(boolean scoperta) {
		this.scoperta = scoperta;
	}

	/**
	 * Segna la carta come scoperta e stampa un messaggio di notifica.
	 */
	public void scopriCarta() {
		setScoperta(true); // scopri la carta
	}

	/**
	 * @return L'azione associata alla carta.
	 */
	public InterfaceAction getAzione() {
		return this.azione;
	}

	/**
	 * @return Una rappresentazione stringa della carta.
	 */
	@Override
	public String toString() {
		return carte.toString();
	}

	/**
	 * @return Il valore della carta.
	 */
	public Valori getValore() {
		return this.carte.getValore();
	}

	@Override
	public Carta azionePosiziona(Carta cartaInMano, List<Carta> carteSulTavolo, MazzoDegliScarti mazzoDegliScarti,
			List<Carta> mano, AbstractGiocatore giocatore) {
		// TODO Implementare il comportamento appropriato
		return null;
	}
}