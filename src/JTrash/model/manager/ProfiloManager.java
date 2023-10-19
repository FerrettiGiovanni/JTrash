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

package JTrash.model.manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import JTrash.model.data.Profilo;

/**
 * La classe ProfiloManager fornisce metodi per salvare, caricare e aggiornare i
 * profili dei giocatori su disco.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class ProfiloManager {

	/**
	 * Salva un singolo profilo su disco.
	 * 
	 * @param profilo  Il profilo da salvare.
	 * @param filename Il nome del file in cui salvare il profilo.
	 * @throws IOException Se si verifica un errore durante la scrittura sul disco.
	 */
	public static void salvaProfilo(Profilo profilo, String filename) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(profilo);
		}
	}

	/**
	 * Salva tutti i profili forniti su disco.
	 * 
	 * @param profili  La lista dei profili da salvare.
	 * @param filename Il nome del file in cui salvare i profili.
	 * @throws IOException Se si verifica un errore durante la scrittura sul disco.
	 */
	public static void salvaTuttiIProfili(List<Profilo> profili, String filename) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(profili);
		}
	}

	/**
	 * Aggiorna un profilo esistente o, se non esiste, aggiunge un nuovo profilo
	 * alla lista, e successivamente salva la lista aggiornata su disco.
	 * 
	 * @param profiloAggiornato Il profilo da aggiornare o aggiungere.
	 * @param filename          Il nome del file in cui sono salvati i profili.
	 * @throws IOException            Se si verifica un errore durante la lettura o
	 *                                la scrittura sul disco.
	 * @throws ClassNotFoundException Se si verifica un errore durante la
	 *                                deserializzazione.
	 */
	public static void aggiornaESalvaProfilo(Profilo profiloAggiornato, String filename)
			throws IOException, ClassNotFoundException {
		List<Profilo> profiliEsistenti = caricaTuttiIProfili(filename);

		int indiceProfiloEsistente = -1;
		for (int i = 0; i < profiliEsistenti.size(); i++) {
			if (profiliEsistenti.get(i).getNickname().equals(profiloAggiornato.getNickname())) {
				indiceProfiloEsistente = i;
				break;
			}
		}

		if (indiceProfiloEsistente != -1) {
			profiliEsistenti.set(indiceProfiloEsistente, profiloAggiornato);
		} else {
			profiliEsistenti.add(profiloAggiornato);
		}

		salvaTuttiIProfili(profiliEsistenti, filename);
	}

	/**
	 * Carica tutti i profili da disco.
	 * 
	 * @param filename Il nome del file da cui caricare i profili.
	 * @return Una lista di profili.
	 * @throws IOException            Se si verifica un errore durante la lettura
	 *                                dal disco.
	 * @throws ClassNotFoundException Se si verifica un errore durante la
	 *                                deserializzazione.
	 */
	@SuppressWarnings("unchecked")
	public static List<Profilo> caricaTuttiIProfili(String filename) throws IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			return (List<Profilo>) ois.readObject();
		}
	}

}
