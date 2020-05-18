package fr.eni.ecole.troc_encheres.bo;
/*
 * @author Edouard
 */
public class Categorie {

	private int numero; 
	private String libelle;
	
	public Categorie(int numero, String libelle) {
		this.numero = numero;
		this.libelle = libelle;
	}
	
	public Categorie(String libelle) {
		this.libelle = libelle;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
