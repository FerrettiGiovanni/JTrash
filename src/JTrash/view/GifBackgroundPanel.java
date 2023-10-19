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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Questo pannello contiene un'immagine GIF di sfondo e due pulsanti per Nuova
 * Partita e Statistiche di Gioco.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class GifBackgroundPanel extends JPanel {
	
	/**
	 * ID per la serializzazione, usato durante la deserializzazione per assicurare che il mittente e il destinatario di un oggetto serializzato abbiano caricato classi per quell'oggetto compatibili in termini di serializzazione.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Immagine GIF utilizzata come sfondo del pannello.
	 */
	private Image gifImage;

	/**
	 * Pulsante per iniziare una nuova partita.
	 */
	private JButton btnNuovaPartita;

	/**
	 * Pulsante per visualizzare le statistiche del gioco.
	 */
	private JButton btnStatistiche;

	/**
	 * Crea un nuovo pannello con un'immagine GIF di sfondo e pulsanti.
	 *
	 * @param gifPath         Il percorso dell'immagine GIF di sfondo.
	 * @param newGameListener Il listener per il pulsante "Nuova Partita".
	 */
	public GifBackgroundPanel(String gifPath, ActionListener newGameListener) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		gifImage = new ImageIcon(gifPath).getImage();
		add(Box.createVerticalGlue());
		JPanel btnPanel = new JPanel();
		btnPanel.setOpaque(false);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));

		btnNuovaPartita = creaBottoni("Nuova Partita", newGameListener);
		btnStatistiche = creaBottoni("Statistiche Di Gioco", null);

		btnPanel.add(Box.createHorizontalGlue());
		btnPanel.add(btnNuovaPartita);
		btnPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		btnPanel.add(btnStatistiche);
		btnPanel.add(Box.createHorizontalGlue());
		add(btnPanel);

		Timer fadeTimer = new Timer(10, new ActionListener() {
			float alpha = 0.0f;

			@Override
			public void actionPerformed(ActionEvent e) {
				alpha += 0.01f;
				if (alpha >= 1.0f) {
					alpha = 1.0f;
					((Timer) e.getSource()).stop();
				}
				btnNuovaPartita.setForeground(new Color(1, 1, 1, alpha));
				btnStatistiche.setForeground(new Color(1, 1, 1, alpha));
			}
		});
		fadeTimer.start();
	}

	/**
	 * Imposta il listener per il pulsante "Statistiche di Gioco".
	 *
	 * @param listener Il listener per il pulsante "Statistiche di Gioco".
	 */
	public void setStatisticheListener(ActionListener listener) {
		btnStatistiche.addActionListener(listener);
	}

	private JButton creaBottoni(String text, ActionListener listener) {
		JButton btn = new JButton(text);
		btn.setFont(Config.getGaugeBold22());
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		if (listener != null)
			btn.addActionListener(listener);
		return btn;
	}

	/**
	 * Ottieni il pulsante per le Statistiche di Gioco.
	 *
	 * @return Il pulsante per le Statistiche di Gioco.
	 */
	public JButton getStatisticsButton() {
		return btnStatistiche;
	}

	/**
	 * Sovrascrive il metodo paintComponent della classe JPanel per disegnare
	 * l'immagine GIF come sfondo del pannello. Adapta l'immagine alle dimensioni
	 * correnti del pannello.
	 * 
	 * @param g L'oggetto Graphics utilizzato per disegnare componenti GUI.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.drawImage(gifImage, 0, 0, width, height, this);
	}
}
