package fr.eni.ecole.troc_encheres.bo;
import java.util.Date;

/**
 * @author Edouard
 */
public class Enchere {
	
	private Date dateEnchere;
	private Utilisateur util;
	private Vente vente;
	
	
	public Enchere(Date dateEnchere, Utilisateur util, Vente vente) {
		this.dateEnchere = dateEnchere;
		this.util = util;
		this.vente = vente;
	}
	
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public Utilisateur getUtil() {
		return util;
	}
	public void setUtil(Utilisateur util) {
		this.util = util;
	}
	public Vente getVente() {
		return vente;
	}
	public void setVente(Vente vente) {
		this.vente = vente;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", util=" + util + ", vente=" + vente + "]";
	}
	
}
