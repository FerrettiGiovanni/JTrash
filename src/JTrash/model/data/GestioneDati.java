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

package JTrash.model.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Classe responsabile della gestione dei dati relativi ai profili degli utenti.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class GestioneDati {

	/**
	 * Salva una lista di profili su file.
	 *
	 * @param profili Lista dei profili da salvare.
	 */
	public static void salvaProfili(List<Profilo> profili) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("profili.dat"))) {
			out.writeObject(profili);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carica una lista di profili da un file.
	 *
	 * @return Lista dei profili caricati dal file.
	 */
	@SuppressWarnings("unchecked")
	public static List<Profilo> caricaProfili() {
		List<Profilo> profili = null;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("profili.dat"))) {
			profili = (List<Profilo>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return profili;
	}
}
