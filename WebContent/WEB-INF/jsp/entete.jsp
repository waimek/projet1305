<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TrocEncheres</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<a class="navbar-brand" href="#">TrocEncheres.org</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarText" aria-controls="navbarText"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.servletContext.contextPath}/connexion">Bonjour ${sessionScope.sessionUtilisateur.pseudo}
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="/WEB-INF/jsp/affichage_compte.jsp">Mon
						compte</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Enchères</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Recherche</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/connexion">Connexion</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/deconnexion">Déconnexion</a></li>
			</ul>
			<span class="navbar-text"> Navbar text with an inline element
			</span>
		</div>
	</nav>

	<div class="container">