package fr.eni.ecole.troc_encheres.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur implements Serializable {


	private static final long serialVersionUID = -4072815160529639421L;
	
	private int numero;
	private String pseudo;
	private String nom; 
	private String prenom; 
	private String email;
	private String tel; 
	private String rue; 
	private String cp;
	private String ville; 
	private String mdp; 
	private int credit; 
	private boolean admin=false;
	//public List<Vente> ventes = new ArrayList<>();
	
	
	public Utilisateur(int numero, String pseudo, String nom, String prenom, String email, String tel, String rue,
			String cp, String ville, String mdp, int credit) {
		this.numero = numero;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
		this.mdp = mdp;
		this.credit = credit;
	}
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String tel, String rue, String cp,
			String ville, String mdp, int credit) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
		this.mdp = mdp;
		this.credit = credit;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	@Override
	public String toString() {
		return "Utilisateur [numero=" + numero + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom
				+ ", email=" + email + ", tel=" + tel + ", rue=" + rue + ", cp=" + cp + ", ville=" + ville + ", mdp="
				+ mdp + ", credit=" + credit + ", admin=" + admin + "]";
	}
	
	
	
}
