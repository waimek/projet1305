<!-- author Dominika -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ include file="entete.jsp"%>
<h2>Créer un compte</h2>

<%
	List<String> listeErreur = (List<String>) request.getAttribute("listeErreur");
if (listeErreur != null) { %>
<p style="color: red;">
	Erreur :
	<% 
	for (int i = 0; i < listeErreur.size(); i++) {
%>
	<%=listeErreur.get(i)%></p>
<%
}
}
%>


<form action="${pageContext.servletContext.contextPath}/nouveauCompte"
	method="post">


	<div class="form-row">
		<div class="form-group col-md-6 col-sm-12 ">
			<label style ="margin-left:12px"for="lblPseudo">Pseudo :</label> <input type="text"
				class="form-control" placeholder="Pseudo" name="pseudo" id="pseudo"
				required>
		</div>
		<div class="form-group col-md-6 col-sm-12">
			<label style ="margin-left:12px" for="lblNom" >Nom :</label> <input type="text"
				class="form-control" placeholder="Nom" name="nom" id="Nom" required>
		</div>
	</div>

	<div class="form-row">
		<div class="form-group col-md-6 col-sm-12 ">
			<label style ="margin-left:12px" for="lblPrenom">Prenom :</label> <input type="text"
				class="form-control" placeholder="Prénom" name="prenom" id="prenom"
				required>
		</div>
		<div class="form-group col-md-6 col-sm-12">
			<label style ="margin-left:12px" for="lblEmail">E-mail :</label> <input type="email"
				class="form-control" placeholder="Email" name="email" id="email"
				required>
		</div>
	</div>

	<div class="form-row">
		<div class="form-group col-md-6 col-sm-12 ">
			<label style ="margin-left:12px" for="lblTel">Téléphone :</label> <input type="tel"
				class="form-control" placeholder="Téléphone" name="tel" id="tel"
				required>
		</div>
		<div class="form-group col-md-6 col-sm-12">
			<label style ="margin-left:12px" for="lblRue">Rue :</label> <input type="text"
				class="form-control" placeholder="Rue" name="rue" id="rue" required>
		</div>
	</div>

	<div class="form-row">
		<div class="form-group col-md-6 col-sm-12 ">
			<label style ="margin-left:12px" for="lblCP">CodePostal :</label> <input type="text"
				class="form-control" placeholder="Code postal" name="cp" id="cp"
				pattern="[0-9]{5}" required>
		</div>
		<div class="form-group col-md-6 col-sm-12">
			<label style ="margin-left:12px" for="lblVille">Ville :</label> <input type="text"
				class="form-control" placeholder="Ville" name="ville" id="ville"
				required>
		</div>
	</div>


	<div class="form-row">
		<div class="form-group col-md-6 col-sm-12 ">
			<label style ="margin-left:12px" for="lblMdp">Mot de passe :</label> <input type="password"
				class="form-control" placeholder="Mot de passe" name="mdp" id="mdp"
				required>
		</div>
		<div class="form-group col-md-6 col-sm-12">
			<label style ="margin-left:12px" for="lblMdpConf">Confirmation :</label> <input type="password"
				class="form-control" placeholder="Confirmation de mot de passe"
				name="mdpConf" id="mdpConf" required>
		</div>
	</div>


	<br />
	<button type="submit" class="btn btn-primary">Valider</button>
	<button type="reset" class="btn btn-primary">Annuler</button>
</form>

<%@ include file="footer.jsp"%>
