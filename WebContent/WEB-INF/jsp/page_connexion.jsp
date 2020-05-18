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
		<label for="pseudoInput">Pseudo</label> <input type="text"
			value="<c:out value="${utilisateur.pseudo}"/>" /> <span class="erreur">${form.erreurs['pseudo']}</span>

	</div>
	<div class="form-group">
		<label for="exampleInputPassword1">Mot de passe</label> <input
			type="password" class="form-control" id="exampleInputPassword1"
			value="<c:out value="${utilisateur.mdp}"/>" /> <span class="erreur">${form.erreurs['mdp']}</span>
	</div>
	<input type="submit" value="Connexion" class="sansLabel" /> <br />

	<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
</form>
<%-- Vérification de la présence d'un objet utilisateur en session --%>
<c:if test="${!empty sessionScope.sessionUtilisateur}">
	<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
	<p class="succes">Vous êtes connecté(e) avec le pseudo :
		${sessionScope.sessionUtilisateur.pseudo}</p>
</c:if>
<%@ include file="footer.jsp"%>
