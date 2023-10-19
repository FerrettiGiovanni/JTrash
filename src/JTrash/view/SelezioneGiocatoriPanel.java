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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Questo pannello consente ai giocatori di selezionare il numero di
 * partecipanti.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
class SelezioneGiocatoriPanel extends JPanel {
	
	/**
	 * Serial version UID utilizzato per la serializzazione.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * L'immagine GIF visualizzata nello sfondo del pannello.
	 */
	private Image gifImage;

	/**
	 * Crea un nuovo pannello per la selezione del numero di giocatori.
	 *
	 * @param gifPath        Il percorso dell'immagine di sfondo.
	 * @param selectListener L'ascoltatore per la selezione del numero di giocatori.
	 */
	public SelezioneGiocatoriPanel(String gifPath, ActionListener selectListener) {

		// Carica l'immagine di sfondo
		gifImage = new ImageIcon(gifPath).getImage();

		setOpaque(false);
		setLayout(new BorderLayout());

		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

		JButton btn2Players = creaBottoneSelezioneNumGiocatori("2 Giocatori", selectListener);
		JButton btn3Players = creaBottoneSelezioneNumGiocatori("3 Giocatori", selectListener);
		JButton btn4Players = creaBottoneSelezioneNumGiocatori("4 Giocatori", selectListener);

		// Aggiungi l'etichetta
		JLabel label = new JLabel("Seleziona il numero di Giocatori:");
		label.setFont(Config.getGaugeBold22());
		label.setForeground(Color.WHITE);
		label.setAlignmentX(CENTER_ALIGNMENT);

		centerPanel.add(Box.createVerticalGlue());
		centerPanel.add(label);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		centerPanel.add(btn2Players);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		centerPanel.add(btn3Players);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		centerPanel.add(btn4Players);
		centerPanel.add(Box.createVerticalGlue());

		// Aggiungi il pannello centrale al pannello principale con un padding verticale
		add(centerPanel, BorderLayout.CENTER);
		add(Box.createRigidArea(new Dimension(0, (getHeight() / 2) + 200)), BorderLayout.NORTH); // Offset aggiornato
	}

	/**
	 * Crea un pulsante per la selezione del numero di giocatori.
	 *
	 * @param text           Il testo del pulsante.
	 * @param selectListener L'ascoltatore per la selezione del numero di giocatori.
	 * @return Un oggetto JButton per selezionare il numero di giocatori.
	 */
	private JButton creaBottoneSelezioneNumGiocatori(String text, ActionListener selectListener) {
		JButton btn = new JButton(text);
		btn.setFont(Config.getGaugeBold22());
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setForeground(Color.WHITE);
		btn.setAlignmentX(CENTER_ALIGNMENT);
		btn.setFocusPainted(false);
		btn.setActionCommand(text);
		btn.addActionListener(selectListener);
		return btn;
	}

	/**
	 * Sovrascrive il metodo paintComponent per disegnare l'immagine di sfondo.
	 *
	 * @param g L'oggetto Graphics utilizzato per disegnare l'immagine.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.drawImage(gifImage, 0, 0, width, height, this);
	}
}
