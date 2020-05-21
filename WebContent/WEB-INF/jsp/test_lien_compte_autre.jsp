<!--author: Lucille/Matthieu/Dominika  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Compte vendeur autre</title>
<%@ include file="head_css.jsp"%>
</head>
<body>
	<!-- Auteur : Lucille -->
	<%@ include file="entete.jsp"%>

	<div class="container">
		<div class="row my-3">
			<h1>Vous êtes sur le profil de ${pseudo}</h1>
		</div>
		<div class="row">
			<ul class="col list-group list-group-flush">
				<li class="list-group-item">Pseudo : <c:out
						value="${pseudo}" /></li>
				<li class="list-group-item">Nom : <c:out
						value="${nom}" /></li>
				<li class="list-group-item">Prenom : <c:out
						value="${prenom}" /></li>
				<li class="list-group-item">Email : <c:out
						value="${email}" /></li>
				<li class="list-group-item">Téléphone : <c:out
						value="${tel}" /></li>
				<li class="list-group-item">Rue : <c:out
						value="${rue}" /></li>
				<li class="list-group-item">Code postal : <c:out
						value="${cp}" /></li>
				<li class="list-group-item">Ville : <c:out
						value="${ville}" /></li>
				<li class="list-group-item">Credit : <c:out
						value="${credit}" /></li>

			</ul>
		</div>
		<a class="btn btn-primary"
			href="${pageContext.servletContext.contextPath}/RechercheServlet"
			title="Recherches">Retour aux recherches</a>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>