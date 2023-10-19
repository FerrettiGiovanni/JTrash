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

package JTrash.controller;

import java.awt.EventQueue;

import JTrash.view.MainFrame;

/**
 * Il punto di ingresso principale per l'applicazione JTrash. Questa classe
 * avvia l'interfaccia grafica e inizializza la logica di base del gioco.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class JTrash {

	/**
	 * Metodo main che lancia l'applicazione.
	 * 
	 * @param args argomenti passati da linea di comando.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			MainFrame frame = new MainFrame();
			frame.setVisible(true);
		});

	}
}
