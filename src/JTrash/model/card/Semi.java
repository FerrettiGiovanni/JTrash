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
 * Enumerazione dei diversi semi delle carte da gioco.
 * 
 * Ha sei possibili valori: CUORI, QUADRI, FIORI, PICCHE, ROSSO e NERO. I primi
 * quattro si riferiscono ai quattro semi tradizionali nel mazzo di carte
 * standard: cuori, quadri, fiori e picche.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public enum Semi {

	/** Rappresenta il seme "Cuori". */
	CUORI("Cuori"),

	/** Rappresenta il seme "Quadri". */
	QUADRI("Quadri"),

	/** Rappresenta il seme "Fiori". */
	FIORI("Fiori"),

	/** Rappresenta il seme "Picche". */
	PICCHE("Picche"),

	/** Rappresenta il colore "Rosso" (non specifico a un seme). */
	ROSSO("Rosso"),

	/** Rappresenta il colore "Nero" (non specifico a un seme). */
	NERO("Nero");

	/** Nome del seme. */
	private final String nome;

	/**
	 * Costruttore dell'enumerazione Semi.
	 *
	 * @param nome Il nome del seme.
	 */
	Semi(String nome) {
		this.nome = nome;
	}

	/**
	 * Ritorna il nome del seme.
	 *
	 * @return Il nome del seme.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Fornisce una rappresentazione stringa del seme.
	 *
	 * @return Il nome del seme.
	 */
	@Override
	public String toString() {
		return nome;
	}
}
