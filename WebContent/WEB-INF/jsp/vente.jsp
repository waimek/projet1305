<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="entete.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Auteur : Edouard -->
<h2 class="text-center h2">Détail vente</h2>
<c:if test="${not empty acheteur}">
	<h3 class="text-center h3">${acheteur.pseudo} a remporté l'enchère</h3>
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
		<td>${vente.dateFinEncheres}</td>
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

<c:if test="${empty acheteur }">
	<a class="btn btn-primary"
		href="${pageContext.servletContext.contextPath}/GestionVenteServlet?action=annulerVente&noVente=${vente.numero}"
		role="button">Annuler la vente</a>
</c:if>

<c:if test="${not empty acheteur }">
	<c:if test="${not empty retrait}">
		<a class="btn btn-primary"
			href="${pageContext.servletContext.contextPath}/RechercheServlet?action=validerRetrait&noVente=${vente.numero}"
			role="button">Retrait effectué</a>
	</c:if>
	<a class="btn btn-primary"
		href="${pageContext.servletContext.contextPath}/GestionVenteServlet?action=contact&utilisateur=${acheteur.numero}"
		role="button">Contacter ${acheteur.pseudo}</a>
</c:if>
<a class="btn btn-primary"
	href="${pageContext.servletContext.contextPath}/RechercheServlet"
	role="button">Retour</a>
<%@ include file="footer.jsp"%>