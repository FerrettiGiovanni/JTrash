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

package JTrash.model.player;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe GiocatoreCpuFactory e' responsabile della creazione di giocatori
 * CPU.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class GiocatoreCpuFactory {

	/**
	 * Crea una lista di giocatori CPU in base al numero specificato.
	 *
	 * @param numeroCpu Il numero di giocatori CPU da creare.
	 * @return Una lista di giocatori CPU.
	 * @throws IllegalArgumentException Se il numero di CPU specificato e' inferiore
	 *                                  a 1 o superiore a 3.
	 */
	public static List<GiocatoreCpu> creaGiocatoriCpu(int numeroCpu) {
		if (numeroCpu < 1 || numeroCpu > 3) {
			throw new IllegalArgumentException("Numero di CPU non valido");
		}

		List<GiocatoreCpu> giocatoriCpu = new ArrayList<>();
		for (int i = 0; i < numeroCpu; i++) {
			giocatoriCpu.add(new GiocatoreCpu(i + 1)); // Passa l'indice come parametro
		}

		return giocatoriCpu;
	}
}
