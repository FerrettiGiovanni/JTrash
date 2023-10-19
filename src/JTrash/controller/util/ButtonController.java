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

import javax.swing.JButton;

import JTrash.view.GiocatoreButtons;
import JTrash.view.MazzoEScartiPanel;

/**
 * Controller che gestisce le azioni dei pulsanti.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class ButtonController implements ActionListener {
	
	/**
	 * Pannello che rappresenta il mazzo di carte e le carte scartate.
	 */
	private MazzoEScartiPanel mazzoPanel;

	/**
	 * Interfaccia utente associata ai pulsanti del giocatore.
	 */
	private GiocatoreButtons giocatoreButtons;

	/**
	 * Costruttore che inizializza il pannello del mazzo.
	 *
	 * @param mazzoPanel Pannello del mazzo.
	 */
	public ButtonController(MazzoEScartiPanel mazzoPanel) {
		this.mazzoPanel = mazzoPanel;
		associaListenerAlMazzoButton();
	}

	/**
	 * Costruttore che inizializza i pulsanti del giocatore.
	 *
	 * @param giocatoreButtons Pulsanti del giocatore.
	 */
	public ButtonController(GiocatoreButtons giocatoreButtons) {
		this.giocatoreButtons = giocatoreButtons;
		if (giocatoreButtons.currentPlayerIndex == 0) {
			associaGiocatoreButtonListener();
		}
	}

	/**
	 * Associa i listener ai pulsanti del mazzo.
	 */
	private void associaListenerAlMazzoButton() {
		mazzoPanel.getMazzoButton().addActionListener(this);
		mazzoPanel.getScartiButton().addActionListener(this);
	}

	/**
	 * Associa i listener ai pulsanti del giocatore.
	 */
	private void associaGiocatoreButtonListener() {
		for (int i = 0; i < 10; i++) {
			JButton button = giocatoreButtons.getButton(i);
			button.addActionListener(this);
		}
	}

	/**
	 * Gestisce le azioni effettuate sui pulsanti.
	 *
	 * @param e L'evento scatenato dall'azione sul pulsante.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		InputManager inputManager = InputManager.getInstance(); // Ottieni l'istanza singleton

		if (mazzoPanel != null) {
			if (e.getSource() == mazzoPanel.getMazzoButton()) { // Premo il bottone del mazzo
				inputManager.setHaPescatoDalMazzoPrincipale(true);
			} else if (e.getSource() == mazzoPanel.getScartiButton()) { // Premo il bottone degli scarti
				inputManager.setHaPescatoDalMazzoPrincipale(false);
				inputManager.setHaScartato(true);
				inputManager.setButtonScartiPremuto(true);
			}
		}

		if (giocatoreButtons != null && giocatoreButtons.currentPlayerIndex == 0) {
			for (int i = 0; i < 10; i++) {
				if (e.getSource() == giocatoreButtons.getButton(i)) { // Premo il button numero i del Giocatore Umano
					inputManager.setPosizioneCartaSelezionata(i + 1);
					inputManager.setButtonGiocatorePremuto(i + 1);

				}
			}
		}
	}
}
