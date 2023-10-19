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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import JTrash.model.data.Profilo;

/**
 * Questa classe rappresenta il pannello delle statistiche dei giocatori nella
 * schermata del gioco. Mostra le informazioni dei profili dei giocatori, tra
 * cui avatar, nickname, partite giocate, partite vinte e partite perse.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class StatistichePanel extends JPanel {
	
	/**
	 * ID seriale per la serializzazione, usato per garantire la consistenza durante la serializzazione e deserializzazione.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Immagine GIF utilizzata come elemento grafico nel pannello.
	 */
	private Image gifImage;

	/**
	 * Bottone per tornare alla schermata o menu precedente.
	 */
	private JButton backButton;


	/**
	 * Costruttore per la classe StatistichePanel.
	 *
	 * @param profili      La lista dei profili dei giocatori da visualizzare nelle
	 *                     statistiche.
	 * @param gifPath      Il percorso del file GIF da utilizzare come sfondo del
	 *                     pannello.
	 * @param backListener L'ascoltatore per il pulsante "Indietro".
	 */
	public StatistichePanel(List<Profilo> profili, String gifPath, ActionListener backListener) {

		gifImage = new ImageIcon(gifPath).getImage();

		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel profilesGridPanel = new JPanel(new GridLayout(1, 6));
		profilesGridPanel.setOpaque(false);

		for (Profilo profilo : profili) {
			JPanel singleProfilePanel = creaSingoloPannelloGiocatore(profilo);
			profilesGridPanel.add(singleProfilePanel);
		}

		backButton = new JButton("Indietro");
		backButton.setFont(Config.getGaugeBold22());
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(backListener);

		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());

		add(profilesGridPanel);
		add(backButton);

		setPreferredSize(new Dimension(1800, 225)); // Ridotto l'altezza del 25%
	}

	/**
	 * Crea un pannello per un singolo profilo giocatore con avatar e informazioni.
	 *
	 * @param profilo Il profilo del giocatore da visualizzare.
	 * @return Il pannello contenente l'avatar e le informazioni del giocatore.
	 */
	private JPanel creaSingoloPannelloGiocatore(Profilo profilo) {
		JPanel panel = new JPanel(new BorderLayout(0, 0)); // Spaziatura 0 tra i componenti

		panel.setOpaque(false);

		ImageIcon avatarIcon = new ImageIcon(getClass().getResource(profilo.getAvatar()));
		Image avatar = avatarIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		JLabel avatarLabel = new JLabel(new ImageIcon(avatar), SwingConstants.CENTER);

		String playerInfo = String.format(
				"<html>Nickname: %s<br>Partite Giocate: %d<br>Partite Vinte: %d<br>Partite Perse: %d</html>",
				profilo.getNickname(), profilo.getPartiteGiocate(), profilo.getPartiteVinte(),
				profilo.getPartitePerse());

		JLabel infoLabel = new JLabel(playerInfo, SwingConstants.CENTER);
		infoLabel.setFont(Config.getGaugeBold16());
		infoLabel.setForeground(Color.WHITE);

		panel.add(avatarLabel, BorderLayout.CENTER);
		panel.add(infoLabel, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Sovrascrive il metodo paintComponent per disegnare il GIF di sfondo.
	 *
	 * @param g L'oggetto Graphics per il disegno.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.drawImage(gifImage, 0, 0, width, height, this);
	}
}
