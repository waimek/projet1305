<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="entete.jsp"%>
<%@ page import="fr.eni.ecole.troc_encheres.bo.Utilisateur"%>

<c:if test="${ !empty sessionScope.pseudo && !empty sessionScope.mdp }">
	<p>Vous êtes ${ sessionScope.pseudo } ${ sessionScope.mdp } !</p>
</c:if>
<h1>Trocencheres.org</h1>
<!-- Ceci est un commentaire de type test -->
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
	<a href="${pageContext.servletContext.contextPath}/TestCheminServlet"
		class="btn btn-primary">Se connecter</a>
</form>
<%@ include file="footer.jsp"%>
