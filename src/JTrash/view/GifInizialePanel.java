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
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Questo pannello contiene un'immagine GIF iniziale.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class GifInizialePanel extends JPanel {

	/**
     * ID seriale generato automaticamente per la serializzazione.
     */
	private static final long serialVersionUID = 1L;

    /**
     * L'immagine GIF che sarà visualizzata all'interno del pannello.
     */
	private Image gifImage;

	/**
	 * Crea un nuovo pannello con un'immagine GIF iniziale.
	 *
	 * @param gifPath Il percorso dell'immagine GIF iniziale.
	 */
	public GifInizialePanel(String gifPath) {
		setLayout(new BorderLayout());

		// Carica l'immagine una volta
		gifImage = new ImageIcon(gifPath).getImage();
	}

	/**
	 * Sovrascrive il metodo paintComponent per personalizzare il disegno dell'immagine GIF 
	 * all'interno del pannello. L'immagine viene ridimensionata in base alle dimensioni correnti del pannello.
	 *
	 * @param g Il contesto grafico utilizzato per disegnare l'immagine.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Ottieni le dimensioni correnti del pannello
		int width = getWidth();
		int height = getHeight();

		// Disegna l'immagine ridimensionata
		g.drawImage(gifImage, 0, 0, width, height, this);
	}

	/**
	 * Ottieni la durata dell'immagine GIF iniziale in millisecondi.
	 *
	 * @return La durata dell'immagine GIF iniziale in millisecondi.
	 */
	public int getDurataGif() {
		return 7650;
	}
}
