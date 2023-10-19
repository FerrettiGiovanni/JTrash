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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import JTrash.controller.util.EseguiPartita;
import JTrash.model.data.Profilo;
import JTrash.model.manager.ProfiloAssigner;

/**
 * Questo pannello consente all'utente di selezionare un profilo esistente.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class SelezioneProfiloPanel extends JPanel {
	
	/**
	 * Serial version UID utilizzato per la serializzazione.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ComboBox utilizzata per selezionare un profilo tra quelli esistenti.
	 */
	private JComboBox<Profilo> comboBox;

	/**
	 * L'immagine GIF visualizzata nello sfondo del pannello.
	 */
	private Image gifImage;

	/**
	 * Pulsante per confermare la selezione del profilo.
	 */
	private JButton confirmButton;

	/**
	 * Crea un nuovo pannello di selezione del profilo.
	 *
	 * @param profili         La lista dei profili disponibili.
	 * @param gifPath         Il percorso dell'immagine di sfondo.
	 * @param confirmListener L'ascoltatore per il pulsante di conferma.
	 */
	public SelezioneProfiloPanel(List<Profilo> profili, String gifPath, ActionListener confirmListener) {
		gifImage = new ImageIcon(gifPath).getImage();

		setOpaque(false);
		setLayout(new BorderLayout());

		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

		JLabel label = new JLabel("Seleziona il profilo:");
		label.setFont(Config.getGaugeBold22());
		label.setForeground(Color.WHITE);
		label.setAlignmentX(CENTER_ALIGNMENT);

		comboBox = new JComboBox<>(profili.toArray(new Profilo[0]));
		comboBox.setFont(Config.getGaugeBold22());
		comboBox.setForeground(Color.WHITE);
		comboBox.setOpaque(true); // Rendilo opaco
		comboBox.setBackground(new Color(188, 185, 167, 235)); // Sfondo grigio opaco
		comboBox.setBorder(BorderFactory.createEmptyBorder());
		comboBox.setAlignmentX(CENTER_ALIGNMENT);

		int desiredWidth = 960;
		int reducedWidth = (int) (desiredWidth * 0.4);
		comboBox.setMaximumSize(new Dimension(reducedWidth, comboBox.getPreferredSize().height));

		comboBox.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			/**
			 * Sovrascrive il metodo getListCellRendererComponent per personalizzare la visualizzazione degli elementi
			 * all'interno della JComboBox. Ogni elemento (Profilo) viene visualizzato con il suo avatar e nickname.
			 *
			 * @param list La lista da cui e' tratta la cella.
			 * @param value L'oggetto che rappresenta l'elemento della lista.
			 * @param index L'indice dell'oggetto all'interno della lista.
			 * @param isSelected Indica se l'elemento e' selezionato.
			 * @param cellHasFocus Indica se l'elemento ha il focus.
			 * @return Il componente (JLabel) che visualizza l'elemento della lista.
			 */
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				label.setOpaque(false);
				label.setHorizontalAlignment(JLabel.CENTER);

				if (value instanceof Profilo) {
					Profilo profile = (Profilo) value;
					URL avatarURL = getClass().getResource(profile.getAvatar());
					ImageIcon avatarIcon = new ImageIcon(avatarURL);
					Image avatar = avatarIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
					label.setIcon(new ImageIcon(avatar));
					label.setText(profile.getNickname());
					label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(),
							label.getFont().getSize() * 2));
				}
				return label;
			}
		});

		confirmButton = new JButton("Conferma");
		confirmButton.setFont(Config.getGaugeBold22());
		confirmButton.setOpaque(false);
		confirmButton.setContentAreaFilled(false);
		confirmButton.setBorderPainted(false);
		confirmButton.setForeground(Color.WHITE);
		confirmButton.setAlignmentX(CENTER_ALIGNMENT);
		confirmButton.addActionListener(e -> {
			Profilo selectedProfilo = (Profilo) comboBox.getSelectedItem();
			if (selectedProfilo != null) {
				ProfiloAssigner.getInstance().setProfiloGiocatoreUmano(selectedProfilo);

				confirmListener.actionPerformed(new ActionEvent(selectedProfilo, e.getID(), e.getActionCommand()));

				// Inizia una nuova partita in un thread separato usando SwingWorker
				new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						@SuppressWarnings("unused")
						EseguiPartita eseguiPartita = new EseguiPartita();
						return null;
					}

					/**
					 * Questo metodo viene invocato dopo che il metodo 'doInBackground' ha completato
					 * la sua esecuzione. Può essere utilizzato per aggiornare l'interfaccia utente
					 * o eseguire altre operazioni post-elaborazione.
					 */
					@Override
					protected void done() {
						// Questo metodo verrà chiamato quando 'doInBackground' e' terminato.
						// Puoi aggiungere qui il codice per aggiornare l'UI se necessario.
					}
				}.execute();
			}
		});

		centerPanel.add(Box.createVerticalGlue());
		centerPanel.add(label);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		centerPanel.add(comboBox);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		centerPanel.add(confirmButton);
		centerPanel.add(Box.createVerticalGlue());

		add(centerPanel, BorderLayout.CENTER);
		add(Box.createRigidArea(new Dimension(0, (getHeight() / 2) + 200)), BorderLayout.NORTH);
		setPreferredSize(new Dimension(300, 150));
	}

	/**
	 * Restituisce il pulsante di conferma.
	 *
	 * @return Il pulsante di conferma.
	 */
	public JButton getConfirmButton() {
		return confirmButton;
	}

	/**
	 * Sovrascrive il metodo di disegno di default per disegnare l'immagine di
	 * sfondo all'interno di questo pannello.
	 *
	 * @param g Il contesto grafico su cui disegnare l'immagine.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.drawImage(gifImage, 0, 0, width, height, this);
	}
}