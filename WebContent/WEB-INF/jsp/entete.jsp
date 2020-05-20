<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-dark bg-dark">
	<a class="navbar-brand" href="${pageContext.servletContext.contextPath}/RechercheServlet">TrocEncheres.org</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarText" aria-controls="navbarText"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarText">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.servletContext.contextPath}/connexion">Bonjour
					${sessionScope.sessionUtilisateur.pseudo}
					${sessionScope.sessionUtilisateur.email} <span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.servletContext.contextPath}/AffichageCompte">Mon profil</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.servletContext.contextPath}/GestionVenteServlet?action=nouvelleVente">Vendre un article</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.servletContext.contextPath}/RechercheServlet">Rechercher</a>
			</li>
			
			<c:set var="name" value="sessionUtilisateur" />
			<c:choose>
				<c:when test="${sessionScope[name] != null}">
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.servletContext.contextPath}/deconnexion">Déconnexion</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.servletContext.contextPath}/connexion">Connexion</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>
