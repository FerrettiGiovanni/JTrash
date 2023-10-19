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

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Pannello di sfondo per l'applicazione. Questa classe estende JPanel e
 * consente di visualizzare un'immagine di sfondo all'interno di un pannello.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class SfondoPanel extends JPanel {
	
	/**
	 * ID di serializzazione per garantire la compatibilità durante la deserializzazione.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Immagine originale che verrà utilizzata come sfondo del pannello.
	 */
	private Image originalImage;

	/**
	 * Immagine scalata utilizzata effettivamente come sfondo del pannello.
	 */
	private Image image;

	/**
	 * Crea un nuovo pannello di sfondo. Carica l'immagine di sfondo specificata
	 * nelle impostazioni di configurazione.
	 */
	public SfondoPanel() {
		try {
			originalImage = ImageIO.read(getClass().getResource(Config.getIconPathBackground()));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Errore nel caricamento dell'immagine di sfondo!");
		}
		setLayout(new GridLayout(3, 3, 0, 0));
	}

	/**
	 * Override del metodo paintComponent per disegnare l'immagine di sfondo.
	 *
	 * @param g Il contesto grafico in cui disegnare l'immagine.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (originalImage != null) {
			image = originalImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			g.drawImage(image, 0, 0, this);
		}
	}
}
