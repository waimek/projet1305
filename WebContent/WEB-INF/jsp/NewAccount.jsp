<!-- author Dominika -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="entete.jsp"%>
<h1>Créer un compte</h1>
<form action="${pageContext.servletContext.contextPath}/nouveauCompte"
	method="post">

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

	<button type="submit">Valider</button>
	<button type="reset">Annuler</button>
</form>


<form>
  <div class="form-row">
    <div class="col">
      <input type="text" class="form-control" placeholder="Pseudo" name="pseudo" id="pseudo" >
    </div>
    <div class="col">
      <input type="text" class="form-control" placeholder="Nom">
    </div>
  </div>
</form>






<%@ include file="footer.jsp"%>
