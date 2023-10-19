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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import JTrash.model.card.Carta;
import JTrash.model.observable.StatoGioco;

/**
 * Questo pannello gestisce la visualizzazione e l'aggiornamento della mano del
 * giocatore.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
@SuppressWarnings("deprecation")
public class ManoGiocatoreUmanoPanel implements Observer {

	/**
	 * Bottone utilizzato per visualizzare una delle carte nella mano del primo giocatore.
	 */
	private JButton manoGiocatore1;

	/**
	 * Crea una nuova istanza di PlayerHandPanel.
	 */
	public ManoGiocatoreUmanoPanel() {
		// Istanza del singleton
		StatoGioco statoGioco = StatoGioco.getInstance();
		statoGioco.addObserver(this);
	}

	/**
	 * Aggiorna la visualizzazione della mano del primo giocatore in base allo stato di gioco e alla carta fornita.
	 * Se la carta fornita corrisponde alla mano del giocatore umano, imposta l'icona del bottone con l'immagine della carta.
	 * In caso contrario, rimuove l'icona dal bottone.
	 *
	 * @param o    L'oggetto osservabile che ha notificato l'aggiornamento. Ci si aspetta che sia un'istanza di {@link StatoGioco}.
	 * @param arg  L'argomento fornito dall'osservabile. Ci si aspetta che sia un'istanza di {@link Carta}.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof StatoGioco && arg instanceof Carta) {
			StatoGioco stato = (StatoGioco) o;

			if (arg == stato.getManoGiocatoreUmano()) {
				Carta carta = (Carta) arg;
				String filePath = Config.getCardBasePath() + carta.toString() + ".png";
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(filePath)).getImage()
						.getScaledInstance(manoGiocatore1.getWidth(), manoGiocatore1.getHeight(), Image.SCALE_DEFAULT));
				manoGiocatore1.setIcon(imageIcon);
			} else {
				manoGiocatore1.setIcon(null);
			}
		}
	}

	/**
	 * Crea un pannello che rappresenta la mano del giocatore.
	 *
	 * @return Il pannello che rappresenta la mano del giocatore.
	 */
	public JPanel creaPannelloManoGiocatore() {
		JPanel panelManoGiocatore1 = new JPanel();
		panelManoGiocatore1.setOpaque(false);
		panelManoGiocatore1.setBackground(new Color(255, 255, 128));
		panelManoGiocatore1.setLayout(new BorderLayout(0, 0));

		panelManoGiocatore1.add(creaPannelloTrasparente(), BorderLayout.WEST);

		JLabel labelNomeMano = new JLabel("Mano Giocatore 1");
		labelNomeMano.setFont(Config.getGaugeBold12());
		labelNomeMano.setForeground(new Color(250, 249, 242));
		labelNomeMano.setHorizontalAlignment(SwingConstants.CENTER);
		labelNomeMano.setBackground(new Color(255, 128, 192));
		panelManoGiocatore1.add(labelNomeMano, BorderLayout.NORTH);

		panelManoGiocatore1.add(creaPannelloTrasparente(), BorderLayout.EAST);
		panelManoGiocatore1.add(creaPannelloTrasparente(), BorderLayout.SOUTH);

		JPanel panel_19_1 = creaPannelloTrasparente();
		panelManoGiocatore1.add(panel_19_1, BorderLayout.CENTER);
		panel_19_1.setLayout(new GridLayout(2, 1, 0, 0));

		panel_19_1.add(creaPannelloTrasparente());

		JPanel panelCardsMano = creaPannelloTrasparente();
		panel_19_1.add(panelCardsMano);
		panelCardsMano.setLayout(new GridLayout(1, 5, 0, 0));

		panelCardsMano.add(creaPannelloTrasparente());

		manoGiocatore1 = new JButton("Mano Giocatore 1");
		manoGiocatore1.setPreferredSize(new Dimension(129, 23));
		manoGiocatore1.setMinimumSize(new Dimension(129, 23));
		manoGiocatore1.setMaximumSize(new Dimension(129, 23));
		manoGiocatore1.setHorizontalTextPosition(SwingConstants.CENTER);
		manoGiocatore1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		manoGiocatore1.setContentAreaFilled(false);
		manoGiocatore1.setForeground(new Color(0, 0, 0, 0));
		manoGiocatore1.addActionListener(e -> System.out.println("Hai premuto il pulsante Mano Giocatore 1"));
		panelCardsMano.add(manoGiocatore1);

		panelCardsMano.add(creaPannelloTrasparente());
		panelCardsMano.add(creaPannelloTrasparente());
		panelCardsMano.add(creaPannelloTrasparente());

		return panelManoGiocatore1;
	}

	/**
	 * Crea e restituisce un pannello trasparente.
	 *
	 * @return Un oggetto JPanel con sfondo trasparente.
	 */
	private JPanel creaPannelloTrasparente() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		return panel;
	}
}
