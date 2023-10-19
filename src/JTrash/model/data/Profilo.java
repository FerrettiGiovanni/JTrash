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

import java.io.Serializable;
import java.util.Objects;

/**
 * Rappresenta un profilo di un giocatore all'interno del gioco JTrash.
 * 
 * @since 1.0, 10-09-2023
 * @author Giovanni Ferretti
 */
public class Profilo implements Serializable {
	/**
     * ID per la serializzazione. Utilizzato per garantire la compatibilità
     * tra versioni diverse di classi serializzate.
     */
	private static final long serialVersionUID = 1L;
	
	/**
     * Soprannome del giocatore.
     */
	private String nickname;
	
	/**
     * Percorso o nome dell'avatar del giocatore.
     */
	private String avatar;
	
	/**
     * Numero totale di partite giocate dal giocatore.
     */
	private int partiteGiocate;
	
	/**
     * Numero totale di partite vinte dal giocatore.
     */
	private int partiteVinte;
	
	/**
     * Numero totale di partite perse dal giocatore.
     */
	private int partitePerse;

	/**
	 * Converte l'oggetto Profilo in una rappresentazione stringa.
	 * 
	 * @return Una stringa che descrive il profilo del giocatore, 
	 *         includendo nickname, avatar e statistiche sulle partite.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nickname: ").append(nickname).append("\n");
		sb.append("Avatar: ").append(avatar).append("\n");
		sb.append("Partite Giocate: ").append((int) partiteGiocate).append("\n");
		sb.append("Partite Vinte: ").append((int) partiteVinte).append("\n");
		sb.append("Partite Perse: ").append((int) partitePerse).append("\n");

		return sb.toString();
	}

	/**
	 * Restituisce il nickname del profilo.
	 * 
	 * @return Il nickname del profilo.
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Imposta il nickname del profilo.
	 * 
	 * @param nickname Il nuovo nickname.
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Restituisce l'avatar del profilo.
	 * 
	 * @return L'avatar del profilo.
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * Imposta l'avatar del profilo.
	 * 
	 * @param avatar Il nuovo avatar.
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * Restituisce il numero di partite giocate.
	 * 
	 * @return Il numero di partite giocate.
	 */
	public int getPartiteGiocate() {
		return partiteGiocate;
	}

	/**
	 * Imposta il numero di partite giocate.
	 * 
	 * @param partiteGiocate Il nuovo numero di partite giocate.
	 */
	public void setPartiteGiocate(int partiteGiocate) {
		this.partiteGiocate = partiteGiocate;
	}

	/**
	 * Restituisce il numero di partite vinte.
	 * 
	 * @return Il numero di partite vinte.
	 */
	public int getPartiteVinte() {
		return partiteVinte;
	}

	/**
	 * Imposta il numero di partite vinte.
	 * 
	 * @param partiteVinte Il nuovo numero di partite vinte.
	 */
	public void setPartiteVinte(int partiteVinte) {
		this.partiteVinte = partiteVinte;
	}

	/**
	 * Restituisce il numero di partite perse.
	 * 
	 * @return Il numero di partite perse.
	 */
	public int getPartitePerse() {
		return partitePerse;
	}

	/**
	 * Imposta il numero di partite perse.
	 * 
	 * @param partitePerse Il nuovo numero di partite perse.
	 */
	public void setPartitePerse(int partitePerse) {
		this.partitePerse = partitePerse;
	}

	/**
     * Confronta questo profilo con un altro oggetto per determinare se sono uguali.
     * 
     * Due profili sono considerati uguali se hanno lo stesso nickname, 
     * assumendo che il nickname sia un attributo univoco per ciascun profilo.
     * 
     * 
     * @param obj L'oggetto da confrontare con questo profilo.
     * @return {@code true} se questo oggetto e' uguale all'oggetto specificato come argomento, 
     *         {@code false} altrimenti.
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Profilo profilo = (Profilo) obj;
		return Objects.equals(nickname, profilo.nickname); // assumendo che il nickname sia un attributo univoco
	}

	/**
     * Restituisce un hash code per questo profilo.
     * 
     * L'hash code di un profilo si basa sul suo nickname, 
     * assumendo che il nickname sia un attributo univoco per ciascun profilo.
     * 
     * 
     * @return Un valore hash code per questo oggetto.
     */
	@Override
	public int hashCode() {
		return Objects.hash(nickname); // assumendo che il nickname sia un attributo univoco
	}
}
