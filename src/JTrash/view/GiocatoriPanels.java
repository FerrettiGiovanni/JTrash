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

package JTrash.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Questa classe gestisce la creazione dei pannelli dei giocatori.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class GiocatoriPanels {

	/**
	 * Crea e restituisce un array di pannelli per i giocatori in base al numero di
	 * giocatori specificato.
	 *
	 * @param numGiocatori Il numero di giocatori.
	 * @return Un array di pannelli per i giocatori.
	 */
	public JPanel[] createGiocatoriPanels(int numGiocatori) {
		String[] positions = { BorderLayout.WEST, BorderLayout.SOUTH, BorderLayout.EAST, BorderLayout.CENTER };

		JPanel[] panelGiocatori = new JPanel[4];

		for (int g = 0; g < 4; g++) {
			final int currentPlayer = g;
			if (currentPlayer < numGiocatori) {
				panelGiocatori[currentPlayer] = new JPanel(new BorderLayout(0, 0));
				panelGiocatori[currentPlayer].setOpaque(false);
				panelGiocatori[currentPlayer].setBackground(new Color(255, 128, 192));

				String nomeGiocatore = currentPlayer == 0 ? "Umano" : "CPU";
				JLabel labelNomeGiocatore = new JLabel("Giocatore " + (currentPlayer + 1) + " " + nomeGiocatore,
						SwingConstants.CENTER);
				labelNomeGiocatore.setFont(Config.getGaugeBold12());
				labelNomeGiocatore.setForeground(new Color(250, 249, 242));
				panelGiocatori[currentPlayer].add(labelNomeGiocatore, BorderLayout.NORTH);

				JPanel[][] panelsGiocatori = new JPanel[4][4];

				for (int i = 0; i < 4; i++) {
					panelsGiocatori[currentPlayer][i] = new JPanel();
					panelsGiocatori[currentPlayer][i].setOpaque(false);
					if (i == 3) {
						panelsGiocatori[currentPlayer][i].setLayout(new GridLayout(2, 5, 0, 0));
						GiocatoreButtons giocatoreButtons = new GiocatoreButtons(currentPlayer);
						for (int j = 0; j < 10; j++) {
							panelsGiocatori[currentPlayer][i].add(giocatoreButtons.getButton(j));
						}
					}
					panelGiocatori[currentPlayer].add(panelsGiocatori[currentPlayer][i], positions[i]);
				}
			} else {
				panelGiocatori[currentPlayer] = new JPanel();
				panelGiocatori[currentPlayer].setOpaque(false);
				panelGiocatori[currentPlayer].setBackground(new Color(255, 255, 128));
			}
		}

		return panelGiocatori;
	}
}
