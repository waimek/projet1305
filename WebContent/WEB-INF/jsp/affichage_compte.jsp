<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="entete.jsp"%>
<%@ page import="fr.eni.ecole.troc_encheres.bo.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MonCompte</title>
</head>
<body>
	<h1>Trocencheres.org</h1>
	<ul class="list-group list-group-flush">
		<li class="list-group-item">Pseudo<c:out
				value="${sessionScope.util.pseudo}" /></li>
		<li class="list-group-item">Nom <c:out
				value="${sessionScope.util.nom}" /></li>
		<li class="list-group-item">Prenom <c:out
				value="${sessionScope.util.prenom}" /></li>
		<li class="list-group-item">Email <c:out
				value="${sessionScope.util.email}" /></li>
		<li class="list-group-item">Téléphone <c:out
				value="${sessionScope.util.telephone}" /></li>
		<li class="list-group-item">Rue <c:out
				value="${sessionScope.util.rue}" /></li>
		<li class="list-group-item">Code postal <c:out
				value="${sessionScope.util.cp}" /></li>
		<li class="list-group-item">Ville <c:out
				value="${sessionScope.util.ville}" /></li>
	</ul>
</body>
</html>