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
 * Rappresenta l'azione di un "Match" nel gioco.
 * 
 * Un "Match" può essere posizionato al posto di una carta coperta sul tavolo
 * che abbia un numero uguale al numero del Match + 1. La carta coperta viene
 * quindi aggiunta alla mano del giocatore.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class AzioneMatch implements InterfaceAction {

	/**
	 * Implementa l'azione specifica che avviene quando un Match viene posizionato
	 * sul tavolo.
	 * 
	 * @param cartaInMano      La carta che il giocatore ha scelto di posizionare.
	 * @param carteSulTavolo   La lista di carte attualmente sul tavolo.
	 * @param mazzoDegliScarti Il mazzo degli scarti dove vengono messe le carte
	 *                         scartate.
	 * @param mano             La mano attuale del giocatore.
	 * @param giocatore        Il giocatore che sta eseguendo l'azione.
	 * @return La carta che e' stata sostituita dal Match, o null se la carta viene
	 *         scartata.
	 */
	@Override
	public Carta azionePosiziona(Carta cartaInMano, List<Carta> carteSulTavolo, MazzoDegliScarti mazzoDegliScarti,
			List<Carta> mano, AbstractGiocatore giocatore) {
		for (int i = 0; i < carteSulTavolo.size(); i++) {
			Carta cartaSulTavolo = carteSulTavolo.get(i);

			boolean isJolly = cartaSulTavolo.getAzione() instanceof AzioneJolly;

			boolean canReplace = (isJolly && cartaSulTavolo.isScoperta()) || !cartaSulTavolo.isScoperta();

			if (canReplace && cartaInMano.getValore().getNumero() == i + 1) {
				cartaSulTavolo.scopriCarta();
				carteSulTavolo.set(i, cartaInMano);
				cartaInMano.setScoperta(true);
				mano.add(cartaSulTavolo); // posiziono la carta cartaInMano al posto di cartaSulTavolo
				mano.remove(cartaInMano);

				if (giocatore instanceof GiocatoreUmano) {
					// Aggiorniamo StatoGioco
					StatoGioco.getInstance().setManoGiocatoreUmano(cartaSulTavolo);
					StatoGioco.getInstance().getManoGiocatoreUmano();
				}
				return cartaSulTavolo;
			}
		}
		scartaCarta(cartaInMano, mazzoDegliScarti, mano, giocatore);
		return null;
	}
}
