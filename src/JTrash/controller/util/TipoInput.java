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
 * Enumerazione che rappresenta i diversi tipi di input che possono essere
 * ricevuti nel contesto del gioco JTrash.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public enum TipoInput {

	/** Rappresenta l'input generato quando un giocatore preme un bottone. */
	BUTTON_GIOCATORE,

	/**
	 * Rappresenta l'input generato quando un giocatore preme il bottone per
	 * scartare.
	 */
	BUTTON_SCARTI,

	/** Rappresenta l'assenza di input. */
	NESSUN_INPUT
}
