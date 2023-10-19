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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import JTrash.model.manager.NumeroGiocatoriManager;

/**
 * Controller per gestire la selezione del numero di giocatori.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class NumeroGiocatoriController implements ActionListener {

	/**
	 * Gestisce le azioni effettuate dall'utente sulla selezione del numero di
	 * giocatori.
	 * 
	 * @param e L'evento associato all'azione dell'utente.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
		case "2 Giocatori":
			NumeroGiocatoriManager.getInstance().setNumeroCpu(1);
			break;
		case "3 Giocatori":
			NumeroGiocatoriManager.getInstance().setNumeroCpu(2);
			break;
		case "4 Giocatori":
			NumeroGiocatoriManager.getInstance().setNumeroCpu(3);
			break;
		default:
			throw new IllegalArgumentException("Selezione non valida");
		}
	}
}
