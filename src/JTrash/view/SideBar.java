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
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import JTrash.model.data.Profilo;
import JTrash.model.observable.StatoGioco;

/**
 * Questa classe rappresenta il pannello laterale nella schermata del gioco, che
 * mostra gli avatar dei giocatori e fornisce un'animazione di rotazione per gli
 * avatar. La classe implementa le interfacce ActionListener e Observer per
 * gestire l'animazione e ricevere gli aggiornamenti sullo stato del gioco.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
@SuppressWarnings("deprecation")
public class SideBar extends JPanel implements ActionListener, Observer {
	
	/**
	 * Numero di versione per la serializzazione. Viene utilizzato durante la deserializzazione
	 * per garantire che il caricatore della classe e l'oggetto serializzato siano compatibili
	 * tra loro.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Timer utilizzato per gestire l'animazione degli avatar dei giocatori.
	 */
	private Timer animationTimer;
	
	/**
	 * Array di pannelli (JPanel) che rappresenta ogni giocatore nella sidebar.
	 * Ogni pannello conterrà un'icona (avatar) e una etichetta con il nome del giocatore.
	 */
	private JPanel[] playerPanels;
	
	/**
	 * Array di etichette (JLabel) che contiene le icone (avatar) dei giocatori.
	 */
	private JLabel[] iconLabels;
	
	/**
	 * Array di etichette (JLabel) che contiene i nomi dei giocatori.
	 */
	private JLabel[] textLabels;
	
	/**
	 * L'angolo corrente di rotazione per l'animazione degli avatar. 
	 * Questo valore determina quanto ogni icona viene ruotata durante l'animazione.
	 */
	private double angle = 0;
	
	/**
	 * Determina la direzione della rotazione dell'animazione. 
	 * Se vero, gli avatar ruoteranno verso destra; altrimenti, ruoteranno verso sinistra.
	 */
	private boolean rotateRight = true;

	/**
	 * Crea un pannello laterale per visualizzare informazioni sui giocatori.
	 *
	 * @param numGiocatori Il numero di giocatori per cui visualizzare le
	 *                     informazioni.
	 */
	public SideBar(int numGiocatori) {
		StatoGioco.getInstance().addObserver(this);
		setBackground(new Color(188, 185, 167));
		setMinimumSize(new Dimension(100, 10));
		setPreferredSize(new Dimension(150, 10));
		setLayout(new GridLayout(4, 1, 0, 0));

		playerPanels = new JPanel[4];
		iconLabels = new JLabel[4];
		textLabels = new JLabel[4];

		for (int i = 0; i < 4; i++) {
			playerPanels[i] = new JPanel(new BorderLayout());
			playerPanels[i].setBackground(new Color(188, 185, 167));
			add(playerPanels[i]);

			if (i < numGiocatori) {
				ImageIcon avatarIcon = new ImageIcon(getClass().getResource("/assets/smallAvatar/Giant3.png"));

				iconLabels[i] = new JLabel(avatarIcon, SwingConstants.CENTER) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					/**
					 * Sovrascrive il metodo paintComponent per permettere la rotazione dell'icona.
					 * Se l'icona e' presente, verrà ruotata in base all'angolo specificato; altrimenti
					 * verrà chiamato il metodo paintComponent della superclasse.
					 *
					 * @param g L'oggetto Graphics usato per disegnare componenti.
					 */
					@Override
					protected void paintComponent(Graphics g) {
						Graphics2D g2d = (Graphics2D) g.create();

						if (getIcon() != null) {
							int iconWidth = getIcon().getIconWidth();
							int iconHeight = getIcon().getIconHeight();
							AffineTransform original = g2d.getTransform();

							g2d.rotate(angle, getWidth() / 2, getHeight() / 2);
							g2d.drawImage(((ImageIcon) getIcon()).getImage(), (getWidth() - iconWidth) / 2,
									(getHeight() - iconHeight) / 2, this);
							g2d.setTransform(original);
						} else {
							super.paintComponent(g);
						}

						g2d.dispose();
					}
				};
				iconLabels[i].setHorizontalAlignment(JLabel.CENTER);
				playerPanels[i].add(iconLabels[i], BorderLayout.CENTER);

				String playerName = "Giocatore " + (i + 1);
				String nickname = "Nickname"; // Placeholder
				int partiteGiocate = 10; // Placeholder
				int partiteVinte = 5; // Placeholder
				int partitePerse = 5; // Placeholder
				String playerStats = String.format(
						"<html>%s<br>Nickname: %s<br>Partite Giocate: %d<br>Partite Vinte: %d<br>Partite Perse: %d</html>",
						playerName, nickname, partiteGiocate, partiteVinte, partitePerse);

				textLabels[i] = new JLabel(playerStats);
				textLabels[i].setFont(Config.getGaugeBold10());
				textLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
				playerPanels[i].add(textLabels[i], BorderLayout.SOUTH);
			} else {
				iconLabels[i] = new JLabel();
				textLabels[i] = new JLabel();
				playerPanels[i].add(iconLabels[i], BorderLayout.CENTER);
				playerPanels[i].add(textLabels[i], BorderLayout.SOUTH);
			}
		}

		animationTimer = new Timer(75, this);
		animationTimer.start();
	}

	/**
	 * Gestisce l'animazione di rotazione degli avatar dei giocatori nel pannello
	 * laterale. La rotazione avviene alternativamente verso destra e sinistra.
	 *
	 * @param e L'evento d'azione che ha scatenato il metodo.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (rotateRight) {
			angle += 0.03;
			if (angle >= 0.2) {
				rotateRight = false;
			}
		} else {
			angle -= 0.03;
			if (angle <= -0.2) {
				rotateRight = true;
			}
		}
		repaint();
	}

	/**
	 * Aggiorna le informazioni sui giocatori nel pannello laterale.
	 *
	 * @param o   L'oggetto osservabile che ha innescato l'aggiornamento.
	 * @param arg L'argomento passato dall'oggetto osservabile.
	 */
	public void update(Observable o, Object arg) {
		if (o instanceof StatoGioco) {
			List<Profilo> profili = StatoGioco.getInstance().getProfiliGiocatori();

			for (int i = 0; i < profili.size(); i++) {
				Profilo profilo = profili.get(i);

				ImageIcon avatarIcon = new ImageIcon(getClass().getResource(profilo.getAvatar()));
				iconLabels[i].setIcon(avatarIcon);
				
				String playerName = "Giocatore " + (i + 1);
				String nickname = profilo.getNickname();
				int partiteGiocate = profilo.getPartiteGiocate();
				int partiteVinte = profilo.getPartiteVinte();
				int partitePerse = profilo.getPartitePerse();
				String playerStats = String.format(
						"<html>%s<br>Nickname: %s<br>Partite Giocate: %d<br>Partite Vinte: %d<br>Partite Perse: %d</html>",
						playerName, nickname, partiteGiocate, partiteVinte, partitePerse);

				textLabels[i].setText(playerStats);
			}
		}
	}
}
