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

/**
 * Enumerazione dei valori delle carte da gioco.
 * 
 * Ogni valore dell'enumerazione ha associato un numero intero e una stringa
 * rappresentativa. Questa enumerazione definisce i valori tipici di un mazzo di
 * carte standard da poker: numeri da 1 (Asso) a 10, le figure (Jack, Queen,
 * King) e un jolly.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public enum Valori {

	/** Rappresenta il valore "Asso" con numero 1. */
	ASSO(1, "Asso"),

	/** Rappresenta il valore "Due" con numero 2. */
	DUE(2, "Due"),

	/** Rappresenta il valore "Tre" con numero 3. */
	TRE(3, "Tre"),

	/** Rappresenta il valore "Quattro" con numero 4. */
	QUATTRO(4, "Quattro"),

	/** Rappresenta il valore "Cinque" con numero 5. */
	CINQUE(5, "Cinque"),

	/** Rappresenta il valore "Sei" con numero 6. */
	SEI(6, "Sei"),

	/** Rappresenta il valore "Sette" con numero 7. */
	SETTE(7, "Sette"),

	/** Rappresenta il valore "Otto" con numero 8. */
	OTTO(8, "Otto"),

	/** Rappresenta il valore "Nove" con numero 9. */
	NOVE(9, "Nove"),

	/** Rappresenta il valore "Dieci" con numero 10. */
	DIECI(10, "Dieci"),

	/** Rappresenta il valore "Jack" con numero 11. */
	JACK(11, "Jack"),

	/** Rappresenta il valore "Queen" con numero 12. */
	QUEEN(12, "Queen"),

	/** Rappresenta il valore "King" con numero 13. */
	KING(13, "King"),

	/** Rappresenta il valore "Jolly" con numero 14. */
	JOLLY(14, "Jolly");

	/** Numero associato al valore della carta. */
	private final int numero;

	/** Nome del valore della carta. */
	private final String nome;

	/**
	 * Costruttore dell'enumerazione Valori.
	 *
	 * @param numero Il numero associato al valore della carta.
	 * @param nome   Il nome del valore della carta.
	 */
	Valori(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
	}

	/**
	 * Ritorna il nome del valore della carta.
	 *
	 * @return Il nome del valore della carta.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Ritorna il numero associato al valore della carta.
	 *
	 * @return Il numero associato al valore della carta.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Fornisce una rappresentazione stringa del valore della carta.
	 *
	 * @return Il nome del valore della carta.
	 */
	@Override
	public String toString() {
		return nome;
	}
}
