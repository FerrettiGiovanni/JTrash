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
 * Enumerazione che rappresenta un mazzo di carte standard da 54 carte, inclusi
 * i due Jolly. Ogni carta nel mazzo e' rappresentata come un elemento
 * dell'enumerazione "Carte", con un seme e un valore specifico.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public enum Carte {

	// (Rappresentazione delle 54 carte)

	C1(Semi.CUORI, Valori.ASSO), C2(Semi.CUORI, Valori.DUE), C3(Semi.CUORI, Valori.TRE), C4(Semi.CUORI, Valori.QUATTRO),
	C5(Semi.CUORI, Valori.CINQUE), C6(Semi.CUORI, Valori.SEI), C7(Semi.CUORI, Valori.SETTE),
	C8(Semi.CUORI, Valori.OTTO), C9(Semi.CUORI, Valori.NOVE), C10(Semi.CUORI, Valori.DIECI),
	CJ(Semi.CUORI, Valori.JACK), CQ(Semi.CUORI, Valori.QUEEN), CK(Semi.CUORI, Valori.KING),
	Q1(Semi.QUADRI, Valori.ASSO), Q2(Semi.QUADRI, Valori.DUE), Q3(Semi.QUADRI, Valori.TRE),
	Q4(Semi.QUADRI, Valori.QUATTRO), Q5(Semi.QUADRI, Valori.CINQUE), Q6(Semi.QUADRI, Valori.SEI),
	Q7(Semi.QUADRI, Valori.SETTE), Q8(Semi.QUADRI, Valori.OTTO), Q9(Semi.QUADRI, Valori.NOVE),
	Q10(Semi.QUADRI, Valori.DIECI), QJ(Semi.QUADRI, Valori.JACK), QQ(Semi.QUADRI, Valori.QUEEN),
	QK(Semi.QUADRI, Valori.KING), F1(Semi.FIORI, Valori.ASSO), F2(Semi.FIORI, Valori.DUE), F3(Semi.FIORI, Valori.TRE),
	F4(Semi.FIORI, Valori.QUATTRO), F5(Semi.FIORI, Valori.CINQUE), F6(Semi.FIORI, Valori.SEI),
	F7(Semi.FIORI, Valori.SETTE), F8(Semi.FIORI, Valori.OTTO), F9(Semi.FIORI, Valori.NOVE),
	F10(Semi.FIORI, Valori.DIECI), FJ(Semi.FIORI, Valori.JACK), FQ(Semi.FIORI, Valori.QUEEN),
	FK(Semi.FIORI, Valori.KING), P1(Semi.PICCHE, Valori.ASSO), P2(Semi.PICCHE, Valori.DUE), P3(Semi.PICCHE, Valori.TRE),
	P4(Semi.PICCHE, Valori.QUATTRO), P5(Semi.PICCHE, Valori.CINQUE), P6(Semi.PICCHE, Valori.SEI),
	P7(Semi.PICCHE, Valori.SETTE), P8(Semi.PICCHE, Valori.OTTO), P9(Semi.PICCHE, Valori.NOVE),
	P10(Semi.PICCHE, Valori.DIECI), PJ(Semi.PICCHE, Valori.JACK), PQ(Semi.PICCHE, Valori.QUEEN),
	PK(Semi.PICCHE, Valori.KING), JR(Semi.ROSSO, Valori.JOLLY), JN(Semi.NERO, Valori.JOLLY);

	/** Valore della carta. */
	private final Valori valore;

	/** Seme della carta. */
	private final Semi seme;

	/**
	 * Costruttore di Carte. Crea un nuovo elemento dell'enumerazione con un seme e
	 * un valore specifici.
	 * 
	 * @param seme   Il seme della carta.
	 * @param valore Il valore della carta.
	 */
	Carte(Semi seme, Valori valore) {
		this.seme = seme;
		this.valore = valore;
	}

	/**
	 * Restituisce il valore associato alla carta.
	 * 
	 * @return Il valore della carta.
	 */
	public Valori getValore() {
		return valore;
	}

	/**
	 * Restituisce una rappresentazione in stringa dell'oggetto Carte.
	 * 
	 * @return Una stringa che rappresenta la carta.
	 */
	@Override
	public String toString() {
		return seme.toString() + "_" + valore.toString();
	}

}
