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

package JTrash.controller.util;

import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import JTrash.model.card.Carta;
import JTrash.model.card.Carte;
import JTrash.view.Config;

/**
 * Gestore delle immagini utilizzato per caricare e gestire le immagini delle
 * carte.
 * 
 * Questa classe si occupa di caricare le immagini delle carte dall'area delle
 * risorse e di fornire accesso alle immagini caricate o ridimensionate su
 * richiesta.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class ImageManager {
	/**
	 * Percorso base per le immagini delle carte
	 */
	private static final String BASE_PATH = "/assets/small/";

	/**
	 * Percorso dell'immagine retro della carta
	 */
	private static final String BACK_IMAGE_PATH = Config.getIconPathRetrocarta();

	/**
	 * Mappa delle immagini delle carte caricate
	 */
	private Map<String, ImageIcon> cardImages = new HashMap<>();

	/**
	 * Mappa delle immagini delle carte ridimensionate
	 */
	private Map<String, ImageIcon> scaledCardImages = new HashMap<>();

	/**
	 * Costruttore della classe ImageManager.
	 * 
	 * Carica tutte le immagini delle carte durante l'inizializzazione.
	 * 
	 */
	public ImageManager() {
		caricaImmaginiCarte();
	}

	/**
	 * Carica tutte le immagini delle carte all'inizio.
	 */
	private void caricaImmaginiCarte() {
		for (Carte carteEnum : Carte.values()) {
			String cardName = carteEnum.toString();
			ImageIcon icon = caricaImmagine(BASE_PATH + cardName + ".png");
			cardImages.put(cardName, icon);
		}
		cardImages.put("BACK", caricaImmagine(BACK_IMAGE_PATH));
	}

	/**
	 * Carica un'immagine dalla data path.
	 *
	 * @param path Il percorso dell'immagine.
	 * @return ImageIcon caricata.
	 */
	private ImageIcon caricaImmagine(String path) {
		try {
			return new ImageIcon(getClass().getResource(path));
		} catch (Exception e) {
			System.err.println("Caricamento immagine fallito: " + path);
			return null;
		}
	}

	/**
	 * Restituisce un'immagine della carta specificata, ridimensionata secondo le
	 * dimensioni fornite.
	 * 
	 * Se l'immagine richiesta non e' stata precedentemente ridimensionata, la
	 * ridimensiona e la memorizza per richieste future.
	 * 
	 * 
	 * @param carta     La carta di cui si desidera l'immagine.
	 * @param dimension Le dimensioni desiderate per l'immagine.
	 * @return L'icona dell'immagine ridimensionata. Potrebbe essere null se
	 *         l'immagine non viene caricata correttamente.
	 */
	public ImageIcon getCardImage(Carta carta, Dimension dimension) {
		String key = carta.isScoperta() ? carta.toString() : "BACK";
		if (!scaledCardImages.containsKey(key)) {
			ImageIcon originalImage = cardImages.get(key);
			if (originalImage != null) {
				Image scaledImage = originalImage.getImage().getScaledInstance(dimension.width, dimension.height,
						Image.SCALE_SMOOTH);
				ImageIcon scaledIcon = new ImageIcon(scaledImage);
				scaledCardImages.put(key, scaledIcon);
			}
		}
		return scaledCardImages.get(key); // potrebbe essere null se l'immagine non e' stata caricata correttamente
	}
}
