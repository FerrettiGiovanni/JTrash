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

package JTrash.controller.audio;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import JTrash.view.Config;

/**
 * Classe responsabile della gestione e riproduzione dei suoni nel gioco.
 * 
 * Il {@code SimpleAudioManager} permette di gestire l'audio in modo efficiente, 
 * fornendo metodi per riprodurre, mettere in pausa e fermare vari suoni. Utilizza 
 * il pattern Singleton per assicurare che vi sia una sola istanza del manager 
 * dell'audio durante l'esecuzione dell'applicazione, evitando sovrapposizioni 
 * o conflitti audio.
 * 
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class SimpleAudioManager {

	/**
	 * Enumerazione che definisce i diversi tipi di suoni disponibili nel gioco.
	 * Ogni tipo di suono fa riferimento a una risorsa audio specifica, definita
	 * nella classe di configurazione {@code Config}.
	 */
	public enum SoundType {
		
		/**
	     * Musica di sottofondo principale del gioco.
	     */
	    GAME_MUSIC(Config.getGameMusic()), 
	    
	    /**
	     * Effetto sonoro per l'azione "pesca"
	     */
	    SOUND_1(Config.getSound1()), 
	    
	    /**
		 * Effetto sonoro per l'azione "mescolare".
		 */
	    SOUND_2(Config.getSound2()), 
	    
	    /**
		 * Brano musicale iniziale.
		 */
	    SOUND_6(Config.getSound6()), 
	    
	    /**
		 * Effetto sonoro voce "TRASH".
		 */
	    SOUND_7(Config.getSound7()), 
	    
	    /**
		 * Effetto sonoro per l'azione "hai perso".
		 */
	    SOUND_8(Config.getSound8()),
	    
	    /**
		 * Effetto sonoro per l'azione "congratulazioni".
		 */
		SOUND_9(Config.getSound9());

		private final String path;

		/**
		 * Costruttore dell'enumerazione.
		 *
		 * @param path Percorso del file audio associato al tipo di suono.
		 */
		SoundType(String path) {
			this.path = path;
		}

		/**
		 * Ottiene il percorso del file audio.
		 *
		 * @return Il percorso del file audio.
		 */
		public String getPath() {
			return path;
		}
	}

	/**
	 * Istanza singleton di SimpleAudioManager.
	 */
	private static final SimpleAudioManager INSTANCE = new SimpleAudioManager();

	/**
	 * Clip utilizzato specificamente per il suono SOUND_6.
	 */
	private Clip currentSound6Clip = null;

	/**
	 * Ritorna l'unica istanza di SimpleAudioManager.
	 *
	 * @return L'istanza di SimpleAudioManager.
	 */
	private SimpleAudioManager() {
	}

	/**
	 * Fornisce l'accesso all'istanza singleton di SimpleAudioManager.
	 *
	 * @return L'istanza singleton di SimpleAudioManager.
	 */
	public static SimpleAudioManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Riproduce un suono specificato con un dato volume e opzione di loop.
	 *
	 * @param soundType Tipo di suono da riprodurre.
	 * @param volume    Livello del volume di riproduzione.
	 * @param loop      Se true, il suono verrà riprodotto in loop.
	 */
	public void play(SoundType soundType, float volume, boolean loop) {
		String resourcePath = soundType.getPath();

		try {
			// Carica il file audio
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(resourcePath));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);

			// Se il suono da riprodurre e' SOUND_6, lo assegna a currentSound6Clip
			if (soundType == SoundType.SOUND_6) {
				currentSound6Clip = clip;
			}

			// Imposta il volume
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);

			// Aggiunge un listener per chiudere il suono una volta completato
			clip.addLineListener(event -> {
				if (event.getType() == LineEvent.Type.STOP) {
					event.getLine().close();
				}
			});

			// Controlla se il suono deve essere riprodotto in loop
			if (loop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.start();
			}

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Riproduce la musica del gioco.
	 */
	public void playGameMusic() {
		play(SoundType.GAME_MUSIC, 0.0f, true);
	}

	/**
	 * Riproduce la musica del percorso specificato.
	 */
	public void playSound1() {
		play(SoundType.SOUND_1, -5.0f, false);
	}

	/**
	 * Riproduce la musica del percorso specificato.
	 */
	public void playSound2() {
		play(SoundType.SOUND_2, 0.0f, false);
	}

	/**
	 * Riproduce la musica del percorso specificato.
	 */
	public void playSound6() {
		play(SoundType.SOUND_6, -5.0f, false);
	}

	/**
	 * Riproduce la musica del percorso specificato.
	 */
	public void playSound7() {
		play(SoundType.SOUND_7, 0.0f, false);
	}

	/**
	 * Riproduce la musica del percorso specificato.
	 */
	public void playSound8() {
		play(SoundType.SOUND_8, 0.0f, false);
	}

	/**
	 * Riproduce la musica del percorso specificato.
	 */
	public void playSound9() {
		play(SoundType.SOUND_9, 0.0f, false);
	}

	/**
	 * Ferma la riproduzione del suono SOUND_6 con un effetto di dissolvenza.
	 */
	public void stopWithFadeOut() {
		if (currentSound6Clip != null && currentSound6Clip.isRunning()) {
			new Thread(() -> {
				FloatControl gainControl = (FloatControl) currentSound6Clip.getControl(FloatControl.Type.MASTER_GAIN);
				float currentVolume = gainControl.getValue();

				// Riduce gradualmente il volume fino a -80.0f
				for (float volume = currentVolume; volume > -80.0f; volume -= 1.0f) {
					gainControl.setValue(volume);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// Ferma il suono e annulla il riferimento
				currentSound6Clip.stop();
				currentSound6Clip = null;
			}).start();
		}
	}
}
