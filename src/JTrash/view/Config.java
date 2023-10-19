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

import java.awt.Font;

/**
 * Classe di configurazione per l'applicazione JTrash. Questa classe contiene
 * variabili statiche utilizzate per impostare le impostazioni
 * dell'applicazione, come font, percorsi delle immagini e suoni.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class Config {

	/**
	 * Titolo dell'applicazione.
	 */
	private static final String TITOLO = "JTrash - Giovanni Ferretti";

	// FONT
	/**
	 * Font personalizzato "Gauge" in grassetto di dimensione 22.
	 */
	private static final Font GAUGE_BOLD22 = new Font("Gauge", Font.BOLD, 22);
	
	/**
	 * Font personalizzato "Gauge" in grassetto di dimensione 16.
	 */
	private static final Font GAUGE_BOLD16 = new Font("Gauge", Font.BOLD, 16);
	
	/**
	 * Font personalizzato "Gauge" in grassetto di dimensione 12.
	 */
	private static final Font GAUGE_BOLD12 = new Font("Gauge", Font.BOLD, 12);
	
	/**
	 * Font personalizzato "Gauge" in grassetto di dimensione 10.
	 */
	private static final Font GAUGE_BOLD8 = new Font("Gauge", Font.BOLD, 10);

	// IMMAGINI
	/**
	 * Percorso base per le immagini delle carte.
	 */
	private static final String CARD_BASE_PATH = "/assets/small/";
	
	/**
	 * Percorso dell'immagine di sfondo.
	 */
	private static final String ICON_PATH_BACKGROUND = "/assets/Giovanni_Ferretti_3d_render_of_a_background_for_a_videogame_of__0b7fb641-75ea-4667-88c4-690f086a459f.png";
	
	/**
	 * Percorso dell'immagine per il retro della carta.
	 */
	private static final String ICON_PATH_RETROCARTA = "/assets/small/retro5.png";
	
	/**
	 * Percorso del GIF iniziale.
	 */
	private static final String GIF_INITIAL_PATH = "/assets/GIF/gifIniziale4.gif";
	
	/**
	 * Percorso del GIF di sfondo.
	 */
	private static final String GIF_BACKGROUND_PATH = "/assets/GIF/gifBackground.gif";

	// AUDIO
	/**
	 * Musica di sfondo del gioco.
	 */
	private static final String GAME_MUSIC = "/assets/audio/softmeadow2.wav";
	
	/**
	 * Effetto sonoro per l'azione "pesca".
	 */
	private static final String SOUND1 = "/assets/audio/pesca.wav";

	/**
	 * Effetto sonoro per l'azione "mescolare".
	 */
	private static final String SOUND2 = "/assets/audio/shuffle2.wav";

	/**
	 * Brano musicale iniziale.
	 */
	private static final String SOUND6 = "/assets/audio/sansevieria2.wav";

	/**
	 * Effetto sonoro voce "TRASH".
	 */
	private static final String SOUND7 = "/assets/audio/TRASH.wav";

	/**
	 * Effetto sonoro per l'azione "hai perso".
	 */
	private static final String SOUND8 = "/assets/audio/Youlostthegame.wav";

	/**
	 * Effetto sonoro per l'azione "congratulazioni".
	 */
	private static final String SOUND9 = "/assets/audio/Congratulations.wav";

	/**
	 * Restituisce il font GAUGE in grassetto con dimensione 22.
	 *
	 * @return Il font GAUGE in grassetto con dimensione 22.
	 */
	public static Font getGaugeBold22() {
		return GAUGE_BOLD22;
	}

	/**
	 * Restituisce il font GAUGE in grassetto con dimensione 16.
	 *
	 * @return Il font GAUGE in grassetto con dimensione 16.
	 */
	public static Font getGaugeBold16() {
		return GAUGE_BOLD16;
	}

	/**
	 * Restituisce il font GAUGE in grassetto con dimensione 12.
	 *
	 * @return Il font GAUGE in grassetto con dimensione 12.
	 */
	public static Font getGaugeBold12() {
		return GAUGE_BOLD12;
	}

	/**
	 * Restituisce il font GAUGE in grassetto con dimensione 10.
	 *
	 * @return Il font GAUGE in grassetto con dimensione 10.
	 */
	public static Font getGaugeBold10() {
		return GAUGE_BOLD8;
	}

	/**
	 * Restituisce il titolo dell'applicazione.
	 *
	 * @return Il titolo dell'applicazione.
	 */
	public static String getTitolo() {
		return TITOLO;
	}

	/**
	 * Restituisce il percorso base delle carte.
	 *
	 * @return Il percorso base delle carte.
	 */
	public static String getCardBasePath() {
		return CARD_BASE_PATH;
	}

	/**
	 * Restituisce il percorso dell'immagine di sfondo.
	 *
	 * @return Il percorso dell'immagine di sfondo.
	 */
	public static String getIconPathBackground() {
		return ICON_PATH_BACKGROUND;
	}

	/**
	 * Restituisce il percorso dell'icona retro della carta.
	 *
	 * @return Il percorso dell'icona retro della carta.
	 */
	public static String getIconPathRetrocarta() {
		return ICON_PATH_RETROCARTA;
	}

	/**
	 * Restituisce il percorso del file GIF iniziale.
	 *
	 * @return Il percorso del file GIF iniziale.
	 */
	public static String getGifInitialPath() {
		return GIF_INITIAL_PATH;
	}

	/**
	 * Restituisce il percorso del file GIF di sfondo.
	 *
	 * @return Il percorso del file GIF di sfondo.
	 */
	public static String getGifBackgroundPath() {
		return GIF_BACKGROUND_PATH;
	}

	/**
	 * Restituisce il percorso del file audio della musica di gioco.
	 *
	 * @return Il percorso del file audio della musica di gioco.
	 */
	public static String getGameMusic() {
		return GAME_MUSIC;
	}

	/**
	 * Restituisce il percorso del file audio SOUND1.
	 *
	 * @return Il percorso del file audio SOUND1.
	 */
	public static String getSound1() {
		return SOUND1;
	}

	/**
	 * Restituisce il percorso del file audio SOUND2.
	 *
	 * @return Il percorso del file audio SOUND2.
	 */
	public static String getSound2() {
		return SOUND2;
	}

	/**
	 * Restituisce il percorso del file audio SOUND6.
	 *
	 * @return Il percorso del file audio SOUND6.
	 */
	public static String getSound6() {
		return SOUND6;
	}

	/**
	 * Restituisce il percorso del file audio SOUND7.
	 *
	 * @return Il percorso del file audio SOUND7.
	 */
	public static String getSound7() {
		return SOUND7;
	}

	/**
	 * Restituisce il percorso del file audio SOUND8.
	 *
	 * @return Il percorso del file audio SOUND8.
	 */
	public static String getSound8() {
		return SOUND8;
	}

	/**
	 * Restituisce il percorso del file audio SOUND9.
	 *
	 * @return Il percorso del file audio SOUND9.
	 */
	public static String getSound9() {
		return SOUND9;
	}
}
