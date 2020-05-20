<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon compte</title>
<%@ include file="head_css.jsp"%>
</head>
<body>
	<!-- Auteur : Lucille -->
	<%@ include file="entete.jsp"%>
	<div class="container">
		<div class="row my-3">
			<h1>Pseudo de l'utilisateur</h1>
		</div>
		<div class="row">
			<ul class="col list-group list-group-flush">
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
		</div>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>