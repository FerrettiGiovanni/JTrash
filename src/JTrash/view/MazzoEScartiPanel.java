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
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import JTrash.controller.util.ButtonController;
import JTrash.model.card.Carta;
import JTrash.model.observable.StatoGioco;

/**
 * Questo pannello gestisce la visualizzazione e l'aggiornamento del mazzo e
 * degli scarti durante il gioco.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
@SuppressWarnings("deprecation")
public class MazzoEScartiPanel implements Observer {

	/** 
     * Bottone che rappresenta gli scarti nel gioco.
     * Quando premuto, potrebbe attivare specifiche azioni o eventi legati agli scarti.
     */
    private JButton scartiButton;

    /** 
     * Bottone che rappresenta il mazzo di carte nel gioco.
     * Quando premuto, potrebbe attivare specifiche azioni o eventi legati al prelievo di carte dal mazzo.
     */
    private JButton mazzoButton;

	/**
	 * Crea una nuova istanza di MazzoEScartiPanel.
	 */
	public MazzoEScartiPanel() {
		this.scartiButton = creaBottoneTrasparente();
		this.mazzoButton = creaBottoneTrasparente();
		StatoGioco.getInstance().addObserver(this);
		new ButtonController(this);
	}

	/**
	 * Ottieni il bottone del mazzo.
	 *
	 * @return Il bottone del mazzo.
	 */
	public JButton getMazzoButton() {
		return mazzoButton;
	}

	/**
	 * Ottieni il bottone degli scarti.
	 *
	 * @return Il bottone degli scarti.
	 */
	public JButton getScartiButton() {
		return scartiButton;
	}

	/**
	 * Crea un pannello che rappresenta il mazzo e gli scarti.
	 *
	 * @return Il pannello che rappresenta il mazzo e gli scarti.
	 */
	public JPanel createMazzoEScartiPanel() {
		JPanel panelMazzoEScarti = new JPanel();
		panelMazzoEScarti.setOpaque(false);
		panelMazzoEScarti.setBackground(new Color(255, 128, 192));
		panelMazzoEScarti.setLayout(new BorderLayout(0, 0));

		JPanel panelVuotoSinistroMazzoEScarti = creaPannelloTrasparente();
		panelMazzoEScarti.add(panelVuotoSinistroMazzoEScarti, BorderLayout.WEST);

		JPanel panelVuotoBassoMazzoEScarti = creaPannelloTrasparente();
		panelMazzoEScarti.add(panelVuotoBassoMazzoEScarti, BorderLayout.SOUTH);

		JPanel panelVuotoDestroMazzoEScarti = creaPannelloTrasparente();
		panelMazzoEScarti.add(panelVuotoDestroMazzoEScarti, BorderLayout.EAST);

		JLabel labelNomeMazzoEScarti = new JLabel("Mazzo e Scarti");
		labelNomeMazzoEScarti.setForeground(new Color(250, 249, 242));
		labelNomeMazzoEScarti.setFont(Config.getGaugeBold12());
		labelNomeMazzoEScarti.setBackground(new Color(255, 128, 192));
		labelNomeMazzoEScarti.setHorizontalAlignment(SwingConstants.CENTER);
		panelMazzoEScarti.add(labelNomeMazzoEScarti, BorderLayout.NORTH);

		JPanel panel_19 = creaPannelloTrasparente();
		panelMazzoEScarti.add(panel_19, BorderLayout.CENTER);
		panel_19.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panelVuotoCentraleMazzoEScarti = creaPannelloTrasparente();
		panel_19.add(panelVuotoCentraleMazzoEScarti);

		JPanel panelCardsMazzoEScarti = creaPannelloTrasparente();
		panel_19.add(panelCardsMazzoEScarti);
		panelCardsMazzoEScarti.setLayout(new GridLayout(1, 5, 0, 0));

		panelCardsMazzoEScarti.add(creaPannelloTrasparente());
		panelCardsMazzoEScarti.add(scartiButton);
		panelCardsMazzoEScarti.add(creaPannelloTrasparente());
		panelCardsMazzoEScarti.add(mazzoButton);
		panelCardsMazzoEScarti.add(creaPannelloTrasparente());

		return panelMazzoEScarti;
	}

	/**
	 * Aggiorna la visualizzazione dei bottoni in base allo stato del gioco.
	 * 
	 * @param o L'oggetto osservabile che invoca l'update.
	 * @param arg L'argomento opzionale passato dall'osservabile.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof StatoGioco) {
			StatoGioco stato = (StatoGioco) o;

			// Se la carta pescata non e' la stessa degli scarti
			if (stato.getUltimaCartaScoperta() != stato.getScartiCarta()) {
				updateImmagineCarta(stato.getUltimaCartaScoperta(), mazzoButton);
			}

			// Aggiorna sempre il bottone degli scarti
			updateImmagineCarta(stato.getScartiCarta(), scartiButton);
		}
	}

	/**
	 * Aggiorna l'icona del bottone in base alla carta fornita.
	 * 
	 * @param carta La carta da visualizzare sul bottone.
	 * @param button Il bottone da aggiornare.
	 */
	private void updateImmagineCarta(Carta carta, JButton button) {
		if (carta != null) {
			if (carta.isScoperta()) {
				button.setIcon(getImmagineCarta(carta));
			} else {
				button.setIcon(getRetroCarta());
			}
		} else {
			if (button == mazzoButton) {
				button.setIcon(getRetroCarta());
			} else if (button == scartiButton) {
				button.setIcon(null);
			}
		}
	}

	/**
	 * Restituisce l'icona di una carta coperta.
	 * 
	 * @return ImageIcon rappresentante la carta coperta.
	 */
	private ImageIcon getRetroCarta() {
		ImageIcon imageIconRetro = new ImageIcon(new ImageIcon(getClass().getResource(Config.getIconPathRetrocarta()))
				.getImage().getScaledInstance(mazzoButton.getWidth(), mazzoButton.getHeight(), Image.SCALE_DEFAULT));
		return imageIconRetro;
	}

	/**
	 * Restituisce l'icona corrispondente alla carta fornita.
	 * 
	 * @param carta La carta di cui si vuole ottenere l'icona.
	 * @return ImageIcon rappresentante la carta specificata.
	 */
	private ImageIcon getImmagineCarta(Carta carta) {
		String filePath = Config.getCardBasePath() + carta.toString() + ".png";
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(filePath)).getImage()
				.getScaledInstance(mazzoButton.getWidth(), mazzoButton.getHeight(), Image.SCALE_DEFAULT));
		return imageIcon;
	}

	/**
	 * Crea e restituisce un pannello trasparente.
	 * 
	 * @return JPanel trasparente.
	 */
	private JPanel creaPannelloTrasparente() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		return panel;
	}

	/**
	 * Crea e restituisce un bottone trasparente con un cursore a forma di mano.
	 * 
	 * @return JButton trasparente.
	 */
	private JButton creaBottoneTrasparente() {
		JButton button = new JButton();
		button.setContentAreaFilled(false);
		button.setBorderPainted(true);
		button.setOpaque(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		return button;
	}
}
