<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="entete.jsp"%>
<%@ page import="fr.eni.ecole.troc_encheres.bo.Utilisateur"%>

<h1>Trocencheres.org</h1>
<!-- Ceci est un commentaire de type test -->
<form action="${pageContext.servletContext.contextPath}/connexion"
	method="post">
	<div class="form-group">
		<label for="nom">Pseudo</label> 
	<input type="text" id="identifiant"
			name="identifiant"
			value="<c:out value="${CHOIX == true ? util.pseudo : util.email}"/>" />
<%-- 		<input type="text" id="identifiant" name="identifiant"
			value="<c:out value="${util.pseudo}"/>" /> --%>
		<span class="erreur">${form.erreurs['pseudo']}</span>

	</div>
	<div class="form-group">
		<label for="password">Mot de passe</label> <input type="password"
			name="mdp" class="form-control" id="mdp"
			value="<c:out value="${util.mdp}"/>" /><span class="erreur">${form.erreurs['mdp']}</span>

		<%-- 		<c:out value="${num % 2 eq 0 ? 'even': 'odd'}" /> --%>

	</div>
	<input type="submit" value="Connexion" class="sansLabel" /> <br />
	<div class="form-check">
		<input class="form-check-input" type="checkbox" value=""
			id="defaultCheck1"> <label class="form-check-label"
			for="defaultCheck1"> Se souvenir de moi </label> </br> <a href="#">Mot
			de passe oublié</a>
	</div>
	</br>
	<button value="${sessionScope.invalidate}" name="deconnexion">Déconnexion</button>

	<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
</form>
<c:choose>
	<c:when test="${!empty sessionScope.sessionUtilisateur}">
		<p class="succes">Vous êtes connecté(e) avec le pseudo :
			${sessionScope.sessionUtilisateur.pseudo}</p>
	</c:when>
	<c:when test="${sessionScope.invalidate}">
		<p class="succes">Vous êtes déconnecté(e) de la session.</p>
	</c:when>
</c:choose>
<%@ include file="footer.jsp"%>
