package fr.eni.ecole.troc_encheres.bo;

public class Retrait {
	
	private Vente vente;
	private String rue; 
	private String ville; 
	private String cp;
	
	
	public Retrait(Vente vente, String rue, String ville, String cp) {
		this.vente = vente;
		this.rue = rue;
		this.ville = ville;
		this.cp = cp;
	}
	
	public Vente getVente() {
		return vente;
	}
	public void setVente(Vente vente) {
		this.vente = vente;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	
	
}
