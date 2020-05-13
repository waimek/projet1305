package fr.eni.ecole.troc_encheres.bo;

import java.util.Date;

public class Vente {
	
	private int numero;
	private String nomArticle;
	private String description;
	private Date dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private Utilisateur util;
	private Categorie categorie;
	
	
	public Vente(int numero, String nomArticle, String description, Date dateFinEncheres, int miseAPrix, int prixVente,
			Utilisateur util, Categorie categorie) {
		
		this.numero = numero;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.util = util;
		this.categorie = categorie;
	}

	public Vente(String nomArticle, String description, Date dateFinEncheres, int miseAPrix, int prixVente,
			Utilisateur util, Categorie categorie) {
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.util = util;
		this.categorie = categorie;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Utilisateur getUtil() {
		return util;
	}
	public void setUtil(Utilisateur util) {
		this.util = util;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public int getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	@Override
	public String toString() {
		return "Vente [numero=" + numero + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix + ", prixVente=" + prixVente
				+ ", util=" + util + ", categorie=" + categorie + "]";
	}
	
	
}
