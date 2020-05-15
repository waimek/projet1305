<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="entete.jsp"%>
<%@ page import="fr.eni.ecole.troc_encheres.bo.Utilisateur"%>

<h1>Trocencheres.org</h1>
<!-- Ceci est un commentaire de type test -->
<%-- <form method="post" action="connexion">
	<fieldset>
		<legend>Connexion</legend>
		<label for="pseudo">S'identifier<span class="required">*</span></label>
		<input type="pseudo" id="pseudo" name="pseudo"
			value="<c:out value="${util.pseudo}"/>" size="20" maxlength="60" />
		<span class="erreur">${form.erreurs['pseudo']}</span> <br /> <label
			for="mdp">Mot de passe <span class="required">*</span></label> <input
			type="password" id="mdp" name="mdp"
			value="<c:out value="${util.mdp}"/>" size="20" maxlength="20" />
		<span class="erreur">${form.erreurs['mdp']}</span>
		<br /> <input type="submit" value="Connexion" class="sansLabel" /> <br />

		  <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
	</fieldset>
</form> --%>
<form action="${pageContext.servletContext.contextPath}/connexion"
	method="post">
	<div class="form-group">
		<label for="pseudoInput">Pseudo</label> <input type="text"
			class="form-control" id="pseudoInput" aria-describedby="pseudoHelp"
			value="<c:out value="${util.pseudo}"/>" /> <small id="emailHelp"
			class="form-text text-muted">Votre pseudo est obligatoire</small>
	</div>
	<div class="form-group">
		<label for="exampleInputPassword1">Mot de passe</label> <input
			type="password" class="form-control" id="exampleInputPassword1"
			value="<c:out value="${util.mdp}"/>" /><small id="emailHelp"
			class="form-text text-muted">Votre mot de passe est
			obligatoire</small>
	</div>
	<!-- 	<div class="form-group form-check">
		<input type="checkbox" class="form-check-input" id="exampleCheck1">
		<label class="form-check-label" for="exampleCheck1">Check me
			out</label>
	</div> -->
	<a href="${pageContext.servletContext.contextPath}/TestCheminServlet" class="btn btn-primary">Se connecter</a>
</form>
<%@ include file="footer.jsp"%>
