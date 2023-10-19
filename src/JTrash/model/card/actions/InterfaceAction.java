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

package JTrash.model.card.actions;

import java.util.List;

import JTrash.model.card.Carta;
import JTrash.model.card.MazzoDegliScarti;
import JTrash.model.observable.StatoGioco;
import JTrash.model.player.AbstractGiocatore;
import JTrash.model.player.GiocatoreUmano;

/**
 * Interfaccia che definisce le azioni che una carta può compiere nel gioco.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public interface InterfaceAction {

	/**
	 * Azione specifica da eseguire quando una carta viene posizionata sul tavolo.
	 *
	 * @param cartaInMano      La carta che il giocatore desidera posizionare.
	 * @param carteSulTavolo   Le carte attualmente presenti sul tavolo.
	 * @param mazzoDegliScarti Il mazzo degli scarti.
	 * @param mano             La mano del giocatore.
	 * @param giocatore        Il giocatore che esegue l'azione.
	 * @return Carta La carta che e' stata gestita o posizionata.
	 */
	Carta azionePosiziona(Carta cartaInMano, List<Carta> carteSulTavolo, MazzoDegliScarti mazzoDegliScarti,
			List<Carta> mano, AbstractGiocatore giocatore);

	/**
	 * Azione predefinita per scartare una carta.
	 *
	 * @param carta            La carta da scartare.
	 * @param mazzoDegliScarti Il mazzo dove la carta verrà scartata.
	 * @param mano             La mano del giocatore.
	 * @param giocatore        Il giocatore che esegue l'azione di scarto.
	 */
	default void scartaCarta(Carta carta, MazzoDegliScarti mazzoDegliScarti, List<Carta> mano,
			AbstractGiocatore giocatore) {
		StatoGioco.getInstance().setManoGiocatoreUmano(null);
		StatoGioco.getInstance().getManoGiocatoreUmano();
		try {
			Thread.sleep(400); // Pausa di 400 millisecondi
		} catch (InterruptedException e) {
			e.printStackTrace(); // Stampa l'errore se l'attesa viene interrotta
		}
		if (giocatore instanceof GiocatoreUmano) {
			MazzoDegliScarti.getInstance().aggiungiCarta(carta); // non e' possibile posizionare la carta quindi viene scartata
			StatoGioco.getInstance().setScartiCarta(MazzoDegliScarti.getInstance().getCartaInCima()); // giocatore umano ha scartato
			mano.remove(carta);
		} else {
			try {
				Thread.sleep(400); // Pausa di 400 millisecondi
			} catch (InterruptedException e) {
				e.printStackTrace(); // Stampa l'errore se l'attesa viene interrotta
			}
			MazzoDegliScarti.getInstance().aggiungiCarta(carta);
			StatoGioco.getInstance().setScartiCarta(MazzoDegliScarti.getInstance().getCartaInCima()); // giocatore cpu ha scartato
			mano.remove(carta);
		}
	}
}
