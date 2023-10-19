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

package JTrash.model.player;

import JTrash.model.card.Carta;

/**
 * Interfaccia che definisce le azioni che un giocatore può eseguire durante il
 * gioco.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
interface AzioniGiocatore {

	/**
	 * Metodo per iniziare il gioco.
	 */
	void iniziaGioco();

	/**
	 * Metodo che permette al giocatore di pescare una carta.
	 */
	void pesca();

	/**
	 * Decide da quale mazzo il giocatore deve pescare in base alla carta in cima al
	 * mazzo degli scarti.
	 *
	 * @param cartaInCimaMazzoScarti La carta che si trova in cima al mazzo degli
	 *                               scarti.
	 */
	void decidiDaQualeMazzoPescare(Carta cartaInCimaMazzoScarti);

	/**
	 * Gestisce l'azione della carta che il giocatore ha in mano.
	 *
	 * @param cartaInMano La carta di cui si vuole gestire l'azione.
	 */
	void gestisciAzioneCarta(Carta cartaInMano);

	/**
	 * Permette al giocatore di scartare una carta.
	 *
	 * @param cartaInMano La carta che il giocatore desidera scartare.
	 */
	void scartaCarta(Carta cartaInMano);

	/**
	 * Sostituisce una carta in mano del giocatore con un'altra carta.
	 *
	 * @param cartaInMano     La carta che il giocatore ha attualmente in mano e che
	 *                        desidera sostituire.
	 * @param cartaSostituita La carta con cui sostituire quella in mano.
	 */
	void sostituisciCartaInMano(Carta cartaInMano, Carta cartaSostituita);

	/**
	 * Aggiorna lo stato del gioco in base alla carta sostituita.
	 *
	 * @param cartaSostituita La carta con cui e' stata sostituita quella originale.
	 */
	void aggiornaStatoGioco(Carta cartaSostituita);
}
