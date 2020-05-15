<!-- author Dominika -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="entete.jsp"%>
<h1>Créer un compte</h1>
<form action="${pageContext.servletContext.contextPath}/nouveauCompte"
	method="post">
	<div class="form-row">
		<div class="col">
			<input type="text" class="form-control" placeholder="Pseudo"
				name="pseudo" id="pseudo" required>
		</div>
		<div class="col">
			<input type="text" class="form-control" placeholder="Nom" name="nom"
				id="Nom" required>
		</div>
	</div>


	<div class="form-row">
		<div class="col">
			<input type="text" class="form-control" placeholder="Prénom"
				name="prenom" id="prenom" required>
		</div>
		<div class="col">
			<input type="email" class="form-control" placeholder="Email"
				name="email" id="email" required>
		</div>
	</div>


	<div class="form-row">
		<div class="col">
			<input type="tel" class="form-control" placeholder="Téléphone"
				name="tel" id="tel" required>
		</div>
		<div class="col">
			<input type="text" class="form-control" placeholder="Rue" name="rue"
				id="rue" required>
		</div>
	</div>

	<div class="form-row">
		<div class="col">
			<input type="text" class="form-control" placeholder="Code postale"
				name="cp" id="cp" required>
		</div>
		<div class="col">
			<input type="text" class="form-control" placeholder="Ville"
				name="ville" id="ville" required>
		</div>
	</div>


	<div class="form-row">
		<div class="col">
			<input type="password" class="form-control" placeholder="Mot de passe"
				name="mdp" id="mdp" required>
		</div>
		<div class="col">
			<input type="password" class="form-control" placeholder="Confirmation"
				name="mdpConf" id="mdpConf" required>
		</div>
	</div>





	<!-- 	<label for="pseudo" id="lblPseudo">Pseudo</label> <input type="text" -->
	<!-- 		name="pseudo" id="pseudo" required><br> <label for="nom" -->
	<!-- 		id="lblNom">Nom</label> <input type="text" name="nom" id="nom" -->
	<!-- 		required><br> <label for="prenom" id="lblPrenom">Prénom</label> -->
	<!-- 	<input type="text" name="prénom" id="prénom" required><br> -->

	<!-- 	<label for="email" id="lblEmail">Email</label> <input type="email" -->
	<!-- 		name="email" id="email" required><br> <label for="tel" -->
	<!-- 		id="lblTel">Téléphone</label> <input type="tel" name="tel" id="tel" -->
	<!-- 		required><br> <label for="rue" id="lblRue">Rue</label> <input -->
	<!-- 		type="text" name="rue" id="rue"><br> <label for="cp" -->
	<!-- 		id="lblCP">Code postal</label> <input type="text" name="cp" id="cp"><br> -->

	<!-- 	<label for="ville" id="lblVille">Ville</label> <input type="text" -->
	<!-- 		name="ville" id="ville"><br> -->

	<!-- 	<label for="mdp" id="lblMdp">Mot de passe</label>  -->
	<!-- 	<input type="password" name="mdp" id="mdp" required><br> -->

	<!-- 	<label for="mdpConf" id="lblMdpConf">Confirmation</label>  -->
	<!-- 	<input type="password" name="mdpConf" id="mdpConf" required><br> -->

	<button type="submit" class="btn btn-info">Valider</button>
	<button type="reset" class="btn btn-info">Annuler</button>
</form>









<%@ include file="footer.jsp"%>
