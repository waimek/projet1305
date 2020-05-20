<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:setLocale value="fr_FR" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enchère</title>
<%@ include file="head_css.jsp"%>
</head>
<body>
	<%@ include file="entete.jsp"%>
	<!-- Auteur : Edouard -->
	<div class="container">
		<h2 class="text-center h2">Détail vente</h2>
		<c:if test="${not empty acheteur }">
			<c:if test="${acheteur.numero == no_utilisateur && now.time > vente.dateFinEncheres.time}">
				<h3 class="text-center h3">Vous avez remporté l'enchère</h3>
			</c:if>
			<c:if test="${acheteur.numero != no_utilisateur  && now.time > vente.dateFinEncheres.time}">
				<h3 class="text-center h3">${acheteur.pseudo} a remporté l'enchère</h3>
			</c:if>
		</c:if>
		<c:if test ="${not empty alert}">
		 ${alert}
		</c:if>
		<h4 class="h4">${ vente.nomArticle}</h4>
		<table>
			<tr>
				<td>Description :</td>
				<td>${vente.description}</td>
			</tr>
			<tr>
				<td>Meilleure offre :</td>
				<td>${vente.prixVente}</td>
			</tr>
			<tr>
				<td>Mise à prix :</td>
				<td>${vente.miseAPrix}</td>
			</tr>
			<tr>
				<td>Fin de l'enchère :</td>
				<td><fmt:formatDate value="${vente.dateFinEncheres}"/></td>
			</tr>
			<tr>
				<td>Retrait :</td>
				<td>${vente.retrait.rue}<br /> ${vente.retrait.cp }
					${vente.retrait.ville}
				</td>
			</tr>
			<tr>
				<td>Vendeur :</td>
				<td>${vente.util.pseudo}</td>
			</tr>
		</table>
		<c:if test="${now.time < vente.dateFinEncheres.time }">
			<form method="post"
				action="${pageContext.servletContext.contextPath}/GestionVenteServlet?action=encherir&noVente=${vente.numero}">
				<label for="proposition" class="form-label">Ma proposition :</label> <input
					type="number" id="proposition" name="proposition" class="form-control" required/>
					<input type="hidden" name="action" value="encherir" /> <input
						type="submit" class="btn btn-primary" value="Enchérir" />
				
		
			</form>
			<c:if test="${derniereEnchere}">
				<!-- <a class="btn btn-primary"
					href="${pageContext.servletContext.contextPath}/GestionVenteServlet?action=annulerEnchere"
					role="button">Annuler ma dernière enchère</a> -->
				<a class="btn btn-primary" href="#" role="button">Annuler ma
					dernière enchère</a>
			</c:if>
		</c:if>
		<a class="btn btn-primary"
			href="${pageContext.servletContext.contextPath}/RechercheServlet"
			role="button">Retour</a>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>