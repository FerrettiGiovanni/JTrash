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

import java.awt.Cursor;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import JTrash.controller.util.ButtonController;
import JTrash.controller.util.ImageManager;
import JTrash.model.card.Carta;
import JTrash.model.observable.StatoGioco;

/**
 * Questa classe rappresenta un insieme di bottoni per il giocatore corrente.
 * Ogni bottone e' associato a una carta nella mano del giocatore.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
@SuppressWarnings("deprecation")
public class GiocatoreButtons implements Observer {
	
	/**
     * Array dei bottoni, dove ogni bottone rappresenta una carta 
     * nella mano del giocatore. La capacità massima e' di 10 bottoni.
     */
    private JButton[] buttons = new JButton[10];

    /**
     * Indice del giocatore corrente.
     */
    public int currentPlayerIndex;

    /**
     * Gestore di immagini utilizzato per manipolare e 
     * visualizzare le immagini associate ai bottoni.
     */
    private ImageManager imageManager = new ImageManager();

	/**
	 * Crea un set di bottoni per il giocatore corrente.
	 *
	 * @param currentPlayer L'indice del giocatore corrente.
	 */
	public GiocatoreButtons(int currentPlayer) {
		this.currentPlayerIndex = currentPlayer;
		StatoGioco statoGioco = StatoGioco.getInstance();
		statoGioco.addObserver(this);

		for (int j = 1; j <= 10; j++) {
			buttons[j - 1] = new JButton("Button" + j + " Giocatore " + (currentPlayer + 1));
			buttons[j - 1].setHorizontalTextPosition(SwingConstants.CENTER);
			if (currentPlayerIndex == 0) {
				buttons[j - 1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			} else {
				buttons[j - 1].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			buttons[j - 1].setContentAreaFilled(false);
		}
		new ButtonController(this);
	}

	/**
	 * Ottieni un bottone specifico dal set.
	 *
	 * @param index L'indice del bottone desiderato.
	 * @return Il bottone corrispondente all'indice specificato.
	 */
	public JButton getButton(int index) {
		return buttons[index];
	}

	/**
	 * Aggiorna l'interfaccia dei bottoni in base alle carte sul tavolo dei giocatori.
	 * Se un giocatore ha meno di 10 carte, i bottoni in eccesso verranno nascosti.
	 * In caso contrario, ogni bottone sarà associato a una carta.
	 * Il metodo viene chiamato ogni volta che lo stato del gioco cambia.
	 * 
	 * @param o   L'oggetto Observable che ha notificato il cambiamento. 
	 *            In questo caso, ci aspettiamo che sia una istanza di {@link StatoGioco}.
	 * @param arg Eventuali argomenti forniti dall'Observable. Non utilizzati in questo metodo.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof StatoGioco) {
			List<ArrayList<Carta>> carteGiocatori = StatoGioco.getInstance().getCarteSulTavoloGiocatori();
			if (carteGiocatori.isEmpty()) {
				for (JButton button : buttons) {
					button.setVisible(false);
				}
				return;
			}
			if (currentPlayerIndex < carteGiocatori.size()) {
				ArrayList<Carta> carteGiocatoreCorrente = carteGiocatori.get(currentPlayerIndex);
				for (int i = 0; i < carteGiocatoreCorrente.size() && i < buttons.length; i++) {
					Carta carta = carteGiocatoreCorrente.get(i);
					JButton button = buttons[i];
					if (carta == null) {
						button.setVisible(false);
						continue;
					} else {
						button.setVisible(true);
					}

					Dimension buttonDimension = new Dimension(button.getWidth(), button.getHeight());
					ImageIcon cardIconScaled = imageManager.getCardImage(carta, buttonDimension);

					button.setIcon(cardIconScaled);
					button.setText("");
				}
				for (int i = carteGiocatoreCorrente.size(); i < buttons.length; i++) {
					buttons[i].setVisible(false);
				}
			}
		}
	}
}
