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

import java.awt.CardLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import JTrash.controller.audio.SimpleAudioManager;
import JTrash.controller.util.NumeroGiocatoriController;
import JTrash.model.data.Profilo;
import JTrash.model.manager.ProfiloManager;

/**
 * La classe principale dell'applicazione JTrash.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class MainFrame extends JFrame {
	
	/** Numero di serie per la serializzazione. */
	private static final long serialVersionUID = 1L; 
	
	/** 
	 * Gestisce l'alternanza tra i diversi pannelli (JPanel) all'interno del frame.
	 * Permette di mostrare un pannello alla volta e di nascondere gli altri.
	 */
	private CardLayout cardLayout; 

	/** 
	 * Pannello principale che contiene altri pannelli.
	 * Questi pannelli secondari possono essere mostrati o nascosti in base al cardLayout.
	 */
	private JPanel cardsPanel; 

	/** 
	 * Lista dei profili degli utenti.
	 * Questi profili possono rappresentare le impostazioni o i dati relativi a ogni utente del gioco.
	 */
	private List<Profilo> profili;

	/**
	 * Crea una nuova istanza di MainFrame.
	 */
	public MainFrame() {
		setTitle(Config.getTitolo()); // Imposta il titolo del frame.
		cardLayout = new CardLayout(); // Inizializzazione del CardLayout e del pannello principale.
		cardsPanel = new JPanel(cardLayout);

		// Usa getResource() per caricare le GIF.
		GifInizialePanel gifInizialePanel = new GifInizialePanel(getClass().getResource(Config.getGifInitialPath()).getPath());
		GifBackgroundPanel gifBackgroundPanel = new GifBackgroundPanel(
				getClass().getResource(Config.getGifBackgroundPath()).getPath(), e -> {
					cardLayout.show(cardsPanel, "PLAYER_SELECTION_PANEL");
				});

		NumeroGiocatoriController numeroGiocatoriController = new NumeroGiocatoriController();
		SelezioneGiocatoriPanel selezioneGiocatoriPanel = new SelezioneGiocatoriPanel(
				getClass().getResource(Config.getGifBackgroundPath()).getPath(), e -> {
					numeroGiocatoriController.actionPerformed(e);
					cardLayout.show(cardsPanel, "PROFILE_SELECTION_PANEL");
				});

		// Imposta il pannello GIF come pannello visibile iniziale.
		cardsPanel.add(gifInizialePanel, "GIF_PANEL");
		cardsPanel.add(gifBackgroundPanel, "BACKGROUD_PANEL");
		cardsPanel.add(selezioneGiocatoriPanel, "PLAYER_SELECTION_PANEL");

		cardLayout.show(cardsPanel, "GIF_PANEL");
		SimpleAudioManager.getInstance().playSound6();

		// Crea un timer che mostra il pannello di sfondo dopo la durata del GIF.
		int gifDuration = gifInizialePanel.getDurataGif();
		Timer timer = new Timer(gifDuration, e -> {
			cardLayout.show(cardsPanel, "BACKGROUD_PANEL");
		});
		timer.setRepeats(false);
		timer.start();

		// Carica i profili degli utenti e inizializza il pannello di selezione del profilo.
		try {
			profili = ProfiloManager.caricaTuttiIProfili("profili.dat");
			String gifPathForProfile = getClass().getResource(Config.getGifBackgroundPath()).getPath();

			SelezioneProfiloPanel profileSelection = new SelezioneProfiloPanel(profili, gifPathForProfile, e -> {
			});

			JButton confermaButton = profileSelection.getConfirmButton();
			confermaButton.addActionListener(e -> {
				TavoloDiGioco tavoloDiGioco = new TavoloDiGioco();

				SimpleAudioManager.getInstance().stopWithFadeOut();

				cardsPanel.add(tavoloDiGioco, "GAME_PANEL");
				cardLayout.show(cardsPanel, "GAME_PANEL");
			});

			cardsPanel.add(profileSelection, "PROFILE_SELECTION_PANEL");
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		JButton btnStatistiche = gifBackgroundPanel.getStatisticsButton();
		btnStatistiche.addActionListener(e -> {
			if (profili != null) {
				StatistichePanel statistichePanel = new StatistichePanel(profili,
						getClass().getResource(Config.getGifBackgroundPath()).getPath(), backEvent -> {
							cardLayout.show(cardsPanel, "BACKGROUD_PANEL");
						});
				cardsPanel.add(statistichePanel, "STATISTICHE_PANEL");
				cardLayout.show(cardsPanel, "STATISTICHE_PANEL");
			} else {
				JOptionPane.showMessageDialog(this, "Errore nel caricamento dei profili.");
			}
		});

		setContentPane(cardsPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(960, 540);
		setLocationRelativeTo(null);
	}
}
